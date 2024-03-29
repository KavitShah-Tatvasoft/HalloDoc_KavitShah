package hallodoc.service;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.password4j.BcryptFunction;
import com.password4j.Password;
import com.password4j.types.Bcrypt;

import hallodoc.dto.CommonRequestDto;
import hallodoc.dto.UserProfileDto;
import hallodoc.email.EmailService;
import hallodoc.enumerations.DocType;
import hallodoc.helper.Constants;
import hallodoc.model.AspNetUsers;
import hallodoc.model.EmailToken;
import hallodoc.model.Request;
import hallodoc.model.RequestWiseFile;
import hallodoc.model.User;
import hallodoc.repository.AspNetUserDao;
import hallodoc.repository.EmailTokenDao;
import hallodoc.repository.RequestDao;
import hallodoc.repository.RequestWiseFileDao;

@Service
public class UserService {

	@Autowired
	private AspNetUserDao apsnetuserdao;
	
	@Autowired
	private EmailTokenDao emailTokenDao;
	
	@Autowired
	private RequestWiseFileDao requestWiseFileDao;

	@Autowired
	private EmailService mailer;
	
	@Autowired
	private RequestDao requestDao;
	
	
	
	private String resendCreatePasswordMail(User user, HttpServletRequest httpServletRequest) {

		EmailToken emailToken = new EmailToken();

		UUID newToken = UUID.randomUUID();
		String createdToken = newToken.toString();

		System.out.println(createdToken);

		emailToken.setToken(createdToken);
		emailToken.setEmail(user.getEmail());
		emailToken.setResetCompleted(false);
		emailToken.setSentDate(LocalDateTime.now());

		// persisting the object of EmailToken

		
			List<EmailToken> emailList = emailTokenDao.getDuplicateEmailEntry(user.getEmail());

			for (EmailToken email : emailList) {
				email.setResetCompleted(true);
			}

			String emailChange = emailTokenDao.updateOldEmailResetStatus(emailList);
			System.out.println(emailChange);
		

		int mailId = emailTokenDao.createNewEmail(emailToken);

		mailer.resendCreatePasswordMail(user, httpServletRequest, LocalDateTime.now(), emailToken);

		return "success";
	}

	public int validateUser(String username, String password, HttpServletRequest request) {

		List<AspNetUsers> list = apsnetuserdao.getUserByUsername(username);

		if (list.isEmpty()) {
			System.out.println("No such user found");
			return -1;
		}

		else {
			AspNetUsers user = list.get(0);
			String passwordHash = user.getPassword_hash();

			if (passwordHash == null) {
				System.out.println("password not set");
				String status = resendCreatePasswordMail(user.getUser(),request);
				return -2;
			} else {
				BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.B, 12);
				int role = user.getUser().getAspNetRoles().getId();
				boolean verified = Password.check(password, passwordHash).with(bcrypt);

				if (verified) {
					System.out.println("verified" + role);
					HttpSession session = request.getSession();
					session.setAttribute("aspUser",user);
					return role;
				} else {
					System.out.println("password not match");
					return -3;
				}

			}

		}
	}
	
	
	public String uploadRequestDocument(CommonsMultipartFile document, String name, Request request, HttpSession session, Date date) {
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy-HH-mm-ss");

		String formattedDate = sdf.format(date);
		
		RequestWiseFile requestWiseFile = new RequestWiseFile();

		// Getting details from file obj

		String fileName = document.getOriginalFilename();
		byte[] data = document.getBytes();
		String fileExtension = document.getOriginalFilename().substring(document.getOriginalFilename().lastIndexOf('.') + 1);
		String storedFileName = "patient" + formattedDate +"-"+ fileName ;
		System.out.println(storedFileName);
		String path = Constants.getUplaodPath(session) + storedFileName;
		System.out.println(path);

		try {
			FileOutputStream fos = new FileOutputStream(path);
			fos.write(data);
			fos.close();
			System.out.println("file uploaded");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Uploading Error");
		}

		requestWiseFile.setRequest(request);
		requestWiseFile.setFileName(fileName);
		requestWiseFile.setCreatedDate(date);
		requestWiseFile.setDocType(DocType.TEST_ONE.getDocId());
		requestWiseFile.setFinalize(false);
		requestWiseFile.setDeleted(false);
		requestWiseFile.setUploaderName(name);
		requestWiseFile.setFileExtension(fileExtension);
		requestWiseFile.setStoredFileName(storedFileName);
		
		requestWiseFileDao.addNewRequestWiseFile(requestWiseFile);
		
		return "Uploaded Succesfully";
	}
	
	public Request getRequestObject(int id) {
		Request request =  requestDao.getRequestOb(id);
		return request;
	}
	
	

}
