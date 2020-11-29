package com.payment.credit.service;


import com.payment.credit.data.CreditCard;
import com.payment.credit.repo.CreditCardRepo;
import com.payment.credit.validators.LuhnTenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Author Santhosh Jackson
 **/
@Service
public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
    private CreditCardRepo creditCardRepo;

    @Autowired
    private LuhnTenValidator validator;

    public CreditCardServiceImpl() {
    }

    /**
     * Creates a credit card along with the user associated with it.
     *
     * @param creditCard
     * @return
     */
    @Override
    public CreditCard createCard(CreditCard creditCard) {

        if (!isValidCreditCard(creditCard.getCardNumber())) {
            throw new IllegalArgumentException("Card could not be saved as it was not a valid card number");
        } else if (isCardPresent(creditCard.getCardNumber())) {
            throw new IllegalArgumentException("Card number already exists");
        } else if (!(creditCard.getLimit() > 0)) {
            throw new IllegalArgumentException("Limit can not be negative");
        }

        creditCard.setBalance(0);
        return creditCardRepo.save(creditCard);
    }

    /**
     * Gets all the credit cards available.
     *
     * @return
     */
    @Override
    public List<CreditCard> getAllCards() {
        return creditCardRepo.findAll();
    }


    public boolean isCardPresent(String creditCardNumber) {
        return creditCardRepo.findFirstByCardNumber(creditCardNumber) != null;
    }


    /**
     * Checks if the credit card number is valid.Checks if the credit card follows the Luhn 10 algorithm.
     *
     * @param creditCardNumber
     * @return
     */
    private boolean isValidCreditCard(String creditCardNumber) {

        //TODO: check for lower bound
        if (creditCardNumber == null || creditCardNumber.isEmpty()) {
            throw new IllegalArgumentException("Credit card number is null or empty");
        } else if (creditCardNumber.length() > 19) {
            throw new IllegalArgumentException("Credit card number is more than 19 digits");
        } else if (!containsOnlyNumbers(creditCardNumber)) {
            throw new IllegalArgumentException("Credit card given contains non numeric character");
        }

        // convert String to list of integers
        List<Integer> digits = creditCardNumber.chars().mapToObj(Character::getNumericValue).collect(Collectors.toList());

        return validator.isValid(digits);
    }

    /**
     * @param creditCard
     * @return
     */
    private boolean containsOnlyNumbers(String creditCard) {
        Pattern pattern = Pattern.compile("^[0-9]\\d*$");
        Matcher matcher = pattern.matcher(creditCard);
        return matcher.matches();
    }


}
