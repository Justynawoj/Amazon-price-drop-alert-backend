package com.amazon_price_drop_alert.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "request")

public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String url;
    @Column
    private String country;
    @Column
    private double requestedPrice;
    @Column
    private String email;
    @Column
    private boolean isActive = true;

    public Request( String url, String country,
                  double requestedPrice,  String email) {

        this.url = url;
        this.country = country;
        this.requestedPrice = requestedPrice;
        this.email = email;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
