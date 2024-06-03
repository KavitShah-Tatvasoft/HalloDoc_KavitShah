package com.uninor.Email;

import com.uninor.dto.SignupRequestDto;
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

}
