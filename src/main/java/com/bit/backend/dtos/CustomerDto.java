package com.bit.backend.dtos;


public class CustomerDto {

    private Integer id;
    private String customerCode;
    private String customerName;
    private String customerType;
    private String email;
    private String phone;
    private String nic;
    private String billingAddress;
    private StatusDto status;

    public CustomerDto() {
    }

    public CustomerDto(Integer id, String customerCode, String customerName, String customerType, String email, String phone, String nic, String billingAddress, StatusDto status) {
        this.id = id;
        this.customerCode = customerCode;
        this.customerName = customerName;
        this.customerType = customerType;
        this.email = email;
        this.phone = phone;
        this.nic = nic;
        this.billingAddress = billingAddress;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public StatusDto getStatus() {
        return status;
    }

    public void setStatus(StatusDto status) {
        this.status = status;
    }
}
