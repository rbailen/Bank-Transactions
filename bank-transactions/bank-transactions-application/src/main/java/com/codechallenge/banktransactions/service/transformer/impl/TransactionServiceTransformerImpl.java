package com.codechallenge.banktransactions.service.transformer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codechallenge.banktransactions.persistence.service.dto.input.TransactionCriteriaIDTO;
import com.codechallenge.banktransactions.persistence.service.dto.input.TransactionPersistenceIDTO;
import com.codechallenge.banktransactions.persistence.service.dto.input.TransactionStatusCriteriaIDTO;
import com.codechallenge.banktransactions.persistence.service.dto.output.TransactionCriteriaODTO;
import com.codechallenge.banktransactions.persistence.service.dto.output.TransactionStatusCriteriaODTO;
import com.codechallenge.banktransactions.service.dto.input.TransactionIDTO;
import com.codechallenge.banktransactions.service.dto.input.TransactionStatusIDTO;
import com.codechallenge.banktransactions.service.dto.output.TransactionODTO;
import com.codechallenge.banktransactions.service.dto.output.TransactionStatusODTO;
import com.codechallenge.banktransactions.service.transformer.TransactionServiceTransformer;
import com.codechallenge.banktransactions.service.transformer.mapper.TransactionServiceMapper;

/**
 * The Class TransactionServiceTransformerImpl.
 */
@Component
public class TransactionServiceTransformerImpl implements TransactionServiceTransformer {

    /** The mapper. */
    @Autowired
    private TransactionServiceMapper mapper;

    /**
     * To transaction persistence IDTO.
     *
     * @param transactionIDTO the transaction IDTO
     * @return the transaction persistence IDTO
     */
    @Override
    public TransactionPersistenceIDTO toTransactionPersistenceIDTO(TransactionIDTO transactionIDTO) {

        return mapper.toTransactionPersistenceIDTO(transactionIDTO);

    }

    /**
     * To transaction criteria IDTO.
     *
     * @param transactionIDTO the transaction IDTO
     * @return the transaction criteria IDTO
     */
    @Override
    public TransactionCriteriaIDTO toTransactionCriteriaIDTO(TransactionIDTO transactionIDTO) {

        return TransactionCriteriaIDTO.builder().accountIban(transactionIDTO.getAccountIban()).sort(transactionIDTO.getSort())
                .orderBy(transactionIDTO.getOrderBy()).build();

    }

    /**
     * To transaction ODTO.
     *
     * @param transactionCriteriaODTO the transaction criteria ODTO
     * @return the transaction ODTO
     */
    @Override
    public TransactionODTO toTransactionODTO(TransactionCriteriaODTO transactionCriteriaODTO) {

        return mapper.toTransactionODTO(transactionCriteriaODTO);

    }

    /**
     * To transaction status criteria IDTO.
     *
     * @param transactionStatusIDTO the transaction status IDTO
     * @return the transaction status criteria IDTO
     */
    @Override
    public TransactionStatusCriteriaIDTO toTransactionStatusCriteriaIDTO(TransactionStatusIDTO transactionStatusIDTO) {

        return mapper.toTransactionStatusCriteriaIDTO(transactionStatusIDTO);

    }

    /**
     * To transaction status ODTO.
     *
     * @param transactionStatusCriteriaODTO the transaction status criteria ODTO
     * @return the transaction status ODTO
     */
    @Override
    public TransactionStatusODTO toTransactionStatusODTO(TransactionStatusCriteriaODTO transactionStatusCriteriaODTO) {

        return mapper.toTransactionStatusODTO(transactionStatusCriteriaODTO);

    }

}
