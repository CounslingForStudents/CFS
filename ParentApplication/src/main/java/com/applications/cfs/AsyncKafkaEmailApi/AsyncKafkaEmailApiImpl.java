package com.applications.cfs.AsyncKafkaEmailApi;

import com.applications.cfs.exception.CfsException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AsyncKafkaEmailApiImpl {
    public static final String TO = "to";
    public static final String CC = "cc";
    public static final String BCC = "bcc";
    public static final String SUBJECT = "subject";
    public static final String BODY = "body";


    public String ApiService(Map<String, String> jsonBody) {
        if (validateRequestBody(jsonBody))
            throw new CfsException("One of the property from FROM,TO,BODY and SUBJECT is Missing");
        Map<String, String> to = new HashMap<>();
        Map<String, String> cc = new HashMap<>();
        Map<String, String> bcc = new HashMap<>();
        String subject;
        String body;
        return null;
    }

    private static boolean validateRequestBody(Map<String, String> propertiesMap) {

        List<String> propertyList;
        propertyList = Arrays.asList(TO, BODY, SUBJECT);
        for (Object property : propertyList) {
            if (!propertiesMap.containsKey(property.toString()))
                return true;
        }
        return false;
    }


}