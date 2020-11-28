package com.payment.credit.repo;


import com.payment.credit.data.CreditCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Santhosh Jackson
 **/
@Repository
public interface CreditCardRepo extends CrudRepository<CreditCard, Long> {

    List<CreditCard> findAll();

    CreditCard findFirstByCardNumber(String crediCardNumber);
}
