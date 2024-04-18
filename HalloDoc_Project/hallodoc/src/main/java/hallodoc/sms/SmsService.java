package hallodoc.sms;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;

import hallodoc.helper.*;

@Service
public class SmsService {

	public String sendAgreementSMS() {

		 try {
		        TwilioRestClient client = new TwilioRestClient(Constants.ACCOUNT_SID, Constants.AUTH_TOKEN);
		 
		        // Build a filter for the MessageList
		        List<NameValuePair> params = new ArrayList<NameValuePair>();
		        params.add(new BasicNameValuePair("Body", "Hello, World! This is Kavit this side"));
		        params.add(new BasicNameValuePair("To", "+916351627219")); //Add real number here
		        params.add(new BasicNameValuePair("From", Constants.TWILIO_NUMBER));

		        MessageFactory messageFactory = client.getAccount().getMessageFactory();
		        Message message = messageFactory.create(params);
		        System.out.println(message.getSid());
		    } 
		    catch (TwilioRestException e) {
		        System.out.println(e.getErrorMessage());
		    }
		 
		 return "SMS Send";
	}
}
