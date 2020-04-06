package com.codechallenge.banktransactions.persistence.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;;

/**
 * The Class TransactionNotFoundException.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class TransactionNotFoundException extends RuntimeException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new transaction not found exception.
     *
     * @param message the message
     */
    public TransactionNotFoundException(String message) {
        super(message);
    }

}
