package com.amazon_price_drop_alert.clients;

import com.amazon_price_drop_alert.clients.domains.ProductDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
@AllArgsConstructor
public class AmazonPriceClient {

    private final RestTemplate restTemplate;
    private final AmazonPriceConfig config;

//    private String marketPlace = "IT";
//    private String asin = "B07FSK3Z6G";

    public ProductDto getResponse(String asin, Country country) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-key", config.getRapidApiKey());
        headers.set("x-rapidapi-host", config.getRapidApiHost());

        HttpEntity<String> entity = new HttpEntity<>("paramters",headers);

        ResponseEntity<ProductDto> responseObj = restTemplate.exchange("https://amazon-price1.p.rapidapi.com/priceReport?asin="+asin+"&marketplace="+country, HttpMethod.GET,entity, ProductDto.class);
        try {
            return responseObj.getBody();
        } catch (Exception e) {
            e.getMessage();
            return new ProductDto();
        }
    }

}
