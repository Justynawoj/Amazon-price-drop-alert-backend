package com.amazon_price_drop_alert.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {

    private Long id;
    private String url;
    private String country;
    private double requestedPrice;
    private String email;
    private boolean isActive = true;

    public RequestDto(String url, String country, double requestedPrice, String email) {
        this.url = url;
        this.country = country;
        this.requestedPrice = requestedPrice;
        this.email = email;
    }
}
