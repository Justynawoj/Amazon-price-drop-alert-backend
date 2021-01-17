package com.amazon_price_drop_alert.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private boolean isActive;
    @ManyToMany
    private List<SearchedProduct> searchedProducts = new ArrayList<>();

    public void setEmail(String mail) {
        this.email = mail;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setSearchedProducts(List<SearchedProduct> searchedProducts) {
        this.searchedProducts = searchedProducts;
    }
}
