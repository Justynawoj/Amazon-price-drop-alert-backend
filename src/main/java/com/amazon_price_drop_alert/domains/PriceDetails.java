package com.amazon_price_drop_alert.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PriceDetails {
    @Override
    public String toString() {
        return "PriceDetails{" +
                "price=" + price +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }

    private double price;
    private String createdAt;

}
