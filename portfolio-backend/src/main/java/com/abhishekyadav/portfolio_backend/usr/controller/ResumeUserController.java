package com.abhishekyadav.portfolio_backend.usr.controller;


import com.abhishekyadav.portfolio_backend.common.entity.ResumeEntity;
import com.abhishekyadav.portfolio_backend.usr.service.ResumeUserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usr/resume")
@CrossOrigin
public class ResumeUserController {

    private final ResumeUserService resumeService;

    public ResumeUserController(ResumeUserService resumeService) {
        this.resumeService = resumeService;
    }

    @GetMapping
    public ResumeEntity getResume() {
        return resumeService.getLatestResume();
    }
}
