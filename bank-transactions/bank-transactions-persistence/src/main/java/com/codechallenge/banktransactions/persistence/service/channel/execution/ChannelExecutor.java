package com.codechallenge.banktransactions.persistence.service.channel.execution;

import com.codechallenge.banktransactions.persistence.service.channel.execution.dto.output.TransactionStatusExecutionODTO;
import com.codechallenge.banktransactions.persistence.service.dto.input.TransactionStatusCriteriaIDTO;

/**
 * The Interface ChannelExecutor.
 */
public interface ChannelExecutor {

    /**
     * Execute.
     *
     * @param transactionStatusCriteriaIDTO the transaction status criteria IDTO
     * @return the transaction status execution ODTO
     */
    TransactionStatusExecutionODTO execute(TransactionStatusCriteriaIDTO transactionStatusCriteriaIDTO);

}
