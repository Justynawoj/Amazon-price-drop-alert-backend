package com.amazon_price_drop_alert.services;

import com.amazon_price_drop_alert.exceptions.AsinNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AsinRetrieverTest {

    @Autowired
    AsinRetriever asinRetriever;

    @Test
    void shouldConvertUrlToAsin() throws AsinNotFoundException {


        //Given
        String amazonUrl = "https://www.amazon.it/Clean-Code-Handbook-Software-Craftsmanship/dp/0132350882/ref=sr_1_1?__mk_it_IT=%C3%85M%C3%85%C5%BD%C3%95%C3%91&dchild=1&keywords=clean+code&qid=1614520467&sr=8-1";

        //When
        String asinRetrieved = asinRetriever.convertUrlToAsin(amazonUrl);

        //Then
        assertEquals("0132350882", asinRetrieved);
        assertNotEquals("0000",asinRetrieved);
    }

    @Test
    void shouldThrowException() throws AsinNotFoundException {
        //Given
        String amazonUrl = "https://www.amazon.it/";

        //When & Then
        assertThrows(AsinNotFoundException.class, () -> {
            asinRetriever.convertUrlToAsin(amazonUrl);
        });
    }

}
