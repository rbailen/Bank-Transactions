package com.codechallenge.banktransactions.controller.transformer.mapper;

import com.codechallenge.banktransactions.controller.dto.request.TransactionRQDTO;
import com.codechallenge.banktransactions.controller.dto.request.TransactionStatusRQDTO;
import com.codechallenge.banktransactions.controller.dto.response.TransactionRSDTO;
import com.codechallenge.banktransactions.controller.dto.response.TransactionStatusRSDTO;
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
public class TransactionControllerMapperImpl implements TransactionControllerMapper {

    @Override
    public TransactionIDTO toTransactionIDTO(TransactionRQDTO transactionRQDTO) {
        if ( transactionRQDTO == null ) {
            return null;
        }

        TransactionIDTO transactionIDTO = new TransactionIDTO();

        transactionIDTO.setReference( transactionRQDTO.getReference() );
        transactionIDTO.setAccountIban( transactionRQDTO.getAccountIban() );
        transactionIDTO.setDate( mapDate( transactionRQDTO.getDate() ) );
        transactionIDTO.setAmount( transactionRQDTO.getAmount() );
        transactionIDTO.setFee( transactionRQDTO.getFee() );
        transactionIDTO.setDescription( transactionRQDTO.getDescription() );

        return transactionIDTO;
    }

    @Override
    public TransactionRSDTO toTransactionRSDTO(TransactionODTO transactionODTO) {
        if ( transactionODTO == null ) {
            return null;
        }

        TransactionRSDTO transactionRSDTO = new TransactionRSDTO();

        transactionRSDTO.setReference( transactionODTO.getReference() );
        transactionRSDTO.setAccountIban( transactionODTO.getAccountIban() );
        transactionRSDTO.setDate( mapDate( transactionODTO.getDate() ) );
        transactionRSDTO.setAmount( transactionODTO.getAmount() );
        transactionRSDTO.setFee( transactionODTO.getFee() );
        transactionRSDTO.setDescription( transactionODTO.getDescription() );

        return transactionRSDTO;
    }

    @Override
    public TransactionStatusRSDTO toTransactionStatusRSDTO(TransactionStatusODTO transactionODTO) {
        if ( transactionODTO == null ) {
            return null;
        }

        TransactionStatusRSDTO transactionStatusRSDTO = new TransactionStatusRSDTO();

        transactionStatusRSDTO.setReference( transactionODTO.getReference() );
        transactionStatusRSDTO.setStatus( transactionODTO.getStatus() );
        transactionStatusRSDTO.setAmount( transactionODTO.getAmount() );
        transactionStatusRSDTO.setFee( transactionODTO.getFee() );

        return transactionStatusRSDTO;
    }

    @Override
    public TransactionStatusIDTO toTransactionStatusIDTO(TransactionStatusRQDTO transactionStatusRQDTO) {
        if ( transactionStatusRQDTO == null ) {
            return null;
        }

        TransactionStatusIDTO transactionStatusIDTO = new TransactionStatusIDTO();

        transactionStatusIDTO.setReference( transactionStatusRQDTO.getReference() );
        transactionStatusIDTO.setChannel( transactionStatusRQDTO.getChannel() );

        return transactionStatusIDTO;
    }
}
