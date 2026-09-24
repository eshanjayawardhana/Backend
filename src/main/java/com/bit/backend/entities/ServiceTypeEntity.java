package com.bit.backend.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "service_type")
public class ServiceTypeEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "service_code")
    private String serviceCode;

    @Column(name = "service_name", nullable = false)
    private String serviceName;

    @Column(name = "description")
    private String description;

    @Column(name = "base_price", precision = 10, scale = 2)
    private BigDecimal basePrice;

    @Column(name = "estimated_duration_hrs", precision = 4, scale = 1)
    private BigDecimal estimatedDurationHrs;

    @ManyToOne
    @JoinColumn(name = "pest_type_id")
    private PestTypeEntity pestType;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusEntity status;

    public ServiceTypeEntity() {
    }

    public ServiceTypeEntity(Integer id, String serviceCode, String serviceName, String description, BigDecimal basePrice, BigDecimal estimatedDurationHrs, PestTypeEntity pestType, StatusEntity status) {
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

    public PestTypeEntity getPestType() {
        return pestType;
    }

    public void setPestType(PestTypeEntity pestType) {
        this.pestType = pestType;
    }

    public StatusEntity getStatus() {
        return status;
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }
}
