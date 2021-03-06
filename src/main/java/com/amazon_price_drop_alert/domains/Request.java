package com.amazon_price_drop_alert.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "request")

public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length=1000000)
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

    public Request(String url, String country, double requestedPrice, String email, boolean isActive) {
        this.url = url;
        this.country = country;
        this.requestedPrice = requestedPrice;
        this.email = email;
        this.isActive = isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Double.compare(request.requestedPrice, requestedPrice) == 0 &&
                isActive == request.isActive &&
                Objects.equals(id, request.id) &&
                Objects.equals(url, request.url) &&
                Objects.equals(country, request.country) &&
                Objects.equals(email, request.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, country, requestedPrice, email, isActive);
    }
}
