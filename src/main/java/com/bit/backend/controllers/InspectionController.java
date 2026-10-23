package com.bit.backend.controllers;


import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.CustomerDto;
import com.bit.backend.dtos.InspectionDto;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.services.InspectionServiceI;
import com.bit.backend.services.StatusServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class InspectionController {

    private final InspectionServiceI inspectionServiceI;
    private final StatusServiceI statusServiceI;

    public InspectionController(InspectionServiceI inspectionServiceI, StatusServiceI statusServiceI) {
        this.inspectionServiceI = inspectionServiceI;
        this.statusServiceI = statusServiceI;
    }

    @GetMapping("/inspection-status")
    public ResponseEntity<ApiListResponse<StatusDto>> getAllInspectionStatus() {
        return ResponseEntity.ok(ApiListResponse.of(statusServiceI.getAllInspectionStatus()));
    }

    @PostMapping("/inspection-add")
    public ResponseEntity<ApiListResponse<InspectionDto>> addInspection(@RequestBody InspectionDto inspectionDto){
        InspectionDto saved = inspectionServiceI.addInspection(inspectionDto);
        return ResponseEntity.created(URI.create("/api/v1/inspection-add/" + saved.getId()))
                .body(ApiListResponse.ofOne(saved));
    }

    @GetMapping("/inspections")
    public ResponseEntity<ApiListResponse<InspectionDto>> getAllInspections(){
        return ResponseEntity.ok(ApiListResponse.of(inspectionServiceI.getAllInspections()));
    }

    @GetMapping("/inspection/{id}")
    public ResponseEntity<ApiListResponse<InspectionDto>> getInspectionById(@PathVariable Integer id){
        return ResponseEntity.ok(ApiListResponse.ofOne(inspectionServiceI.getInspectionById(id)));
    }

    @PutMapping("/inspection/{id}")
    public ResponseEntity<ApiListResponse<InspectionDto>> updateInspection(
            @PathVariable Integer id,
            @RequestBody InspectionDto inspectionDto){
        return ResponseEntity.ok(ApiListResponse.ofOne(inspectionServiceI.updateInspection(id, inspectionDto)));
    }

    @DeleteMapping("/inspection/{id}")
    public ResponseEntity<ApiListResponse<InspectionDto>> deleteInspection(@PathVariable Integer id){
        return ResponseEntity.ok(ApiListResponse.ofOne(inspectionServiceI.deleteInspection(id)));
    }
}
