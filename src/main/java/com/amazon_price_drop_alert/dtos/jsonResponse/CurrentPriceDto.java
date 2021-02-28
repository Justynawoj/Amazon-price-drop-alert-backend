package com.amazon_price_drop_alert.dtos.jsonResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrentPriceDto {

    @JsonProperty
    private Double priceAmazon;
    @JsonProperty
    private Double priceNew;
    @JsonProperty
    private Double priceUsed;
}
