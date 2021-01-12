package com.amazon_price_drop_alert.clients.domains;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PriceByType {

    @JsonProperty
    private PriceDetails priceAmazon;

    @JsonProperty
    private PriceDetails priceNew;

    @JsonProperty
    private PriceDetails priceUsed;
}
