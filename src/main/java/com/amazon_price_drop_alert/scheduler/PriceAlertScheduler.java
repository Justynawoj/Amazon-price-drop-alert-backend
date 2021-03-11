package com.amazon_price_drop_alert.scheduler;

import com.amazon_price_drop_alert.controllers.AmazonPriceApiController;
import com.amazon_price_drop_alert.domains.Mail;
import com.amazon_price_drop_alert.dtos.ProductDetailsDto;
import com.amazon_price_drop_alert.domains.Request;
import com.amazon_price_drop_alert.dtos.jsonResponse.PriceDetailsDto;
import com.amazon_price_drop_alert.exceptions.AsinNotFoundException;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(PriceAlertScheduler.class);

  //  @Scheduled(cron = "0 0 10 * * *")
    @Scheduled(fixedDelay = 10000)
    public void sendAlert() throws AsinNotFoundException {
        LOGGER.info("Starting Scheduler");
        List<Request> allRequests = requestRepository.findAll();
        for (Request request : allRequests
        ) {
            if (request.isActive()) {
                LOGGER.info("Calling api for request " + request.getId() + " id");
           //     ProductDetailsDto currentDetails = priceApiController.getResponse(request.getUrl(), request.getCountry());

                /*
                    Stubbed data to be deleted once implementation is over
                 */

                ProductDetailsDto currentDetails = new ProductDetailsDto("asin",
                        "date",
                        "$",
                        "titile1111titile2222titile3333titile4444titile5555titile6666titile77777titile8888",
                        20.00,
                        new PriceDetailsDto("highest date",40.00),
                        new PriceDetailsDto("lowest Date",10.00),
                        20.00,
                        new PriceDetailsDto("highest date",40.00),
                        new PriceDetailsDto("lowest date",20.00));

                /*
                    End of stubbed data
                 */

                if (currentDetails.getCurrentPriceAmazon() <= request.getRequestedPrice() &&
                        currentDetails.getCurrentPriceAmazon() > 0 ||
                        currentDetails.getCurrentPriceThirdPart() <= request.getRequestedPrice() &&
                                currentDetails.getCurrentPriceThirdPart() > 0) {
                    String subject = generateSubject(currentDetails);
                    emailService.send(new Mail(request.getEmail(), subject, request, currentDetails));
                    requestService.setNotActive(request.getId());
                }
            }
        }
    }

    private String generateSubject(ProductDetailsDto productDetailsDto) {
        return "Price drop alert for " + productDetailsDto.getTitle().substring(0,50)+"...";
    }
}
