package com.amazon_price_drop_alert.mappers;

import com.amazon_price_drop_alert.dtos.ProductDetailsDto;
import com.amazon_price_drop_alert.dtos.jsonResponse.PriceDetailsDto;
import com.amazon_price_drop_alert.dtos.jsonResponse.ProductDto;
import org.springframework.stereotype.Component;


@Component
public class PriceMapper {

    public ProductDetailsDto mapToProductDetails(ProductDto productDto) {

        if(productDto.getHighestPricing().getPriceAmazon()==null){
            productDto.getHighestPricing().setPriceAmazon(new PriceDetailsDto());
        }
        if(productDto.getLowestPricing().getPriceAmazon()==null){
            productDto.getLowestPricing().setPriceAmazon(new PriceDetailsDto());
        }
        if(productDto.getHighestPricing().getPriceNew()==null){
            productDto.getHighestPricing().setPriceNew(new PriceDetailsDto());
        }
        if(productDto.getLowestPricing().getPriceNew() == null){
            productDto.getLowestPricing().setPriceNew(new PriceDetailsDto());
        }
        ProductDetailsDto productDetailsDto = new ProductDetailsDto(
                productDto.getAsin(),
                productDto.getCreatedAt(),
                productDto.getCurrencySymbol(),
                productDto.getTitle(),
                productDto.getCurrentPriceDto().getPriceAmazon(),
                productDto.getHighestPricing().getPriceAmazon(),
                productDto.getLowestPricing().getPriceAmazon(),
                productDto.getCurrentPriceDto().getPriceNew(),
                productDto.getHighestPricing().getPriceNew(),
                productDto.getLowestPricing().getPriceNew());

        return convertCentsToEuro(productDetailsDto);
    }

    private ProductDetailsDto convertCentsToEuro(ProductDetailsDto productDetailsDtoToBeConverted){

        return new ProductDetailsDto(
                productDetailsDtoToBeConverted.getAsin(),
                productDetailsDtoToBeConverted.getCreatedAt(),
                productDetailsDtoToBeConverted.getCurrencySymbol(),
                productDetailsDtoToBeConverted.getTitle(),
                productDetailsDtoToBeConverted.getCurrentPriceAmazon()/100,
                new PriceDetailsDto(productDetailsDtoToBeConverted.getHighestPriceAmazon().getCreatedAt(),
                        productDetailsDtoToBeConverted.getHighestPriceAmazon().getPrice()/100),
                new PriceDetailsDto(productDetailsDtoToBeConverted.getLowestPricingAmazon().getCreatedAt(),
                        productDetailsDtoToBeConverted.getLowestPricingAmazon().getPrice()/100),

                productDetailsDtoToBeConverted.getCurrentPriceThirdPart()/100,
                new PriceDetailsDto(productDetailsDtoToBeConverted.getHighestPriceThirdPart().getCreatedAt(),
                        productDetailsDtoToBeConverted.getHighestPriceThirdPart().getPrice()/100),
                new PriceDetailsDto(productDetailsDtoToBeConverted.getLowestPricingThirdPart().getCreatedAt(),
                        productDetailsDtoToBeConverted.getLowestPricingThirdPart().getPrice()/100));
    }

}
