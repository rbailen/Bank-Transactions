package com.codechallenge.banktransactions.controller.dto.request;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRQDTO {

    /** The reference. */
    @ApiModelProperty(notes = "The transaction unique reference number in our system", example = "12345A")
    @Nullable
    private String reference;

    /** The account iban. */
    @ApiModelProperty(notes = "The IBAN number of the account where the transaction has happened", example = "ES9820385778983000760236")
    @JsonProperty("account_iban")
    @NotBlank(message = "Account Iban is mandatory")
    private String accountIban;

    /** The date. */
    @ApiModelProperty(notes = "Date when the transaction took place", example = "2019-07-16T16:55:42.000Z")
    @Nullable
    private OffsetDateTime date;

    /** The amount. */
    @ApiModelProperty(notes = "Add or deduct money from the account", example = "193.38")
    @NotNull(message = "Amount is mandatory")
    private BigDecimal amount;

    /** The fee. */
    @ApiModelProperty(notes = "Fee that will be deducted from the amount", example = "3.18")
    @Nullable
    private BigDecimal fee;

    /** The description. */
    @ApiModelProperty(notes = "The description of the transaction", example = "Restaurant payment")
    @Nullable
    private String description;

}
