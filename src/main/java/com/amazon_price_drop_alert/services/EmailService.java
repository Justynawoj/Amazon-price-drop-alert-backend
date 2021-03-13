package com.amazon_price_drop_alert.services;

import com.amazon_price_drop_alert.domains.Mail;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final EmailCreatorService emailCreatorService;

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMailMessage.class);

    public void send(final Mail mail, boolean isScheduled) {

        LOGGER.info("Starting email preparation");

        try {
            if (isScheduled) {
                javaMailSender.send(createScheduledMimeMessage(mail));
            } else
                javaMailSender.send(createNewRequestMimeMessage(mail));
            LOGGER.info("Email has been sent.");

        } catch (MailException e) {
            LOGGER.error("Failed to process email sending: ", e, e.getMessage());
        }
    }

    private MimeMessagePreparator createScheduledMimeMessage(final Mail mail) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(emailCreatorService.buildAlertEmail(mail.getRequest(), mail.getProductDetailsDto()), true);
        };
    }

    private MimeMessagePreparator createNewRequestMimeMessage(final Mail mail) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(emailCreatorService.buildNewRequestEmail(mail.getRequest()), true);
        };
    }
}
