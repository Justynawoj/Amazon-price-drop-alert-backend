package com.amazon_price_drop_alert.controllers;

import com.amazon_price_drop_alert.clients.AmazonPriceClient;
import com.amazon_price_drop_alert.domains.PriceDetails;
import com.amazon_price_drop_alert.domains.ProductDetails;
import com.amazon_price_drop_alert.dtos.ProductDto;
import com.amazon_price_drop_alert.domains.Country;
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
//@CrossOrigin(origins = "*")
public class AmazonPriceApiController {

    private final AmazonPriceClient amazonPriceClient;
    private final AsinRetriever asinRetriever;
    private final PriceMapper priceMapper;

    @GetMapping()
//    public ProductDto getResponse(@RequestParam String url, @RequestParam Country country) {
//
//        String asin = asinRetriever.convertUrlToAsin(url);
//        return amazonPriceClient.getResponse(asin, country);
//    }
    public ProductDetails getResponse(@RequestParam String url, @RequestParam Country country) {

        String asin = asinRetriever.convertUrlToAsin(url);
        ProductDetails pd = priceMapper.mapToProductDetails(amazonPriceClient.getResponse(asin, country));

        System.out.println(pd.toString());

        return pd;
    }
}
