package com.codechallenge.banktransactions.persistence.service.channel.execution.dto.input;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionStatusExecutionIDTO {

    /** The reference. */
    private String reference;

    /** The amount. */
    private BigDecimal amount;

    /** The fee. */
    private BigDecimal fee;

    /** The date. */
    private OffsetDateTime date;

}
