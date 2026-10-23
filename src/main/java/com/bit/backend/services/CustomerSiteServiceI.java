package com.bit.backend.services;

import com.bit.backend.dtos.CustomerSiteDto;

import java.util.List;

public interface CustomerSiteServiceI {
    CustomerSiteDto addCustomerSite(CustomerSiteDto customerSiteDto);

    List<CustomerSiteDto> getAllCustomerSites();

    CustomerSiteDto getCustomerSiteById(Integer id);

    CustomerSiteDto updateCustomerSite(Integer id, CustomerSiteDto customerSiteDto);

    CustomerSiteDto deleteCustomerSite(Integer id);
}
