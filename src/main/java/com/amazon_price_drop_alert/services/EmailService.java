package com.amazon_price_drop_alert.services;

import com.amazon_price_drop_alert.domains.Mail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final EmailCreatorService emailCreatorService;

    public void send(final Mail mail, boolean isScheduled) {

        log.info("Starting email preparation");
        try {
            if (isScheduled) {
                javaMailSender.send(createScheduledMimeMessage(mail));
            } else
                javaMailSender.send(createNewRequestMimeMessage(mail));
            log.info("Email has been sent.");

        } catch (MailException e) {
            log.error("Failed to process email sending to: {}. Cause: {}", mail.getMailTo(), e.getMessage());
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
