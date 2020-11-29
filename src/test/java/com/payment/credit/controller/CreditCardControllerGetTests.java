package com.payment.credit.controller;

import com.payment.credit.data.CreditCard;
import com.payment.credit.data.User;
import com.payment.credit.service.CreditCardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Author Santhosh Jackson
 **/
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CreditCardControllerGetTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CreditCardService creditCardService;

    @Test
    public void checkGetAll() throws Exception {
        Mockito.when(creditCardService.getAllCards()).thenReturn(getCreditCard());

        mvc.perform(get("/credit/cards/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].cardNumber", is("4014405120749392")));
    }


    private List<CreditCard> getCreditCard() {

        List<CreditCard> cards = new ArrayList<>();

        CreditCard card = new CreditCard();
        card.setLimit(100l);
        card.setBalance(0l);
        card.setCardNumber("4014405120749392");
        User user = new User();
        user.setFirstName("TestName");
        user.setLastName("TestLastName");
        card.setUser(user);
        cards.add(card);

        card = new CreditCard();
        card.setLimit(200l);
        card.setBalance(0l);
        card.setCardNumber("6506104911035323305");
        user = new User();
        user.setFirstName("TestNameTwo");
        user.setLastName("TestLastNameTwo");
        card.setUser(user);
        cards.add(card);


        return cards;
    }


}
