package com.codechallenge.banktransactions.persistence.service.channel.execution;

import org.springframework.beans.factory.annotation.Autowired;

import com.codechallenge.banktransactions.persistence.model.TransactionMO;
import com.codechallenge.banktransactions.persistence.repository.TransactionRepository;
import com.codechallenge.banktransactions.persistence.service.channel.execution.dto.input.TransactionStatusExecutionIDTO;
import com.codechallenge.banktransactions.persistence.service.channel.execution.dto.output.TransactionStatusExecutionODTO;
import com.codechallenge.banktransactions.persistence.service.channel.execution.transformer.ChannelExecutorTransformer;
import com.codechallenge.banktransactions.persistence.service.dto.input.TransactionStatusCriteriaIDTO;
import com.codechallenge.banktransactions.persistence.type.StatusType;

/**
 * The Class AbstractExecutorImpl.
 */
public abstract class AbstractExecutorImpl implements ChannelExecutor {

    /** The repository. */
    @Autowired
    private TransactionRepository repository;

    /** The transformer. */
    @Autowired
    private ChannelExecutorTransformer transformer;

    /**
     * Execute.
     *
     * @param transactionStatusCriteriaIDTO the transaction status criteria IDTO
     * @return the transaction status execution ODTO
     */
    @Override
    public TransactionStatusExecutionODTO execute(TransactionStatusCriteriaIDTO transactionStatusCriteriaIDTO) {
        TransactionMO transactionMO = repository.findById(transactionStatusCriteriaIDTO.getReference()).orElse(null);

        if (null == transactionMO) {
            return TransactionStatusExecutionODTO.builder().reference(transactionStatusCriteriaIDTO.getReference())
                    .status(StatusType.INVALID.getCode()).build();
        }

        TransactionStatusExecutionIDTO transactionStatusStrategyIDTO = transformer.toTransactionStatusStrategyIDTO(transactionMO);
        return getTransactionStatus(transactionStatusStrategyIDTO);
    }

    /**
     * Gets the transaction status.
     *
     * @param transactionStatusStrategyIDTO the transaction status strategy IDTO
     * @return the transaction status
     */
    public abstract TransactionStatusExecutionODTO getTransactionStatus(TransactionStatusExecutionIDTO transactionStatusStrategyIDTO);

}
