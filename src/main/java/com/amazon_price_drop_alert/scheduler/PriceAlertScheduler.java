package com.amazon_price_drop_alert.scheduler;

import com.amazon_price_drop_alert.controllers.AmazonPriceApiController;
import com.amazon_price_drop_alert.domains.ProductDetails;
import com.amazon_price_drop_alert.domains.Request;
import com.amazon_price_drop_alert.repositories.RequestRepository;
import com.amazon_price_drop_alert.services.AmazonPriceService;
import com.amazon_price_drop_alert.services.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class PriceAlertScheduler {

    private final RequestRepository requestRepository;
    private final AmazonPriceApiController priceApiController;
    private final RequestService requestService;
 //   private final EmailService emailService;

    @Scheduled(cron = "0 0 10 * * *")
    public void sendAlert(){
        List<Request> allRequests = requestRepository.findAll();

        for (Request request: allRequests
             ) {
            ProductDetails currentDetails = priceApiController.getResponse(request.getUrl(),request.getCountry());
            if (currentDetails.getCurrentPriceDto().getPrice()<=request.getRequestedPrice()){
         //       emailService.sendInfo(currentDetails.getTitle(), currentDetails.getCurrentPriceDto(),request.getRequestedPrice());
               requestService.setNotActive(request.getId());
            }

        }

    }


}
