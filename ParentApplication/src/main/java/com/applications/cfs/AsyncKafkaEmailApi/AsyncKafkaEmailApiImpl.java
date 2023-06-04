package com.applications.cfs.AsyncKafkaEmailApi;

import com.applications.cfs.Publishers.EmailPublisher;
import com.applications.cfs.dto.AsyncKafkaEmailApiDto;
import com.applications.cfs.dto.AttachmentDTO;
import com.applications.cfs.exception.CfsException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AsyncKafkaEmailApiImpl {
    @Autowired
    EmailPublisher emailPublisher;
    public static final String TO = "to";
    public static final String CC = "cc";
    public static final String BCC = "bcc";
    public static final String SUBJECT = "subject";
    public static final String BODY = "body";
    public static final String ATTACHMENTS ="attachments";
    public static final String HTMLFLAG  ="HtmlFlag";



    public String ApiService(Map<String,Object> jsonBody) throws JsonProcessingException {

        if (validateRequestBody(jsonBody))
            throw new CfsException("One of the property from FROM,TO,BODY and SUBJECT is Missing");
        AsyncKafkaEmailApiDto asyncKafkaEmailApiDto=AddDetailsToDto(jsonBody);
        emailPublisher.sendMessage(asyncKafkaEmailApiDto);



        return null;
    }

    private static boolean validateRequestBody(Map<String,Object> propertiesMap) {

        List<String> propertyList;
        propertyList = Arrays.asList(TO, BODY, SUBJECT);
        for (Object property : propertyList) {
            if (!propertiesMap.containsKey(property.toString()))
                return true;
        }
        return false;
    }
    private static AsyncKafkaEmailApiDto AddDetailsToDto(Map<String, Object> jsonBody){
        AsyncKafkaEmailApiDto asyncKafkaEmailApiDto=new AsyncKafkaEmailApiDto();
        asyncKafkaEmailApiDto.setTo((List<String>) jsonBody.get(TO));
        asyncKafkaEmailApiDto.setCc((List<String>) jsonBody.getOrDefault(CC,null));
        asyncKafkaEmailApiDto.setBcc((List<String>) jsonBody.getOrDefault(BCC,null));
        asyncKafkaEmailApiDto.setBody((String) jsonBody.get(BODY));
        asyncKafkaEmailApiDto.setSubject((String) jsonBody.get(SUBJECT));
        asyncKafkaEmailApiDto.setHtmlFlag((Boolean) jsonBody.getOrDefault(HTMLFLAG,false));
        asyncKafkaEmailApiDto.setAttachments((List<AttachmentDTO>) jsonBody.getOrDefault(ATTACHMENTS,null));
        return asyncKafkaEmailApiDto;

    }


}