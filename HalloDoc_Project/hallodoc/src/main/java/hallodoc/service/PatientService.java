package hallodoc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hallodoc.dto.CreatePatientDto;
import hallodoc.model.AspNetUsers;
import hallodoc.repository.AspNetUserDao;

import java.util.Date;
import java.util.List;

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

	public boolean validatePatient(String u_username, String u_password) {

		List<AspNetUsers> list = apsnetuserdao.getUserByUsername(u_username);

		if (list.isEmpty()) {
			System.out.println("False");
			return false;
		}

		else {
			AspNetUsers user = list.get(0);
			String passwordHash = user.getPassword_hash();
			BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.B, 12);

			boolean verified = Password.check(u_password, passwordHash).with(bcrypt);

			if (verified) {
				return true;
			} else {
				return false;
			}

		}
	}
	
	
	
//	public int createPatient(AspNetUsers user) {
//		String username = user.getEmail();
//		List<AspNetUsers> list = apsnetuserdao.getUserByUsername(username);
//		if (list.isEmpty()) {
//			user.setUser_name(user.getEmail());
//			
//			String password = user.getPassword_hash();
//			
//			BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.B, 12);
//
//			Hash hash = Password.hash(password)
//	                .with(bcrypt);
//			
//			user.setPassword_hash(hash.getResult());
//			
//			Date d = new Date();
//			user.setCreated_date(d);
//			
//			int id = apsnetuserdao.createPatient(user);
//			return id;
//		}
//		
//		else {
//			return -1;
//		}
//
//	}
// remove this if dto works
	
	public int createPatient(CreatePatientDto user) {
		
		String pass1 = user.getPassword_hash();
		String pass2 = user.getConfirmPassword();
		
		System.out.println(pass1);
		System.out.println(pass2);
		String username = user.getEmail();
		List<AspNetUsers> list = apsnetuserdao.getUserByUsername(username);
		
		if(!(pass1.equals(pass2))) {
			return -2;
		}

		else if(list.isEmpty()) {
			
			AspNetUsers aspnetuser = new AspNetUsers();
			aspnetuser.setUser_name(user.getEmail());
			aspnetuser.setEmail(user.getEmail());
			
			BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.B, 12);

			Hash hash = Password.hash(user.getPassword_hash())
	                .with(bcrypt);
			
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
}
