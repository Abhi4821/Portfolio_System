package com.abhishekyadav.portfolio_backend.common.security.otp;


import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class OtpService {
    private final SecureRandom random = new SecureRandom();
    public String generateOtp() {
        return String.valueOf(
                100000 + random.nextInt(900000));
    }
}
