package com.amazon_price_drop_alert.clients.domains;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrentPrice {

    @JsonProperty
    private double priceAmazon;
    @JsonProperty
    private double priceNew;
    @JsonProperty
    private double priceUsed;
}
