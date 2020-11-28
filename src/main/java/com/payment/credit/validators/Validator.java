package com.payment.credit.validators;

/**
 * @Author Santhosh Jackson
 **/
public interface Validator<T> {

    boolean isValid(T objToValidate);
}
