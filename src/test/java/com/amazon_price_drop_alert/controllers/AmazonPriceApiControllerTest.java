package com.amazon_price_drop_alert.controllers;

import com.amazon_price_drop_alert.clients.AmazonPriceClient;
import com.amazon_price_drop_alert.dtos.ProductDetailsDto;
import com.amazon_price_drop_alert.mappers.PriceMapper;
import com.amazon_price_drop_alert.services.AsinRetriever;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(AmazonPriceApiController.class)
public class AmazonPriceApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AmazonPriceClient amazonPriceClient;
    @MockBean
    private AsinRetriever asinRetriever;
    @MockBean
    private PriceMapper priceMapper;


    @Test
    public void shouldGetResponse() throws Exception {

        //Given
        ProductDetailsDto product = new ProductDetailsDto();
        product.setTitle("Clean code. R. Martin");
        when(priceMapper.mapToProductDetails(amazonPriceClient.getResponse("0132350882","IT"))).thenReturn(product);

        //When & Then
        mockMvc.perform(get("/v1/price").contentType(MediaType.ALL).
                param("url","dp/0132350882").param("country","it"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.title", is("Clean code. R. Martin")))
                .andDo(MockMvcResultHandlers.print());
    }

}
