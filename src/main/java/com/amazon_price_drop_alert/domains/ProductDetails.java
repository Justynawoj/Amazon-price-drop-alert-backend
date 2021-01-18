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
    private PriceDetails currentPriceDto;
    @JsonProperty
    private PriceDetails highestPricing;
    @JsonProperty
    private PriceDetails lowestPricing;

    @Override
    public String toString() {
        return "ProductDetails{" +
                "asin='" + asin + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", currencySymbol='" + currencySymbol + '\'' +
                ", title='" + title + '\'' +
                ", currentPriceDto=" + currentPriceDto +
                ", highestPricing=" + highestPricing +
                ", lowestPricing=" + lowestPricing +
                '}';
    }
}
