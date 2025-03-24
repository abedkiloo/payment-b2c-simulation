package com.abedkiloo.repository;

import com.abedkiloo.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Find transaction by its unique transaction ID (if stored as a String)
    Optional<Transaction> findById(String transactionId);

    // Find all transactions for a specific phone number
    List<Transaction> findByPhoneNumber(String phoneNumber);

    // Find transactions by their status (e.g., SUCCESS, PENDING, FAILED)
    List<Transaction> findByStatus(String status);
}
