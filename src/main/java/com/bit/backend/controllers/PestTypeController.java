package com.bit.backend.controllers;

import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.CustomerSiteDto;
import com.bit.backend.dtos.PestTypeDto;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.services.PestTypeServiceI;
import com.bit.backend.services.StatusServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class PestTypeController {

    private final PestTypeServiceI pestTypeServiceI;
    private final StatusServiceI statusServiceI;

    public PestTypeController(PestTypeServiceI pestTypeServiceI, StatusServiceI statusServiceI) {
        this.pestTypeServiceI = pestTypeServiceI;
        this.statusServiceI = statusServiceI;
    }

    @GetMapping("/pest-type-status")
    public ResponseEntity<ApiListResponse<StatusDto>> getAllPestTypeStatus() {
        return ResponseEntity.ok(ApiListResponse.of(statusServiceI.getAllPestTypeStatus()));
    }

    @PostMapping("/pest-type-add")
    public ResponseEntity<ApiListResponse<PestTypeDto>> addPestType(@RequestBody PestTypeDto pestTypeDto){
        PestTypeDto saved = pestTypeServiceI.addPestType(pestTypeDto);
        return ResponseEntity.created(URI.create("/api/v1/pest-type-add/" + saved.getId()))
                .body(ApiListResponse.ofOne(saved));
    }

    @GetMapping("/pest-types")
    public ResponseEntity<ApiListResponse<PestTypeDto>> getAllPestTypes(){
        return ResponseEntity.ok(ApiListResponse.of(pestTypeServiceI.getAllPestTypes()));
    }

    @GetMapping("/pest-type/{id}")
    public ResponseEntity<ApiListResponse<PestTypeDto>> getPestTypeById(@PathVariable Integer id){
        return ResponseEntity.ok(ApiListResponse.ofOne(pestTypeServiceI.getPestTypeById(id)));
    }

    @PutMapping("/pest-type/{id}")
    public ResponseEntity<ApiListResponse<PestTypeDto>> updatePestType(
            @PathVariable Integer id,
            @RequestBody PestTypeDto pestTypeDto){
        return ResponseEntity.ok(ApiListResponse.ofOne(pestTypeServiceI.updatePestType(id, pestTypeDto)));
    }

    @DeleteMapping("/pest-type/{id}")
    public ResponseEntity<ApiListResponse<PestTypeDto>> deletePestType(@PathVariable Integer id){
        return ResponseEntity.ok(ApiListResponse.ofOne(pestTypeServiceI.deletePestType(id)));
    }
}
