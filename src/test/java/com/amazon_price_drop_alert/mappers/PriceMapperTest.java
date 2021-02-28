package com.amazon_price_drop_alert.mappers;

import com.amazon_price_drop_alert.dtos.ProductDetailsDto;
import com.amazon_price_drop_alert.dtos.jsonResponse.CurrentPriceDto;
import com.amazon_price_drop_alert.dtos.jsonResponse.PriceByTypeDto;
import com.amazon_price_drop_alert.dtos.jsonResponse.PriceDetailsDto;
import com.amazon_price_drop_alert.dtos.jsonResponse.ProductDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PriceMapperTest {

    @Autowired
    PriceMapper mapper;

    @Test
    void mapToProductDetails() {

        //Given
        ProductDto productDto = generateSampleProduct();

        //When
        ProductDetailsDto productDetailsDto = mapper.mapToProductDetails(productDto);

        //Then
        assertEquals(productDto.getAsin(), productDetailsDto.getAsin());
        assertEquals(productDto.getCreatedAt(), productDetailsDto.getCreatedAt());
        assertEquals(productDto.getCurrencySymbol(), productDetailsDto.getCurrencySymbol());
        assertEquals(productDto.getTitle(), productDetailsDto.getTitle());
        assertEquals(30.00, productDetailsDto.getCurrentPriceAmazon(), 1);
        assertEquals(40.00, productDetailsDto.getHighestPriceAmazon().getPrice(), 1);
        assertEquals(productDto.getHighestPricing().getPriceAmazon().getCreatedAt(),
                productDetailsDto.getHighestPriceAmazon().getCreatedAt());
        assertEquals(20.00, productDetailsDto.getLowestPricingAmazon().getPrice(), 1);
        assertEquals(productDto.getLowestPricing().getPriceAmazon().getCreatedAt(),
                productDetailsDto.getLowestPricingAmazon().getCreatedAt());
        assertEquals(35.00, productDetailsDto.getCurrentPriceThirdPart(), 1);
        assertEquals(45.00, productDetailsDto.getHighestPriceThirdPart().getPrice(), 1);
        assertEquals(productDto.getHighestPricing().getPriceNew().getCreatedAt(),
                productDetailsDto.getHighestPriceThirdPart().getCreatedAt());
        assertEquals(25.00, productDetailsDto.getLowestPricingThirdPart().getPrice(), 1);
        assertEquals(productDto.getLowestPricing().getPriceNew().getCreatedAt(),
                productDetailsDto.getLowestPricingThirdPart().getCreatedAt());

    }

    @Test
    public void propertyShouldNotBeNull() {

        //Given
        ProductDto productDto = generateSampleProductWithNullValues();

        //When
        ProductDetailsDto productDetailsDto = mapper.mapToProductDetails(productDto);

        //Then
        assertNotNull(productDetailsDto.getLowestPricingAmazon().getCreatedAt());
        assertNotNull(productDetailsDto.getLowestPricingAmazon().getPrice());
        assertNotNull(productDetailsDto.getHighestPriceAmazon().getCreatedAt());
        assertNotNull(productDetailsDto.getHighestPriceAmazon().getPrice());
        assertNotNull(productDetailsDto.getCurrentPriceAmazon());
        assertNotNull(productDetailsDto.getCurrentPriceThirdPart());
        assertNotNull(productDetailsDto.getHighestPriceThirdPart().getCreatedAt());
        assertNotNull(productDetailsDto.getHighestPriceThirdPart().getPrice());
        assertNotNull(productDetailsDto.getLowestPricingThirdPart().getCreatedAt());
        assertNotNull(productDetailsDto.getLowestPricingThirdPart().getPrice());
        assertNotNull(productDetailsDto);
    }

    private ProductDto generateSampleProduct() {
        ProductDto productDto = new ProductDto();
        productDto.setAsin("0000000000");
        productDto.setCreatedAt(LocalDate.now().toString());
        productDto.setCurrencySymbol("$");
        productDto.setTitle("Clean Code");
        productDto.setCurrentPriceDto(new CurrentPriceDto(
                3000.00, 3500.00));
        productDto.setHighestPricing(new PriceByTypeDto(new PriceDetailsDto(LocalDate.now().getMonth().minus(2).toString(), 4000.00),
                new PriceDetailsDto(LocalDate.now().getMonth().minus(4).toString(), 4500.00)));
        productDto.setLowestPricing(new PriceByTypeDto(new PriceDetailsDto(LocalDate.now().getMonth().minus(1).toString(), 2000.00),
                new PriceDetailsDto(LocalDate.now().getMonth().toString(), 2500.00)));
        return productDto;
    }

    private ProductDto generateSampleProductWithNullValues() {
        ProductDto productDto = new ProductDto();
        productDto.setAsin("0000000000");
        productDto.setCreatedAt(LocalDate.now().toString());
        productDto.setCurrencySymbol("$");
        productDto.setTitle("Clean Code");
        productDto.setCurrentPriceDto(new CurrentPriceDto(null, null));
        productDto.setHighestPricing(new PriceByTypeDto(null, null));
        productDto.setLowestPricing(new PriceByTypeDto(null, null));
        return productDto;
    }
}
