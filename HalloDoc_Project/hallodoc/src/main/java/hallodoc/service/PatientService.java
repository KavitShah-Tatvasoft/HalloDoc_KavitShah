package hallodoc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import hallodoc.dto.CreatePatientDto;
import hallodoc.dto.DashboardDataDto;
import hallodoc.dto.RequestDocumentsDto;
import hallodoc.dto.UserProfileDto;
import hallodoc.mapper.RequestToDashboardDataMapper;
import hallodoc.model.AspNetUsers;
import hallodoc.model.Region;
import hallodoc.model.Request;
import hallodoc.model.RequestClient;
import hallodoc.model.RequestType;
import hallodoc.model.RequestWiseFile;
import hallodoc.model.User;
import hallodoc.repository.AspNetUserDao;
import hallodoc.repository.RegionDao;
import hallodoc.repository.RequestDao;
import hallodoc.repository.RequestTypeDao;
import hallodoc.repository.UserDao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.password4j.BcryptFunction;
import com.password4j.Hash;
import com.password4j.Password;
import com.password4j.types.Bcrypt;

@Service
public class PatientService {

	@Autowired
	AspNetUserDao apsnetuserdao;

	@Autowired
	private RequestDao requestDao;

	@Autowired
	private RegionDao regionDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private RequestTypeDao requestTypeDao;
	
	public List<DashboardDataDto> getDashboardData(AspNetUsers aspNetUsers) {

		User user = aspNetUsers.getUser();
		int userId = user.getUserID();
		List<Request> requests = requestDao.getAllUserRequests(user);
		List<DashboardDataDto> dashboardDataDtoData = new ArrayList<DashboardDataDto>();
		
		for (Request request : requests) {
			DashboardDataDto dashboardDataDto =  RequestToDashboardDataMapper.mapDashboardData(request);
			dashboardDataDtoData.add(dashboardDataDto);
		}
		
		return dashboardDataDtoData;
	}

	public List<RequestDocumentsDto> getRequestDocuments(int id) {
		List<RequestDocumentsDto> requestList = requestDao.getDocuments(id);

		return requestList;
	}

	public String updateAspNetUserPassword(String email, String password) {
		AspNetUsers aspNetUsers = apsnetuserdao.getUserByEmail(email).get(0);

		User user = aspNetUsers.getUser();
		user.setRequestWithEmail(false);

		BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.B, 12);

		Hash hash = Password.hash(password).with(bcrypt);

		aspNetUsers.setPassword_hash(hash.getResult());

		aspNetUsers.setModified_date(new Date());

		apsnetuserdao.updateAspNetUser(aspNetUsers);

		return "Updated Password";
	}

	public boolean validatePatient(String username, String password) {

		List<AspNetUsers> list = apsnetuserdao.getUserByUsername(username);

		if (list.isEmpty()) {
			 ;
			return false;
		}

		else {
			AspNetUsers user = list.get(0);
			String passwordHash = user.getPassword_hash();
			BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.B, 12);

			boolean verified = Password.check(password, passwordHash).with(bcrypt);

			if (verified) {
				return true;
			} else {
				return false;
			}

		}
	}

	public int createPatient(CreatePatientDto user) {

		String pass1 = user.getPassword_hash();
		String pass2 = user.getConfirmPassword();

		String username = user.getEmail();
		List<AspNetUsers> list = apsnetuserdao.getUserByUsername(username);

		if (!(pass1.equals(pass2))) {
			return -2;
		}

		else if (list.isEmpty()) {

			AspNetUsers aspnetuser = new AspNetUsers();
			aspnetuser.setUser_name(user.getEmail());
			aspnetuser.setEmail(user.getEmail());

			BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.B, 12);

			Hash hash = Password.hash(user.getPassword_hash()).with(bcrypt);

			aspnetuser.setPassword_hash(hash.getResult());

			Date d = new Date();
			aspnetuser.setCreated_date(d);

			int id = apsnetuserdao.createPatient(aspnetuser);
			return id;
		}

		else {
			return -1;
		}

	}

	public List<Region> getAllRegions() {
		List<Region> regionList = regionDao.getAllRegions();
		return regionList;
	}

	public void updateUserProfile(UserProfileDto userProfileDto, HttpServletRequest request) {

		Date modDate = new Date();
		AspNetUsers aspNetUsers = (AspNetUsers) request.getSession().getAttribute("aspUser");

		User user = aspNetUsers.getUser();

		String[] monthArray = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		String dateString = userProfileDto.getUserDOB().format(userProfileDto.getUserDOB());
		String[] tokens = dateString.split("-");
		int year = Integer.parseInt(tokens[0]);
		int day = Integer.parseInt(tokens[2]);
		int month = Integer.parseInt(tokens[1]);
		String monthName = monthArray[month - 1];
//			String monthName = new SimpleDateFormat("MMMM", Locale.ENGLISH).format(userProfileDto.getUserDOB());

		user.setFirstName(userProfileDto.getUserFirstName());
		user.setLastName(userProfileDto.getUserLastName());
		user.setIntDate(day);
		user.setIntYear(year);
		user.setStrMonth(monthName);

		if (userProfileDto.getUserMobile().startsWith("0")) {
			String mobile = userProfileDto.getUserMobile().substring(1);
			user.setMobile(mobile);
		} else {
			String mobile = userProfileDto.getUserMobile();
			user.setMobile(mobile);
		}

		List<Region> regionList = regionDao.getRegionEntry(userProfileDto.getUserState());
		try {
			Region region = regionList.get(0);
			user.setRegion(region);
		} catch (Exception e) {
			 ;
		}

		user.setStreet(userProfileDto.getUserStreet());
		user.setCity(userProfileDto.getUserCity());
		user.setState(userProfileDto.getUserState());
		user.setZipcode(userProfileDto.getUserZipCode());
		user.setModifiedDate(modDate);
		
		aspNetUsers.setPhone_number(user.getMobile());
		aspNetUsers.setUser(user);
		
		apsnetuserdao.updateAspNetUser(aspNetUsers);
		
		//updating request table
		RequestType requestTypePatient = requestTypeDao.getRequestTypeObject("Patient");
		List<Request> reqList = requestDao.getUserRequestByType(user,requestTypePatient);

		for (Request requestListData : reqList) {
			requestListData.setFirstName(userProfileDto.getUserFirstName());
			requestListData.setLastName(userProfileDto.getUserLastName());
			requestListData.setPhoneNumber(user.getMobile());
			requestListData.setModifieDate(modDate);
			
			//updating requestWiseFile
			
			List<RequestWiseFile> reqWiseFileList = requestListData.getListRequestWiseFiles();
			List<RequestWiseFile> updatedList = new ArrayList();
			for (RequestWiseFile requestWiseFile : reqWiseFileList) {
				requestWiseFile.setUploaderName(userProfileDto.getUserFirstName()+" "+userProfileDto.getUserLastName());
				updatedList.add(requestWiseFile);
			}
			
			requestListData.setListRequestWiseFiles(updatedList);
			
			//updating requestClient table
			
			RequestClient client = requestListData.getRequestClient();
			client.setFirstName(userProfileDto.getUserFirstName());
			client.setLastName(userProfileDto.getUserLastName());
			client.setPhoneNumber(user.getMobile());
			client.setNotiMobile(user.getMobile());
			client.setIntDate(day);
			client.setIntYear(year);
			client.setStrMonth(monthName);
			
			requestListData.setRequestClient(client);
			
			requestDao.updateRequest(requestListData);
		}
		
		List<Request> reqListOtherType = requestDao.getUserRequestByOtherType(user,requestTypePatient);
		for (Request requestOther : reqListOtherType) {
			RequestClient client = requestOther.getRequestClient();
			client.setFirstName(userProfileDto.getUserFirstName());
			client.setLastName(userProfileDto.getUserLastName());
			client.setPhoneNumber(user.getMobile());
			client.setNotiMobile(user.getMobile());
			client.setIntDate(day);
			client.setIntYear(year);
			client.setStrMonth(monthName);
			
			requestOther.setRequestClient(client);
			requestDao.updateRequest(requestOther);
		}
		
		
		request.getSession(false).removeAttribute("aspUser");
		request.getSession(false).setAttribute("aspUser", aspNetUsers);

	}

}
