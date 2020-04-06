package com.codechallenge.banktransactions.persistence.service.dto.input;

import com.codechallenge.banktransactions.persistence.type.ChannelType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionStatusCriteriaIDTO {

    /** The reference. */
    private String reference;

    /** The channel. */
    private ChannelType channel;

}
