package com.bit.backend.dtos;

import com.bit.backend.entities.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TreatmentJobDto {

    private Integer id;
    private String jobCode;
    private LocalDateTime scheduledDate;
    private LocalDateTime completedDate;
    private String workDone;
    private BigDecimal hoursSpent;
    private String customerSignature;
    private ServiceRequestEntity serviceRequest;
    private InspectionEntity inspection;
    private ServiceContractEntity serviceContract;
    private CustomerSiteEntity customerSite;
    private TechnicianEntity technician;
    private ServiceTypeEntity serviceType;
    private StatusEntity status;

    public TreatmentJobDto() {
    }

    public TreatmentJobDto(Integer id, String jobCode, LocalDateTime scheduledDate, LocalDateTime completedDate, String workDone, BigDecimal hoursSpent, String customerSignature, ServiceRequestEntity serviceRequest, InspectionEntity inspection, ServiceContractEntity serviceContract, CustomerSiteEntity customerSite, TechnicianEntity technician, ServiceTypeEntity serviceType, StatusEntity status) {
        this.id = id;
        this.jobCode = jobCode;
        this.scheduledDate = scheduledDate;
        this.completedDate = completedDate;
        this.workDone = workDone;
        this.hoursSpent = hoursSpent;
        this.customerSignature = customerSignature;
        this.serviceRequest = serviceRequest;
        this.inspection = inspection;
        this.serviceContract = serviceContract;
        this.customerSite = customerSite;
        this.technician = technician;
        this.serviceType = serviceType;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public LocalDateTime getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDateTime scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public LocalDateTime getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(LocalDateTime completedDate) {
        this.completedDate = completedDate;
    }

    public String getWorkDone() {
        return workDone;
    }

    public void setWorkDone(String workDone) {
        this.workDone = workDone;
    }

    public BigDecimal getHoursSpent() {
        return hoursSpent;
    }

    public void setHoursSpent(BigDecimal hoursSpent) {
        this.hoursSpent = hoursSpent;
    }

    public String getCustomerSignature() {
        return customerSignature;
    }

    public void setCustomerSignature(String customerSignature) {
        this.customerSignature = customerSignature;
    }

    public ServiceRequestEntity getServiceRequest() {
        return serviceRequest;
    }

    public void setServiceRequest(ServiceRequestEntity serviceRequest) {
        this.serviceRequest = serviceRequest;
    }

    public InspectionEntity getInspection() {
        return inspection;
    }

    public void setInspection(InspectionEntity inspection) {
        this.inspection = inspection;
    }

    public ServiceContractEntity getServiceContract() {
        return serviceContract;
    }

    public void setServiceContract(ServiceContractEntity serviceContract) {
        this.serviceContract = serviceContract;
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

    public ServiceTypeEntity getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceTypeEntity serviceType) {
        this.serviceType = serviceType;
    }

    public StatusEntity getStatus() {
        return status;
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }
}
