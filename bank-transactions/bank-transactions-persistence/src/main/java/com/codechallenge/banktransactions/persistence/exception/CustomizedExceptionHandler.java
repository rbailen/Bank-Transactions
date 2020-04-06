package com.codechallenge.banktransactions.persistence.exception;

import java.util.Date;

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

/**
 * The Class CustomizedExceptionHandler.
 */
@ControllerAdvice
@RestController
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle all exceptions.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     * @throws Exception the exception
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle user not found exception.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     * @throws Exception the exception
     */
    @ExceptionHandler(TransactionNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(TransactionNotFoundException ex, WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle method argument not valid.
     *
     * @param ex      the ex
     * @param headers the headers
     * @param status  the status
     * @param request the request
     * @return the response entity
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
            WebRequest request) {

        String errorMessage = ex.getBindingResult().getAllErrors().stream().findFirst().map(ObjectError::getDefaultMessage).orElse(null);

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Failed", errorMessage);

        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
