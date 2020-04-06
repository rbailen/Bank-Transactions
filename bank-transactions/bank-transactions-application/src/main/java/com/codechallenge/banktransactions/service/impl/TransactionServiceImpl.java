package com.codechallenge.banktransactions.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codechallenge.banktransactions.persistence.service.TransactionPersistenceService;
import com.codechallenge.banktransactions.persistence.service.dto.input.TransactionCriteriaIDTO;
import com.codechallenge.banktransactions.persistence.service.dto.input.TransactionPersistenceIDTO;
import com.codechallenge.banktransactions.persistence.service.dto.input.TransactionStatusCriteriaIDTO;
import com.codechallenge.banktransactions.persistence.service.dto.output.TransactionCriteriaODTO;
import com.codechallenge.banktransactions.persistence.service.dto.output.TransactionStatusCriteriaODTO;
import com.codechallenge.banktransactions.service.TransactionService;
import com.codechallenge.banktransactions.service.dto.input.TransactionIDTO;
import com.codechallenge.banktransactions.service.dto.input.TransactionStatusIDTO;
import com.codechallenge.banktransactions.service.dto.output.TransactionODTO;
import com.codechallenge.banktransactions.service.dto.output.TransactionStatusODTO;
import com.codechallenge.banktransactions.service.transformer.TransactionServiceTransformer;

/**
 * The Class TransactionServiceImpl.
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    /** The transformer. */
    @Autowired
    private TransactionServiceTransformer transformer;

    /** The service. */
    @Autowired
    private TransactionPersistenceService service;

    /**
     * Creates the transaction.
     *
     * @param transactionIDTO the transaction IDTO
     */
    @Override
    public void createTransaction(TransactionIDTO transactionIDTO) {

        TransactionPersistenceIDTO transactionPersistenceIDTO = transformer.toTransactionPersistenceIDTO(transactionIDTO);

        service.createTransaction(transactionPersistenceIDTO);

    }

    /**
     * Find transactions by account iban.
     *
     * @param transactionIDTO the transaction IDTO
     * @return the list
     */
    @Override
    public List<TransactionODTO> findTransactionsByAccountIban(TransactionIDTO transactionIDTO) {
        TransactionCriteriaIDTO transactionCriteria = transformer.toTransactionCriteriaIDTO(transactionIDTO);

        List<TransactionCriteriaODTO> transactionCriteriaODTOList = service.findTransactionsByAccountIban(transactionCriteria);

        return transactionCriteriaODTOList.stream().map(transactionCriteriaODTO -> transformer.toTransactionODTO(transactionCriteriaODTO))
                .collect(Collectors.toList());
    }

    /**
     * Gets the transaction status.
     *
     * @param transactionStatusIDTO the transaction status IDTO
     * @return the transaction status
     */
    @Override
    public TransactionStatusODTO getTransactionStatus(TransactionStatusIDTO transactionStatusIDTO) {

        TransactionStatusCriteriaIDTO transactionStatusCriteriaIDTO = transformer.toTransactionStatusCriteriaIDTO(transactionStatusIDTO);

        TransactionStatusCriteriaODTO transactionStatusCriteriaODTO = service.getTransactionStatus(transactionStatusCriteriaIDTO);

        return transformer.toTransactionStatusODTO(transactionStatusCriteriaODTO);
    }

}
