package com.codechallenge.banktransactions.persistence.service.transformer.mapper;

import org.mapstruct.Mapper;

import com.codechallenge.banktransactions.persistence.model.TransactionMO;
import com.codechallenge.banktransactions.persistence.service.dto.input.TransactionPersistenceIDTO;
import com.codechallenge.banktransactions.persistence.service.dto.output.TransactionCriteriaODTO;

/**
 * The Interface TransactionPersistenceServiceMapper.
 */
@Mapper
public interface TransactionPersistenceServiceMapper {

    /**
     * To transaction MO.
     *
     * @param transactionPersistenceIDTO the transaction persistence IDTO
     * @return the transaction MO
     */
    TransactionMO toTransactionMO(TransactionPersistenceIDTO transactionPersistenceIDTO);

    /**
     * To transaction criteria ODTO.
     *
     * @param transactionMO the transaction MO
     * @return the transaction criteria ODTO
     */
    TransactionCriteriaODTO toTransactionCriteriaODTO(TransactionMO transactionMO);

}
