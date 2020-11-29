package com.payment.credit.service;



import com.payment.credit.data.CreditCard;

import java.util.List;

/**
 *
 *  Services for {@link CreditCard} functionality
 *
 * @Author Santhosh Jackson
 **/
public interface CreditCardService {

   List<CreditCard> getAllCards();

   CreditCard createCard(CreditCard creditCard);
}
