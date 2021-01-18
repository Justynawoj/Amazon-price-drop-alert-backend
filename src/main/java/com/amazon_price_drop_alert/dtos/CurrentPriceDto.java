package com.amazon_price_drop_alert.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CurrentPriceDto {

    @JsonProperty
    private double priceAmazon;
    @JsonProperty
    private double priceNew;
    @JsonProperty
    private double priceUsed;
}
