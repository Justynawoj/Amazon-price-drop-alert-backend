package com.amazon_price_drop_alert.controllers;

import com.amazon_price_drop_alert.clients.AmazonPriceClient;
import com.amazon_price_drop_alert.domains.ProductDetailsDto;
import com.amazon_price_drop_alert.mappers.PriceMapper;
import com.amazon_price_drop_alert.services.AsinRetriever;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/price")
@AllArgsConstructor
public class AmazonPriceApiController {

    private final AmazonPriceClient amazonPriceClient;
    private final AsinRetriever asinRetriever;
    private final PriceMapper priceMapper;

    @GetMapping
    public ProductDetailsDto getResponse(@RequestParam String url, @RequestParam String country) {

        String asin = asinRetriever.convertUrlToAsin(url);
        return priceMapper.mapToProductDetails(amazonPriceClient.getResponse(asin, country));
    }
}
