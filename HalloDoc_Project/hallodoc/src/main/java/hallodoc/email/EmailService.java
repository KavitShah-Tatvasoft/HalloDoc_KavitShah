package hallodoc.email;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import hallodoc.dto.CommonRequestDto;
import hallodoc.model.AspNetUsers;
import hallodoc.model.EmailToken;

@Service("emailService")
public class EmailService
{

    @Autowired
    private JavaMailSender mailSender;
    
    public static final String capitalize(String str) {
		if (str == null || str.length() == 0)
			return str;
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
    
    public String getBaseUrl(HttpServletRequest req) {
		return "" + req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath();
	}

    public void sendCreatePasswordMail(CommonRequestDto commonRequestDto, HttpServletRequest httpServletRequest, LocalDateTime date, EmailToken token) {  
    	String name = capitalize( commonRequestDto.getPtFirstName()) +" " + capitalize(commonRequestDto.getPtLastName());
    	String url = getBaseUrl(httpServletRequest) + "/createPatient?emailToken=" + token.getToken();
    	MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {  
    		public void prepare(MimeMessage mimeMessage) throws Exception {  
    			MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
    			message.setFrom("hallodoc29@outlook.com");
    			message.setTo(commonRequestDto.getPtEmail());
    			message.setSubject("Create Password Link");
    			String content = "<html><h1>Create Password Request<h1>"
    					+ "<br>"
    					+ "<h2> Hello, " + name + "</h2><br>"
    					+ "<p style=\"\"margin-top:30px;\"\">We have received an account creation request. "
    					+ "So,in order to create your account we need your password,so please click the below link to create password:</p>"
    					+ " <a href=" + url + ">Click here to create your password</a> <br>"
    					+ "<p>If you didn't request an account creation then please ignore this mail.</p>"
    					+ "</html>";
    			message.setText(content, true);
 
    		}  
    	}; 

    	mailSender.send(messagePreparator);  
    }
    
        
}