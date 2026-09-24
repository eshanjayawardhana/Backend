package com.bit.backend.dtos;

import com.bit.backend.entities.PestTypeEntity;
import com.bit.backend.entities.StatusEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;

public class ServiceTypeDto {

    private Integer id;
    private String serviceCode;
    private String serviceName;
    private String description;
    private BigDecimal basePrice;
    private BigDecimal estimatedDurationHrs;
    private PestTypeDto pestType;
    private StatusDto status;

    public ServiceTypeDto() {
    }

    public ServiceTypeDto(Integer id, String serviceCode, String serviceName, String description, BigDecimal basePrice, BigDecimal estimatedDurationHrs, PestTypeDto pestType, StatusDto status) {
        this.id = id;
        this.serviceCode = serviceCode;
        this.serviceName = serviceName;
        this.description = description;
        this.basePrice = basePrice;
        this.estimatedDurationHrs = estimatedDurationHrs;
        this.pestType = pestType;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public BigDecimal getEstimatedDurationHrs() {
        return estimatedDurationHrs;
    }

    public void setEstimatedDurationHrs(BigDecimal estimatedDurationHrs) {
        this.estimatedDurationHrs = estimatedDurationHrs;
    }

    public PestTypeDto getPestType() {
        return pestType;
    }

    public void setPestType(PestTypeDto pestType) {
        this.pestType = pestType;
    }

    public StatusDto getStatus() {
        return status;
    }

    public void setStatus(StatusDto status) {
        this.status = status;
    }
}

