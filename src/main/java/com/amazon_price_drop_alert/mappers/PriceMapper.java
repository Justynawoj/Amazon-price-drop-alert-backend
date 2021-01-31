package com.amazon_price_drop_alert.mappers;

import com.amazon_price_drop_alert.domains.PriceDetails;
import com.amazon_price_drop_alert.domains.ProductDetails;
import com.amazon_price_drop_alert.dtos.PriceDetailsDto;
import com.amazon_price_drop_alert.dtos.ProductDto;
import org.springframework.stereotype.Component;


@Component
public class PriceMapper {
    public ProductDetails mapToProductDetails(ProductDto productDto) {

        ProductDetails productDetails = new ProductDetails(
                productDto.getAsin(),
                productDto.getCreatedAt(),
                productDto.getCurrencySymbol(),
                productDto.getTitle());

        if(productDto.getCurrentPriceDto().getPriceAmazon()== null){
            productDto.getCurrentPriceDto().setPriceAmazon(0.0);
        }
        productDetails.setCurrentPriceAmazon(new PriceDetails(productDto.getCurrentPriceDto().getPriceAmazon()/100,"Today"));

        if(productDto.getHighestPricing().getPriceAmazon()==null){
            productDto.getHighestPricing().setPriceAmazon(new PriceDetailsDto("not available",0.0));
        }
        productDetails.setHighestPriceAmazon(new PriceDetails(
                productDto.getHighestPricing().getPriceAmazon().getPrice(),
                productDto.getHighestPricing().getPriceAmazon().getCreatedAt()));

        if(productDto.getLowestPricing().getPriceAmazon()==null){
            productDto.getLowestPricing().setPriceAmazon(new PriceDetailsDto("not available", 0.0));
        }
        productDetails.setLowestPricingAmazon(new PriceDetails(
                productDto.getLowestPricing().getPriceAmazon().getPrice(),
                productDto.getLowestPricing().getPriceAmazon().getCreatedAt()));

        if(productDto.getCurrentPriceDto().getPriceNew()==null){
            productDto.getCurrentPriceDto().setPriceNew(0.0);
        }
        productDetails.setCurrentPriceThirdPart(new PriceDetails(productDto.getCurrentPriceDto().getPriceNew(),"Today"));

        if(productDto.getHighestPricing().getPriceNew()==null){
            productDto.getHighestPricing().setPriceNew(new PriceDetailsDto("not available", 0.0));
        }
        productDetails.setHighestPriceThirdPart(new PriceDetails(
                productDto.getHighestPricing().getPriceNew().getPrice(),
                productDto.getHighestPricing().getPriceNew().getCreatedAt()));

        if(productDto.getLowestPricing().getPriceNew()==null){
            productDto.getLowestPricing().setPriceNew(new PriceDetailsDto("not available", 0.0));
        }

        productDetails.setLowestPricingAmazon(new PriceDetails(
                productDto.getLowestPricing().getPriceNew().getPrice(),
                productDto.getLowestPricing().getPriceNew().getCreatedAt()));

        return productDetails;
    }
}
