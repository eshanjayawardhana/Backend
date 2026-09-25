package com.bit.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "service_request")
public class ServiceRequestEntity extends AuditableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "request_code")
    private String requestCode;

    @Column(name = "request_date", nullable = false)
    private LocalDateTime requestDate;

    @Column(name = "preferred_date")
    private LocalDate preferredDate;

    @Column(name = "problem_description", nullable = false)
    private String problemDescription;

    @Column(name = "priority")
    private String priority;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "customer_site")
    private CustomerSiteEntity customerSite;

    @ManyToOne
    @JoinColumn(name = "pest_type_id")
    private PestTypeEntity pestType;

    @ManyToOne
    @JoinColumn(name = "service_type")
    private ServiceTypeEntity serviceType;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusEntity status;


    public ServiceRequestEntity() {
    }

    public ServiceRequestEntity(Integer id, String requestCode, LocalDateTime requestDate, LocalDate preferredDate, String problemDescription, String priority, CustomerEntity customer, CustomerSiteEntity customerSite, PestTypeEntity pestType, ServiceTypeEntity serviceType, StatusEntity status) {
        this.id = id;
        this.requestCode = requestCode;
        this.requestDate = requestDate;
        this.preferredDate = preferredDate;
        this.problemDescription = problemDescription;
        this.priority = priority;
        this.customer = customer;
        this.customerSite = customerSite;
        this.pestType = pestType;
        this.serviceType = serviceType;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public LocalDate getPreferredDate() {
        return preferredDate;
    }

    public void setPreferredDate(LocalDate preferredDate) {
        this.preferredDate = preferredDate;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public CustomerSiteEntity getCustomerSite() {
        return customerSite;
    }

    public void setCustomerSite(CustomerSiteEntity customerSite) {
        this.customerSite = customerSite;
    }

    public PestTypeEntity getPestType() {
        return pestType;
    }

    public void setPestType(PestTypeEntity pestType) {
        this.pestType = pestType;
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
