package com.payment.credit.validators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author Santhosh Jackson
 **/

@Component
public final class LuhnTenValidator implements Validator<List<Integer>> {
    Logger LOGGER = LoggerFactory.getLogger(LuhnTenValidator.class);

    /**
     * @param digits
     * @return
     */
    public boolean isValid(List<Integer> digits) {

        // Step 1 : Doubling the value of all the numbers with a even index and it they are greater than 9 , We are subtracting  9 from them
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