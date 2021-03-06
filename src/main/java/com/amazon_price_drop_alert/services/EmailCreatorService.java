package com.amazon_price_drop_alert.services;

import com.amazon_price_drop_alert.domains.Request;
import com.amazon_price_drop_alert.dtos.ProductDetailsDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@AllArgsConstructor
public class EmailCreatorService {

  //  @Qualifier("templateEngine")
    private final TemplateEngine templateEngine;

    public String buildEmail(String message, Request request, ProductDetailsDto productDetailsDto) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("amazon_url", request.getUrl());
        return templateEngine.process("mail/alert-mail.html", context);
    }

}
