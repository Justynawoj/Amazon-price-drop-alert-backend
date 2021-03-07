package com.amazon_price_drop_alert.domains;

import com.amazon_price_drop_alert.dtos.ProductDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Mail {
    private final String mailTo;
    private final String subject;
    private final Request request;
    private final ProductDetailsDto productDetailsDto;
}
