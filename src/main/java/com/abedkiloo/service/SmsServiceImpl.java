package com.abedkiloo.service;

import org.springframework.stereotype.Service;

@Service
public class SmsServiceImpl implements SmsService {

    @Override
    public void sendSms(String phoneNumber, String message) {
        // Simulate SMS sending
        System.out.println("Sending SMS to " + phoneNumber + ": " + message);
    }
}