package com.codechallenge.banktransactions.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.codechallenge.banktransactions.persistence.service.TransactionPersistenceService;
import com.codechallenge.banktransactions.persistence.service.dto.input.TransactionCriteriaIDTO;
import com.codechallenge.banktransactions.persistence.service.dto.input.TransactionPersistenceIDTO;
import com.codechallenge.banktransactions.persistence.service.dto.input.TransactionStatusCriteriaIDTO;
import com.codechallenge.banktransactions.persistence.service.dto.output.TransactionCriteriaODTO;
import com.codechallenge.banktransactions.persistence.service.dto.output.TransactionStatusCriteriaODTO;
import com.codechallenge.banktransactions.service.dto.input.TransactionIDTO;
import com.codechallenge.banktransactions.service.dto.input.TransactionStatusIDTO;
import com.codechallenge.banktransactions.service.dto.output.TransactionODTO;
import com.codechallenge.banktransactions.service.dto.output.TransactionStatusODTO;
import com.codechallenge.banktransactions.service.impl.TransactionServiceImpl;
import com.codechallenge.banktransactions.service.transformer.TransactionServiceTransformer;

@SpringBootTest
public class TransactionServiceTest {

    @InjectMocks
    TransactionServiceImpl service;

    @Mock
    TransactionServiceTransformer transformer;

    @Mock
    TransactionPersistenceService persistenceService;

    @Test
    public void createTransaction() {

        TransactionIDTO transactionIDTOMock = Mockito.mock(TransactionIDTO.class);
        TransactionPersistenceIDTO transactionPersistenceIDTOMock = Mockito.mock(TransactionPersistenceIDTO.class);

        when(transformer.toTransactionPersistenceIDTO(transactionIDTOMock)).thenReturn(transactionPersistenceIDTOMock);
        doNothing().when(persistenceService).createTransaction(transactionPersistenceIDTOMock);

        service.createTransaction(transactionIDTOMock);

        verify(transformer, times(1)).toTransactionPersistenceIDTO(transactionIDTOMock);
        verify(persistenceService, times(1)).createTransaction(transactionPersistenceIDTOMock);

    }

    @Test
    public void findTransactionsByAccountIban() {

        TransactionIDTO transactionIDTOMock = Mockito.mock(TransactionIDTO.class);
        TransactionCriteriaIDTO transactionCriteriaIDTOMock = Mockito.mock(TransactionCriteriaIDTO.class);
        TransactionCriteriaODTO transactionCriteriaODTOMock = Mockito.mock(TransactionCriteriaODTO.class);
        List<TransactionCriteriaODTO> transactionCriteriaODTOMockList = Arrays.asList(transactionCriteriaODTOMock);
        TransactionODTO transactionODTOMock = Mockito.mock(TransactionODTO.class);
        List<TransactionODTO> transactionODTOMockList = Arrays.asList(transactionODTOMock);

        when(transformer.toTransactionCriteriaIDTO(transactionIDTOMock)).thenReturn(transactionCriteriaIDTOMock);
        when(persistenceService.findTransactionsByAccountIban(transactionCriteriaIDTOMock)).thenReturn(transactionCriteriaODTOMockList);
        when(transformer.toTransactionODTO(transactionCriteriaODTOMock)).thenReturn(transactionODTOMock);

        List<TransactionODTO> response = service.findTransactionsByAccountIban(transactionIDTOMock);

        assertEquals(response, transactionODTOMockList);

        verify(transformer, times(1)).toTransactionCriteriaIDTO(transactionIDTOMock);
        verify(persistenceService, times(1)).findTransactionsByAccountIban(transactionCriteriaIDTOMock);
        verify(transformer, times(1)).toTransactionODTO(transactionCriteriaODTOMock);

    }

    @Test
    public void getTransactionStatus() {

        TransactionStatusIDTO transactionStatusIDTOMock = Mockito.mock(TransactionStatusIDTO.class);
        TransactionStatusCriteriaIDTO transactionStatusCriteriaIDTOMock = Mockito.mock(TransactionStatusCriteriaIDTO.class);
        TransactionStatusCriteriaODTO transactionStatusCriteriaODTOMock = Mockito.mock(TransactionStatusCriteriaODTO.class);
        TransactionStatusODTO transactionStatusODTOMock = Mockito.mock(TransactionStatusODTO.class);

        when(transformer.toTransactionStatusCriteriaIDTO(transactionStatusIDTOMock)).thenReturn(transactionStatusCriteriaIDTOMock);
        when(persistenceService.getTransactionStatus(transactionStatusCriteriaIDTOMock)).thenReturn(transactionStatusCriteriaODTOMock);
        when(transformer.toTransactionStatusODTO(transactionStatusCriteriaODTOMock)).thenReturn(transactionStatusODTOMock);

        TransactionStatusODTO response = service.getTransactionStatus(transactionStatusIDTOMock);

        assertEquals(response, transactionStatusODTOMock);

        verify(transformer, times(1)).toTransactionStatusCriteriaIDTO(transactionStatusIDTOMock);
        verify(persistenceService, times(1)).getTransactionStatus(transactionStatusCriteriaIDTOMock);
        verify(transformer, times(1)).toTransactionStatusODTO(transactionStatusCriteriaODTOMock);

    }
}
