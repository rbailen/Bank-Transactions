package com.codechallenge.banktransactions.persistence.type;

import lombok.Getter;

/**
 * The Enum StatusType.
 */
public enum StatusType {

    /** The invalid. */
    INVALID("INVALID"),
    /** The settled. */
    SETTLED("SETTLED"),
    /** The pending. */
    PENDING("PENDING"),
    /** The future. */
    FUTURE("FUTURE");

    /**
     * Gets the code.
     *
     * @return the code
     */
    @Getter
    private String code;

    /**
     * Instantiates a new status type.
     *
     * @param code the code
     */
    private StatusType(String code) {

        this.code = code;
    }

}
