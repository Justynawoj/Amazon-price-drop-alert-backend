package com.amazon_price_drop_alert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class AmazonPriceDropAlertApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(AmazonPriceDropAlertApplication.class, args);

/*        AmazonPriceClient client = new AmazonPriceClient();
        Product product = client.getResponse();
        product.getAsin();
        product.getHighestPricing();
        System.out.println(product.getTitle());
        System.out.println(product.toString());*/

    }

}


