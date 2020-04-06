package com.codechallenge.banktransactions.controller.transformer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.codechallenge.banktransactions.controller.dto.request.TransactionRQDTO;
import com.codechallenge.banktransactions.controller.dto.request.TransactionStatusRQDTO;
import com.codechallenge.banktransactions.controller.dto.response.TransactionRSDTO;
import com.codechallenge.banktransactions.controller.dto.response.TransactionStatusRSDTO;
import com.codechallenge.banktransactions.controller.transformer.TransactionControllerTransformer;
import com.codechallenge.banktransactions.controller.transformer.mapper.TransactionControllerMapper;
import com.codechallenge.banktransactions.service.dto.input.TransactionIDTO;
import com.codechallenge.banktransactions.service.dto.input.TransactionStatusIDTO;
import com.codechallenge.banktransactions.service.dto.output.TransactionODTO;
import com.codechallenge.banktransactions.service.dto.output.TransactionStatusODTO;

/**
 * The Class TransactionControllerTransformerImpl.
 */
@Component
public class TransactionControllerTransformerImpl implements TransactionControllerTransformer {

    /** The mapper. */
    @Autowired
    private TransactionControllerMapper mapper;

    /**
     * To transaction IDTO.
     *
     * @param transactionRQDTO the transaction RQDTO
     * @return the transaction IDTO
     */
    @Override
    public TransactionIDTO toTransactionIDTO(TransactionRQDTO transactionRQDTO) {

        return mapper.toTransactionIDTO(transactionRQDTO);

    }

    /**
     * To transaction RSDTO.
     *
     * @param transactionODTO the transaction ODTO
     * @return the transaction RSDTO
     */
    @Override
    public TransactionRSDTO toTransactionRSDTO(TransactionODTO transactionODTO) {

        return mapper.toTransactionRSDTO(transactionODTO);
    }

    /**
     * To transaction IDTO.
     *
     * @param accountIban the account iban
     * @param sort        the sort
     * @param orderBy     the order by
     * @return the transaction IDTO
     */
    @Override
    public TransactionIDTO toTransactionIDTO(String accountIban, Direction sort, String orderBy) {

        return TransactionIDTO.builder().accountIban(accountIban).sort(sort).orderBy(orderBy).build();

    }

    /**
     * To transaction status IDTO.
     *
     * @param transactionStatusRQDTO the transaction status RQDTO
     * @return the transaction status IDTO
     */
    @Override
    public TransactionStatusIDTO toTransactionStatusIDTO(TransactionStatusRQDTO transactionStatusRQDTO) {

        return mapper.toTransactionStatusIDTO(transactionStatusRQDTO);

    }

    /**
     * To transaction status RSDTO.
     *
     * @param transactionODTO the transaction ODTO
     * @return the transaction status RSDTO
     */
    @Override
    public TransactionStatusRSDTO toTransactionStatusRSDTO(TransactionStatusODTO transactionODTO) {

        return mapper.toTransactionStatusRSDTO(transactionODTO);

    }

}
