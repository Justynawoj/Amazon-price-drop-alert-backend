package com.amazon_price_drop_alert.services;

import com.amazon_price_drop_alert.exceptions.AsinNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class AsinRetriever {

    public String convertUrlToAsin(String url) throws AsinNotFoundException {

        String retrievedAsin = "";
        String[] splitUrl = url.split("/dp/");
        try{
            retrievedAsin = splitUrl[1].substring(0, 10);
        }catch (final IndexOutOfBoundsException e){
            throw new AsinNotFoundException("Couldn't find product code. Please provide amazon url");
        }
        return retrievedAsin;
    }
}
