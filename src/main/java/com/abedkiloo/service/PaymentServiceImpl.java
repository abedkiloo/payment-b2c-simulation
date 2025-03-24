package com.abedkiloo.service;

import com.abedkiloo.dto.PaymentRequest;
import com.abedkiloo.model.Transaction;
import com.abedkiloo.model.TransactionStatus;
import com.abedkiloo.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final MobileMoneyService mobileMoneyService;
    private final SmsService smsService;
    private final TransactionRepository transactionRepository;

    public PaymentServiceImpl(MobileMoneyService mobileMoneyService, SmsService smsService, TransactionRepository transactionRepository) {
        this.mobileMoneyService = mobileMoneyService;
        this.smsService = smsService;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction processPayment(PaymentRequest request) {
        // Create transaction with default status PENDING
        Transaction transaction = Transaction.builder()
                .recipientPhone(request.getPhoneNumber())
                .amount(request.getAmount())
                .provider(request.getProvider())
                .status(TransactionStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();

        // Save initial transaction
        transactionRepository.save(transaction);

        // Call Mobile Money API
        String result = String.valueOf(mobileMoneyService.sendMoney(request.getProvider(), request.getPhoneNumber(), request.getAmount()));

        // Update transaction based on result
        if (result.contains("SUCCESS")) {
            transaction.setStatus(TransactionStatus.SUCCESS);
            smsService.sendSms(request.getPhoneNumber(), "Payment successful: " + request.getAmount());
        } else {
            transaction.setStatus(TransactionStatus.FAILED);
            smsService.sendSms(request.getPhoneNumber(), "Payment failed. Please retry.");
        }

        transactionRepository.save(transaction);
        return transaction;
    }

    @Override
    public String makePayment(PaymentRequest request) {
        return processPayment(request).getStatus().toString();
    }

    @Override
    public String getTransactionStatus(String transactionId) {
        return transactionRepository.findById(Long.valueOf(transactionId))
                .map(t -> "Transaction Status: " + t.getStatus())
                .orElse("Transaction not found");
    }

    @Override
    public String retryTransaction(String transactionId) {
        return transactionRepository.findById(Long.valueOf(transactionId)) // Convert String to Long
                .map(t -> {
                    if (TransactionStatus.SUCCESS.equals(t.getStatus())) {
                        return "Transaction already completed successfully.";
                    }
                    return makePayment(new PaymentRequest(t.getRecipientPhone(), t.getAmount(), t.getProvider()));
                })
                .orElse("Transaction not found");
    }

    @Override
    public List<Transaction> getTransactionHistory(String phoneNumber) {
        return transactionRepository.findByRecipientPhone(phoneNumber);
    }
}
