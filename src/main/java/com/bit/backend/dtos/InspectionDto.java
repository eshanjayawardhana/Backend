package com.bit.backend.dtos;
import java.time.LocalDateTime;

public class InspectionDto {

    private Integer id;
    private String inspectionCode;
    private LocalDateTime inspectionDate;
    private String infestationLevel;
    private String affectedAreas;
    private String findings;
    private String recommendation;
    private ServiceRequestDto serviceRequest;
    private CustomerSiteDto customerSite;
    private TechnicianDto technician;
    private StatusDto status;

    public InspectionDto() {
    }

    public InspectionDto(Integer id, String inspectionCode, LocalDateTime inspectionDate, String infestationLevel, String affectedAreas, String findings, String recommendation, ServiceRequestDto serviceRequest, CustomerSiteDto customerSite, TechnicianDto technician, StatusDto status) {
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

    public ServiceRequestDto getServiceRequest() {
        return serviceRequest;
    }

    public void setServiceRequest(ServiceRequestDto serviceRequest) {
        this.serviceRequest = serviceRequest;
    }

    public CustomerSiteDto getCustomerSite() {
        return customerSite;
    }

    public void setCustomerSite(CustomerSiteDto customerSite) {
        this.customerSite = customerSite;
    }

    public TechnicianDto getTechnician() {
        return technician;
    }

    public void setTechnician(TechnicianDto technician) {
        this.technician = technician;
    }

    public StatusDto getStatus() {
        return status;
    }

    public void setStatus(StatusDto status) {
        this.status = status;
    }
}
