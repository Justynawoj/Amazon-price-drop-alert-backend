package com.amazon_price_drop_alert.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PriceByTypeDto {

    @JsonProperty
    private PriceDetailsDto priceAmazon;

    @JsonProperty
    private PriceDetailsDto priceNew;

    @JsonProperty
    private PriceDetailsDto priceUsed;
}
