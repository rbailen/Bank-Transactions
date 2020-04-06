package com.codechallenge.banktransactions.persistence.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Transaction")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionMO {

    /** The reference. */
    @Id
    @Column(name = "REFERENCE", nullable = false)
    private String reference;

    /** The account iban. */
    @Column(name = "ACCOUNT_IBAN", nullable = false)
    private String accountIban;

    /** The date. */
    @Column(name = "DATE", nullable = false)
    private OffsetDateTime date;

    /** The amount. */
    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;

    /** The fee. */
    @Column(name = "FEE", nullable = true)
    private BigDecimal fee;

    /** The description. */
    @Column(name = "DESCRIPTION", nullable = true)
    private String description;

    /** The total account balance. */
    @Column(name = "ACCOUNT_BALANCE", nullable = false)
    private BigDecimal accountBalance;

}
