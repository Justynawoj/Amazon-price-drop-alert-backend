package com.amazon_price_drop_alert.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity (name = "searched_products")
@Getter
public class SearchedProduct {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String asin;
    @Column
    private boolean isRequestActive;
    @ManyToMany
    List<User> users = new ArrayList<>();

}
