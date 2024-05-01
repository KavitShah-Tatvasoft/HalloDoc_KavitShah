package hallodoc.sms;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;

import hallodoc.dto.SendAgreementDto;
import hallodoc.helper.*;
import hallodoc.model.Physician;
import hallodoc.model.Request;

@Service
public class SmsService {
	
	public static final String capitalize(String str) {
		if (str == null || str.length() == 0)
			return str;
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	public String sendAgreementSms(String smsSubject, Request request,HttpServletRequest httpServletRequest, SendAgreementDto sendAgreementDto) {

		 try {
		        TwilioRestClient client = new TwilioRestClient(Constants.ACCOUNT_SID, Constants.AUTH_TOKEN);
		        String url = "https://localhost:8080/hallodoc/user/sendReviewAgreement/" + request.getRequestId() + "\\";
		        String messageBody = "Please Review this Agreement. Hi " + capitalize(request.getRequestClient().getFirstName()) + " " + capitalize(request.getRequestClient().getLastName()) 
		        		+ ",hope you are fine, "
		        		+ "we have received your treatment request for " + request.getRequestClient().getNotes() + "."
		        		+ " To view agreement,please click the below link: "
		        		+ url;
		        // Build a filter for the MessageList 
		        List<NameValuePair> params = new ArrayList<NameValuePair>();
		        params.add(new BasicNameValuePair("Body", messageBody));
		        params.add(new BasicNameValuePair("To", "+916351627219")); //Add real number here
		        params.add(new BasicNameValuePair("From", Constants.TWILIO_NUMBER));

		        MessageFactory messageFactory = client.getAccount().getMessageFactory();
		        Message message = messageFactory.create(params);
		         ;
		    } 
		    catch (TwilioRestException e) {
		         ;
		    }
		 
		 return "SMS Send";
	}
	
	
	public String sendCommunicationSms(String smsSubject, String messages, Physician physician) {

		 try {
		        TwilioRestClient client = new TwilioRestClient(Constants.ACCOUNT_SID, Constants.AUTH_TOKEN);
		    
		        String messageBody = "Greeting from Admin. Hi " + capitalize(physician.getFirstName()) + " " + capitalize(physician.getLastName()) 
		        		+ ",hope you are fine. "
		        		+ messages ;
		        		
		        // Build a filter for the MessageList 
		        List<NameValuePair> params = new ArrayList<NameValuePair>();
		        params.add(new BasicNameValuePair("Body", messageBody));
		        params.add(new BasicNameValuePair("To", "+916351627219")); //Add real number here
		        params.add(new BasicNameValuePair("From", Constants.TWILIO_NUMBER));

		        MessageFactory messageFactory = client.getAccount().getMessageFactory();
		        Message message = messageFactory.create(params);
		         ;
		    } 
		    catch (TwilioRestException e) {
		         ;
		    }
		 
		 return "SMS Send";
	}
	
}
