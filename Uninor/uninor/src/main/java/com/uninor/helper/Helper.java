package com.uninor.helper;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Helper {

    public static final String capitalize(String str) {
        if (str == null || str.isEmpty())
            return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static LocalDate parseToLocalDate(String str) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(str, formatter);
    }



    public static String formatLocalDateTime(LocalDateTime dateTime) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy HH:mm");
        return dateTime.format(outputFormatter);
    }

    public static String formatBirthDate(LocalDate dateTime) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
        return dateTime.format(outputFormatter);
    }

    public static String getNotificationTime(LocalDateTime dateTime) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return dateTime.format(outputFormatter);
    }

    public static String getPlanExpirationNotificationMessage(String mobilenumber, long daysLeft){
        return "Your recharge plan on mobile number " + mobilenumber + " is about to expire in " + daysLeft + " days. Please recharge to continue the services.";
    }

    public static String getPlanExpiredMessage(String mobilenumber){
        return "Your recharge plan on mobile number " + mobilenumber + " is expired. Any upcoming plan would be activated accordingly or else please recharge to continue the services.";
    }

    public static String getRechargeSuccessfulMessage(String planAmount){
        return "Recharge Successfully of Rs." + planAmount +". Your plan would activate as soon as current plan gets expired";
    }

    public static String getDataUsageMessage(int percentage, String sentDateTime){
        return "You have used "+ percentage +"% of your daily data as of "+ sentDateTime +". To know more view your current usage on the website.";
    }

    public static String getDataUsageHeader(int percentage){
        return percentage + "% Data Used";
    }

    public static final int VALIDATION_ATTEMPTS = 3;
    public static final String PLAN_EXPIRATION_HEADER = "Plan Expiration Notice";
    public static final String RECHARGE_SUCCESSFUL_HEADER = "Recharge Successfully";
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String ORDER_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int COUPON_LENGTH = 6;
    private static final int PLAN_CODE_LENGTH = 6;
    private static final int ORDER_ID_LENGTH = 14;
    private static final int PAYMENT_REF_LENGTH = 14;
    private static final Random RANDOM = new SecureRandom();

    public static final double CGST = 9.0;
    public static final double SGST = 9.0;
    public static final double POSTPAID_SMS_CHARGES = 1.0;
    public static final double POSTPAID_DATA_CHARGES = 10.0;

    public static String planCodeGenerator(){

        StringBuilder planCode = new StringBuilder(PLAN_CODE_LENGTH);

        for (int i = 0; i < PLAN_CODE_LENGTH; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            planCode.append(CHARACTERS.charAt(index));
        }

        return planCode.toString();
    }

    public static String cuponCodeGenerator(){

        StringBuilder couponCode = new StringBuilder(COUPON_LENGTH);

        for (int i = 0; i < COUPON_LENGTH; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            couponCode.append(CHARACTERS.charAt(index));
        }

        return couponCode.toString();
    }

    public static String orderIdGenerator(){

        StringBuilder orderId = new StringBuilder(ORDER_ID_LENGTH+6);
        orderId.append("ORDER_");
        for (int i = 0; i < ORDER_ID_LENGTH; i++) {
            int index = RANDOM.nextInt(ORDER_CHARACTERS.length());
            orderId.append(ORDER_CHARACTERS.charAt(index));
        }

        return orderId.toString();
    }

    public static String paymentRefGenerator(){

        StringBuilder orderId = new StringBuilder(PAYMENT_REF_LENGTH + 4);
        orderId.append("PAY_");
        for (int i = 0; i < PAYMENT_REF_LENGTH; i++) {
            int index = RANDOM.nextInt(ORDER_CHARACTERS.length());
            orderId.append(ORDER_CHARACTERS.charAt(index));
        }
        return orderId.toString();
    }
}
