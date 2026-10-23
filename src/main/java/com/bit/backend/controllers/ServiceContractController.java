package com.bit.backend.controllers;

import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.ServiceContractDto;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.services.ServiceContractServiceI;
import com.bit.backend.services.StatusServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class ServiceContractController {

    private final StatusServiceI statusServiceI;
    private final ServiceContractServiceI serviceContractServiceI;

    public ServiceContractController(StatusServiceI statusServiceI, ServiceContractServiceI serviceContractServiceI) {
        this.statusServiceI = statusServiceI;
        this.serviceContractServiceI = serviceContractServiceI;
    }

    @GetMapping("/service-contract-status")
    public ResponseEntity<ApiListResponse<StatusDto>> getAllServiceContractStatus() {
        return ResponseEntity.ok(ApiListResponse.of(statusServiceI.getAllServiceContractStatus()));
    }

    @PostMapping("/service-contract-add")
    public ResponseEntity<ApiListResponse<ServiceContractDto>> addServiceContract(@RequestBody ServiceContractDto serviceContractDto) {

        ServiceContractDto saved = serviceContractServiceI.addServiceContract(serviceContractDto);

        return ResponseEntity.created(URI.create("/api/v1/service-contract-add/" + saved.getId()))
                .body(ApiListResponse.ofOne(saved));
    }

    @GetMapping("/service-contracts")
    public ResponseEntity<ApiListResponse<ServiceContractDto>> getServiceContracts() {
        return ResponseEntity.ok(ApiListResponse.of(serviceContractServiceI.getServiceContracts()));
    }

    @GetMapping("/service-contract/{id}")
    public ResponseEntity<ApiListResponse<ServiceContractDto>> getServiceContractById(@PathVariable Integer id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(serviceContractServiceI.getServiceContractById(id)));
    }

    @PutMapping("/service-contract/{id}")
    public ResponseEntity<ApiListResponse<ServiceContractDto>> updateServiceContract(
            @PathVariable Integer id,
            @RequestBody ServiceContractDto serviceContractDto) {
        return ResponseEntity.ok(ApiListResponse.ofOne(serviceContractServiceI.updateServiceContract(id, serviceContractDto)));
    }

    @DeleteMapping("/service-contract/{id}")
    public ResponseEntity<ApiListResponse<ServiceContractDto>> deleteServiceContract(@PathVariable Integer id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(serviceContractServiceI.deleteServiceContract(id)));
    }
}
