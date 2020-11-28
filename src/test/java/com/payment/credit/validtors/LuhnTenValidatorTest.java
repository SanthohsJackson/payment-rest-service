package com.payment.credit.validtors;


import com.payment.credit.validators.LuhnTenValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;


@SpringBootTest
public class LuhnTenValidatorTest {

    private static LuhnTenValidator validator;

    @BeforeAll
    private static void init() {
        validator = new LuhnTenValidator();
    }


    @Test
    public void simpleValidCheck() {
        String creditCardNumber = "4985308549728754";
        List<Integer> digits = creditCardNumber.chars().mapToObj(Character::getNumericValue).collect(Collectors.toList());
        Assertions.assertTrue(validator.isValid(digits));
    }


}
