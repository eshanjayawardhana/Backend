package com.bit.backend.controllers;

import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.CustomerDto;
import com.bit.backend.dtos.ServiceTypeDto;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.services.ServiceTypeI;
import com.bit.backend.services.StatusServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class ServiceTypeController {

    private final ServiceTypeI serviceTypeI;
    private final StatusServiceI statusServiceI;

    public ServiceTypeController(ServiceTypeI serviceTypeI, StatusServiceI statusServiceI) {
        this.serviceTypeI = serviceTypeI;
        this.statusServiceI = statusServiceI;
    }

    @GetMapping("/service-type-status")
    public ResponseEntity<ApiListResponse<StatusDto>> getAllServiceTypeStatus() {
        return ResponseEntity.ok(ApiListResponse.of(statusServiceI.getAllServiceTypeStatus()));
    }

    @PostMapping("/service-type")
    public ResponseEntity<ApiListResponse<ServiceTypeDto>> addServiceType(@RequestBody ServiceTypeDto serviceTypeDto){
        ServiceTypeDto saved = serviceTypeI.addServiceType(serviceTypeDto);
        return ResponseEntity.created(URI.create("/api/v1/service-type/" + saved.getId()))
                .body(ApiListResponse.ofOne(saved));
    }

    @GetMapping("/service-types")
    public ResponseEntity<ApiListResponse<ServiceTypeDto>> getAllServiceTypes(){
        return ResponseEntity.ok(ApiListResponse.of(serviceTypeI.getAllServiceTypes()));
    }

    @GetMapping("/service-type/{id}")
    public ResponseEntity<ApiListResponse<ServiceTypeDto>> getServiceTypeById(@PathVariable Integer id){
        return ResponseEntity.ok(ApiListResponse.ofOne(serviceTypeI.getServiceTypeById(id)));
    }

    @PutMapping("/service-type/{id}")
    public ResponseEntity<ApiListResponse<ServiceTypeDto>> updateServiceType(
            @PathVariable Integer id,
            @RequestBody ServiceTypeDto serviceTypeDto){
        return ResponseEntity.ok(ApiListResponse.ofOne(serviceTypeI.updateServiceType(id, serviceTypeDto)));
    }

    @DeleteMapping("/service-type/{id}")
    public ResponseEntity<ApiListResponse<ServiceTypeDto>> deleteServiceType(@PathVariable Integer id){
        return ResponseEntity.ok(ApiListResponse.ofOne(serviceTypeI.deleteServiceType(id)));
    }
}
