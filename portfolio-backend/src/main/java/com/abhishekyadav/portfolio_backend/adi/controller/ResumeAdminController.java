package com.abhishekyadav.portfolio_backend.adi.controller;

import com.abhishekyadav.portfolio_backend.adi.service.ResumeAdminService;
import com.abhishekyadav.portfolio_backend.common.entity.ResumeEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

//@RestController
//@RequestMapping("/api/adi/resume")
//@CrossOrigin
//public class ResumeAdminController {
//
//    private final ResumeAdminService resumeService;
//
//    public ResumeAdminController(ResumeAdminService resumeService) {
//        this.resumeService = resumeService;
//    }
//
//    @PostMapping(consumes = "multipart/form-data")
//    public ResumeEntity uploadResume(
//            @RequestPart("file") MultipartFile file,
////            @RequestPart("resume") ResumeEntity resume
//            @RequestPart("resume") String resumeJson
//
//
//    ) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        ResumeEntity resume = mapper.readValue(resumeJson, ResumeEntity.class);
//        return resumeService.uploadResume(file, resume);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteResume(@PathVariable Long id) {
//        resumeService.deleteResume(id);
//    }
//}
@RestController
@RequestMapping("/api/adi/resume")
@CrossOrigin
public class ResumeAdminController {
    private final ResumeAdminService resumeService;

    public ResumeAdminController(ResumeAdminService resumeService) {
        this.resumeService = resumeService;
    }


    @PostMapping(consumes = "multipart/form-data")
    public ResumeEntity uploadResume(
            @RequestPart("file") MultipartFile file,
            @RequestPart("resumeTitle") String resumeTitle
    ) {
        ResumeEntity resume = new ResumeEntity();
        resume.setResumeTitle(resumeTitle);
        return resumeService.uploadResume(file, resume);
    }


    @DeleteMapping("/{id}")
    public void deleteResume(@PathVariable Long id) {
        resumeService.deleteResume(id);
    }
}