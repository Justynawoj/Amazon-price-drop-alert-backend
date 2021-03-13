package com.amazon_price_drop_alert.controllers;

import com.amazon_price_drop_alert.dtos.RequestDto;
import com.amazon_price_drop_alert.exceptions.RequestNotFoundException;
import com.amazon_price_drop_alert.mappers.RequestMapper;
import com.amazon_price_drop_alert.services.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@AllArgsConstructor
@RequestMapping("/v1/request")
@EnableAspectJAutoProxy
public class RequestController {

    private final RequestService requestService;
    private final RequestMapper requestMapper;

    @GetMapping("{id}")
    public RequestDto getRequest(@PathVariable final Long id) {
        return requestMapper.mapToRequestDto(requestService.getRequestById(id));
    }

    @GetMapping
    public List<RequestDto> getRequests() {
        return requestMapper.mapToRequestDtoList(requestService.getAllRequests());
    }

    @PostMapping
    public void createRequest(@RequestBody RequestDto requestDto) {
        requestService.createRequest(requestMapper.mapToRequest(requestDto));
    }

    @PutMapping("{id}")
    public void setNotActive(@PathVariable final Long id) throws RequestNotFoundException {
        requestService.setNotActive(id);
    }

    @DeleteMapping("{id}")
    public void deleteRequest(@PathVariable final Long id) throws RequestNotFoundException {
        requestService.deleteById(id);
    }
}
