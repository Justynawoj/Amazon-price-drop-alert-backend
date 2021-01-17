package com.amazon_price_drop_alert.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AmazonPriceConfig {

    @Value("${rapidApiKey}")
    private String rapidApiKey;
    @Value("${rapidApiHost}")
    private String rapidApiHost;
    @Value("${rapidApiEndpoint}")
    private String rapidApiEndpoint;

}
