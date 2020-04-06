package com.codechallenge.banktransactions.persistence.exception;

/**
 * The Class TransactionDuplicatedException.
 */
public class TransactionDuplicatedException extends RuntimeException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new transaction duplicated exception.
     *
     * @param message the message
     */
    public TransactionDuplicatedException(String message) {
        super(message);
    }

}
