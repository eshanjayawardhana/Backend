package com.bit.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "customer_site")
public class CustomerSiteEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "site_code")
    private String siteCode;

    @Column(name = "site_name")
    private String siteName;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "contact_person")
    private String contactPerson;

    @Column(name = "contact_phone")
    private String contactPhone;

    @Column(name = "site_notes")
    private String siteNotes;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusEntity status;


    public CustomerSiteEntity() {
    }

    public CustomerSiteEntity(Integer id, String siteCode, String siteName, String address, String city, String contactPerson, String contactPhone, String siteNotes, CustomerEntity customer, StatusEntity status) {
        this.id = id;
        this.siteCode = siteCode;
        this.siteName = siteName;
        this.address = address;
        this.city = city;
        this.contactPerson = contactPerson;
        this.contactPhone = contactPhone;
        this.siteNotes = siteNotes;
        this.customer = customer;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getSiteNotes() {
        return siteNotes;
    }

    public void setSiteNotes(String siteNotes) {
        this.siteNotes = siteNotes;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public StatusEntity getStatus() {
        return status;
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }
}
