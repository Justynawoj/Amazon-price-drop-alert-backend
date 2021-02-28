package com.amazon_price_drop_alert.dtos.jsonResponse;

import com.amazon_price_drop_alert.dtos.jsonResponse.CurrentPriceDto;
import com.amazon_price_drop_alert.dtos.jsonResponse.PriceByTypeDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
