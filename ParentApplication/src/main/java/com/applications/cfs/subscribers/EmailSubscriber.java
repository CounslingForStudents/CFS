package com.applications.cfs.subscribers;

import com.applications.cfs.AsyncKafkaEmailApi.EmailService;
import com.applications.cfs.dto.AsyncKafkaEmailApiDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class EmailSubscriber {
    @Autowired
    EmailService emailService;
    private final Logger LOGGER = LoggerFactory.getLogger(EmailSubscriber.class);
    @KafkaListener(topics ="Email-Topic", groupId = "group_id")
    public void consume(@Payload String jsonString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        AsyncKafkaEmailApiDto asyncKafkaEmailApiDto = objectMapper.readValue(jsonString,AsyncKafkaEmailApiDto.class);

        LOGGER.info("Email to {} processing started.", asyncKafkaEmailApiDto.getTo());
        emailService.sendEmail(asyncKafkaEmailApiDto);

    }


}
