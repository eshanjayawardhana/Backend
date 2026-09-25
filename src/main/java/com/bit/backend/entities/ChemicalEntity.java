package com.bit.backend.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "chemical")
public class ChemicalEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "chemical_code")
    private String chemicalCode;

    @Column(name = "chemical_name", nullable = false)
    private String chemicalName;

    @Column(name = "active_ingredient")
    private String activeIngredient;

    @Column(name = "unit")
    private String unit;

    @Column(name = "stock_quantity", precision = 10, scale = 2)
    private BigDecimal stockQuantity;

    @Column(name = "reorder_level", precision = 10, scale = 2)
    private BigDecimal reorderLevel;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(name = "safety_notes")
    private String safetyNotes;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusEntity status;

    public ChemicalEntity() {
    }

    public ChemicalEntity(Integer id, String chemicalCode, String chemicalName, String activeIngredient, String unit, BigDecimal stockQuantity, BigDecimal reorderLevel, LocalDate expiryDate, String safetyNotes, StatusEntity status) {
        this.id = id;
        this.chemicalCode = chemicalCode;
        this.chemicalName = chemicalName;
        this.activeIngredient = activeIngredient;
        this.unit = unit;
        this.stockQuantity = stockQuantity;
        this.reorderLevel = reorderLevel;
        this.expiryDate = expiryDate;
        this.safetyNotes = safetyNotes;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChemicalCode() {
        return chemicalCode;
    }

    public void setChemicalCode(String chemicalCode) {
        this.chemicalCode = chemicalCode;
    }

    public String getChemicalName() {
        return chemicalName;
    }

    public void setChemicalName(String chemicalName) {
        this.chemicalName = chemicalName;
    }

    public String getActiveIngredient() {
        return activeIngredient;
    }

    public void setActiveIngredient(String activeIngredient) {
        this.activeIngredient = activeIngredient;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(BigDecimal stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public BigDecimal getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(BigDecimal reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getSafetyNotes() {
        return safetyNotes;
    }

    public void setSafetyNotes(String safetyNotes) {
        this.safetyNotes = safetyNotes;
    }

    public StatusEntity getStatus() {
        return status;
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }
}
