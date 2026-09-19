package com.bit.backend.controllers;

import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.dtos.TechnicianDto;
import com.bit.backend.services.StatusServiceI;
import com.bit.backend.services.TechnicianServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class TechnicianController {

    private final TechnicianServiceI technicianServiceI;
    private final StatusServiceI statusServiceI;

    public TechnicianController(TechnicianServiceI technicianServiceI, StatusServiceI statusServiceI) {
        this.technicianServiceI = technicianServiceI;
        this.statusServiceI = statusServiceI;
    }

    @GetMapping("/technician-status")
    public ResponseEntity<ApiListResponse<StatusDto>> getAllStatus() {
        return ResponseEntity.ok(ApiListResponse.of(statusServiceI.getAllStatus()));
    }

    @PostMapping("/technician")
    public ResponseEntity<ApiListResponse<TechnicianDto>> addTechnician(@RequestBody TechnicianDto technicianDto) {
        TechnicianDto created = technicianServiceI.addTechnician(technicianDto);
        return ResponseEntity.created(URI.create("/api/v1/technician/" + created.getId()))
                .body(ApiListResponse.ofOne(created));
    }

    @GetMapping("/technicians")
    public ResponseEntity<ApiListResponse<TechnicianDto>> getAllTechnicians() {
        return ResponseEntity.ok(ApiListResponse.of(technicianServiceI.getAllTechnicians()));
    }

    @GetMapping("/technician/{id}")
    public ResponseEntity<ApiListResponse<TechnicianDto>> getTechnicianById(@PathVariable Integer id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(technicianServiceI.getTechnicianById(id)));
    }



    @PutMapping("/technician/{id}")
    public ResponseEntity<ApiListResponse<TechnicianDto>> updateTechnician(
            @PathVariable Integer id,
            @RequestBody TechnicianDto technicianDto) {
        return ResponseEntity.ok(ApiListResponse.ofOne(technicianServiceI.updateTechnician(id, technicianDto)));
    }

    @DeleteMapping("/technician/{id}")
    public ResponseEntity<ApiListResponse<TechnicianDto>> deleteTechnician(@PathVariable Integer id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(technicianServiceI.deleteTechnician(id)));
    }
}
