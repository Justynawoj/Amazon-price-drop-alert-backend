package com.amazon_price_drop_alert.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ProductDto {

    @JsonProperty
    private String asin;
    @JsonProperty
    private String createdAt;
    @JsonProperty
    private String currencySymbol;
    @JsonProperty
    private String title;
    @JsonProperty(value = "prices")
    private CurrentPriceDto currentPriceDto;
    @JsonProperty
    private PriceByTypeDto highestPricing;
    @JsonProperty
    private PriceByTypeDto lowestPricing;
}
