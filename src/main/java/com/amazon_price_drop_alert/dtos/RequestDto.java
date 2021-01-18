package com.amazon_price_drop_alert.dtos;

import com.amazon_price_drop_alert.domains.Country;
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
    private String asin;
    private Country country;
    private double requestedPrice;
    private String email;
    private boolean isActive = true;

    public RequestDto(String asin, Country country, double requestedPrice, String email) {
        this.asin = asin;
        this.country = country;
        this.requestedPrice = requestedPrice;
        this.email = email;
    }
}
