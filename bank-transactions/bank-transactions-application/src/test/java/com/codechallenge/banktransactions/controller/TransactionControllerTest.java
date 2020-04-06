package com.codechallenge.banktransactions.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort.Direction;

import com.codechallenge.banktransactions.controller.dto.request.TransactionRQDTO;
import com.codechallenge.banktransactions.controller.dto.request.TransactionStatusRQDTO;
import com.codechallenge.banktransactions.controller.dto.response.TransactionRSDTO;
import com.codechallenge.banktransactions.controller.dto.response.TransactionStatusRSDTO;
import com.codechallenge.banktransactions.controller.transformer.TransactionControllerTransformer;
import com.codechallenge.banktransactions.persistence.exception.TransactionNotAllowed;
import com.codechallenge.banktransactions.service.TransactionService;
import com.codechallenge.banktransactions.service.dto.input.TransactionIDTO;
import com.codechallenge.banktransactions.service.dto.input.TransactionStatusIDTO;
import com.codechallenge.banktransactions.service.dto.output.TransactionODTO;
import com.codechallenge.banktransactions.service.dto.output.TransactionStatusODTO;

@SpringBootTest
public class TransactionControllerTest {

    @InjectMocks
    TransactionController controller;

    @Mock
    TransactionControllerTransformer transformer;

    @Mock
    TransactionService service;

    @Test
    public void createTransaction() {

        TransactionRQDTO transactionRQDTOMock = Mockito.mock(TransactionRQDTO.class);
        TransactionIDTO transactionIDTOMock = Mockito.mock(TransactionIDTO.class);

        when(transformer.toTransactionIDTO(transactionRQDTOMock)).thenReturn(transactionIDTOMock);
        doNothing().when(service).createTransaction(transactionIDTOMock);

        controller.createTransaction(transactionRQDTOMock);

        verify(transformer, times(1)).toTransactionIDTO(transactionRQDTOMock);
        verify(service, times(1)).createTransaction(transactionIDTOMock);

    }

    @Test
    public void TransactionNotAllowed() {

        TransactionRQDTO transactionRQDTO = TransactionRQDTO.builder().fee(BigDecimal.ONE.negate()).build();

        Exception exception = assertThrows(TransactionNotAllowed.class, () -> {
            controller.createTransaction(transactionRQDTO);
        });

        assertTrue(exception.getMessage().contains("Transaction not allowed"));

    }

    @Test
    public void getTransactions() {

        TransactionIDTO transactionIDTOMock = Mockito.mock(TransactionIDTO.class);
        TransactionODTO transactionODTOMock = Mockito.mock(TransactionODTO.class);
        List<TransactionODTO> transactionODTOMockList = Arrays.asList(transactionODTOMock);
        TransactionRSDTO transactionRSDTOMock = Mockito.mock(TransactionRSDTO.class);
        List<TransactionRSDTO> transactionRSDTOMockList = Arrays.asList(transactionRSDTOMock);

        when(transformer.toTransactionIDTO("any_item", Direction.ASC, "amount")).thenReturn(transactionIDTOMock);
        when(service.findTransactionsByAccountIban(transactionIDTOMock)).thenReturn(transactionODTOMockList);
        when(transformer.toTransactionRSDTO(transactionODTOMock)).thenReturn(transactionRSDTOMock);

        List<TransactionRSDTO> response = controller.getTransactions("any_item", Direction.ASC, "amount");

        assertEquals(response, transactionRSDTOMockList);

        verify(transformer, times(1)).toTransactionIDTO("any_item", Direction.ASC, "amount");
        verify(service, times(1)).findTransactionsByAccountIban(transactionIDTOMock);
        verify(transformer, times(1)).toTransactionRSDTO(transactionODTOMock);

    }

    @Test
    public void transactionStatus() {

        TransactionStatusRQDTO transactionStatusRQDTOMock = Mockito.mock(TransactionStatusRQDTO.class);
        TransactionStatusIDTO transactionStatusIDTOMock = Mockito.mock(TransactionStatusIDTO.class);
        TransactionStatusODTO transactionStatusODTOMock = Mockito.mock(TransactionStatusODTO.class);
        TransactionStatusRSDTO transactionStatusRSDTOMock = Mockito.mock(TransactionStatusRSDTO.class);

        when(transformer.toTransactionStatusIDTO(transactionStatusRQDTOMock)).thenReturn(transactionStatusIDTOMock);
        when(service.getTransactionStatus(transactionStatusIDTOMock)).thenReturn(transactionStatusODTOMock);
        when(transformer.toTransactionStatusRSDTO(transactionStatusODTOMock)).thenReturn(transactionStatusRSDTOMock);

        TransactionStatusRSDTO response = controller.transactionStatus(transactionStatusRQDTOMock);

        assertEquals(response, transactionStatusRSDTOMock);

        verify(transformer, times(1)).toTransactionStatusIDTO(transactionStatusRQDTOMock);
        verify(service, times(1)).getTransactionStatus(transactionStatusIDTOMock);
        verify(transformer, times(1)).toTransactionStatusRSDTO(transactionStatusODTOMock);

    }

}
