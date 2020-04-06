package com.codechallenge.banktransactions.persistence.service.dto.input;

import org.springframework.data.domain.Sort.Direction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionCriteriaIDTO {

    /** The account iban. */
    private String accountIban;

    /** The sort. */
    private Direction sort;

    /** The order by. */
    private String orderBy;

}
