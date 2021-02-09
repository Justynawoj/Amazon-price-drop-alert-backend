package com.amazon_price_drop_alert.scheduler;

import com.amazon_price_drop_alert.controllers.AmazonPriceApiController;
import com.amazon_price_drop_alert.domains.Mail;
import com.amazon_price_drop_alert.domains.ProductDetailsDto;
import com.amazon_price_drop_alert.domains.Request;
import com.amazon_price_drop_alert.repositories.RequestRepository;
import com.amazon_price_drop_alert.services.EmailService;
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
    private final EmailService emailService;

     @Scheduled(fixedDelay = 10000)
  //  @Scheduled(cron = "0 0 10 * * *")
  //  @Scheduled(cron = "1 1 1 1 1 1")
    public void sendAlert() {
         System.out.println("starting scheduler");
        List<Request> allRequests = requestRepository.findAll();
        for (Request request : allRequests
        ) {
            if(request.isActive()) {
                System.out.println("calling api for"+request.getUrl());
                ProductDetailsDto currentDetails = priceApiController.getResponse(request.getUrl(), request.getCountry());
                if (currentDetails.getCurrentPriceAmazon().getPrice() <= request.getRequestedPrice() &&
                        currentDetails.getCurrentPriceAmazon().getPrice() > 0 ||
                        currentDetails.getCurrentPriceThirdPart().getPrice() <= request.getRequestedPrice() &&
                        currentDetails.getCurrentPriceThirdPart().getPrice() > 0 )
                {
                    String subject = generateSubject(currentDetails);
                    String message = generateMessage(request, currentDetails);
                    emailService.send(new Mail(request.getEmail(), "justynabuonanno@gmail.com", subject, message));
                    System.out.println("setting request not active");
                    requestService.setNotActive(request.getId());
                    System.out.println("is request active"+request.isActive());
                }
            }
        }
    }

    private String generateMessage(Request request, ProductDetailsDto productDetailsDto) {

        String message = "Good news!\nThe price of \n" + productDetailsDto.getTitle()
                + "\nhas dropped down! \nCurrent Amazon price is: "+ productDetailsDto.getCurrentPriceAmazon().getPrice()
                + "and current Amazon third part price is: "+ productDetailsDto.getCurrentPriceThirdPart().getPrice()
                + "\nDon't miss it. Go to "+request.getUrl();

        return message;
    }
    private String generateSubject(ProductDetailsDto productDetailsDto){
        return "Price drop alert for " + productDetailsDto.getTitle();
    }
}
