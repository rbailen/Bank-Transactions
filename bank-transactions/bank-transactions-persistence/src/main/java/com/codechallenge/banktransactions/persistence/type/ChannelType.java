package com.codechallenge.banktransactions.persistence.type;

import lombok.Getter;

/**
 * The Enum ChannelType.
 */
public enum ChannelType {

    /** The client. */
    CLIENT("CLIENT"),
    /** The atm. */
    ATM("ATM"),
    /** The internal. */
    INTERNAL("INTERNAL");

    /**
     * Gets the code.
     *
     * @return the code
     */

    /**
     * Gets the code.
     *
     * @return the code
     */
    @Getter
    private String code;

    /**
     * Instantiates a new channel type.
     *
     * @param code the code
     */
    private ChannelType(String code) {

        this.code = code;
    }

}
