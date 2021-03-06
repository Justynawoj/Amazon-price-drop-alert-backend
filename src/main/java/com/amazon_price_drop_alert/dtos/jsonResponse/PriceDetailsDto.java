package com.amazon_price_drop_alert.dtos.jsonResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PriceDetailsDto {

    @JsonProperty
    private String createdAt;
    @JsonProperty
    private double price;
}
