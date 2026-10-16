package com.bit.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "inspection")
public class InspectionEntity extends AuditableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "inspection_code")
    private String inspectionCode;

    @Column(name = "inspection_date", nullable = false)
    private LocalDateTime inspectionDate;

    @Column(name = "infestation_level")
    private String infestationLevel;

    @Column(name = "affected_areas")
    private String affectedAreas;

    @Column(name = "findings")
    private String findings;

    @Column(name = "recommendation")
    private String recommendation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "request_id")
    private ServiceRequestEntity serviceRequest;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "site_id")
    private CustomerSiteEntity customerSite;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "technician_id")
    private TechnicianEntity technician;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id")
    private StatusEntity status;

    public InspectionEntity() {
    }

    public InspectionEntity(Integer id, String inspectionCode, LocalDateTime inspectionDate, String infestationLevel, String affectedAreas, String findings, String recommendation, ServiceRequestEntity serviceRequest, CustomerSiteEntity customerSite, TechnicianEntity technician, StatusEntity status) {
        this.id = id;
        this.inspectionCode = inspectionCode;
        this.inspectionDate = inspectionDate;
        this.infestationLevel = infestationLevel;
        this.affectedAreas = affectedAreas;
        this.findings = findings;
        this.recommendation = recommendation;
        this.serviceRequest = serviceRequest;
        this.customerSite = customerSite;
        this.technician = technician;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInspectionCode() {
        return inspectionCode;
    }

    public void setInspectionCode(String inspectionCode) {
        this.inspectionCode = inspectionCode;
    }

    public LocalDateTime getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(LocalDateTime inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public String getInfestationLevel() {
        return infestationLevel;
    }

    public void setInfestationLevel(String infestationLevel) {
        this.infestationLevel = infestationLevel;
    }

    public String getAffectedAreas() {
        return affectedAreas;
    }

    public void setAffectedAreas(String affectedAreas) {
        this.affectedAreas = affectedAreas;
    }

    public String getFindings() {
        return findings;
    }

    public void setFindings(String findings) {
        this.findings = findings;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public ServiceRequestEntity getServiceRequest() {
        return serviceRequest;
    }

    public void setServiceRequest(ServiceRequestEntity serviceRequest) {
        this.serviceRequest = serviceRequest;
    }

    public CustomerSiteEntity getCustomerSite() {
        return customerSite;
    }

    public void setCustomerSite(CustomerSiteEntity customerSite) {
        this.customerSite = customerSite;
    }

    public TechnicianEntity getTechnician() {
        return technician;
    }

    public void setTechnician(TechnicianEntity technician) {
        this.technician = technician;
    }

    public StatusEntity getStatus() {
        return status;
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }
}
