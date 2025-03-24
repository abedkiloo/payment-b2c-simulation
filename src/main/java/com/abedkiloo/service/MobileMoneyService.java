package com.abedkiloo.service;

import com.abedkiloo.dto.PaymentRequest;
import com.abedkiloo.dto.PaymentResponse;

public interface MobileMoneyService {
    boolean sendMoney(String provider, String phone, Double amount);

}