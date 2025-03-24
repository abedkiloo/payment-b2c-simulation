package com.abedkiloo.controller;


import com.abedkiloo.dto.PaymentRequest;
import com.abedkiloo.model.Transaction;
import com.abedkiloo.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<Transaction> initiatePayment(@RequestBody PaymentRequest request) {
        Transaction transaction = paymentService.processPayment(request);
        return ResponseEntity.ok(transaction);
    }
    @GetMapping("/status/{transactionId}")
    public String getTransactionStatus(@PathVariable String transactionId) {
        return paymentService.getTransactionStatus(transactionId);
    }

    @PostMapping("/retry/{transactionId}")
    public String retryPayment(@PathVariable String transactionId) {
        return paymentService.retryTransaction(transactionId);
    }

    @GetMapping("/history/{phoneNumber}")
    public List<Transaction> getTransactionHistory(@PathVariable String phoneNumber) {
        return paymentService.getTransactionHistory(phoneNumber);
    }
}