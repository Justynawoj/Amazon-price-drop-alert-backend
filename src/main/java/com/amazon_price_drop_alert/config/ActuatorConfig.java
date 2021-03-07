package com.amazon_price_drop_alert.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ActuatorConfig {

    @Value("${info.author.name}")
    private String authorName;

    @Value("${info.author.email}")
    private String authorEmail;

    @Value("${info.author.phone}")
    private String authorPhone;

}
