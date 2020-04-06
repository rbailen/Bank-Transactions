package com.codechallenge.banktransactions.persistence.service.channel.execution.transformer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codechallenge.banktransactions.persistence.model.TransactionMO;
import com.codechallenge.banktransactions.persistence.service.channel.execution.dto.input.TransactionStatusExecutionIDTO;
import com.codechallenge.banktransactions.persistence.service.channel.execution.dto.output.TransactionStatusExecutionODTO;
import com.codechallenge.banktransactions.persistence.service.channel.execution.transformer.ChannelExecutorTransformer;
import com.codechallenge.banktransactions.persistence.service.channel.execution.transformer.mapper.ChannelExecutorMapper;
import com.codechallenge.banktransactions.persistence.service.dto.output.TransactionStatusCriteriaODTO;

/**
 * The Class ChannelExecutorTransformerImpl.
 */
@Component
public class ChannelExecutorTransformerImpl implements ChannelExecutorTransformer {

    /** The mapper. */
    @Autowired
    private ChannelExecutorMapper mapper;

    /**
     * To transaction status strategy IDTO.
     *
     * @param transactionMO the transaction MO
     * @return the transaction status execution IDTO
     */
    @Override
    public TransactionStatusExecutionIDTO toTransactionStatusStrategyIDTO(TransactionMO transactionMO) {

        return TransactionStatusExecutionIDTO.builder().reference(transactionMO.getReference()).amount(transactionMO.getAmount())
                .fee(transactionMO.getFee()).date(transactionMO.getDate()).build();

    }

    /**
     * To transaction status criteria ODTO.
     *
     * @param TransactionStatusStrategyODTO the transaction status strategy ODTO
     * @return the transaction status criteria ODTO
     */
    @Override
    public TransactionStatusCriteriaODTO toTransactionStatusCriteriaODTO(TransactionStatusExecutionODTO TransactionStatusStrategyODTO) {

        return mapper.toTransactionStatusCriteriaODTO(TransactionStatusStrategyODTO);

    }

}
