package com.bit.backend.dtos;

public class CustomerSiteDto {

    private Integer id;
    private String siteCode;
    private String siteName;
    private String address;
    private String city;
    private String contactPerson;
    private String contactPhone;
    private String siteNotes;
    private CustomerDto customer;
    private StatusDto status;

    public CustomerSiteDto() {
    }

    public CustomerSiteDto(Integer id, String siteCode, String siteName, String address, String city, String contactPerson, String contactPhone, String siteNotes, CustomerDto customer, StatusDto status) {
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

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public StatusDto getStatus() {
        return status;
    }

    public void setStatus(StatusDto status) {
        this.status = status;
    }
}
