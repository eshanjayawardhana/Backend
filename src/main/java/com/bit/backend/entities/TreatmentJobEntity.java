package com.bit.backend.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "treatment_job")
public class TreatmentJobEntity extends AuditableEntity{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(name = "job_code", length = 50)
        private String jobCode;

        @Column(name = "scheduled_date", nullable = false)
        private LocalDateTime scheduledDate;

        @Column(name = "completed_date")
        private LocalDateTime completedDate;

        @Column(name = "work_done", columnDefinition = "TEXT")
        private String workDone;

        @Column(name = "hours_spent", precision = 4, scale = 1)
        private BigDecimal hoursSpent;

        @Column(name = "customer_signature")
        private String customerSignature;

        @ManyToOne
        @JoinColumn(name = "request_id")
        private ServiceRequestEntity serviceRequest;

        @ManyToOne
        @JoinColumn(name = "inspection_id")
        private InspectionEntity inspection;

        @ManyToOne
        @JoinColumn(name = "contract_id")
        private ServiceContractEntity serviceContract;

        @ManyToOne
        @JoinColumn(name = "site_id")
        private CustomerSiteEntity customerSite;

        @ManyToOne
        @JoinColumn(name = "technician_id")
        private TechnicianEntity technician;

        @ManyToOne
        @JoinColumn(name = "service_type_id")
        private ServiceTypeEntity serviceType;

        @ManyToOne
        @JoinColumn(name = "status_id")
        private StatusEntity status;

    public TreatmentJobEntity() {
    }

    public TreatmentJobEntity(Integer id, String jobCode, LocalDateTime scheduledDate, LocalDateTime completedDate, String workDone, BigDecimal hoursSpent, String customerSignature, ServiceRequestEntity serviceRequest, InspectionEntity inspection, ServiceContractEntity serviceContract, CustomerSiteEntity customerSite, TechnicianEntity technician, ServiceTypeEntity serviceType, StatusEntity status) {
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
