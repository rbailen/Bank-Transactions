package com.codechallenge.banktransactions.controller.dto.request;

import javax.validation.constraints.NotBlank;

import org.springframework.lang.Nullable;

import com.codechallenge.banktransactions.persistence.type.ChannelType;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionStatusRQDTO {

    /** The reference. */
    @ApiModelProperty(notes = "The transaction reference number", example = "12345A")
    @NotBlank(message = "Reference is mandatory")
    private String reference;

    /** The channel. */
    @ApiModelProperty(notes = "The type of the channel that is asking for the status", example = "CLIENT")
    @Nullable
    private ChannelType channel;

}
