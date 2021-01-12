package com.amazon_price_drop_alert.clients;

import com.amazon_price_drop_alert.clients.domains.ProductDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/price")
@AllArgsConstructor
//@CrossOrigin(origins = "*")
public class AmazonPriceApiController {

    private final AmazonPriceClient amazonPriceClient;
    private final AsinRetriever asinRetriever;

    @GetMapping(value = "getResponse")
    public ProductDto getResponse(String url, Country country){

        String asin = asinRetriever.convertUrlToAsin(url);
        return amazonPriceClient.getResponse(asin, country);
    }

}
