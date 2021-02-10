package com.amazon_price_drop_alert.mappers;

import com.amazon_price_drop_alert.domains.PriceDetails;
import com.amazon_price_drop_alert.domains.ProductDetailsDto;
import com.amazon_price_drop_alert.dtos.PriceDetailsDto;
import com.amazon_price_drop_alert.dtos.ProductDto;
import org.springframework.stereotype.Component;


@Component
public class PriceMapper {
    public ProductDetailsDto mapToProductDetails(ProductDto productDto) {

        ProductDetailsDto productDetailsDto = new ProductDetailsDto(
                productDto.getAsin(),
                productDto.getCreatedAt(),
                productDto.getCurrencySymbol(),
                productDto.getTitle());

        if(productDto.getCurrentPriceDto().getPriceAmazon() == null || productDto.getCurrentPriceDto() == null){
            productDto.getCurrentPriceDto().setPriceAmazon(0.0);
        }
        productDetailsDto.setCurrentPriceAmazon(new PriceDetails(productDto.getCurrentPriceDto().getPriceAmazon()/100,"Today"));

        if(productDto.getHighestPricing().getPriceAmazon()==null || productDto.getHighestPricing() == null){
            productDto.getHighestPricing().setPriceAmazon(new PriceDetailsDto("not available",0.0));
        }
        productDetailsDto.setHighestPriceAmazon(new PriceDetails(
                productDto.getHighestPricing().getPriceAmazon().getPrice()/100,
                productDto.getHighestPricing().getPriceAmazon().getCreatedAt()));

        if(productDto.getLowestPricing().getPriceAmazon()==null || productDto.getLowestPricing()==null){
            productDto.getLowestPricing().setPriceAmazon(new PriceDetailsDto("not available", 0.0));
        }
        productDetailsDto.setLowestPricingAmazon(new PriceDetails(
                productDto.getLowestPricing().getPriceAmazon().getPrice()/100,
                productDto.getLowestPricing().getPriceAmazon().getCreatedAt()));

        if(productDto.getCurrentPriceDto().getPriceNew()==null || productDto.getCurrentPriceDto()==null){
            productDto.getCurrentPriceDto().setPriceNew(0.0);
        }
        productDetailsDto.setCurrentPriceThirdPart(new PriceDetails(productDto.getCurrentPriceDto().getPriceNew()/100,"Today"));

        if(productDto.getHighestPricing().getPriceNew()==null || productDto.getHighestPricing()==null){
            productDto.getHighestPricing().setPriceNew(new PriceDetailsDto("not available", 0.0));
        }
        productDetailsDto.setHighestPriceThirdPart(new PriceDetails(
                productDto.getHighestPricing().getPriceNew().getPrice()/100,
                productDto.getHighestPricing().getPriceNew().getCreatedAt()));

        if(productDto.getLowestPricing().getPriceNew() == null || productDto.getLowestPricing() == null){
            productDto.getLowestPricing().setPriceNew(new PriceDetailsDto("not available", 0.0));
        }

        productDetailsDto.setLowestPricingThirdPart(new PriceDetails(
                productDto.getLowestPricing().getPriceNew().getPrice()/100,
                productDto.getLowestPricing().getPriceNew().getCreatedAt()));

        return productDetailsDto;
    }
}
