package com.codechallenge.banktransactions.persistence.service.channel.strategy;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Component;

import com.codechallenge.banktransactions.persistence.service.channel.execution.AbstractExecutorImpl;
import com.codechallenge.banktransactions.persistence.service.channel.execution.dto.input.TransactionStatusExecutionIDTO;
import com.codechallenge.banktransactions.persistence.service.channel.execution.dto.output.TransactionStatusExecutionODTO;
import com.codechallenge.banktransactions.persistence.type.StatusType;

/**
 * The Class ClientStrategyImpl.
 */
@Component
public class ClientStrategyImpl extends AbstractExecutorImpl {

    /**
     * Gets the transaction status.
     *
     * @param transactionStatusStrategyIDTO the transaction status strategy IDTO
     * @return the transaction status
     */
    @Override
    public TransactionStatusExecutionODTO getTransactionStatus(TransactionStatusExecutionIDTO transactionStatusStrategyIDTO) {

        TransactionStatusExecutionODTO transactionStatusExecutionODTO = TransactionStatusExecutionODTO.builder()
                .reference(transactionStatusStrategyIDTO.getReference())
                .amount(transactionStatusStrategyIDTO.getAmount().subtract(transactionStatusStrategyIDTO.getFee())).build();

        if (transactionStatusStrategyIDTO.getDate().isBefore(OffsetDateTime.now())) {
            transactionStatusExecutionODTO.setStatus(StatusType.SETTLED.getCode());
        } else if (transactionStatusStrategyIDTO.getDate().isAfter(OffsetDateTime.now())) {
            transactionStatusExecutionODTO.setStatus(StatusType.FUTURE.getCode());
        }

        return transactionStatusExecutionODTO;

    }

}
