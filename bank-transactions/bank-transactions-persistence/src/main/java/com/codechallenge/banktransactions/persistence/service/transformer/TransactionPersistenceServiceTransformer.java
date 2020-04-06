package com.codechallenge.banktransactions.persistence.service.transformer;

import com.codechallenge.banktransactions.persistence.model.TransactionMO;
import com.codechallenge.banktransactions.persistence.service.dto.input.TransactionPersistenceIDTO;
import com.codechallenge.banktransactions.persistence.service.dto.output.TransactionCriteriaODTO;

/**
 * The Interface TransactionPersistenceServiceTransformer.
 */
public interface TransactionPersistenceServiceTransformer {

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
