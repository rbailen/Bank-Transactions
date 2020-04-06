package com.codechallenge.banktransactions.persistence.service;

import java.util.List;

import com.codechallenge.banktransactions.persistence.service.dto.input.TransactionCriteriaIDTO;
import com.codechallenge.banktransactions.persistence.service.dto.input.TransactionPersistenceIDTO;
import com.codechallenge.banktransactions.persistence.service.dto.input.TransactionStatusCriteriaIDTO;
import com.codechallenge.banktransactions.persistence.service.dto.output.TransactionCriteriaODTO;
import com.codechallenge.banktransactions.persistence.service.dto.output.TransactionStatusCriteriaODTO;

/**
 * The Interface TransactionPersistenceService.
 */
public interface TransactionPersistenceService {

    /**
     * Find transactions by account iban.
     *
     * @param transactionCriteria the transaction criteria
     * @return the list
     */
    List<TransactionCriteriaODTO> findTransactionsByAccountIban(TransactionCriteriaIDTO transactionCriteria);

    /**
     * Creates the transaction.
     *
     * @param transactionPersistenceIDTO the transaction persistence IDTO
     */
    void createTransaction(TransactionPersistenceIDTO transactionPersistenceIDTO);

    /**
     * Gets the transaction status.
     *
     * @param transactionStatusCriteriaIDTO the transaction status criteria IDTO
     * @return the transaction status
     */
    TransactionStatusCriteriaODTO getTransactionStatus(TransactionStatusCriteriaIDTO transactionStatusCriteriaIDTO);

}
