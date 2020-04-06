package com.codechallenge.banktransactions.persistence.service.impl;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.EnumMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.codechallenge.banktransactions.persistence.exception.TransactionDuplicatedException;
import com.codechallenge.banktransactions.persistence.exception.TransactionNotAllowed;
import com.codechallenge.banktransactions.persistence.exception.TransactionNotFoundException;
import com.codechallenge.banktransactions.persistence.model.TransactionMO;
import com.codechallenge.banktransactions.persistence.repository.TransactionRepository;
import com.codechallenge.banktransactions.persistence.service.TransactionPersistenceService;
import com.codechallenge.banktransactions.persistence.service.channel.execution.ChannelExecutor;
import com.codechallenge.banktransactions.persistence.service.channel.execution.dto.output.TransactionStatusExecutionODTO;
import com.codechallenge.banktransactions.persistence.service.channel.execution.transformer.ChannelExecutorTransformer;
import com.codechallenge.banktransactions.persistence.service.channel.strategy.AtmStrategyImpl;
import com.codechallenge.banktransactions.persistence.service.channel.strategy.ClientStrategyImpl;
import com.codechallenge.banktransactions.persistence.service.channel.strategy.InternalStrategyImpl;
import com.codechallenge.banktransactions.persistence.service.dto.input.TransactionCriteriaIDTO;
import com.codechallenge.banktransactions.persistence.service.dto.input.TransactionPersistenceIDTO;
import com.codechallenge.banktransactions.persistence.service.dto.input.TransactionStatusCriteriaIDTO;
import com.codechallenge.banktransactions.persistence.service.dto.output.TransactionCriteriaODTO;
import com.codechallenge.banktransactions.persistence.service.dto.output.TransactionStatusCriteriaODTO;
import com.codechallenge.banktransactions.persistence.service.transformer.TransactionPersistenceServiceTransformer;
import com.codechallenge.banktransactions.persistence.type.ChannelType;
import com.codechallenge.banktransactions.persistence.type.StatusType;

/**
 * The Class TransactionPersistenceServiceImpl.
 */
@Service
public class TransactionPersistenceServiceImpl implements TransactionPersistenceService {

    /** The repository. */
    @Autowired
    private TransactionRepository repository;

    /** The transformer. */
    @Autowired
    private TransactionPersistenceServiceTransformer transformer;

    /** The executor strategy map. */
    private EnumMap<ChannelType, ChannelExecutor> executorStrategyMap;

    /** The atm executor. */
    @Autowired
    private AtmStrategyImpl atmExecutor;

    /** The internal executor. */
    @Autowired
    private InternalStrategyImpl internalExecutor;

    /** The client executor. */
    @Autowired
    private ClientStrategyImpl clientExecutor;

    /** The channel transformer. */
    @Autowired
    private ChannelExecutorTransformer channelTransformer;

    /** The Constant EMPTY. */
    private static final String EMPTY = "";

    /**
     * Gets the executor.
     *
     * @param channel the channel
     * @return the executor
     */
    private ChannelExecutor getExecutor(ChannelType channel) {

        return executorStrategyMap.get(channel);

    }

    /**
     * Load strategies.
     */
    @PostConstruct
    public void loadStrategies() {

        executorStrategyMap = new EnumMap<>(ChannelType.class);

        executorStrategyMap.put(ChannelType.ATM, atmExecutor);
        executorStrategyMap.put(ChannelType.INTERNAL, internalExecutor);
        executorStrategyMap.put(ChannelType.CLIENT, clientExecutor);

    }

    /**
     * Find transactions by account iban.
     *
     * @param transactionCriteria the transaction criteria
     * @return the list
     */
    @Override
    public List<TransactionCriteriaODTO> findTransactionsByAccountIban(TransactionCriteriaIDTO transactionCriteria) {

        Sort sort = Sort.by(transactionCriteria.getSort(), transactionCriteria.getOrderBy());

        List<TransactionMO> transactionsMOList = repository.findByAccountIban(transactionCriteria.getAccountIban(), sort);

        if (transactionsMOList.isEmpty()) {
            throw new TransactionNotFoundException("No transaction found with account iban " + transactionCriteria.getAccountIban());
        }

        return transactionsMOList.stream().map(transactionMO -> transformer.toTransactionCriteriaODTO(transactionMO)).collect(Collectors.toList());
    }

    /**
     * Creates the transaction.
     *
     * @param transactionPersistenceIDTO the transaction persistence IDTO
     */
    @Override
    public void createTransaction(TransactionPersistenceIDTO transactionPersistenceIDTO) {

        BigDecimal accountBalance = getAccountBalance(transactionPersistenceIDTO.getAmount(), transactionPersistenceIDTO.getFee(),
                transactionPersistenceIDTO.getAccountIban(), transactionPersistenceIDTO.getDate());

        TransactionMO transactionMO = transformer.toTransactionMO(transactionPersistenceIDTO);

        transactionMO.setReference(generateReference(transactionMO.getReference()));
        transactionMO.setAccountBalance(accountBalance);

        repository.save(transactionMO);

    }

    /**
     * Gets the transaction status.
     *
     * @param transactionStatusCriteriaIDTO the transaction status criteria IDTO
     * @return the transaction status
     */
    @Override
    public TransactionStatusCriteriaODTO getTransactionStatus(TransactionStatusCriteriaIDTO transactionStatusCriteriaIDTO) {

        if (null == transactionStatusCriteriaIDTO.getChannel()) {
            TransactionMO transactionMO = repository.findById(transactionStatusCriteriaIDTO.getReference()).orElse(null);

            return TransactionStatusCriteriaODTO.builder().reference(transactionStatusCriteriaIDTO.getReference())
                    .status(StatusType.INVALID.getCode()).amount(Optional.ofNullable(transactionMO).map(TransactionMO::getAmount).orElse(null))
                    .fee(Optional.ofNullable(transactionMO).map(TransactionMO::getFee).orElse(null)).build();
        }

        // Strategy pattern
        TransactionStatusExecutionODTO transactionStatusStrategyODTO = getExecutor(transactionStatusCriteriaIDTO.getChannel())
                .execute(transactionStatusCriteriaIDTO);

        return channelTransformer.toTransactionStatusCriteriaODTO(transactionStatusStrategyODTO);

    }

    /**
     * Generate reference.
     *
     * @param reference the reference
     * @return the string
     */
    private String generateReference(String reference) {

        if (null == reference || EMPTY.equals(reference.trim())) {
            return UUID.randomUUID().toString();
        }

        TransactionMO transactionMO = repository.findById(reference).orElse(null);
        if (null != transactionMO) {
            throw new TransactionDuplicatedException("Duplicate reference");
        }

        return reference;

    }

    /**
     * If the amount is positive, the transaction is a credit (add money to the
     * account) If the amount is negative, it is a debit (deduct money from the
     * account).
     *
     * @param amount      the amount
     * @param fee         the fee
     * @param accountIban the account iban
     * @param date        the date
     * @return accountBalance after applying the calculations
     */
    private BigDecimal getAccountBalance(BigDecimal amount, BigDecimal fee, String accountIban, OffsetDateTime date) {

        TransactionMO transactionMO = findTransactionByAccountIbanOrderByDateDesc(accountIban);

        checkTransactionDate(date, Optional.ofNullable(transactionMO).map(TransactionMO::getDate).orElse(OffsetDateTime.MIN));

        BigDecimal currentAccountBalance = Optional.ofNullable(transactionMO).map(TransactionMO::getAccountBalance).orElse(BigDecimal.ZERO);
        BigDecimal amountWithFee = getAmountWithFee(amount, Optional.ofNullable(fee).orElse(BigDecimal.ZERO));

        return calculateNewAccountBalance(currentAccountBalance, amountWithFee);
    }

    /**
     * Find transaction by account iban order by date desc.
     *
     * @param accountIban the account iban
     * @return the transaction MO
     */
    private TransactionMO findTransactionByAccountIbanOrderByDateDesc(String accountIban) {
        return repository.findByAccountIbanOrderByDateDesc(accountIban).stream().findFirst().orElse(null);
    }

    /**
     * Check transaction date.
     *
     * @param currentTransactionDate the current transaction date
     * @param lastTransactionDate    the last transaction date
     */
    private void checkTransactionDate(OffsetDateTime currentTransactionDate, OffsetDateTime lastTransactionDate) {

        if (currentTransactionDate.isBefore(lastTransactionDate)) {
            throw new TransactionNotAllowed("Transaction not allowed");
        }

    }

    /**
     * Fee (optional) will be deducted from the amount, regardless on the a{mount
     * being positive or negative.
     *
     * @param amount the amount
     * @param fee    the fee
     * @return amount after applying the calculations
     */
    private BigDecimal getAmountWithFee(BigDecimal amount, BigDecimal fee) {

        return amount.subtract(fee);

    }

    /**
     * Gets the account balance.
     *
     * @param accountBalance the account balance
     * @param amountWithFee  the amount with fee
     * @return the account balance
     */
    private BigDecimal calculateNewAccountBalance(BigDecimal accountBalance, BigDecimal amountWithFee) {

        BigDecimal newAccountBalance = accountBalance.add(amountWithFee);

        if (newAccountBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new TransactionNotAllowed("Transaction not allowed");
        }

        return newAccountBalance;

    }

}
