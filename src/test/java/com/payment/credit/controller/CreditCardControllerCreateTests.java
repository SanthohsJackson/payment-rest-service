package com.payment.credit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.payment.credit.data.CreditCard;
import com.payment.credit.data.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Author Santhosh Jackson
 **/
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CreditCardControllerCreateTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void checkCreate() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mvc.perform(post("/credit/cards/")
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(getCreditCard())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.cardNumber", is("4014405120749392")))
                .andExpect(jsonPath("$.user.firstName", is("TestName")));

    }

    @Test
    public void checkNullFirstNameOnCreate() throws Exception {
        CreditCard card = getCreditCard();
        card.getUser().setFirstName(null);

        ObjectMapper mapper = new ObjectMapper();
        mvc.perform(post("/credit/cards/")
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(card)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("First name can not be null")));
    }

    @Test
    public void checkNullCardNumberOnCreate() throws Exception {
        CreditCard card = getCreditCard();
        card.setCardNumber(null);

        ObjectMapper mapper = new ObjectMapper();
        mvc.perform(post("/credit/cards/")
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(card)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Card number can not be null")));
    }

    private CreditCard getCreditCard() {
        CreditCard card = new CreditCard();
        card.setLimit(100l);
        card.setBalance(0l);
        card.setCardNumber("4014405120749392");
        User user = new User();
        user.setFirstName("TestName");
        user.setLastName("TestLastName");
        card.setUser(user);
        return card;
    }

}
