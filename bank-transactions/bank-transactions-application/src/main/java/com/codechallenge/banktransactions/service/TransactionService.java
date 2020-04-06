package com.codechallenge.banktransactions.service;

import java.util.List;

import com.codechallenge.banktransactions.service.dto.input.TransactionIDTO;
import com.codechallenge.banktransactions.service.dto.input.TransactionStatusIDTO;
import com.codechallenge.banktransactions.service.dto.output.TransactionODTO;
import com.codechallenge.banktransactions.service.dto.output.TransactionStatusODTO;

/**
 * The Interface TransactionService.
 */
public interface TransactionService {

    /**
     * Find transactions by account iban.
     *
     * @param transactionIDTO the transaction IDTO
     * @return the list
     */
    List<TransactionODTO> findTransactionsByAccountIban(TransactionIDTO transactionIDTO);

    /**
     * Creates the transaction.
     *
     * @param transactionIDTO the transaction IDTO
     */
    void createTransaction(TransactionIDTO transactionIDTO);

    /**
     * Gets the transaction status.
     *
     * @param transactionStatusIDTO the transaction status IDTO
     * @return the transaction status
     */
    TransactionStatusODTO getTransactionStatus(TransactionStatusIDTO transactionStatusIDTO);

}
