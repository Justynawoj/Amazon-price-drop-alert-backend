package com.amazon_price_drop_alert.scheduler;

import com.amazon_price_drop_alert.controllers.AmazonPriceApiController;
import com.amazon_price_drop_alert.domains.Mail;
import com.amazon_price_drop_alert.dtos.ProductDetailsDto;
import com.amazon_price_drop_alert.domains.Request;
import com.amazon_price_drop_alert.repositories.RequestRepository;
import com.amazon_price_drop_alert.services.EmailService;
import com.amazon_price_drop_alert.services.RequestService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger LOGGER = LoggerFactory.getLogger(PriceAlertScheduler.class);

    @Scheduled(cron = "0 0 10 * * *")
    public void sendAlert() {
        LOGGER.info("Starting Scheduler");
        List<Request> allRequests = requestRepository.findAll();
        for (Request request : allRequests
        ) {
            if (request.isActive()) {
                LOGGER.info("Calling api for request " + request.getId() + " id");
                ProductDetailsDto currentDetails = priceApiController.getResponse(request.getUrl(), request.getCountry());
                if (currentDetails.getCurrentPriceAmazon()/ 100 <= request.getRequestedPrice() &&
                        currentDetails.getCurrentPriceAmazon() > 0 ||
                        currentDetails.getCurrentPriceThirdPart() / 100 <= request.getRequestedPrice() &&
                                currentDetails.getCurrentPriceThirdPart() > 0) {
                    String subject = generateSubject(currentDetails);
                    String message = generateMessage(request, currentDetails);
                    emailService.send(new Mail(request.getEmail(), "justynabuonanno@gmail.com", subject, message));
                    requestService.setNotActive(request.getId());
                }
            }
        }
    }

    private String generateMessage(Request request, ProductDetailsDto productDetailsDto) {

        String message = "Good news!\nThe price of \n" + productDetailsDto.getTitle()
                + "\nhas dropped down! \nCurrent Amazon price is: " + productDetailsDto.getCurrentPriceAmazon()
                + "and current Amazon third part price is: " + productDetailsDto.getCurrentPriceThirdPart()
                + "\nDon't miss it. Go to " + request.getUrl();

        return message;
    }

    private String generateSubject(ProductDetailsDto productDetailsDto) {
        return "Price drop alert for " + productDetailsDto.getTitle();
    }
}
