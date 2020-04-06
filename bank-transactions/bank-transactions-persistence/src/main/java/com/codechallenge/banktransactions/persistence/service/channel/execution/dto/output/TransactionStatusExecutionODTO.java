package com.codechallenge.banktransactions.persistence.service.channel.execution.dto.output;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionStatusExecutionODTO {

    /** The reference. */
    private String reference;

    /** The status. */
    private String status;

    /** The amount. */
    private BigDecimal amount;

    /** The fee. */
    private BigDecimal fee;

}
