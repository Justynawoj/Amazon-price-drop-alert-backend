package com.amazon_price_drop_alert.dtos;

import com.amazon_price_drop_alert.domains.SearchedProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String mail;
    private boolean isActive;
    private List<SearchedProduct> searchedProduct;

}
