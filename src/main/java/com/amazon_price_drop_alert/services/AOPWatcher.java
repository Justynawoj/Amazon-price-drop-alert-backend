package com.amazon_price_drop_alert.services;

import com.amazon_price_drop_alert.domains.Mail;
import com.amazon_price_drop_alert.dtos.RequestDto;
import com.amazon_price_drop_alert.mappers.RequestMapper;
import lombok.AllArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@AllArgsConstructor
public class AOPWatcher {

    private final EmailService emailService;
    private final RequestMapper requestMapper;

    @AfterReturning("execution(public * com.amazon_price_drop_alert.controllers.RequestController.createRequest(..))"
    + "&& args(requestDto) && target(Object)")
    public void sendEmailWhenRequestIsAdded(final RequestDto requestDto) {
        emailService.send(new Mail(requestDto.getEmail(), "Alert drop activation",requestMapper.mapToRequest(requestDto)),false);
    }
}
