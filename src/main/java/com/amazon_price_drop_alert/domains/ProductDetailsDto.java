package com.amazon_price_drop_alert.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailsDto {

    private String asin;
    private String createdAt;
    private String currencySymbol;
    private String title;
    private PriceDetails currentPriceAmazon;
    private PriceDetails highestPriceAmazon;
    private PriceDetails lowestPricingAmazon;
    private PriceDetails currentPriceThirdPart;
    private PriceDetails highestPriceThirdPart;
    private PriceDetails lowestPricingThirdPart;

    public ProductDetailsDto(String asin, String createdAt, String currencySymbol, String title) {
        this.asin = asin;
        this.createdAt = createdAt;
        this.currencySymbol = currencySymbol;
        this.title = title;
    }
}
