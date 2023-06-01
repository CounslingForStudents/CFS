package com.applications.cfs.Publishers;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * The type Email publisher.
 */
@Service
public class EmailPublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailPublisher.class);

    @Autowired
    private KafkaTemplate<String, Email> kafkaTemplate;

    /**
     * Send message.
     *
     * @param email the email
     */
    public void sendMessage(Email email) {
        LOGGER.info("Email send to Process.");
        this.kafkaTemplate.send(ApplicationConstant.TOPIC_NAME, email);
    }
}
