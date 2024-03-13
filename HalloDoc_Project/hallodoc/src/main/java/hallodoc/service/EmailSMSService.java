package hallodoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import hallodoc.email.EmailService;
import hallodoc.model.AspNetUsers;
import hallodoc.repository.AspNetUserDao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

@Controller
public class EmailSMSService {
	
	@Autowired
	AspNetUserDao apsnetuserdao;

	@Autowired
	EmailService mailer;
	
	public int resetPassword(String email) {
		
		 
		List<AspNetUsers> list = apsnetuserdao.getUserByEmail(email);
		
		if(list.isEmpty()) {
			return -1;
		}
		else {
			mailer.sendMail();
			System.out.println("Mail Sent");
			return 0;
			//send email logic 
		}
	}
}