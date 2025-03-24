package com.abedkiloo.service;


import com.abedkiloo.service.MobileMoneyService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MobileMoneyServiceImpl implements MobileMoneyService {

    private final Random random = new Random();

    @Override
    public boolean sendMoney(String provider, String phone, Double amount) {
        System.out.println("Processing " + provider + " B2C payment to " + phone + " for amount " + amount);

        boolean isSuccessful = random.nextDouble() < 0.8;

        if (isSuccessful) {
            System.out.println("Payment SUCCESS for " + phone);
        } else {
            System.out.println("Payment FAILED for " + phone);
        }

        return isSuccessful;
    }
}
