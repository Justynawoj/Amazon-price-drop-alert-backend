package com.amazon_price_drop_alert.dtos;

import com.amazon_price_drop_alert.dtos.jsonResponse.PriceDetailsDto;
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
    private Double currentPriceAmazon;
    private PriceDetailsDto highestPriceAmazon;
    private PriceDetailsDto lowestPricingAmazon;
    private Double currentPriceThirdPart;
    private PriceDetailsDto highestPriceThirdPart;
    private PriceDetailsDto lowestPricingThirdPart;

    public ProductDetailsDto(String asin, String createdAt, String currencySymbol, String title) {
        this.asin = asin;
        this.createdAt = createdAt;
        this.currencySymbol = currencySymbol;
        this.title = title;
    }
}
