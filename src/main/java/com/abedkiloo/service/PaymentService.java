package com.abedkiloo.service;

import com.abedkiloo.dto.PaymentRequest;
import com.abedkiloo.model.Transaction;

import java.util.List;

public interface PaymentService {
    Transaction processPayment(PaymentRequest request);

    String makePayment(PaymentRequest request);

    String getTransactionStatus(String transactionId);

    String retryTransaction(String transactionId);

    List<Transaction> getTransactionHistory(String phoneNumber);
}