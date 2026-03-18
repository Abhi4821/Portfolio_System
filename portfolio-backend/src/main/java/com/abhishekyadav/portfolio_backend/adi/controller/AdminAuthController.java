package com.abhishekyadav.portfolio_backend.adi.controller;
import com.abhishekyadav.portfolio_backend.common.security.jwt.JwtUtil;
import com.abhishekyadav.portfolio_backend.common.security.otp.OtpService;
import com.abhishekyadav.portfolio_backend.common.security.otp.OtpStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/adi/auth")
public class AdminAuthController {
    private final OtpService otpService;
    private final OtpStore otpStore;
    private final JavaMailSender mailSender;
    private final JwtUtil jwtUtil;
    @Value("${app.admin.email}")
    private String ADMIN_EMAIL;
    public AdminAuthController(
            OtpService otpService,
            OtpStore otpStore,
            JavaMailSender mailSender,
            JwtUtil jwtUtil
    ) {
        this.otpService = otpService;
        this.otpStore = otpStore;
        this.mailSender = mailSender;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/send-otp")
    public String sendOtp(@RequestParam String email) {

        if (ADMIN_EMAIL == null||!ADMIN_EMAIL.trim().equalsIgnoreCase(email.trim())) {

            System.out.println("Email Not valid CONTROLLER HIT");
            return "Unauthorized email";
        }
        System.out.println("Send OTP CONTROLLER HIT");
        System.out.println("DEBUG: .env Admin Email is -> " + ADMIN_EMAIL);
        String otp = otpService.generateOtp();
        otpStore.saveOtp(email, otp);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Admin Login OTP");
        message.setText("Your OTP is: " + otp);

        try {
            mailSender.send(message);
            return "OTP sent successfully";
        } catch (Exception e) {
            System.err.println("MAIL ERROR: " + e.getMessage());
            return "Mail Error: " + e.getMessage();
        }
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(
            @RequestParam String email,
            @RequestParam String otp
    ) {
        System.out.println("VERIFY OTP CONTROLLER HIT");
        String storedOtp = otpStore.getOtp(email);
        if (storedOtp == null || !storedOtp.equals(otp)) {
            return "Invalid OTP";
        }
        otpStore.removeOtp(email);
        return jwtUtil.generateToken(email);
    }
}

