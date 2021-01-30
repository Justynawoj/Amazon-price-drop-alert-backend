package com.amazon_price_drop_alert.domains;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductDetails {

    @JsonProperty
    private String asin;
    @JsonProperty
    private String createdAt;
    @JsonProperty
    private String currencySymbol;
    @JsonProperty
    private String title;
    @JsonProperty
    private PriceDetails currentPriceAmazon;
    @JsonProperty
    private PriceDetails highestPriceAmazon;
    @JsonProperty
    private PriceDetails lowestPricingAmazon;

    @JsonProperty
    private PriceDetails currentPriceThirdPart;
    @JsonProperty
    private PriceDetails highestPriceThirdPart;
    @JsonProperty
    private PriceDetails lowestPricingThirdPart;

}
