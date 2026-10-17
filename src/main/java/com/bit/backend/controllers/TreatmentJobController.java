package com.bit.backend.controllers;

import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.ServiceRequestDto;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.dtos.TreatmentJobDto;
import com.bit.backend.services.StatusServiceI;
import com.bit.backend.services.TreatmentJobServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class TreatmentJobController {

    private final StatusServiceI statusServiceI;
    private final TreatmentJobServiceI treatmentJobServiceI;

    public TreatmentJobController(StatusServiceI statusServiceI, TreatmentJobServiceI treatmentJobServiceI) {
        this.statusServiceI = statusServiceI;
        this.treatmentJobServiceI = treatmentJobServiceI;
    }

    @GetMapping("/treatment-job-status")
    public ResponseEntity<ApiListResponse<StatusDto>> getAllTreatmentJobStatus() {
        return ResponseEntity.ok(ApiListResponse.of(statusServiceI.getAllTreatmentJobStatus()));
    }

    @PostMapping("/treatment-job-add")
    public ResponseEntity<ApiListResponse<TreatmentJobDto>> addTreatmentJob(@RequestBody TreatmentJobDto treatmentJobDto){
        TreatmentJobDto saved = treatmentJobServiceI.addTreatmentJob(treatmentJobDto);
        return ResponseEntity.created(URI.create("/api/v1/treatment-job-add/" + saved.getId()))
                .body(ApiListResponse.ofOne(saved));
    }

    @GetMapping("/treatment-jobs")
    public ResponseEntity<ApiListResponse<TreatmentJobDto>> getTreatmentJobs(){
        return ResponseEntity.ok(ApiListResponse.of(treatmentJobServiceI.getTreatmentJobs()));
    }

    @GetMapping("/treatment-job/{id}")
    public ResponseEntity<ApiListResponse<TreatmentJobDto>> getTreatmentJobById(@PathVariable Integer id){
        return ResponseEntity.ok(ApiListResponse.ofOne(treatmentJobServiceI.getTreatmentJobById(id)));
    }

    @PutMapping("/treatment-job/{id}")
    public ResponseEntity<ApiListResponse<TreatmentJobDto>> updateTreatmentJob(
            @PathVariable Integer id,
            @RequestBody TreatmentJobDto treatmentJobDto){
        return ResponseEntity.ok(ApiListResponse.ofOne(treatmentJobServiceI.updateTreatmentJob(id, treatmentJobDto)));
    }

    @DeleteMapping("/treatment-job/{id}")
    public ResponseEntity<ApiListResponse<TreatmentJobDto>> deleteTreatmentJob(@PathVariable Integer id){
        return ResponseEntity.ok(ApiListResponse.ofOne(treatmentJobServiceI.deleteTreatmentJob(id)));
    }
}
