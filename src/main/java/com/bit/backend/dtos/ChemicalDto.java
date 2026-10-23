package com.bit.backend.dtos;

import com.bit.backend.entities.StatusEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ChemicalDto {

    private Integer id;
    private String chemicalCode;
    private String chemicalName;
    private String activeIngredient;
    private String unit;
    private BigDecimal stockQuantity;
    private BigDecimal reorderLevel;
    private LocalDate expiryDate;
    private String safetyNotes;
    private StatusDto status;

    public ChemicalDto() {
    }

    public ChemicalDto(Integer id, String chemicalCode, String chemicalName, String activeIngredient, String unit, BigDecimal stockQuantity, BigDecimal reorderLevel, LocalDate expiryDate, String safetyNotes, StatusDto status) {
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

    public StatusDto getStatus() {
        return status;
    }

    public void setStatus(StatusDto status) {
        this.status = status;
    }
}
