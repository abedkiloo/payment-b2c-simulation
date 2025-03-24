package com.abedkiloo.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequest {

    @NotBlank(message = "Recipient phone number is required")
    private String recipientPhone;

    @Min(value = 1, message = "Amount must be at least 1")
    private double amount; // Changed to primitive to avoid null values

    @NotBlank(message = "Provider is required")
    private String provider; // MPESA, AIRTEL, etc.

    @NotBlank(message = "Reference is required")
    private String reference; // Transaction reference number
}
