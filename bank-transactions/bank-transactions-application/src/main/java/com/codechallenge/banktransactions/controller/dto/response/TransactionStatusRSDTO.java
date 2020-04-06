package com.codechallenge.banktransactions.controller.dto.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
public class TransactionStatusRSDTO {

    /** The reference. */
    @ApiModelProperty(notes = "The transaction reference number", example = "12345A")
    private String reference;

    /** The status. */
    @ApiModelProperty(notes = "The status of the transaction", example = "PENDING")
    private String status;

    /** The amount. */
    @ApiModelProperty(notes = "The amount of the transaction", example = "193.38")
    private BigDecimal amount;

    /** The fee. */
    @ApiModelProperty(notes = "The fee applied to the transaction", example = "3.18")
    private BigDecimal fee;

}
