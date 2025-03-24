package com.abedkiloo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    private String phoneNumber;
    private Double amount;
    private String provider; // Either "MPESA" or "AIRTEL"

    // Getters (Lombok already generates these, so no need to manually define them)
    public String getRecipientPhone() {
        return phoneNumber;
    }
}
