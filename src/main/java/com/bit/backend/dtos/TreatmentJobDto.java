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
    private ServiceRequestDto serviceRequest;
    private InspectionDto inspection;
    private ServiceContractDto serviceContract;
    private CustomerSiteDto customerSite;
    private TechnicianDto technician;
    private ServiceTypeDto serviceType;
    private StatusDto status;

    public TreatmentJobDto() {
    }

    public TreatmentJobDto(Integer id, String jobCode, LocalDateTime scheduledDate, LocalDateTime completedDate, String workDone, BigDecimal hoursSpent, String customerSignature, ServiceRequestDto serviceRequest, InspectionDto inspection, ServiceContractDto serviceContract, CustomerSiteDto customerSite, TechnicianDto technician, ServiceTypeDto serviceType, StatusDto status) {
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

    public ServiceRequestDto getServiceRequest() {
        return serviceRequest;
    }

    public void setServiceRequest(ServiceRequestDto serviceRequest) {
        this.serviceRequest = serviceRequest;
    }

    public InspectionDto getInspection() {
        return inspection;
    }

    public void setInspection(InspectionDto inspection) {
        this.inspection = inspection;
    }

    public ServiceContractDto getServiceContract() {
        return serviceContract;
    }

    public void setServiceContract(ServiceContractDto serviceContract) {
        this.serviceContract = serviceContract;
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

    public ServiceTypeDto getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceTypeDto serviceType) {
        this.serviceType = serviceType;
    }

    public StatusDto getStatus() {
        return status;
    }

    public void setStatus(StatusDto status) {
        this.status = status;
    }
}
