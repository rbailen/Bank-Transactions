package com.codechallenge.banktransactions.controller.dto.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
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
@JsonInclude(Include.NON_NULL)
public class TransactionRSDTO {

    /** The reference. */
    @ApiModelProperty(notes = "The transaction reference number", example = "12345A")
    private String reference;

    /** The account iban. */
    @ApiModelProperty(notes = "The IBAN number of the account where the transaction has happened", example = "ES9820385778983000760236")
    @JsonProperty("account_iban")
    private String accountIban;

    /** The date. */
    @ApiModelProperty(notes = "Date when the transaction took place", example = "2019-07-16T16:55:42.000Z")
    private OffsetDateTime date;

    /** The amount. */
    @ApiModelProperty(notes = "The amount of the transaction", example = "193.38")
    private BigDecimal amount;

    /** The fee. */
    @ApiModelProperty(notes = "The fee applied to the transaction", example = "3.18")
    private BigDecimal fee;

    /** The description. */
    @ApiModelProperty(notes = "The description of the transaction", example = "Restaurant payment")
    private String description;

}
