package com.amazon_price_drop_alert.domains;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    public ProductDetails(String asin, String createdAt, String currencySymbol, String title) {
        this.asin = asin;
        this.createdAt = createdAt;
        this.currencySymbol = currencySymbol;
        this.title = title;
    }
}
