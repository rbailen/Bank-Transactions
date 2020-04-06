package com.codechallenge.banktransactions.service.transformer.mapper;

import org.mapstruct.Mapper;

import com.codechallenge.banktransactions.persistence.service.dto.input.TransactionPersistenceIDTO;
import com.codechallenge.banktransactions.persistence.service.dto.input.TransactionStatusCriteriaIDTO;
import com.codechallenge.banktransactions.persistence.service.dto.output.TransactionCriteriaODTO;
import com.codechallenge.banktransactions.persistence.service.dto.output.TransactionStatusCriteriaODTO;
import com.codechallenge.banktransactions.service.dto.input.TransactionIDTO;
import com.codechallenge.banktransactions.service.dto.input.TransactionStatusIDTO;
import com.codechallenge.banktransactions.service.dto.output.TransactionODTO;
import com.codechallenge.banktransactions.service.dto.output.TransactionStatusODTO;

/**
 * The Interface TransactionServiceMapper.
 */
@Mapper
public interface TransactionServiceMapper {

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
