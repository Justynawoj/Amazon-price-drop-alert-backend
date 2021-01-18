package com.amazon_price_drop_alert.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class PriceDetailsDto {

    @JsonProperty
    private String createdAt;
    @JsonProperty
    private Double price;

    @Override
    public String toString() {
        return "Price{" +
                "createdAt='" + createdAt + '\'' +
                ", price=" + price +
                '}';
    }
}
