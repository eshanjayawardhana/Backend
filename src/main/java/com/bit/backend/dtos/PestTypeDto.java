package com.bit.backend.dtos;

import jakarta.persistence.Column;

public class PestTypeDto {

    private Integer id;
    private String pestName;
    private String description;
    private String severityLevel;

    public PestTypeDto() {
    }

    public PestTypeDto(Integer id, String pestName, String description, String severityLevel) {
        this.id = id;
        this.pestName = pestName;
        this.description = description;
        this.severityLevel = severityLevel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPestName() {
        return pestName;
    }

    public void setPestName(String pestName) {
        this.pestName = pestName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(String severityLevel) {
        this.severityLevel = severityLevel;
    }
}
