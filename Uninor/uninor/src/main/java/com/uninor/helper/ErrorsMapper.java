package com.uninor.helper;

import java.util.HashMap;
import java.util.Map;

public class ErrorsMapper {

    public static Map<String, String> globalMap = new HashMap<>();

    static {
        globalMap.put("fname", "First Name");
        globalMap.put("lname", "Last Name");
        globalMap.put("selector", "Request Type");
        globalMap.put("clientId", "Client ID");
        globalMap.put("mobileNumber", "Mobile Number");
        globalMap.put("mobileId", "Mobile ID");
        globalMap.put("companyId", "Company ID");
        globalMap.put("dob", "Date of Birth");
        globalMap.put("email", "Email");
        globalMap.put("street", "Street");
        globalMap.put("city", "City");
        globalMap.put("state", "State");
        globalMap.put("zipcode", "Zipcode");
        globalMap.put("panNumber", "PAN Number");
        globalMap.put("aadharCardNumber", "Aadhar Card Number");
        globalMap.put("GSTNumber", "GST Number");
        globalMap.put("simType", "Plan Type");
        globalMap.put("panCardUploadedFile", "Pan Card File");
        globalMap.put("aadharCardUploadedFile", "Aadhar Card File");
        globalMap.put("profilePicUploadedFile", "Profile Photo File");
        globalMap.put("otp", "OTP");
        globalMap.put("number", "Mobile Number");
        globalMap.put("clientID", "Client ID");
        globalMap.put("cuponCode", "Cupon Code");
        globalMap.put("enteredWalletAmount", "Wallet Amount");
        globalMap.put("planId", "Plan ID");
        globalMap.put("planAmount", "Plan Amount");
        globalMap.put("currentPage", "Current Page");
        globalMap.put("pageSize", "Page Size");

    }

}
