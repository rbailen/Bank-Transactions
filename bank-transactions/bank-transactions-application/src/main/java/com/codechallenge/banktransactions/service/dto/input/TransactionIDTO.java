package com.codechallenge.banktransactions.service.dto.input;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.springframework.data.domain.Sort.Direction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionIDTO {

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

    /** The sort. */
    private Direction sort;

    /** The order by. */
    private String orderBy;

}
