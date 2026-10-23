package com.bit.backend.dtos;


public class CustomerDto {

    private Integer id;
    private String customerCode;
    private String customer_name;
    private String customer_type;
    private String email;
    private String phone;
    private String nic;
    private String billing_address;
    private StatusDto status;

    public CustomerDto() {
    }

    public CustomerDto(Integer id, String customerCode, String customer_name, String customer_type, String email, String phone, String nic, String billing_address, StatusDto status) {
        this.id = id;
        this.customerCode = customerCode;
        this.customer_name = customer_name;
        this.customer_type = customer_type;
        this.email = email;
        this.phone = phone;
        this.nic = nic;
        this.billing_address = billing_address;
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

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_type() {
        return customer_type;
    }

    public void setCustomer_type(String customer_type) {
        this.customer_type = customer_type;
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

    public String getBilling_address() {
        return billing_address;
    }

    public void setBilling_address(String billing_address) {
        this.billing_address = billing_address;
    }

    public StatusDto getStatus() {
        return status;
    }

    public void setStatus(StatusDto status) {
        this.status = status;
    }
}
