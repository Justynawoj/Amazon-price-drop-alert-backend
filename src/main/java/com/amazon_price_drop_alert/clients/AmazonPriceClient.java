package com.amazon_price_drop_alert.clients;

import com.amazon_price_drop_alert.config.AmazonPriceConfig;
import com.amazon_price_drop_alert.dtos.ProductDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger LOGGER = LoggerFactory.getLogger(AmazonPriceClient.class);

    public ProductDto getResponse(String asin, String country) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-key", config.getRapidApiKey());
        headers.set("x-rapidapi-host", config.getRapidApiHost());
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<ProductDto> responseObj = restTemplate.exchange(
                config.getRapidApiEndpoint()
                        + asin
                        + "&marketplace=" + country,
                HttpMethod.GET, entity, ProductDto.class);
        LOGGER.info("Received request");
        try {
            return responseObj.getBody();
        } catch (Exception e) {
            e.getMessage();
            return new ProductDto();
        }
    }
}
