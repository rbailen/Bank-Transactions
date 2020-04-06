package com.codechallenge.banktransactions.persistence.service.channel.execution.transformer.mapper;

import com.codechallenge.banktransactions.persistence.service.channel.execution.dto.output.TransactionStatusExecutionODTO;
import com.codechallenge.banktransactions.persistence.service.dto.output.TransactionStatusCriteriaODTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class ChannelExecutorMapperImpl implements ChannelExecutorMapper {

    @Override
    public TransactionStatusCriteriaODTO toTransactionStatusCriteriaODTO(TransactionStatusExecutionODTO TransactionStatusStrategyODTO) {
        if ( TransactionStatusStrategyODTO == null ) {
            return null;
        }

        TransactionStatusCriteriaODTO transactionStatusCriteriaODTO = new TransactionStatusCriteriaODTO();

        transactionStatusCriteriaODTO.setReference( TransactionStatusStrategyODTO.getReference() );
        transactionStatusCriteriaODTO.setStatus( TransactionStatusStrategyODTO.getStatus() );
        transactionStatusCriteriaODTO.setAmount( TransactionStatusStrategyODTO.getAmount() );
        transactionStatusCriteriaODTO.setFee( TransactionStatusStrategyODTO.getFee() );

        return transactionStatusCriteriaODTO;
    }
}
