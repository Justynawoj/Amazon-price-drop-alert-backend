package com.amazon_price_drop_alert.mappers;

import com.amazon_price_drop_alert.domains.Request;
import com.amazon_price_drop_alert.dtos.RequestDto;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class RequestMapperTest {

    @Autowired
    RequestMapper requestMapper;

    @Test
    public void mapToRequest() {
        //Given
        RequestDto requestDto = generateRequest1();

        //When
        Request request = requestMapper.mapToRequest(requestDto);

        //Then
        Assert.assertNotNull(request);
        Assert.assertEquals(requestDto.getCountry(),request.getCountry());
        Assert.assertEquals(requestDto.getEmail(),request.getEmail());
    }

    @Test
    public void mapToRequestDto() {
        //Given
        Request request = new Request(1L,"www.google.com","IT",20.00,"json@gmail.com",true);

        //When
        RequestDto requestDto = requestMapper.mapToRequestDto(request);

        //Then
        Assert.assertNotNull(requestDto);
        Assert.assertEquals(request.getCountry(),requestDto.getCountry());
        Assert.assertEquals(request.getEmail(),requestDto.getEmail());
        Assert.assertEquals(request.getId(),requestDto.getId());

    }

    @Test
    public void mapToRequestDtoList() {
        //Given
        Request request = new Request(1L,"www.google.com","IT",20.00,"json@gmail.com",true);
        Request request2 = new Request("www.google.com","EN",20.00,"json@gmail.com");
            request2.setActive(false);

        List<Request> requestList = new ArrayList<>(List.of(request,request2));

        //When
        List<RequestDto> requestDtoList = requestMapper.mapToRequestDtoList(requestList);

        //Then
        Assert.assertEquals(requestList.size(), requestDtoList.size());
        Assert.assertEquals(requestList.get(0).getId(),requestDtoList.get(0).getId());
    }

    public RequestDto generateRequest1() {

        RequestDto request = new RequestDto();
        request.setActive(false);
        request.setCountry("IT");
        request.setEmail("justyna@gmail.com");
        request.setRequestedPrice(200.00);
        request.setUrl("https://www.amazon.it/Regolabile-Poltrona-Girevole-Poggiapiedi-Estraibile/dp/B078N5WRSP?ref_=Oct_DLandingS_D_682ebd0d_65&smid=A1NU6L6HP7KWNK");
        request.setId(1L);
        return request;
    }
}
