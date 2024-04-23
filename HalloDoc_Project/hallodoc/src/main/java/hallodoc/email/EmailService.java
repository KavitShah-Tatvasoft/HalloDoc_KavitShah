package hallodoc.email;

import java.io.File;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import hallodoc.dto.CommonRequestDto;
import hallodoc.dto.CreatePatientRequestDto;
import hallodoc.dto.OrdersDetailsDto;
import hallodoc.dto.SendAgreementDto;
import hallodoc.dto.SendLinkDto;
import hallodoc.dto.SomeoneElseRequestDto;
import hallodoc.model.AspNetUsers;
import hallodoc.model.EmailToken;
import hallodoc.model.Request;
import hallodoc.model.User;
import hallodoc.repository.EmailTokenDao;
import hallodoc.repository.UserDao;

import hallodoc.helper.*;

@Service("emailService")
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private EmailTokenDao emailTokenDao;

	@Autowired
	private UserDao userDao;

	public static final String capitalize(String str) {
		if (str == null || str.length() == 0)
			return str;
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	public String getBaseUrl(HttpServletRequest req) {
		return "" + req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath();
	}

	public void sendPatientCreatePasswordMail(CreatePatientRequestDto createPatientRequestDto,
			HttpServletRequest httpServletRequest, LocalDateTime date, EmailToken token) {
		String name = capitalize(createPatientRequestDto.getFirstName()) + " "
				+ capitalize(createPatientRequestDto.getLastName());
		String url = getBaseUrl(httpServletRequest) + "/createPatient/" + token.getToken();
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				message.setFrom("hallodoc29@outlook.com");
				message.setTo(createPatientRequestDto.getEmail());
				message.setSubject("Create Password Link");
				String content = "<html><h1>Create Password Request<h1>" + "<br>" + "<h2> Hello, " + name + "</h2><br>"
						+ "<p style=\"\"margin-top:30px;\"\">We have received an account creation request. "
						+ "So,in order to create your account we need your password,so please click the below link to create password:</p>"
						+ " <a href=" + url + ">Click here to create your password</a> <br>"
						+ "<p>If you didn't request an account creation then please ignore this mail.</p>" + "</html>";
				message.setText(content, true);

			}
		};

		mailSender.send(messagePreparator);
	}

	public void sendCreatePasswordMail(CommonRequestDto commonRequestDto, HttpServletRequest httpServletRequest,
			LocalDateTime date, EmailToken token) {
		String name = capitalize(commonRequestDto.getPtFirstName()) + " "
				+ capitalize(commonRequestDto.getPtLastName());
		String url = getBaseUrl(httpServletRequest) + "/createPatient/" + token.getToken();
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				message.setFrom("hallodoc29@outlook.com");
				message.setTo(commonRequestDto.getPtEmail());
				message.setSubject("Create Password Link");
				String content = "<html><h1>Create Password Request<h1>" + "<br>" + "<h2> Hello, " + name + "</h2><br>"
						+ "<p style=\"\"margin-top:30px;\"\">We have received an account creation request. "
						+ "So,in order to create your account we need your password,so please click the below link to create password:</p>"
						+ " <a href=" + url + ">Click here to create your password</a> <br>"
						+ "<p>If you didn't request an account creation then please ignore this mail.</p>" + "</html>";
				message.setText(content, true);

			}
		};

		mailSender.send(messagePreparator);
	}

	public void sendCreatePasswordMailForOthers(SomeoneElseRequestDto someoneElseRequestDto,
			HttpServletRequest httpServletRequest, LocalDateTime date, EmailToken token) {
		String name = capitalize(someoneElseRequestDto.getFirstName()) + " "
				+ capitalize(someoneElseRequestDto.getLastName());
		String url = getBaseUrl(httpServletRequest) + "/createPatient/" + token.getToken();
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				message.setFrom("hallodoc29@outlook.com");
				message.setTo(someoneElseRequestDto.getEmail());
				message.setSubject("Create Password Link");
				String content = "<html><h1>Create Password Request<h1>" + "<br>" + "<h2> Hello, " + name + "</h2><br>"
						+ "<p style=\"\"margin-top:30px;\"\">We have received an account creation request. "
						+ "So,in order to create your account we need your password,so please click the below link to create password:</p>"
						+ " <a href=" + url + ">Click here to create your password</a> <br>"
						+ "<p>If you didn't request an account creation then please ignore this mail.</p>" + "</html>";
				message.setText(content, true);

			}
		};

		mailSender.send(messagePreparator);
	}

	public void resendCreatePasswordMail(User user, HttpServletRequest httpServletRequest, LocalDateTime date,
			EmailToken token) {
		String name = capitalize(user.getFirstName()) + " " + capitalize(user.getLastName());
		String url = getBaseUrl(httpServletRequest) + "/createPatient/" + token.getToken();
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				message.setFrom("hallodoc29@outlook.com");
				message.setTo(user.getEmail());
				message.setSubject("Create Password Link");
				String content = "<html><h1>Create Password Request<h1>" + "<br>" + "<h2> Hello, " + name + "</h2><br>"
						+ "<p style=\"\"margin-top:30px;\"\">We have received an account creation request. "
						+ "So,in order to create your account we need your password,so please click the below link to create password:</p>"
						+ " <a href= ' " + url + " ' target = '_blank' >Click here to create your password</a> <br>"
						+ "<p>If you didn't request an account creation then please ignore this mail.</p>" + "</html>";
				message.setText(content, true);

			}
		};

		mailSender.send(messagePreparator);
	}

	public String sendForgetPasswordMail(HttpServletRequest httpServletRequest, String email) {

		List<User> userList = userDao.getUserByEmail(email);

		if (userList.size() > 0) {

			EmailToken emailToken = new EmailToken();
			UUID uuid = UUID.randomUUID();
			String token = uuid.toString();
			LocalDateTime sentDate = LocalDateTime.now();

			emailToken.setToken(token);
			emailToken.setEmail(email);
			emailToken.setSentDate(sentDate);
			emailToken.setResetCompleted(false);

			int id = emailTokenDao.createNewEmail(emailToken);

			System.out.println("EmailToken created id: " + id);

			User user = userList.get(0);
			String name = capitalize(user.getFirstName()) + " " + capitalize(user.getLastName());
			String url = getBaseUrl(httpServletRequest) + "/resetPasswordScreen/" + token;

			MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
				public void prepare(MimeMessage mimeMessage) throws Exception {
					MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
					message.setFrom("hallodoc29@outlook.com");
					message.setTo(email);
					message.setSubject("Forget Password Link");
					String content = "<html><h1>Reset Password Request<h1>" + "<br>" + "<h2> Hello, " + name
							+ "</h2><br>"
							+ "<p style=\"\"margin-top:30px;\"\">We have received an account password reset request. "
							+ "So,in order to reset your account password we need you to click the below link to reset password:</p>"
							+ " <a href=" + url + ">Click here to reset your password</a> <br>"
							+ "<p>If you didn't request password reset request then please ignore this mail.</p>"
							+ "</html>";
					message.setText(content, true);

				}
			};

			mailSender.send(messagePreparator);

			String msg = "User found and mail sent";
			return msg;

		} else {
			String msg = "No such user exsist,no mail sent";
			return msg;
		}

	}

	public String getTokenCorrespondingEmail(String token) {
		List<EmailToken> list = emailTokenDao.getTokenObject(token);
		return list.get(0).getEmail();
	}

	public boolean isTokenExpired(String token) {
		List<EmailToken> list = emailTokenDao.getTokenObject(token);
		return list.get(0).isResetCompleted();
	}

	public boolean isForgetPassTokenExpired(String token) {

		List<EmailToken> list = emailTokenDao.getTokenObject(token);
		EmailToken emailToken;

		if (list.size() > 0) {
			emailToken = list.get(0);
			boolean isResetCompleted = emailToken.isResetCompleted();

			LocalDateTime now = emailToken.getSentDate();
			LocalDateTime expiredateTime = now.plus(24, ChronoUnit.HOURS);
			LocalDateTime currentdateTime = LocalDateTime.now();

			if (currentdateTime.isBefore(expiredateTime) && !(isResetCompleted)) {
				return false;
			} else {
				return true;
			}

		} else {
			return true;
		}
	}

	public String updateIsResetWithMail(String token) {
		List<EmailToken> list = emailTokenDao.getTokenObject(token);
		EmailToken emailToken = list.get(0);
		emailToken.setResetCompleted(true);

		List<EmailToken> updateList = new ArrayList<EmailToken>();
		updateList.add(emailToken);
		emailTokenDao.updateOldEmailResetStatus(updateList);
		return "Updated isResetWithEmail";
	}

	public void sendRequestLinkByEmail(HttpServletRequest httpServletRequest, SendLinkDto sendLinkDto) {
		String name = capitalize(sendLinkDto.getFirstName()) + " " + capitalize(sendLinkDto.getLastName());
		String url = getBaseUrl(httpServletRequest) + "/patient_submit_request";
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				message.setFrom("hallodoc29@outlook.com");
				message.setTo(sendLinkDto.getEmail());
				message.setSubject(sendLinkDto.getEmailSubject());
				String content = "<html><h1>Create New Request<h1>" + "<br>" + "<h2> Hello, " + name + "</h2><br>"
						+ "<p style=\"\"margin-top:30px;\"\">We have attached below a link to register a new case. "
						+ "So,in order to register the patient click the below link.</p>" + " <a href= ' " + url
						+ " ' target = '_blank' >Click here to register.</a> <br>"
						+ "<p>If you didn't request an account creation then please ignore this mail.</p>" + "</html>";
				message.setText(content, true);

			}
		};

		mailSender.send(messagePreparator);
	}

	public void sendAgreementLink(String emailSubject, Request request, HttpServletRequest httpServletRequest,
			SendAgreementDto sendAgreementDto) {
		String name = capitalize(request.getRequestClient().getFirstName()) + " "
				+ capitalize(request.getRequestClient().getLastName());
		String url = getBaseUrl(httpServletRequest) + "/user/sendReviewAgreement/" + request.getRequestId();

		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				message.setFrom("hallodoc29@outlook.com");
				message.setTo(sendAgreementDto.getEmail());
				message.setSubject(emailSubject);
				String content = "<html><h1>Review Agreement<h1>" + "<br>" + "<h2> Hello, " + name + "</h2><br>"
						+ "<p style=\"\"margin-top:30px;\"\">We have attached below a link so that you can review the agreement. "
						+ "So,in order to continue with the process please click the below link to view the agreement.</p>"
						+ " <a href= ' " + url + " ' target = '_blank' >Review Agreement</a> <br>" + "</html>";
				message.setText(content, true);

			}
		};

		mailSender.send(messagePreparator);
	}

	public void sendFilesViaEmail(String emailSubject, Request request, HttpServletRequest httpServletRequest,
			List<String> fileNames, List<String> realFileName) {
		String name = capitalize(request.getRequestClient().getFirstName()) + " "
				+ capitalize(request.getRequestClient().getLastName());

		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				message.setFrom("hallodoc29@outlook.com");
				message.setTo(request.getRequestClient().getEmail());
//				message.setTo("pevovoy782@idsho.com");
				message.setSubject(emailSubject);
				String content = "<html> <h2> Hello, " + name + "</h2><br>"
						+ "<p style=\"\"margin-top:30px;\"\">Hope that now you are well. We have attached below document/health reports that you might need for your refrence. For any assistance feel free to contact on our helpline number anytime.";
					
				message.setText(content, true);
				
				String path = Constants.getUplaodPath(httpServletRequest.getSession());
				
				for (int i=0;i<fileNames.size();i++) {
					FileSystemResource file = new FileSystemResource(new File(path+"/"+fileNames.get(i)));
					message.addAttachment(realFileName.get(i), file);
				}
				
			}
		};

		mailSender.send(messagePreparator);

	}
	
	public void sendNewOrder(String subject,Request request,HttpServletRequest httpServletRequest, OrdersDetailsDto ordersDetailsDto, String recipientName) {
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				message.setFrom("hallodoc29@outlook.com");
				message.setTo(ordersDetailsDto.getBusinessEmailContact());
//				message.setTo("pevovoy782@idsho.com");
				message.setSubject(subject);
				String content = "<html> <h2> Hello, " + recipientName + "</h2><br>"
						+ "<p style=\"\"margin-top:30px;\"\">We would like to place a new order. We have provided the prescription and order refills required below. Try to deliver the medicines on time. For any assistance feel free to contact on our helpline number anytime.</p>" +
						"<br>Order Details are as follows:" +
						"<br><br>Prescription : " + ordersDetailsDto.getBusinessPrescriptionDetails() + 
						"<br><br>No. of Refills Required : " + ordersDetailsDto.getRefillsDetails();
					
				message.setText(content, true);
			}
		};

		mailSender.send(messagePreparator);
	}

}