package com.bit.backend.controllers;

import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.CustomerDto;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.services.CustomerServiceI;
import com.bit.backend.services.StatusServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    private final CustomerServiceI customerServiceI;
    private final StatusServiceI statusServiceI;

    public CustomerController(CustomerServiceI customerServiceI, StatusServiceI statusServiceI) {
        this.customerServiceI = customerServiceI;
        this.statusServiceI = statusServiceI;
    }

    @GetMapping("/customer-status")
    public ResponseEntity<ApiListResponse<StatusDto>> getAllCustomerStatus() {
        return ResponseEntity.ok(ApiListResponse.of(statusServiceI.getAllCustomerStatus()));
    }

    @PostMapping("/customer-add")
    public ResponseEntity<ApiListResponse<CustomerDto>> addCustomer(@RequestBody CustomerDto customerDto){
        CustomerDto saved = customerServiceI.addCustomer(customerDto);
        return ResponseEntity.created(URI.create("/api/v1/customer-add/" + saved.getId()))
                .body(ApiListResponse.ofOne(saved));
    }

    @GetMapping("/customers")
    public ResponseEntity<ApiListResponse<CustomerDto>> getAllCustomers(){
        return ResponseEntity.ok(ApiListResponse.of(customerServiceI.getAllCustomers()));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<ApiListResponse<CustomerDto>> getCustomerById(@PathVariable Integer id){
        return ResponseEntity.ok(ApiListResponse.ofOne(customerServiceI.getCustomerById(id)));
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<ApiListResponse<CustomerDto>> updateCustomer(
            @PathVariable Integer id,
            @RequestBody CustomerDto customerDto){
        return ResponseEntity.ok(ApiListResponse.ofOne(customerServiceI.updateCustomer(id, customerDto)));
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<ApiListResponse<CustomerDto>> deleteCustomer(@PathVariable Integer id){
        return ResponseEntity.ok(ApiListResponse.ofOne(customerServiceI.deleteCustomer(id)));
    }
}
