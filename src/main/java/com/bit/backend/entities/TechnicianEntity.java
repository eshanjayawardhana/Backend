package com.bit.backend.entities;


import jakarta.persistence.*;


@Entity
@Table(name = "technician")
public class TechnicianEntity extends AuditableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "technician_code")
    private String technicianCode;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "license_no")
    private String licenseNo;

    @Column(name = "specialization")
    private String specialization;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUserEntity user;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusEntity status;

//    @Column(name = "created_date")
//    private LocalDateTime createdDate;

//    @ManyToOne
//    @JoinColumn(name = "created_by")
//    private AppUserEntity createdBy;

//    @Column(name = "updated_date")
//    private LocalDateTime updatedDate;

//    @ManyToOne
//    @JoinColumn(name = "updated_by")
//    private AppUserEntity updatedBy;

    public TechnicianEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public AppUserEntity getUser() {
        return user;
    }

    public void setUser(AppUserEntity user) {
        this.user = user;
    }

    public StatusEntity getStatus() {
        return status;
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }


}
