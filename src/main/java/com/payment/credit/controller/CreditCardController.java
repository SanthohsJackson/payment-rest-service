package com.payment.credit.controller;


import com.payment.credit.data.CreditCard;
import com.payment.credit.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author Santhosh Jackson
 **/
@RestController
@RequestMapping("/credit/cards")
public class CreditCardController {

    @Autowired
    private CreditCardService creditCardService;


    /**
     * Returns all the credit cards available
     *
     * @return List<CreditCard>
     */
    @GetMapping("/")
    public List<CreditCard> getAllCards() {
        return creditCardService.getAllCards();
    }


    /**
     * Saves a credit card information along with its user.
     *
     * @param creditCard
     * @return CreditCard
     */
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard createCard(@Valid @RequestBody CreditCard creditCard) {
        return creditCardService.createCard(creditCard);
    }

}
