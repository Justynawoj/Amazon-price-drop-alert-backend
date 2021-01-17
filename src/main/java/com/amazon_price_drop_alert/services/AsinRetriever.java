package com.amazon_price_drop_alert.services;

import org.springframework.stereotype.Component;


@Component
public class AsinRetriever {

    public String convertUrlToAsin(String url) {

        String[] splitUrl = url.split("/dp/");
        return splitUrl[1].substring(0, 10);
    }
}
