package com.codechallenge.banktransactions.controller.transformer.mapper;

import java.time.OffsetDateTime;

import org.mapstruct.Mapper;

import com.codechallenge.banktransactions.controller.dto.request.TransactionRQDTO;
import com.codechallenge.banktransactions.controller.dto.request.TransactionStatusRQDTO;
import com.codechallenge.banktransactions.controller.dto.response.TransactionRSDTO;
import com.codechallenge.banktransactions.controller.dto.response.TransactionStatusRSDTO;
import com.codechallenge.banktransactions.service.dto.input.TransactionIDTO;
import com.codechallenge.banktransactions.service.dto.input.TransactionStatusIDTO;
import com.codechallenge.banktransactions.service.dto.output.TransactionODTO;
import com.codechallenge.banktransactions.service.dto.output.TransactionStatusODTO;

/**
 * The Interface TransactionControllerMapper.
 */
@Mapper
public interface TransactionControllerMapper {

    /**
     * To transaction IDTO.
     *
     * @param transactionRQDTO the transaction RQDTO
     * @return the transaction IDTO
     */
    TransactionIDTO toTransactionIDTO(TransactionRQDTO transactionRQDTO);

    /**
     * To transaction RSDTO.
     *
     * @param transactionODTO the transaction ODTO
     * @return the transaction RSDTO
     */
    TransactionRSDTO toTransactionRSDTO(TransactionODTO transactionODTO);

    /**
     * To transaction status RSDTO.
     *
     * @param transactionODTO the transaction ODTO
     * @return the transaction status RSDTO
     */
    TransactionStatusRSDTO toTransactionStatusRSDTO(TransactionStatusODTO transactionODTO);

    /**
     * To transaction status IDTO.
     *
     * @param transactionStatusRQDTO the transaction status RQDTO
     * @return the transaction status IDTO
     */
    TransactionStatusIDTO toTransactionStatusIDTO(TransactionStatusRQDTO transactionStatusRQDTO);

    /**
     * Map date.
     *
     * @param date the date
     * @return the offset date time
     */
    default OffsetDateTime mapDate(OffsetDateTime date) {
        return null == date ? OffsetDateTime.now() : date;
    }

}
