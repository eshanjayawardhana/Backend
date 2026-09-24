package com.bit.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "pest_type")
public class PestTypeEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "pest_name", unique = true, nullable = false)
    private String pestName;

    @Column(name = "description")
    private String description;

    @Column(name = "severity_level")
    private String severityLevel;

    public PestTypeEntity() {
    }

    public PestTypeEntity(Integer id, String pestName, String description, String severityLevel) {
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
