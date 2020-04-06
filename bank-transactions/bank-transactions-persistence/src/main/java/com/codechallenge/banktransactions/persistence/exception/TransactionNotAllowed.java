package com.codechallenge.banktransactions.persistence.exception;

/**
 * The Class TransactionNotAllowed.
 */
public class TransactionNotAllowed extends RuntimeException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new transaction not allowed.
     *
     * @param message the message
     */
    public TransactionNotAllowed(String message) {
        super(message);
    }

}
