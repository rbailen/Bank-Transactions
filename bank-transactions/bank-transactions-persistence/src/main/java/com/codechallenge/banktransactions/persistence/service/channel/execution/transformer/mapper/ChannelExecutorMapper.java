package com.codechallenge.banktransactions.persistence.service.channel.execution.transformer.mapper;

import org.mapstruct.Mapper;

import com.codechallenge.banktransactions.persistence.service.channel.execution.dto.output.TransactionStatusExecutionODTO;
import com.codechallenge.banktransactions.persistence.service.dto.output.TransactionStatusCriteriaODTO;

/**
 * The Interface ChannelExecutorMapper.
 */
@Mapper
public interface ChannelExecutorMapper {

    /**
     * To transaction status criteria ODTO.
     *
     * @param TransactionStatusStrategyODTO the transaction status strategy ODTO
     * @return the transaction status criteria ODTO
     */
    TransactionStatusCriteriaODTO toTransactionStatusCriteriaODTO(TransactionStatusExecutionODTO TransactionStatusStrategyODTO);

}
