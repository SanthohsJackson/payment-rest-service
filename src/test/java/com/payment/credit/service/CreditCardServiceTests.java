package com.payment.credit.service;


import com.payment.credit.data.CreditCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @Author Santhosh Jackson
 **/
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CreditCardServiceTests {

    @Autowired
    private CreditCardServiceImpl service;


    @Test
    public void checkLengthConditions() {
        CreditCard card = new CreditCard();
        card.setLimit(100l);
        card.setBalance(0l);
        card.setUserName("TestName");

        //check when empty
        card.setCardNumber("");
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.createCard(card), "Empty String check failed");

        // check when greater 19
        card.setCardNumber("498530854972874985308549728754");
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.createCard(card), "Length greater than 19 check failed");

    }


    @Test
    public void checkNonNumericCardNumber(){
        CreditCard card = new CreditCard();
        card.setLimit(100l);
        card.setBalance(0l);
        card.setUserName("TestName");

        //check when non numeric
        card.setCardNumber("-+234234WER34@#$");
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.createCard(card), "Non numeric check failed");
    }


    @Test
    public void checkSameCardNumber() {
        CreditCard card = new CreditCard();
        card.setLimit(100l);
        card.setBalance(0l);
        card.setCardNumber("5181372761753188");
        card.setUserName("TestName");
        service.createCard(card);



        card.setUserName("TestNameTwo");

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.createCard(card), "Duplicated card check failed");
    }

    @Test
    public void checkNegativeLimit() {
        CreditCard card = new CreditCard();
        card.setLimit(-100l);
        card.setBalance(0l);
        card.setCardNumber("5181372761753188");
        card.setUserName("TestName");


        Assertions.assertThrows(IllegalArgumentException.class, () -> service.createCard(card), "Duplicated card check failed");
    }


}
