package com.bit.backend.controllers;

import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.ServiceRequestDto;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.services.ServiceRequestServiceI;
import com.bit.backend.services.StatusServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class ServiceRequestController {

    private final ServiceRequestServiceI serviceRequestServiceI;
    private final StatusServiceI statusServiceI;

    public ServiceRequestController(ServiceRequestServiceI serviceRequestServiceI, StatusServiceI statusServiceI) {
        this.serviceRequestServiceI = serviceRequestServiceI;
        this.statusServiceI = statusServiceI;
    }

    @GetMapping("/service-request-status")
    public ResponseEntity<ApiListResponse<StatusDto>> getAllServiceRequestStatus() {
        return ResponseEntity.ok(ApiListResponse.of(statusServiceI.getAllServiceRequestStatus()));
    }

    @PostMapping("/service-request-add")
    public ResponseEntity<ApiListResponse<ServiceRequestDto>> addServiceRequest(@RequestBody ServiceRequestDto serviceRequestDto){
        ServiceRequestDto saved = serviceRequestServiceI.addServiceRequest(serviceRequestDto);
        return ResponseEntity.created(URI.create("/api/v1/service-request-add/" + saved.getId()))
                .body(ApiListResponse.ofOne(saved));
    }

    @GetMapping("/service-requests")
    public ResponseEntity<ApiListResponse<ServiceRequestDto>> getServiceRequests(){
        return ResponseEntity.ok(ApiListResponse.of(serviceRequestServiceI.getServiceRequests()));
    }

    @GetMapping("/service-request/{id}")
    public ResponseEntity<ApiListResponse<ServiceRequestDto>> getServiceRequestById(@PathVariable Integer id){
        return ResponseEntity.ok(ApiListResponse.ofOne(serviceRequestServiceI.getServiceRequestById(id)));
    }

    @PutMapping("/service-request/{id}")
    public ResponseEntity<ApiListResponse<ServiceRequestDto>> updateServiceRequest(
            @PathVariable Integer id,
            @RequestBody ServiceRequestDto serviceRequestDto){
        return ResponseEntity.ok(ApiListResponse.ofOne(serviceRequestServiceI.updateServiceRequest(id, serviceRequestDto)));
    }

    @DeleteMapping("/service-request/{id}")
    public ResponseEntity<ApiListResponse<ServiceRequestDto>> deleteServiceRequest(@PathVariable Integer id){
        return ResponseEntity.ok(ApiListResponse.ofOne(serviceRequestServiceI.deleteServiceRequest(id)));
    }
}
