package com.payment.credit.repo;


import com.payment.credit.data.CreditCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

/**
 * @Author Santhosh Jackson
 **/


@DataJpaTest
public class CreditCardRepoTests {

    @Autowired
    private CreditCardRepo creditCardRepo;

    @Test
    public void checkfinalAll() {
        List<CreditCard> cards = creditCardRepo.findAll();
        Assertions.assertNotNull(cards);
        Assertions.assertEquals(1, cards.size());
    }


    @Test
    public void checkSave() {
        CreditCard savedCard = creditCardRepo.save(getCreditCard());

        Assertions.assertNotNull(savedCard);
        Assertions.assertNotNull(savedCard.getId());

        Assertions.assertEquals("4014405120749392", savedCard.getCardNumber());
        Assertions.assertEquals("TestName", savedCard.getUserName());

    }


    private CreditCard getCreditCard() {
        CreditCard card = new CreditCard();
        card.setLimit(100l);
        card.setBalance(0l);
        card.setCardNumber("4014405120749392");
        card.setUserName("TestName");
        return card;
    }


}
