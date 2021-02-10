package com.amazon_price_drop_alert.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PriceDetails {

    private double price;
    private String createdAt;


}
