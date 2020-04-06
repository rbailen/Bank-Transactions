package com.codechallenge.banktransactions.persistence.service.transformer.mapper;

import com.codechallenge.banktransactions.persistence.model.TransactionMO;
import com.codechallenge.banktransactions.persistence.service.dto.input.TransactionPersistenceIDTO;
import com.codechallenge.banktransactions.persistence.service.dto.output.TransactionCriteriaODTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class TransactionPersistenceServiceMapperImpl implements TransactionPersistenceServiceMapper {

    @Override
    public TransactionMO toTransactionMO(TransactionPersistenceIDTO transactionPersistenceIDTO) {
        if ( transactionPersistenceIDTO == null ) {
            return null;
        }

        TransactionMO transactionMO = new TransactionMO();

        transactionMO.setReference( transactionPersistenceIDTO.getReference() );
        transactionMO.setAccountIban( transactionPersistenceIDTO.getAccountIban() );
        transactionMO.setDate( transactionPersistenceIDTO.getDate() );
        transactionMO.setAmount( transactionPersistenceIDTO.getAmount() );
        transactionMO.setFee( transactionPersistenceIDTO.getFee() );
        transactionMO.setDescription( transactionPersistenceIDTO.getDescription() );

        return transactionMO;
    }

    @Override
    public TransactionCriteriaODTO toTransactionCriteriaODTO(TransactionMO transactionMO) {
        if ( transactionMO == null ) {
            return null;
        }

        TransactionCriteriaODTO transactionCriteriaODTO = new TransactionCriteriaODTO();

        transactionCriteriaODTO.setReference( transactionMO.getReference() );
        transactionCriteriaODTO.setAccountIban( transactionMO.getAccountIban() );
        transactionCriteriaODTO.setDate( transactionMO.getDate() );
        transactionCriteriaODTO.setAmount( transactionMO.getAmount() );
        transactionCriteriaODTO.setFee( transactionMO.getFee() );
        transactionCriteriaODTO.setDescription( transactionMO.getDescription() );

        return transactionCriteriaODTO;
    }
}
