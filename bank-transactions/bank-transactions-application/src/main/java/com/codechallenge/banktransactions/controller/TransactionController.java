package com.codechallenge.banktransactions.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

/**
 * The Class TransactionController.
 */
@RestController
public class TransactionController {

    /** The Constant LOG. */
    private static final Logger LOG = LoggerFactory.getLogger(TransactionController.class);

    /** The transformer. */
    @Autowired
    private TransactionControllerTransformer transformer;

    /** The service. */
    @Autowired
    private TransactionService service;

    /** The Constant PATH. */
    private static final String PATH = "/transactions";

    /**
     * Creates the transaction.
     *
     * @param transactionRQDTO the transaction RQDTO
     */
    @PostMapping(PATH)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createTransaction(@Valid @RequestBody TransactionRQDTO transactionRQDTO) {

        LOG.info("Transaction creation request with account iban {}", transactionRQDTO.getAccountIban());

        if (null != transactionRQDTO.getFee() && transactionRQDTO.getFee().compareTo(BigDecimal.ZERO) < 0) {
            throw new TransactionNotAllowed("Transaction not allowed");
        }

        TransactionIDTO transactionIDTO = transformer.toTransactionIDTO(transactionRQDTO);

        service.createTransaction(transactionIDTO);

        LOG.info("Transaction with account iban {} created successfully", transactionRQDTO.getAccountIban());

    }

    /**
     * Gets the transactions.
     *
     * @param accountIban the account iban
     * @param sort        the sort
     * @return the transactions
     */
    @GetMapping(PATH + "/{account_iban}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<TransactionRSDTO> getTransactions(@PathVariable("account_iban") String accountIban,
            @RequestParam(name = "sort", required = true) Direction sort, @RequestParam(name = "orderBy", required = true) String orderBy) {

        LOG.debug("Transaction request with account iban {}", accountIban);

        TransactionIDTO transactionIDTO = transformer.toTransactionIDTO(accountIban, sort, orderBy);

        List<TransactionODTO> transactionODTOList = service.findTransactionsByAccountIban(transactionIDTO);

        List<TransactionRSDTO> transactionRSDTOList = transactionODTOList.stream()
                .map(transactionODTO -> transformer.toTransactionRSDTO(transactionODTO)).collect(Collectors.toList());

        LOG.debug("Transactions with account iban {} have been obtained", accountIban);

        return transactionRSDTOList;
    }

    /**
     * Transaction status.
     *
     * @param transactionStatusRQDTO the transaction status RQDTO
     * @return the transaction status RSDTO
     */
    @PostMapping(PATH + "/status")
    @ResponseStatus(value = HttpStatus.OK)
    public TransactionStatusRSDTO transactionStatus(@Valid @RequestBody TransactionStatusRQDTO transactionStatusRQDTO) {

        LOG.info("Transaction status request with reference {}", transactionStatusRQDTO.getReference());

        TransactionStatusIDTO transactionStatusIDTO = transformer.toTransactionStatusIDTO(transactionStatusRQDTO);

        TransactionStatusODTO transactionStatusODTO = service.getTransactionStatus(transactionStatusIDTO);

        LOG.info("Transaction with reference {} has been obtained", transactionStatusRQDTO.getReference());

        return transformer.toTransactionStatusRSDTO(transactionStatusODTO);

    }

}
