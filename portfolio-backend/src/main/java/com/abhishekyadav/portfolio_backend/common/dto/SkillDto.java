package com.abhishekyadav.portfolio_backend.common.dto;



public class SkillDto {

    private Long id;
    private String skillName;
    private String skillLevel;
    private String skillImageUrl;
    private String certificateUrl;

    public Long getId() {
        return id;
    }
    public void setId(Long id) { this.id = id; }

    public String getSkillName() { return skillName; }
    public void setSkillName(String skillName) { this.skillName = skillName; }

    public String getSkillLevel() { return skillLevel; }
    public void setSkillLevel(String skillLevel) { this.skillLevel = skillLevel; }

    public String getSkillImageUrl() { return skillImageUrl; }
    public void setSkillImageUrl(String skillImageUrl) { this.skillImageUrl = skillImageUrl; }

    public String getCertificateUrl() { return certificateUrl; }
    public void setCertificateUrl(String certificateUrl) { this.certificateUrl = certificateUrl; }
}
