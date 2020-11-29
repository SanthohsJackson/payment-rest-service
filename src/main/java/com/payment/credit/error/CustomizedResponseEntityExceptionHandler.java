package com.payment.credit.error;


import com.payment.credit.validators.LuhnTenValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Date;

/**
 * Default error handler for the application
 *
 * @author Santhosh Jackson
 */
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    Logger LOGGER = LoggerFactory.getLogger(CustomizedResponseEntityExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<ErrorDetails> handleUserNotFoundException(IllegalArgumentException ex,
                                                                          WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        LOGGER.error(errorDetails.getMessage());
        LOGGER.debug("",ex);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(new Date(),
                ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).reduce("", String::concat),
                request.getDescription(false));
        LOGGER.error(errorDetails.getMessage());
        LOGGER.debug("",ex);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
                request.getDescription(false));
        LOGGER.error(errorDetails.getMessage());
        LOGGER.debug("",ex);
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(new Date(),
                ex.getAllErrors().stream().map(ObjectError::getDefaultMessage).reduce("", String::concat)
                , request.getDescription(false));
        LOGGER.error(errorDetails.getMessage());
        LOGGER.debug("",ex);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


}
