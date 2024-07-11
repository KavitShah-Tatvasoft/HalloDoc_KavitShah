package com.uninor.Email;

import com.uninor.dto.SignupRequestDto;
import org.apache.xmlbeans.SchemaStringEnumEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;


@Service("emailService")
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendOTP(SignupRequestDto signupRequestDto, String randomPin) {
        String name = signupRequestDto.getFname() + " " + signupRequestDto.getLname();

        MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                message.setFrom("hallodoc29@outlook.com");
                message.setTo(signupRequestDto.getEmail());
                message.setSubject("OTP for Registration");
                String content = "<html><h1>One Time Password<h1>" + "<br>" + "<h2> Hello, " + name + "</h2><br>"
                        + "<p style=\"\"margin-top:30px;\"\">We have received an account creation request. "
                        + "So,in order to create your account you would need an OTP so that we can verify your email. Your OTP will be valid for 5 minutes. Your OTP is mentioned below: <br> "
                        + "<h3>OTP : " + randomPin + "</h3></p>"
                        + "<p>If you didn't request an account creation then please ignore this mail.</p>" + "</html>";
                message.setText(content, true);
            }
        };

        mailSender.send(messagePreparator);
    }

    public void portNumberEmail(String companyEmail, String companyName, String phoneNumber) {

        MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                message.setFrom("hallodoc29@outlook.com");
                message.setTo(companyEmail);
                message.setSubject("Porting Number Information");
                String content = "<html><h1>Port Number<h1>" + "<br>" + "<h2> Hello, " + companyName + "</h2><br>"
                        + "<p style=\"\"margin-top:30px;\"\">We have received an porting request for the number  "
                        + phoneNumber + ". We have started to process the request and would be completing it within a week. Please complete all the required formalities for the same number. <br> "
                        + "<br><p>Thankyou,<br><h3>Team Uninor</h3></p>" + "</html>";
                message.setText(content, true);
            }
        };

        mailSender.send(messagePreparator);
    }


    public void registrationEmail(String email, String phoneNumber, String name) {

        MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                message.setFrom("hallodoc29@outlook.com");
                message.setTo(email);
                message.setSubject("Successful Registration");
                String content = "<html><h1>Welcome to Uninor Family<h1>" + "<br><br>" + "<h2> Hello, " + name + "</h2><br>"
                        + "<p style=\"\"margin-top:30px;\"\">We have successfully registered you with our network. The required process are underway and will send you confirmation mail once all the process are completed. You can login into our app once all of process are completed and your documents are verified. For any further query feel free to reach us out."
                        + "<br>Your registered number for your refrence is : " + phoneNumber
                        + "<br><p>Thankyou,<br><h3>Team Uninor</h3></p>" + "</html>";
                message.setText(content, true);
            }
        };

        mailSender.send(messagePreparator);
    }
}
