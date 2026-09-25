package com.bit.backend.dtos;

import com.bit.backend.entities.*;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ServiceRequestDto {

    private Integer id;
    private String requestCode;
    private LocalDateTime requestDate;
    private LocalDate preferredDate;
    private String problemDescription;
    private String priority;
    private CustomerDto customer;
    private CustomerSiteDto customerSite;
    private PestTypeDto pestType;
    private ServiceTypeDto serviceType;
    private StatusDto status;

    public ServiceRequestDto() {
    }

    public ServiceRequestDto(Integer id, String requestCode, LocalDateTime requestDate, LocalDate preferredDate, String problemDescription, String priority, CustomerDto customer, CustomerSiteDto customerSite, PestTypeDto pestType, ServiceTypeDto serviceType, StatusDto status) {
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

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public CustomerSiteDto getCustomerSite() {
        return customerSite;
    }

    public void setCustomerSite(CustomerSiteDto customerSite) {
        this.customerSite = customerSite;
    }

    public PestTypeDto getPestType() {
        return pestType;
    }

    public void setPestType(PestTypeDto pestType) {
        this.pestType = pestType;
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
