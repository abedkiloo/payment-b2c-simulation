package com.abedkiloo.repository;

import com.abedkiloo.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {


    List<Transaction> findByRecipientPhone(String recipientPhone);

    List<Transaction> findByStatus(String status);
}
