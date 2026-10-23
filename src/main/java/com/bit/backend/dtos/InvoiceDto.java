package com.bit.backend.dtos;

import com.bit.backend.entities.CustomerEntity;
import com.bit.backend.entities.ServiceContractEntity;
import com.bit.backend.entities.StatusEntity;
import com.bit.backend.entities.TreatmentJobEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InvoiceDto {

    private Integer id;
    private String invoiceCode;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private BigDecimal subtotal;
    private BigDecimal taxAmount;
    private BigDecimal totalAmount;
    private CustomerDto customer;
    private TreatmentJobDto treatmentJob;
    private ServiceContractDto serviceContract;
    private StatusDto status;

    public InvoiceDto() {
    }

    public InvoiceDto(Integer id, String invoiceCode, LocalDate issueDate, LocalDate dueDate, BigDecimal subtotal, BigDecimal taxAmount, BigDecimal totalAmount, CustomerDto customer, TreatmentJobDto treatmentJob, ServiceContractDto serviceContract, StatusDto status) {
        this.id = id;
        this.invoiceCode = invoiceCode;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.subtotal = subtotal;
        this.taxAmount = taxAmount;
        this.totalAmount = totalAmount;
        this.customer = customer;
        this.treatmentJob = treatmentJob;
        this.serviceContract = serviceContract;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public TreatmentJobDto getTreatmentJob() {
        return treatmentJob;
    }

    public void setTreatmentJob(TreatmentJobDto treatmentJob) {
        this.treatmentJob = treatmentJob;
    }

    public ServiceContractDto getServiceContract() {
        return serviceContract;
    }

    public void setServiceContract(ServiceContractDto serviceContract) {
        this.serviceContract = serviceContract;
    }

    public StatusDto getStatus() {
        return status;
    }

    public void setStatus(StatusDto status) {
        this.status = status;
    }
}
