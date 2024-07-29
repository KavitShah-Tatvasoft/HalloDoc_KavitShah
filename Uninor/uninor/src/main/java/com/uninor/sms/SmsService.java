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
            System.out.println("Failed to send Recharge success SMS!");
        }

        return true;
    }

    public boolean sendPlanExpirationNotice(String mobileNumber, long daysLeft) {

        try {
            TwilioRestClient client = new TwilioRestClient(System.getenv("TwilioAccountSID"), System.getenv("TwilioAuthToken"));
            String messageBody = "\n\nPlan Expiration Notice.\n\n "
                    + "Your plan for mobile number : " + mobileNumber + " is about to get expire in " + daysLeft +" days. Recharge as soon as possible to enjoy various rewards. Make sure to keep an active recharge in your number before it gets expired.\n\n"
                    + "\n\n\nRegards,\nTeam Uninor";
            // Build a filter for the MessageList
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("Body", messageBody));
            params.add(new BasicNameValuePair("To", "+916351627219")); //Add real number here
            params.add(new BasicNameValuePair("From", System.getenv("TwilioMobileNumber")));

            MessageFactory messageFactory = client.getAccount().getMessageFactory();
            Message message = messageFactory.create(params);
        }
        catch (TwilioRestException e) {
            System.out.println("Failed to send expiration SMS!");
        }

        return true;
    }

    public boolean sendPlanExpiredSms(String mobileNumber) {

        try {
            TwilioRestClient client = new TwilioRestClient(System.getenv("TwilioAccountSID"), System.getenv("TwilioAuthToken"));
            String messageBody = "\n\nPlan Expiration Notice.\n\n "
                    + "Your plan for mobile number : " + mobileNumber + " is expired. If you have any upcoming plan, it would be activated directly. If you don't have any other plan, please recharge as soon as possible to continue enjoying the services.\n\n"
                    + "\n\n\nRegards,\nTeam Uninor";
            // Build a filter for the MessageList
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("Body", messageBody));
            params.add(new BasicNameValuePair("To", "+916351627219")); //Add real number here
            params.add(new BasicNameValuePair("From", System.getenv("TwilioMobileNumber")));

            MessageFactory messageFactory = client.getAccount().getMessageFactory();
            Message message = messageFactory.create(params);
        }
        catch (TwilioRestException e) {
            System.out.println("Failed to send expired plan SMS!");
        }

        return true;
    }

    public boolean sendDataUsageMessage(String mobileNumber, double dataUsage) {

        try {
            TwilioRestClient client = new TwilioRestClient(System.getenv("TwilioAccountSID"), System.getenv("TwilioAuthToken"));
            String messageBody = "\n\nPlan Expiration Notice.\n\n "
                    + "You have used " + dataUsage +"% of your daily quota. You can always buy add on plans in case your daily quota expires.\n\n"
                    + "\n\n\nRegards,\nTeam Uninor";
            // Build a filter for the MessageList
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("Body", messageBody));
            params.add(new BasicNameValuePair("To", "+916351627219")); //Add real number here
            params.add(new BasicNameValuePair("From", System.getenv("TwilioMobileNumber")));

            MessageFactory messageFactory = client.getAccount().getMessageFactory();
            Message message = messageFactory.create(params);
        }
        catch (TwilioRestException e) {
            System.out.println("Failed to send data usage SMS!");
        }

        return true;
    }

    public boolean sendBlockMessage(String mobileNumber) {

        try {
            TwilioRestClient client = new TwilioRestClient(System.getenv("TwilioAccountSID"), System.getenv("TwilioAuthToken"));
            String messageBody = "\n\nSIM BLOCKED.\n\n "
                    + "Your sim card with number : " + mobileNumber + " has been blocked as per your request. You can create an unblock request from the app to continue using the services. In case you need support feel free to contact us on the toll free number.\n\n"
                    + "\n\n\nRegards,\nTeam Uninor";
            // Build a filter for the MessageList
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("Body", messageBody));
            params.add(new BasicNameValuePair("To", "+916351627219")); //Add real number here
            params.add(new BasicNameValuePair("From", System.getenv("TwilioMobileNumber")));

            MessageFactory messageFactory = client.getAccount().getMessageFactory();
            Message message = messageFactory.create(params);
        }
        catch (TwilioRestException e) {
            System.out.println("Failed to send block SMS!");
        }

        return true;
    }

    public boolean sendRejectBlockRequestMessage(String mobileNumber) {

        try {
            TwilioRestClient client = new TwilioRestClient(System.getenv("TwilioAccountSID"), System.getenv("TwilioAuthToken"));
            String messageBody = "\n\nSIM BLOCK REQUEST REJECTED.\n\n "
                    + "The sim block request on the phone number : " +mobileNumber + " has been rejected. If you wish to block create a new block request again. For any other queries feel free to reach out on our toll free number.\n\n"
                    + "\n\n\nRegards,\nTeam Uninor";
            // Build a filter for the MessageList
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("Body", messageBody));
            params.add(new BasicNameValuePair("To", "+916351627219")); //Add real number here
            params.add(new BasicNameValuePair("From", System.getenv("TwilioMobileNumber")));

            MessageFactory messageFactory = client.getAccount().getMessageFactory();
            Message message = messageFactory.create(params);
        }
        catch (TwilioRestException e) {
            System.out.println("Failed to send block request reject SMS!");
        }

        return true;
    }

    public boolean sendUnblockMessage(String mobileNumber) {

        try {
            TwilioRestClient client = new TwilioRestClient(System.getenv("TwilioAccountSID"), System.getenv("TwilioAuthToken"));
            String messageBody = "\n\nSIM UNBLOCKED.\n\n "
                    + "Your sim card with number : " + mobileNumber + " has been unblocked as per your request. You can now continue using the services. In case you need support feel free to contact us on the toll free number.\n\n"
                    + "\n\n\nRegards,\nTeam Uninor";
            // Build a filter for the MessageList
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("Body", messageBody));
            params.add(new BasicNameValuePair("To", "+916351627219")); //Add real number here
            params.add(new BasicNameValuePair("From", System.getenv("TwilioMobileNumber")));

            MessageFactory messageFactory = client.getAccount().getMessageFactory();
            Message message = messageFactory.create(params);
        }
        catch (TwilioRestException e) {
            System.out.println("Failed to send unblock SMS!");
        }

        return true;
    }

    public boolean sendRejectUnblockRequestMessage(String mobileNumber) {

        try {
            TwilioRestClient client = new TwilioRestClient(System.getenv("TwilioAccountSID"), System.getenv("TwilioAuthToken"));
            String messageBody = "\n\nSIM UNBLOCK REQUEST REJECTED.\n\n "
                    + "The sim unblock request on the phone number : " +mobileNumber + " has been rejected. If you wish to unblock, create a new block request again. For any other queries feel free to reach out on our toll free number.\n\n"
                    + "\n\n\nRegards,\nTeam Uninor";
            // Build a filter for the MessageList
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("Body", messageBody));
            params.add(new BasicNameValuePair("To", "+916351627219")); //Add real number here
            params.add(new BasicNameValuePair("From", System.getenv("TwilioMobileNumber")));

            MessageFactory messageFactory = client.getAccount().getMessageFactory();
            Message message = messageFactory.create(params);
        }
        catch (TwilioRestException e) {
            System.out.println("Failed to send unblock request reject SMS!");
        }

        return true;
    }

    public boolean sendRejectDeactivationRequestMessage(String mobileNumber) {

        try {
            TwilioRestClient client = new TwilioRestClient(System.getenv("TwilioAccountSID"), System.getenv("TwilioAuthToken"));
            String messageBody = "\n\nSIM DEACTIVATION REQUEST REJECTED.\n\n "
                    + "The sim deactivation request on the phone number : " + mobileNumber + " has been rejected. If you wish to deactivate, please check for any unpaid dues and then create a new deactivation request again. For any other queries feel free to reach out on our toll free number.\n\n"
                    + "\n\n\nRegards,\nTeam Uninor";
            // Build a filter for the MessageList
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("Body", messageBody));
            params.add(new BasicNameValuePair("To", "+916351627219")); //Add real number here
            params.add(new BasicNameValuePair("From", System.getenv("TwilioMobileNumber")));

            MessageFactory messageFactory = client.getAccount().getMessageFactory();
            Message message = messageFactory.create(params);
        }
        catch (TwilioRestException e) {
            System.out.println("Failed to send deactivation request reject SMS!");
        }

        return true;
    }

    public boolean sendAcceptDeactivationRequestMessage(String mobileNumber) {

        try {
            TwilioRestClient client = new TwilioRestClient(System.getenv("TwilioAccountSID"), System.getenv("TwilioAuthToken"));
            String messageBody = "\n\nSIM DEACTIVATION REQUEST ACCEPTED.\n\n "
                    + "The sim deactivation request on the phone number : " + mobileNumber + " has been accepted. Under 24 hours you would be disconnected to all the services provided by us. Thank you for being a loyal customer. For any further queries feel free to contact on our toll free number.\n\n"
                    + "\n\n\nRegards,\nTeam Uninor";
            // Build a filter for the MessageList
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("Body", messageBody));
            params.add(new BasicNameValuePair("To", "+916351627219")); //Add real number here
            params.add(new BasicNameValuePair("From", System.getenv("TwilioMobileNumber")));

            MessageFactory messageFactory = client.getAccount().getMessageFactory();
            Message message = messageFactory.create(params);
        }
        catch (TwilioRestException e) {
            System.out.println("Failed to send deactivation request SMS!");
        }

        return true;
    }

}
