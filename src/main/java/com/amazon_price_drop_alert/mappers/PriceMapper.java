package com.amazon_price_drop_alert.mappers;

import com.amazon_price_drop_alert.domains.PriceDetails;
import com.amazon_price_drop_alert.domains.ProductDetails;
import com.amazon_price_drop_alert.dtos.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class PriceMapper {
    public ProductDetails mapToProductDetails(ProductDto productDto) {
        return new ProductDetails(
                productDto.getAsin(),
                productDto.getCreatedAt(),
                productDto.getCurrencySymbol(),
                productDto.getTitle(),
                new PriceDetails(productDto.getCurrentPriceDto().getPriceAmazon()/100,
                        "today"),
                new PriceDetails(productDto.getHighestPricing().getPriceAmazon().getPrice()/100,
                        productDto.getHighestPricing().getPriceAmazon().getCreatedAt()),
                new PriceDetails(productDto.getLowestPricing().getPriceAmazon().getPrice()/100,
                        productDto.getLowestPricing().getPriceAmazon().getCreatedAt()),

                new PriceDetails(productDto.getCurrentPriceDto().getPriceNew()/100,
                "today"),
                new PriceDetails(productDto.getHighestPricing().getPriceNew().getPrice()/100,
                        productDto.getHighestPricing().getPriceNew().getCreatedAt()),
                new PriceDetails(productDto.getLowestPricing().getPriceNew().getPrice()/100,
                        productDto.getLowestPricing().getPriceNew().getCreatedAt())
        );
    }
}
