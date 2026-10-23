package com.bit.backend.dtos;

import com.bit.backend.entities.CustomerEntity;
import com.bit.backend.entities.CustomerSiteEntity;
import com.bit.backend.entities.ServiceTypeEntity;
import com.bit.backend.entities.StatusEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ServiceContractDto {

    private Integer id;
    private String contractCode;
    private LocalDate startDate;
    private LocalDate endDate;
    private String visitFrequency;
    private BigDecimal contractAmount;
    private String terms;
    private CustomerEntity customer;
    private CustomerSiteEntity customerSite;
    private ServiceTypeEntity serviceType;
    private StatusEntity status;

    public ServiceContractDto() {
    }

    public ServiceContractDto(Integer id, String contractCode, LocalDate startDate, LocalDate endDate, String visitFrequency, BigDecimal contractAmount, String terms, CustomerEntity customer, CustomerSiteEntity customerSite, ServiceTypeEntity serviceType, StatusEntity status) {
        this.id = id;
        this.contractCode = contractCode;
        this.startDate = startDate;
        this.endDate = endDate;
        this.visitFrequency = visitFrequency;
        this.contractAmount = contractAmount;
        this.terms = terms;
        this.customer = customer;
        this.customerSite = customerSite;
        this.serviceType = serviceType;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getVisitFrequency() {
        return visitFrequency;
    }

    public void setVisitFrequency(String visitFrequency) {
        this.visitFrequency = visitFrequency;
    }

    public BigDecimal getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(BigDecimal contractAmount) {
        this.contractAmount = contractAmount;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
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
