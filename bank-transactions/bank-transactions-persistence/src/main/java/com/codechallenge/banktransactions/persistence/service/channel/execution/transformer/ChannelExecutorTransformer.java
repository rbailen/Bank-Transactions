package com.codechallenge.banktransactions.persistence.service.channel.execution.transformer;

import com.codechallenge.banktransactions.persistence.model.TransactionMO;
import com.codechallenge.banktransactions.persistence.service.channel.execution.dto.input.TransactionStatusExecutionIDTO;
import com.codechallenge.banktransactions.persistence.service.channel.execution.dto.output.TransactionStatusExecutionODTO;
import com.codechallenge.banktransactions.persistence.service.dto.output.TransactionStatusCriteriaODTO;

/**
 * The Interface ChannelExecutorTransformer.
 */
public interface ChannelExecutorTransformer {

    /**
     * To transaction status strategy IDTO.
     *
     * @param transactionMO the transaction MO
     * @return the transaction status execution IDTO
     */
    TransactionStatusExecutionIDTO toTransactionStatusStrategyIDTO(TransactionMO transactionMO);

    /**
     * To transaction status criteria ODTO.
     *
     * @param TransactionStatusStrategyODTO the transaction status strategy ODTO
     * @return the transaction status criteria ODTO
     */
    TransactionStatusCriteriaODTO toTransactionStatusCriteriaODTO(TransactionStatusExecutionODTO TransactionStatusStrategyODTO);

}
