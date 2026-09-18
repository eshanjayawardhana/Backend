package com.bit.backend.dtos;

import com.bit.backend.entities.AppUserEntity;
import com.bit.backend.entities.StatusEntity;


public class TechnicianDto {

    private Long id;
    private String technicianCode;
    private String fullName;
    private String phone;
    private String licenseNo;
    private String specialization;
    private UserDto user;
    private StatusDto status;

    public TechnicianDto() {
    }

    public TechnicianDto(Long id, String technicianCode, String fullName, String phone, String licenseNo, String specialization, UserDto user, StatusDto status) {
        this.id = id;
        this.technicianCode = technicianCode;
        this.fullName = fullName;
        this.phone = phone;
        this.licenseNo = licenseNo;
        this.specialization = specialization;
        this.user = user;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTechnicianCode() {
        return technicianCode;
    }

    public void setTechnicianCode(String technicianCode) {
        this.technicianCode = technicianCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public StatusDto getStatus() {
        return status;
    }

    public void setStatus(StatusDto status) {
        this.status = status;
    }
}
