package com.bit.backend.services;

import com.bit.backend.dtos.CustomerDto;

import java.util.List;

public interface CustomerServiceI {
    CustomerDto addCustomer(CustomerDto customerDto);

    List<CustomerDto> getAllCustomers();

    CustomerDto getCustomerById(Integer id);

    CustomerDto updateCustomer(Integer id, CustomerDto customerDto);

    CustomerDto deleteCustomer(Integer id);
}
