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
@RequestMapping("/cards")
public class CreditCardController {

    @Autowired
    private CreditCardService creditCardService;


    @GetMapping("/")
    public List<CreditCard> getAllCards() {
        return creditCardService.getAllCards();
    }


    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard createCard(@Valid @RequestBody CreditCard creditCard) {
        return creditCardService.createCard(creditCard);
    }

}
