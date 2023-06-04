package com.applications.cfs.Publishers;



import com.applications.cfs.dto.AsyncKafkaEmailApiDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * The type Email publisher.
 */
@Service
public class EmailPublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailPublisher.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Value("${kafka.Email.topic.name}")
    private String kafkaEmailTopic;
    public void sendMessage(AsyncKafkaEmailApiDto asyncKafkaEmailApiDto) throws JsonProcessingException {
        LOGGER.info("Email send to Process.");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(asyncKafkaEmailApiDto);
        this.kafkaTemplate.send(kafkaEmailTopic, jsonString);
    }
}
