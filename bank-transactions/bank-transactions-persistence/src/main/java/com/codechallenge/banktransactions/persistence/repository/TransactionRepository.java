package com.codechallenge.banktransactions.persistence.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codechallenge.banktransactions.persistence.model.TransactionMO;

/**
 * The Interface TransactionRepository.
 */
@Repository
public interface TransactionRepository extends JpaRepository<TransactionMO, String> {

    /**
     * Find by account iban.
     *
     * @param accountIban the account iban
     * @param sort        the sort
     * @return the list
     */
    List<TransactionMO> findByAccountIban(String accountIban, Sort sort);

    /**
     * Find by account iban order by date desc.
     *
     * @param accountIban the account iban
     * @return the list
     */
    List<TransactionMO> findByAccountIbanOrderByDateDesc(String accountIban);

}
