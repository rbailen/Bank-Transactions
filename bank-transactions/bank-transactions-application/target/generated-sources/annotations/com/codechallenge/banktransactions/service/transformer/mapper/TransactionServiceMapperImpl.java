package com.codechallenge.banktransactions.service.transformer.mapper;

import com.codechallenge.banktransactions.persistence.service.dto.input.TransactionPersistenceIDTO;
import com.codechallenge.banktransactions.persistence.service.dto.input.TransactionPersistenceIDTO.TransactionPersistenceIDTOBuilder;
import com.codechallenge.banktransactions.persistence.service.dto.input.TransactionStatusCriteriaIDTO;
import com.codechallenge.banktransactions.persistence.service.dto.input.TransactionStatusCriteriaIDTO.TransactionStatusCriteriaIDTOBuilder;
import com.codechallenge.banktransactions.persistence.service.dto.output.TransactionCriteriaODTO;
import com.codechallenge.banktransactions.persistence.service.dto.output.TransactionStatusCriteriaODTO;
import com.codechallenge.banktransactions.service.dto.input.TransactionIDTO;
import com.codechallenge.banktransactions.service.dto.input.TransactionStatusIDTO;
import com.codechallenge.banktransactions.service.dto.output.TransactionODTO;
import com.codechallenge.banktransactions.service.dto.output.TransactionStatusODTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class TransactionServiceMapperImpl implements TransactionServiceMapper {

    @Override
    public TransactionPersistenceIDTO toTransactionPersistenceIDTO(TransactionIDTO transactionIDTO) {
        if ( transactionIDTO == null ) {
            return null;
        }

        TransactionPersistenceIDTOBuilder transactionPersistenceIDTO = TransactionPersistenceIDTO.builder();

        transactionPersistenceIDTO.reference( transactionIDTO.getReference() );
        transactionPersistenceIDTO.accountIban( transactionIDTO.getAccountIban() );
        transactionPersistenceIDTO.date( transactionIDTO.getDate() );
        transactionPersistenceIDTO.amount( transactionIDTO.getAmount() );
        transactionPersistenceIDTO.fee( transactionIDTO.getFee() );
        transactionPersistenceIDTO.description( transactionIDTO.getDescription() );

        return transactionPersistenceIDTO.build();
    }

    @Override
    public TransactionODTO toTransactionODTO(TransactionCriteriaODTO transactionCriteriaODTO) {
        if ( transactionCriteriaODTO == null ) {
            return null;
        }

        TransactionODTO transactionODTO = new TransactionODTO();

        transactionODTO.setReference( transactionCriteriaODTO.getReference() );
        transactionODTO.setAccountIban( transactionCriteriaODTO.getAccountIban() );
        transactionODTO.setDate( transactionCriteriaODTO.getDate() );
        transactionODTO.setAmount( transactionCriteriaODTO.getAmount() );
        transactionODTO.setFee( transactionCriteriaODTO.getFee() );
        transactionODTO.setDescription( transactionCriteriaODTO.getDescription() );

        return transactionODTO;
    }

    @Override
    public TransactionStatusCriteriaIDTO toTransactionStatusCriteriaIDTO(TransactionStatusIDTO transactionStatusIDTO) {
        if ( transactionStatusIDTO == null ) {
            return null;
        }

        TransactionStatusCriteriaIDTOBuilder transactionStatusCriteriaIDTO = TransactionStatusCriteriaIDTO.builder();

        transactionStatusCriteriaIDTO.reference( transactionStatusIDTO.getReference() );
        transactionStatusCriteriaIDTO.channel( transactionStatusIDTO.getChannel() );

        return transactionStatusCriteriaIDTO.build();
    }

    @Override
    public TransactionStatusODTO toTransactionStatusODTO(TransactionStatusCriteriaODTO transactionStatusCriteriaODTO) {
        if ( transactionStatusCriteriaODTO == null ) {
            return null;
        }

        TransactionStatusODTO transactionStatusODTO = new TransactionStatusODTO();

        transactionStatusODTO.setReference( transactionStatusCriteriaODTO.getReference() );
        transactionStatusODTO.setStatus( transactionStatusCriteriaODTO.getStatus() );
        transactionStatusODTO.setAmount( transactionStatusCriteriaODTO.getAmount() );
        transactionStatusODTO.setFee( transactionStatusCriteriaODTO.getFee() );

        return transactionStatusODTO;
    }
}
