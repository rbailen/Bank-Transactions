package com.codechallenge.banktransactions.controller.transformer;

import org.springframework.data.domain.Sort.Direction;

import com.codechallenge.banktransactions.controller.dto.request.TransactionRQDTO;
import com.codechallenge.banktransactions.controller.dto.request.TransactionStatusRQDTO;
import com.codechallenge.banktransactions.controller.dto.response.TransactionRSDTO;
import com.codechallenge.banktransactions.controller.dto.response.TransactionStatusRSDTO;
import com.codechallenge.banktransactions.service.dto.input.TransactionIDTO;
import com.codechallenge.banktransactions.service.dto.input.TransactionStatusIDTO;
import com.codechallenge.banktransactions.service.dto.output.TransactionODTO;
import com.codechallenge.banktransactions.service.dto.output.TransactionStatusODTO;

/**
 * The Interface TransactionControllerTransformer.
 */
public interface TransactionControllerTransformer {

    /**
     * To transaction IDTO.
     *
     * @param transactionRQDTO the transaction RQDTO
     * @return the transaction IDTO
     */
    TransactionIDTO toTransactionIDTO(TransactionRQDTO transactionRQDTO);

    /**
     * To transaction RSDTO.
     *
     * @param transactionODTO the transaction ODTO
     * @return the transaction RSDTO
     */
    TransactionRSDTO toTransactionRSDTO(TransactionODTO transactionODTO);

    /**
     * To transaction IDTO.
     *
     * @param accountIban the account iban
     * @param sort        the sort
     * @param orderBy     the order by
     * @return the transaction IDTO
     */
    TransactionIDTO toTransactionIDTO(String accountIban, Direction sort, String orderBy);

    /**
     * To transaction status IDTO.
     *
     * @param transactionStatusRQDTO the transaction status RQDTO
     * @return the transaction status IDTO
     */
    TransactionStatusIDTO toTransactionStatusIDTO(TransactionStatusRQDTO transactionStatusRQDTO);

    /**
     * To transaction status RSDTO.
     *
     * @param transactionODTO the transaction ODTO
     * @return the transaction status RSDTO
     */
    TransactionStatusRSDTO toTransactionStatusRSDTO(TransactionStatusODTO transactionODTO);

}
