package hallodoc.email;
import org.springframework.beans.factory.annotation.Autowired;


public class SpringEmailTest
{
    //Get the mailer instance
	@Autowired
    private EmailService mailer; 
	public void main(String[] args)
    {
        //Send a composed mail
//        mailer.sendMail();
    }
}