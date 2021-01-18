package com.amazon_price_drop_alert.mappers;

import com.amazon_price_drop_alert.domains.Request;
import com.amazon_price_drop_alert.dtos.RequestDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component

public class RequestMapper {

    public Request mapToRequest(RequestDto requestDto){
        return new Request(
                requestDto.getId(),
                requestDto.getAsin(),
                requestDto.getCountry(),
                requestDto.getRequestedPrice(),
                requestDto.getEmail(),
                requestDto.isActive());
    }

    public RequestDto mapToRequestDto(Request request){
        return new RequestDto(request.getId(),
                request.getUrl(),
                request.getCountry(),
                request.getRequestedPrice(),
                request.getEmail(),
                request.isActive());
    }

    public List<RequestDto> mapToRequestDtoList (List<Request> requests){
        List <RequestDto> requestDtos = new ArrayList<>();
        for (Request request: requests
             ) {
            requestDtos.add(mapToRequestDto(request));
        }
        return requestDtos;
    }

}
