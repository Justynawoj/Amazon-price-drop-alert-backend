package com.amazon_price_drop_alert.services;

import com.amazon_price_drop_alert.domains.Mail;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    private final Logger LOGGER = LoggerFactory.getLogger(SimpleMailMessage.class);

    private SimpleMailMessage createMailMessage(final Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        if (mail.getToCc() != null) {
            mailMessage.setCc(mail.getToCc());
        }
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        return mailMessage;
    }

    public void send(final Mail mail){

        LOGGER.info("Starting email preparation");

        try {
            SimpleMailMessage mailMessage = createMailMessage(mail);
            javaMailSender.send(mailMessage);
            LOGGER.info("Email has been sent.");

        }catch (MailException e){
            LOGGER.error("Failed to process email sending: ",e.getMessage(),e);
        }
    }

}
