package com.amazon_price_drop_alert.services;

import com.amazon_price_drop_alert.config.ActuatorConfig;
import com.amazon_price_drop_alert.domains.Request;
import com.amazon_price_drop_alert.dtos.ProductDetailsDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@AllArgsConstructor
public class EmailCreatorService {

    private final TemplateEngine templateEngine;
    private final ActuatorConfig actuatorConfig;

    public String buildEmail( Request request, ProductDetailsDto productDetailsDto) {
        Context context = new Context();
        context.setVariable("amazon_url", request.getUrl());
        context.setVariable("author_name", actuatorConfig.getAuthorName());
        context.setVariable("author_phone", actuatorConfig.getAuthorPhone());
        context.setVariable("author_email", actuatorConfig.getAuthorEmail());
        context.setVariable("product_title",productDetailsDto.getTitle());
        context.setVariable("current_amazon_price",productDetailsDto.getCurrentPriceAmazon());
        context.setVariable("current_third_part_price",productDetailsDto.getCurrentPriceThirdPart());
        context.setVariable("is_amazon_price_dropped",
                productDetailsDto.getCurrentPriceAmazon() <= request.getRequestedPrice()
                && productDetailsDto.getCurrentPriceAmazon() > 0);
        context.setVariable("is_third_part_price_dropped",
                productDetailsDto.getCurrentPriceThirdPart() <= request.getRequestedPrice()
                        && productDetailsDto.getCurrentPriceThirdPart() > 0);

        return templateEngine.process("mail/alert-mail.html", context);
    }

}
