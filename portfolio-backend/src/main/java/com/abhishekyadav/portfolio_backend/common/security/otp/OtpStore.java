package com.abhishekyadav.portfolio_backend.common.security.otp;


import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class OtpStore {
    private final Map<String, String> otpMap = new ConcurrentHashMap<>();
    public void saveOtp(String email, String otp) {
        otpMap.put(email, otp);
    }

    public String getOtp(String email) {
        return otpMap.get(email);
    }

    public void removeOtp(String email) {
        otpMap.remove(email);
    }
}

