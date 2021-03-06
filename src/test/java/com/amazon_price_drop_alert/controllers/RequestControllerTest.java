package com.amazon_price_drop_alert.controllers;

import com.amazon_price_drop_alert.dtos.RequestDto;
import com.amazon_price_drop_alert.mappers.RequestMapper;
import com.amazon_price_drop_alert.services.RequestService;
import com.google.gson.Gson;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RequestController.class)
public class RequestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RequestService requestService;
    @MockBean
    private RequestMapper requestMapper;

    @Test
    public void shouldGetRequest() throws Exception {
        //Given
        RequestDto request1 = generateRequest1();
        when(requestMapper.mapToRequestDto(requestService.getRequestById(request1.getId()))).thenReturn(request1);

        //WHen & Then
        mockMvc.perform(get("/v1/request/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetAllRequests() throws Exception {

        //Given
        RequestDto request1 = generateRequest1();
        RequestDto request2 = generateRequest2();
        List<RequestDto> requests = new ArrayList<>(List.of(request1,request2));

        when(requestMapper.mapToRequestDtoList(requestService.getAllRequests())).thenReturn(requests);

        //When & Then
        mockMvc.perform(get("/v1/request")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void shouldCreateRequest() throws Exception {
        //Given
        RequestDto request1 = generateRequest1();
        Gson gson = new Gson();
        String jsonContent = gson.toJson(request1);

        //WHen * Then
        mockMvc.perform(post("/v1/request")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void shouldSetNotActive() throws Exception {

        //When & Then
        mockMvc.perform(put("/v1/request/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void shouldDeleteRequest() throws Exception {

        //WHen & THen
        mockMvc.perform(delete("/v1/request/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    public RequestDto generateRequest1(){

        RequestDto request = new RequestDto();
        request.setActive(false);
        request.setCountry("IT");
        request.setEmail("justyna@gmail.com");
        request.setRequestedPrice(200.00);
        request.setUrl("https://www.amazon.it/Regolabile-Poltrona-Girevole-Poggiapiedi-Estraibile/dp/B078N5WRSP?ref_=Oct_DLandingS_D_682ebd0d_65&smid=A1NU6L6HP7KWNK");
        request.setId(1L);
        return request;
    }

    public RequestDto generateRequest2(){

        RequestDto request = new RequestDto();
        request.setActive(false);
        request.setCountry("IT");
        request.setEmail("justyna@gmail.com");
        request.setRequestedPrice(1000.00);
        request.setUrl("https://www.amazon.it/Acer-AN515-52-71ME-Notebook-Processore-i7-8750H/dp/B07FWM2FQZ/?_encoding=UTF8&pd_rd_w=aHoCv&pf_rd_p=fd2b02e5-4e5d-4723-bcf6-31b0e9d6ecfc&pf_rd_r=9GJ4VC0F1A24ZV7TQB09&pd_rd_r=a2f14957-31c4-4155-9a41-981b2fff548e&pd_rd_wg=V9urG&ref_=pd_gw_ci_mcx_mr_hp_d");
        request.setId(2L);
        return request;
    }
}
