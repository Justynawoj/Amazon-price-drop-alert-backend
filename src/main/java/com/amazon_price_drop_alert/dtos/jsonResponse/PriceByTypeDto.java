package com.amazon_price_drop_alert.dtos.jsonResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PriceByTypeDto {

    @JsonProperty
    private PriceDetailsDto priceAmazon;
    @JsonProperty
    private PriceDetailsDto priceNew;
    @JsonProperty
    private PriceDetailsDto priceUsed;
}
