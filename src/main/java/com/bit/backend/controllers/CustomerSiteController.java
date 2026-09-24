package com.bit.backend.controllers;

import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.CustomerDto;
import com.bit.backend.dtos.CustomerSiteDto;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.entities.StatusEntity;
import com.bit.backend.services.CustomerSiteServiceI;
import com.bit.backend.services.StatusServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class CustomerSiteController {

    private final CustomerSiteServiceI customerSiteServiceI;
    private final StatusServiceI statusServiceI;

    public CustomerSiteController(CustomerSiteServiceI customerSiteServiceI, StatusServiceI statusServiceI) {
        this.customerSiteServiceI = customerSiteServiceI;
        this.statusServiceI = statusServiceI;
    }

    @GetMapping("/customer-site-status")
    public ResponseEntity<ApiListResponse<StatusDto>> getAllCustomerSiteStatus() {
        return ResponseEntity.ok(ApiListResponse.of(statusServiceI.getAllCustomerSiteStatus()));
    }

    @PostMapping("/customer-site-add")
    public ResponseEntity<ApiListResponse<CustomerSiteDto>> addCustomerSite(@RequestBody CustomerSiteDto customerSiteDto){
        CustomerSiteDto saved = customerSiteServiceI.addCustomerSite(customerSiteDto);
        return ResponseEntity.created(URI.create("/api/v1/customer-site-add/" + saved.getId()))
                .body(ApiListResponse.ofOne(saved));
    }

    @GetMapping("/customer-sites")
    public ResponseEntity<ApiListResponse<CustomerSiteDto>> getAllCustomerSites(){
        return ResponseEntity.ok(ApiListResponse.of(customerSiteServiceI.getAllCustomerSites()));
    }

    @GetMapping("/customer-site/{id}")
    public ResponseEntity<ApiListResponse<CustomerSiteDto>> getCustomerSiteById(@PathVariable Integer id){
        return ResponseEntity.ok(ApiListResponse.ofOne(customerSiteServiceI.getCustomerSiteById(id)));
    }

    @PutMapping("/customer-site/{id}")
    public ResponseEntity<ApiListResponse<CustomerSiteDto>> updateCustomerSite(
            @PathVariable Integer id,
            @RequestBody CustomerSiteDto customerSiteDto){
        return ResponseEntity.ok(ApiListResponse.ofOne(customerSiteServiceI.updateCustomerSite(id, customerSiteDto)));
    }

    @DeleteMapping("/customer-site/{id}")
    public ResponseEntity<ApiListResponse<CustomerSiteDto>> deleteCustomerSite(@PathVariable Integer id){
        return ResponseEntity.ok(ApiListResponse.ofOne(customerSiteServiceI.deleteCustomerSite(id)));
    }
}
