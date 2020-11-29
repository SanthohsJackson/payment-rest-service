package com.payment.credit.validators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This class validates if the given card number is Luhn10 compatible.
 *
 * @Author Santhosh Jackson
 **/

@Component
public final class LuhnTenValidator implements Validator<List<Integer>> {
    Logger LOGGER = LoggerFactory.getLogger(LuhnTenValidator.class);

    /**
     * Checks if the given digits are Luhn10 compatible.
     *
     * @param digits
     * @return
     */
    public boolean isValid(List<Integer> digits) {

        // Step 1 : Doubling the value of all the numbers with a even index in a descending order
        // and it they are greater than 9 , we are subtracting  9 from them.
        LOGGER.debug("Number of digits in the card are {}", digits.size());
        for (int i = digits.size() - 2; i >= 0; i = i - 2) {
            int num = digits.get(i) * 2;
            if (num > 9) {
                num = num - 9;  // step 2
            }
            digits.set(i, num);
        }

        // Step 2 : Adding all the values of the list
        int sumOfAllNumbers = digits.stream().reduce(0, Integer::sum);
        LOGGER.debug("Sum of all the credit card numbers is : {}", sumOfAllNumbers);

        // Step 3 : If its divisible by 10 then return true else false
        return sumOfAllNumbers % 10 == 0;
    }

}
