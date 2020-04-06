package com.codechallenge.banktransactions.persistence.service.transformer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codechallenge.banktransactions.persistence.model.TransactionMO;
import com.codechallenge.banktransactions.persistence.service.dto.input.TransactionPersistenceIDTO;
import com.codechallenge.banktransactions.persistence.service.dto.output.TransactionCriteriaODTO;
import com.codechallenge.banktransactions.persistence.service.transformer.TransactionPersistenceServiceTransformer;
import com.codechallenge.banktransactions.persistence.service.transformer.mapper.TransactionPersistenceServiceMapper;

/**
 * The Class TransactionPersistenceServiceTransformerImpl.
 */
@Component
public class TransactionPersistenceServiceTransformerImpl implements TransactionPersistenceServiceTransformer {

    /** The mapper. */
    @Autowired
    private TransactionPersistenceServiceMapper mapper;

    /**
     * To transaction MO.
     *
     * @param transactionPersistenceIDTO the transaction persistence IDTO
     * @return the transaction MO
     */
    @Override
    public TransactionMO toTransactionMO(TransactionPersistenceIDTO transactionPersistenceIDTO) {

        return mapper.toTransactionMO(transactionPersistenceIDTO);

    }

    /**
     * To transaction criteria ODTO.
     *
     * @param transactionMO the transaction MO
     * @return the transaction criteria ODTO
     */
    @Override
    public TransactionCriteriaODTO toTransactionCriteriaODTO(TransactionMO transactionMO) {

        return mapper.toTransactionCriteriaODTO(transactionMO);

    }

}
