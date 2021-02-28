package com.amazon_price_drop_alert.mappers;

import com.amazon_price_drop_alert.dtos.ProductDetailsDto;
import com.amazon_price_drop_alert.dtos.jsonResponse.PriceDetailsDto;
import com.amazon_price_drop_alert.dtos.jsonResponse.ProductDto;
import org.springframework.stereotype.Component;


@Component
public class PriceMapper {
    public ProductDetailsDto mapToProductDetails(ProductDto productDto) {

        ProductDetailsDto productDetailsDto = new ProductDetailsDto(
                productDto.getAsin(),
                productDto.getCreatedAt(),
                productDto.getCurrencySymbol(),
                productDto.getTitle());

        if(productDto.getCurrentPriceDto().getPriceAmazon() == null){
            productDto.getCurrentPriceDto().setPriceAmazon(0.0);
        }
        productDetailsDto.setCurrentPriceAmazon(productDto.getCurrentPriceDto().getPriceAmazon()/100);

        if(productDto.getHighestPricing().getPriceAmazon()==null){
            productDto.getHighestPricing().setPriceAmazon(new PriceDetailsDto("not available",0.0));
        }
        productDetailsDto.setHighestPriceAmazon(new PriceDetailsDto(
                productDto.getHighestPricing().getPriceAmazon().getCreatedAt(),
                productDto.getHighestPricing().getPriceAmazon().getPrice()/100));

        if(productDto.getLowestPricing().getPriceAmazon()==null){
            productDto.getLowestPricing().setPriceAmazon(new PriceDetailsDto("not available", 0.0));
        }
        productDetailsDto.setLowestPricingAmazon(new PriceDetailsDto(
                productDto.getLowestPricing().getPriceAmazon().getCreatedAt(),
                productDto.getLowestPricing().getPriceAmazon().getPrice()/100));

        if(productDto.getCurrentPriceDto().getPriceNew()==null){
            productDto.getCurrentPriceDto().setPriceNew(0.0);
        }
        productDetailsDto.setCurrentPriceThirdPart(productDto.getCurrentPriceDto().getPriceNew()/100);

        if(productDto.getHighestPricing().getPriceNew()==null){
            productDto.getHighestPricing().setPriceNew(new PriceDetailsDto("not available", 0.0));
        }
        productDetailsDto.setHighestPriceThirdPart(new PriceDetailsDto(
                productDto.getHighestPricing().getPriceNew().getCreatedAt(),
                productDto.getHighestPricing().getPriceNew().getPrice()/100));

        if(productDto.getLowestPricing().getPriceNew() == null){
            productDto.getLowestPricing().setPriceNew(new PriceDetailsDto("not available", 0.0));
        }

        productDetailsDto.setLowestPricingThirdPart(new PriceDetailsDto(
                productDto.getLowestPricing().getPriceNew().getCreatedAt(),
                productDto.getLowestPricing().getPriceNew().getPrice()/100));

        return productDetailsDto;
    }
}
