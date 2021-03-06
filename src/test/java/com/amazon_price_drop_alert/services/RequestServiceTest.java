package com.amazon_price_drop_alert.services;

import com.amazon_price_drop_alert.domains.Request;

import com.amazon_price_drop_alert.exceptions.RequestNotFoundException;
import com.amazon_price_drop_alert.repositories.RequestRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class RequestServiceTest {

    @Autowired
    private RequestService requestService;

    @Autowired
    private RequestRepository requestRepository;

    @Test
    public void shouldGetRequestById() {

        //Given
        Request request = new Request("www.amazon.com","IT",200.00,"gmail@gmail.com",false);
        Long requestId = requestService.createRequest(request).getId();

        //When
        Request requestRetrieved = requestService.getRequestById(requestId);

        //Then
        assertEquals(request.getId(),requestRetrieved.getId());
        assertEquals(request.getEmail(),requestRetrieved.getEmail());
        assertEquals(request.getRequestedPrice(),requestRetrieved.getRequestedPrice(),0);

        //CleanUp
        requestService.deleteById(requestId);
    }

    @Test
    public void getAllRequests() {
        //Given
        Request request = new Request("www.amazon.com","EN",200.00,"gmail@gmail.com",false);
        Request request2 = new Request("www.amazon.com","IT",200.00,"gmail@gmail.it",false);

        Long request1Id = requestService.createRequest(request).getId();
        Long request2Id = requestService.createRequest(request2).getId();

        //When
        List<Request> allRequests = requestService.getAllRequests();

        //Then
        assertTrue(allRequests.contains(request));
        assertTrue(allRequests.contains(request2));

        //CleanUp
        requestService.deleteById(request1Id);
        requestService.deleteById(request2Id);
    }

    @Test
    public void shouldCreateRequest() {
        //Given
        Request request = new Request("www.amazon.com","EN",200.00,"gmail@gmail.com",false);

        //When
        Long requestId = requestService.createRequest(request).getId();
        List<Request> allRequest = requestService.getAllRequests();

        //Then
        assertTrue(requestRepository.existsById(requestId));
        assertTrue(allRequest.contains(request));

        //CleanUp
        requestService.deleteById(requestId);
    }

    @Test
    public void setNotActive() {
        //Given
        Request request = new Request("www.amazon.com","EN",200.00,"gmail@gmail.com",true);
        Long requestId = requestRepository.save(request).getId();

        //When
        requestService.setNotActive(requestId);
        Request requestNotActive =requestRepository.findById(requestId).orElseThrow(() ->
                new RequestNotFoundException("Given request is not present in DB")
        );

        //Then
        assertNotEquals(requestNotActive,request);
        assertNotEquals(requestNotActive.isActive(),request.isActive());
        assertEquals(request.getId(),requestNotActive.getId());

        //CleanUp
        requestRepository.deleteById(requestId);
    }

    @Test
    public void deleteById() {
        //Given
        Request request = new Request("www.amazon.com","EN",200.00,"gmail@gmail.com",true);
        Long requestId = requestRepository.save(request).getId();

        //When
        requestService.deleteById(requestId);
        List<Request> allRequests = requestRepository.findAll();

        //Then
        assertFalse(allRequests.contains(request));
    }

    @Test
    public void shouldThrowException() {
        //Given
        Request request = new Request("www.amazon.com","EN",200.00,"gmail@gmail.com",true);
        Long requestId = requestRepository.save(request).getId();
        requestService.deleteById(requestId);

        //When & Then
        assertThrows(RequestNotFoundException.class, () -> requestService.getRequestById(requestId));
    }
}
