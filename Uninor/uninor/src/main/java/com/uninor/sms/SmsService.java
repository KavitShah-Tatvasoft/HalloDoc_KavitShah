package com.uninor.sms;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;
import com.uninor.model.Plan;
import com.uninor.model.PlanActivation;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SmsService {

    public boolean sendLoginOtpSms(String mobileNumber, String otp) {

        try {
            TwilioRestClient client = new TwilioRestClient(System.getenv("TwilioAccountSID"), System.getenv("TwilioAuthToken"));
            String messageBody = "\n\nLogin OTP.\n\n "
                    + "The login OTP for your account with mobile number : " + mobileNumber + " is as below. Don't share your OTP with anyone for saftey of your account. \n\n"
                    + "OTP : " + otp + "\n\n";
            // Build a filter for the MessageList
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("Body", messageBody));
            params.add(new BasicNameValuePair("To", "+916351627219")); //Add real number here
            params.add(new BasicNameValuePair("From", System.getenv("TwilioMobileNumber")));

            MessageFactory messageFactory = client.getAccount().getMessageFactory();
            Message message = messageFactory.create(params);
        }
        catch (TwilioRestException e) {
            System.out.println("Failed to send OTP SMS!");
        }

        return true;
    }

    public boolean sendNewRequestSms(String mobileNumber, String type) {

        try {
            TwilioRestClient client = new TwilioRestClient(System.getenv("TwilioAccountSID"), System.getenv("TwilioAuthToken"));
            String messageBody = "\n\nBlock Request Created.\n\n "
                    + "We have received a sim " + type + " request for the mobile number : " + mobileNumber + " It would be processed under 24 hours If not created contact the admin immediately. \n\n";
            // Build a filter for the MessageList
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("Body", messageBody));
            params.add(new BasicNameValuePair("To", "+916351627219")); //Add real number here
            params.add(new BasicNameValuePair("From", System.getenv("TwilioMobileNumber")));

            MessageFactory messageFactory = client.getAccount().getMessageFactory();
            Message message = messageFactory.create(params);
        }
        catch (TwilioRestException e) {
            System.out.println("Failed to send OTP SMS!");
        }

        return true;
    }

    public boolean sendPlanBoughtSms(String mobileNumber, Plan plan) {

        try {
            TwilioRestClient client = new TwilioRestClient(System.getenv("TwilioAccountSID"), System.getenv("TwilioAuthToken"));
            String messageBody = "\n\nPlan Recharged Successfully.\n\n "
                    + "We are pleased to inform you that your mobile recharge has been successfully processed. Here are the details of your transaction:/n/n"
                    + "Mobile Number : " + mobileNumber + "\n\n"
                    + "Plan Amount : " + plan.getRechargeAmount() + "\n\n"
                    + "Recharge Date : " + LocalDateTime.now() + "\n\n\n"
                    + "Your account has been credited with the specified amount, and you should be able to use the balance immediately. Thank you for using our service!\n" +
                    "\n" +
                    "If you have any questions or need further assistance, please do not hesitate to contact our customer support team at 9999999999. We are here to help you 24/7.";
            // Build a filter for the MessageList
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("Body", messageBody));
            params.add(new BasicNameValuePair("To", "+916351627219")); //Add real number here
            params.add(new BasicNameValuePair("From", System.getenv("TwilioMobileNumber")));

            MessageFactory messageFactory = client.getAccount().getMessageFactory();
            Message message = messageFactory.create(params);
        }
        catch (TwilioRestException e) {
            System.out.println("Failed to send OTP SMS!");
        }

        return true;
    }

}
