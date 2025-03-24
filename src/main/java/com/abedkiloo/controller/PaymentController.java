package com.abedkiloo.controller;

import com.abedkiloo.dto.PaymentRequest;
import com.abedkiloo.model.Transaction;
import com.abedkiloo.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<?> initiatePayment(@RequestBody PaymentRequest request) {
        Transaction transaction = paymentService.processPayment(request);
        if (transaction == null) {
            return ResponseEntity.badRequest().body("Payment could not be processed.");
        }
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/status/{transactionId}")
    public ResponseEntity<String> getTransactionStatus(@PathVariable String transactionId) {
        String status = paymentService.getTransactionStatus(transactionId);
        return ResponseEntity.ok(status);
    }

    @PostMapping("/retry/{transactionId}")
    public ResponseEntity<String> retryPayment(@PathVariable String transactionId) {
        String result = paymentService.retryTransaction(transactionId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/history/{phoneNumber}")
    public ResponseEntity<List<Transaction>> getTransactionHistory(@PathVariable String phoneNumber) {
        List<Transaction> transactions = paymentService.getTransactionHistory(phoneNumber);
        if (transactions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(transactions);
    }
}
