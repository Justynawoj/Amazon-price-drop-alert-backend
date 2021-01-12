package com.amazon_price_drop_alert.clients.domains;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PriceDetails {

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
