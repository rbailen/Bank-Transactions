package com.codechallenge.banktransactions.service.transformer;

import com.codechallenge.banktransactions.persistence.service.dto.input.TransactionCriteriaIDTO;
import com.codechallenge.banktransactions.persistence.service.dto.input.TransactionPersistenceIDTO;
import com.codechallenge.banktransactions.persistence.service.dto.input.TransactionStatusCriteriaIDTO;
import com.codechallenge.banktransactions.persistence.service.dto.output.TransactionCriteriaODTO;
import com.codechallenge.banktransactions.persistence.service.dto.output.TransactionStatusCriteriaODTO;
import com.codechallenge.banktransactions.service.dto.input.TransactionIDTO;
import com.codechallenge.banktransactions.service.dto.input.TransactionStatusIDTO;
import com.codechallenge.banktransactions.service.dto.output.TransactionODTO;
import com.codechallenge.banktransactions.service.dto.output.TransactionStatusODTO;

/**
 * The Interface TransactionServiceTransformer.
 */
public interface TransactionServiceTransformer {

    /**
     * To transaction criteria IDTO.
     *
     * @param transactionIDTO the transaction IDTO
     * @return the transaction criteria IDTO
     */
    TransactionCriteriaIDTO toTransactionCriteriaIDTO(TransactionIDTO transactionIDTO);

    /**
     * To transaction persistence IDTO.
     *
     * @param transactionIDTO the transaction IDTO
     * @return the transaction persistence IDTO
     */
    TransactionPersistenceIDTO toTransactionPersistenceIDTO(TransactionIDTO transactionIDTO);

    /**
     * To transaction ODTO.
     *
     * @param transactionCriteriaODTO the transaction criteria ODTO
     * @return the transaction ODTO
     */
    TransactionODTO toTransactionODTO(TransactionCriteriaODTO transactionCriteriaODTO);

    /**
     * To transaction status criteria IDTO.
     *
     * @param transactionStatusIDTO the transaction status IDTO
     * @return the transaction status criteria IDTO
     */
    TransactionStatusCriteriaIDTO toTransactionStatusCriteriaIDTO(TransactionStatusIDTO transactionStatusIDTO);

    /**
     * To transaction status ODTO.
     *
     * @param transactionStatusCriteriaODTO the transaction status criteria ODTO
     * @return the transaction status ODTO
     */
    TransactionStatusODTO toTransactionStatusODTO(TransactionStatusCriteriaODTO transactionStatusCriteriaODTO);

}
