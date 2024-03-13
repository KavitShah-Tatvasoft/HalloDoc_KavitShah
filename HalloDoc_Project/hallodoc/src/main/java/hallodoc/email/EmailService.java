package hallodoc.email;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailService
{

    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String user_email) {  

    	MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {  
    		public void prepare(MimeMessage mimeMessage) throws Exception {  
    			MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
    			message.setFrom("hallodoc29@outlook.com");
    			message.setTo("user_email");
    			message.setSubject("Reset Password Link");
    			message.setText("Hello Derek! Feeling good like you shouldd!", true);
 
    		}  
    	};  

    	mailSender.send(messagePreparator);  
    }
    
}