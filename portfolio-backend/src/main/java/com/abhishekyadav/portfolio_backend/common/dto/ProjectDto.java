package com.abhishekyadav.portfolio_backend.common.dto;



public class ProjectDto {

    private Long id;
    private String projectTitle;
    private String projectDescription;
    private String projectImageUrl;
    private String sourceCodeUrl;
    private String demoUrl;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectTitle() {
        return projectTitle;
    }
    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getProjectDescription() {
        return projectDescription;
    }
    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getProjectImageUrl() {
        return projectImageUrl;
    }
    public void setProjectImageUrl(String projectImageUrl) {
        this.projectImageUrl = projectImageUrl;
    }

    public String getSourceCodeUrl() {
        return sourceCodeUrl;
    }
    public void setSourceCodeUrl(String sourceCodeUrl) {
        this.sourceCodeUrl = sourceCodeUrl;
    }

    public String getDemoUrl() {
        return demoUrl;
    }
    public void setDemoUrl(String demoUrl) {
        this.demoUrl = demoUrl;
    }
}
