package com.codechallenge.banktransactions.persistence.service.dto.output;

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
public class TransactionCriteriaODTO {

    /** The reference. */
    private String reference;

    /** The account iban. */
    private String accountIban;

    /** The date. */
    private OffsetDateTime date;

    /** The amount. */
    private BigDecimal amount;

    /** The fee. */
    private BigDecimal fee;

    /** The description. */
    private String description;

}
