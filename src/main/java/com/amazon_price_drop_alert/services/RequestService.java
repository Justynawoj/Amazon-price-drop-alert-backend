package com.amazon_price_drop_alert.services;

import com.amazon_price_drop_alert.domains.Request;
import com.amazon_price_drop_alert.exceptions.RequestNotFoundException;
import com.amazon_price_drop_alert.repositories.RequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RequestService {

    private final RequestRepository requestRepository;

    public Request getRequestById(final Long id){
        return requestRepository.findById(id)
                .orElseThrow(() -> new RequestNotFoundException("Request not found"));
    }

    public List<Request> getAllRequests(){
        return requestRepository.findAll();
    }

    public Request createRequest(final Request request){
        return requestRepository.save(request);
    }

    public void setNotActive(final Long id) {
        Request requestToUpdate = getRequestById(id);
        requestToUpdate.setActive(false);
        requestRepository.save(requestToUpdate);
    }
}
