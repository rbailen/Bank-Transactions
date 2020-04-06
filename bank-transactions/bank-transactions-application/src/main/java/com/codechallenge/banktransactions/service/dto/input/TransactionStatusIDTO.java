package com.codechallenge.banktransactions.service.dto.input;

import com.codechallenge.banktransactions.persistence.type.ChannelType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionStatusIDTO {

    /** The reference. */
    private String reference;

    /** The channel. */
    private ChannelType channel;

}
