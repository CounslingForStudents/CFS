package com.applications.cfs.AsyncKafkaEmailApi;

import com.applications.cfs.dto.AsyncKafkaEmailApiDto;
import com.applications.cfs.dto.AttachmentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {
    public static Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);
    @Autowired(required = false)
    private JavaMailSender emailSender;
    @Value("${sender.email}")
    private String sender;
    @Value("${sender.email.host}")
    private String host;
    @Value("${sender.email.port}")
    private String port;
    @Value("${sender.email.username}")
    private String username;
    @Value("${sender.email.password}")
    private String password;
    public static final String MAIL_SMTP_START_TLS_ENABLE="mail.smtp.starttls.enable";


    @Override
    public void sendEmail(AsyncKafkaEmailApiDto asyncKafkaEmailApiDto) {
        try {
            JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
            javaMailSender.setHost(host);
            javaMailSender.setPort(Integer.parseInt(port));
            javaMailSender.setUsername(username);
            javaMailSender.setPassword(password);
            javaMailSender.setProtocol("smtp");
            javaMailSender.setDefaultEncoding("UTF-8");
            Properties javaMailProps = new Properties();
            javaMailProps.put("mail.smtp.starttls.enable", "true");
            javaMailProps.put("mail.smtp.auth", "true");
            javaMailProps.put("mail.smtp.connectiontimeout", "5000");
            javaMailSender.setJavaMailProperties(javaMailProps);
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(sender);
            helper.setTo(asyncKafkaEmailApiDto.getTo().toArray(new String[0]));
            if (!asyncKafkaEmailApiDto.getCc().isEmpty()) {
                helper.setCc(asyncKafkaEmailApiDto.getCc().toArray(new String[0]));
            }
            if (!asyncKafkaEmailApiDto.getBcc().isEmpty()) {
                helper.setBcc(asyncKafkaEmailApiDto.getBcc().toArray(new String[0]));
            }
            helper.setSubject(asyncKafkaEmailApiDto.getSubject());
            // Set body
            helper.setText(asyncKafkaEmailApiDto.getBody(), asyncKafkaEmailApiDto.getHtmlFlag());
            // Set attachments
            for (AttachmentDTO attachment : asyncKafkaEmailApiDto.getAttachments()) {
                helper.addAttachment(attachment.getAttachmentFileName(), new File(attachment.getAttachmentFileUrl()));
            }
            javaMailSender.send(mimeMessage);
            System.out.println("Email sent successfully.");


        } catch (Exception ex){
            LOGGER.error("Email send failed because {} error occurred ",ex.getLocalizedMessage());
        }
    }


    }

