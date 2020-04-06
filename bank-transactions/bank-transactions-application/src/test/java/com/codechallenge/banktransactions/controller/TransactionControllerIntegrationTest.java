package com.codechallenge.banktransactions.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.codechallenge.banktransactions.controller.dto.request.TransactionRQDTO;
import com.codechallenge.banktransactions.controller.dto.request.TransactionStatusRQDTO;
import com.codechallenge.banktransactions.controller.dto.response.TransactionRSDTO;
import com.codechallenge.banktransactions.controller.dto.response.TransactionStatusRSDTO;
import com.codechallenge.banktransactions.persistence.type.ChannelType;
import com.codechallenge.banktransactions.persistence.type.StatusType;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TransactionControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private static HttpHeaders headers = new HttpHeaders();

    @BeforeAll
    public static void setUp() {
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    }

    @Test
    public void createTransactionWithOptionalFields() {

        TransactionRQDTO request = TransactionRQDTO.builder().reference("56789A").accountIban("ES9820385778983000760238").date(OffsetDateTime.now())
                .amount(BigDecimal.TEN).fee(BigDecimal.ONE).description("Restaurant payment").build();
        ResponseEntity<Void> response = this.restTemplate.postForEntity("/transactions", request, Void.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }

    @Test
    public void createTransactionWithMandatoryFields() {

        TransactionRQDTO request = TransactionRQDTO.builder().accountIban("ES9820385778983000760238").amount(BigDecimal.TEN).build();
        ResponseEntity<Void> response = this.restTemplate.postForEntity("/transactions", request, Void.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }

    @Test
    public void getTransactions() {

        String accountIban = "ES9820385778983000760236";
        String sort = "ASC";
        String orderBy = "amount";

        ResponseEntity<List<TransactionRSDTO>> response = restTemplate.exchange("/transactions/{account_iban}?sort={sort}&orderBy={orderBy}",
                HttpMethod.GET, new HttpEntity(headers), new ParameterizedTypeReference<List<TransactionRSDTO>>() {
                }, accountIban, sort, orderBy);
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void getTransactionsAccountIbanNotFound() {

        String accountIban = "ES9820385778983000760237";
        String sort = "ASC";
        String orderBy = "amount";

        ResponseEntity<TransactionRSDTO> response = restTemplate.exchange("/transactions/{account_iban}?sort={sort}&orderBy={orderBy}",
                HttpMethod.GET, new HttpEntity(headers), TransactionRSDTO.class, accountIban, sort, orderBy);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    public void transactionStatusBusinessRuleA() {

        TransactionStatusRQDTO request = TransactionStatusRQDTO.builder().reference("12345A").channel(ChannelType.CLIENT).build();
        ResponseEntity<TransactionStatusRSDTO> response = this.restTemplate.postForEntity("/transactions/status", request,
                TransactionStatusRSDTO.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(StatusType.INVALID.getCode(), response.getBody().getStatus());

    }

    @Test
    public void transactionStatusBusinessRuleB_CLIENT() {

        TransactionStatusRQDTO request = TransactionStatusRQDTO.builder().reference("A").channel(ChannelType.CLIENT).build();
        ResponseEntity<TransactionStatusRSDTO> response = this.restTemplate.postForEntity("/transactions/status", request,
                TransactionStatusRSDTO.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(new BigDecimal(100).compareTo(response.getBody().getAmount()) == 0);
        assertEquals(StatusType.SETTLED.getCode(), response.getBody().getStatus());

    }

    @Test
    public void transactionStatusBusinessRuleB_ATM() {

        TransactionStatusRQDTO request = TransactionStatusRQDTO.builder().reference("A").channel(ChannelType.ATM).build();
        ResponseEntity<TransactionStatusRSDTO> response = this.restTemplate.postForEntity("/transactions/status", request,
                TransactionStatusRSDTO.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(new BigDecimal(100).compareTo(response.getBody().getAmount()) == 0);
        assertEquals(StatusType.SETTLED.getCode(), response.getBody().getStatus());

    }

    @Test
    public void transactionStatusBusinessRuleC() {
        TransactionStatusRQDTO request = TransactionStatusRQDTO.builder().reference("A").channel(ChannelType.INTERNAL).build();
        ResponseEntity<TransactionStatusRSDTO> response = this.restTemplate.postForEntity("/transactions/status", request,
                TransactionStatusRSDTO.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(new BigDecimal(105).compareTo(response.getBody().getAmount()) == 0);
        assertTrue(new BigDecimal(5).compareTo(response.getBody().getFee()) == 0);
        assertEquals(StatusType.SETTLED.getCode(), response.getBody().getStatus());
    }

    @Test
    public void transactionStatusBusinessRuleD() {
        TransactionStatusRQDTO request = TransactionStatusRQDTO.builder().reference("B").channel(ChannelType.ATM).build();
        ResponseEntity<TransactionStatusRSDTO> response = this.restTemplate.postForEntity("/transactions/status", request,
                TransactionStatusRSDTO.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(new BigDecimal(200).compareTo(response.getBody().getAmount()) == 0);
        assertEquals(StatusType.PENDING.getCode(), response.getBody().getStatus());
    }

    @Test
    public void transactionStatusBusinessRuleE() {
        TransactionStatusRQDTO request = TransactionStatusRQDTO.builder().reference("B").channel(ChannelType.INTERNAL).build();
        ResponseEntity<TransactionStatusRSDTO> response = this.restTemplate.postForEntity("/transactions/status", request,
                TransactionStatusRSDTO.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(new BigDecimal(205).compareTo(response.getBody().getAmount()) == 0);
        assertTrue(new BigDecimal(5).compareTo(response.getBody().getFee()) == 0);
        assertEquals(StatusType.PENDING.getCode(), response.getBody().getStatus());
    }

    @Test
    public void transactionStatusBusinessRuleF() {
        TransactionStatusRQDTO request = TransactionStatusRQDTO.builder().reference("C").channel(ChannelType.CLIENT).build();
        ResponseEntity<TransactionStatusRSDTO> response = this.restTemplate.postForEntity("/transactions/status", request,
                TransactionStatusRSDTO.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(new BigDecimal(300).compareTo(response.getBody().getAmount()) == 0);
        assertEquals(StatusType.FUTURE.getCode(), response.getBody().getStatus());
    }

    @Test
    public void transactionStatusBusinessRuleG() {
        TransactionStatusRQDTO request = TransactionStatusRQDTO.builder().reference("C").channel(ChannelType.ATM).build();
        ResponseEntity<TransactionStatusRSDTO> response = this.restTemplate.postForEntity("/transactions/status", request,
                TransactionStatusRSDTO.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(new BigDecimal(300).compareTo(response.getBody().getAmount()) == 0);
        assertEquals(StatusType.PENDING.getCode(), response.getBody().getStatus());
    }

    @Test
    public void transactionStatusBusinessRuleH() {
        TransactionStatusRQDTO request = TransactionStatusRQDTO.builder().reference("C").channel(ChannelType.INTERNAL).build();
        ResponseEntity<TransactionStatusRSDTO> response = this.restTemplate.postForEntity("/transactions/status", request,
                TransactionStatusRSDTO.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(new BigDecimal(305).compareTo(response.getBody().getAmount()) == 0);
        assertTrue(new BigDecimal(5).compareTo(response.getBody().getFee()) == 0);
        assertEquals(StatusType.FUTURE.getCode(), response.getBody().getStatus());
    }

    @Test
    public void transactionStatusBusinessNoChannelTransactionFound() {
        TransactionStatusRQDTO request = TransactionStatusRQDTO.builder().reference("C").build();
        ResponseEntity<TransactionStatusRSDTO> response = this.restTemplate.postForEntity("/transactions/status", request,
                TransactionStatusRSDTO.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(new BigDecimal(305).compareTo(response.getBody().getAmount()) == 0);
        assertTrue(new BigDecimal(5).compareTo(response.getBody().getFee()) == 0);
        assertEquals(StatusType.INVALID.getCode(), response.getBody().getStatus());
    }

    @Test
    public void transactionStatusBusinessNoChannelTransactionNotFound() {
        TransactionStatusRQDTO request = TransactionStatusRQDTO.builder().reference("D").build();
        ResponseEntity<TransactionStatusRSDTO> response = this.restTemplate.postForEntity("/transactions/status", request,
                TransactionStatusRSDTO.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(StatusType.INVALID.getCode(), response.getBody().getStatus());
    }
}
