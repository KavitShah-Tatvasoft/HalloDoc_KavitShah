package com.uninor.helper;

import java.util.HashMap;
import java.util.Map;

public class ErrorsMapper {

    public static Map<String, String> globalMap = new HashMap<>();

    static {
        globalMap.put("fname", "First Name");
        globalMap.put("lname", "Last Name");
        globalMap.put("email", "com/uninor/Email");
    }

}
