package com.bit.backend.controllers;

import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.ChemicalDto;
import com.bit.backend.dtos.CustomerSiteDto;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.services.ChemicalServiceI;
import com.bit.backend.services.CustomerSiteServiceI;
import com.bit.backend.services.StatusServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class ChemicalController {

    private final ChemicalServiceI chemicalServiceI;
    private final StatusServiceI statusServiceI;

    public ChemicalController(ChemicalServiceI chemicalServiceI, StatusServiceI statusServiceI) {
        this.chemicalServiceI = chemicalServiceI;
        this.statusServiceI = statusServiceI;
    }

    @GetMapping("/chemical-status")
    public ResponseEntity<ApiListResponse<StatusDto>> getAllChemicalStatus() {
        return ResponseEntity.ok(ApiListResponse.of(statusServiceI.getAllChemicalStatus()));
    }

    @PostMapping("/chemical-add")
    public ResponseEntity<ApiListResponse<ChemicalDto>> addChemical(@RequestBody ChemicalDto chemicalDto){
        ChemicalDto saved = chemicalServiceI.addChemical(chemicalDto);
        return ResponseEntity.created(URI.create("/api/v1/chemical-add/" + saved.getId()))
                .body(ApiListResponse.ofOne(saved));
    }

    @GetMapping("/chemicals")
    public ResponseEntity<ApiListResponse<ChemicalDto>> getAllChemicals(){
        return ResponseEntity.ok(ApiListResponse.of(chemicalServiceI.getAllChemicals()));
    }

    @GetMapping("/chemical/{id}")
    public ResponseEntity<ApiListResponse<ChemicalDto>> getChemicalById(@PathVariable Integer id){
        return ResponseEntity.ok(ApiListResponse.ofOne(chemicalServiceI.getChemicalById(id)));
    }

    @PutMapping("/chemical/{id}")
    public ResponseEntity<ApiListResponse<ChemicalDto>> updateChemical(
            @PathVariable Integer id,
            @RequestBody ChemicalDto chemicalDto){
        return ResponseEntity.ok(ApiListResponse.ofOne(chemicalServiceI.updateChemical(id, chemicalDto)));
    }

    @DeleteMapping("/chemical/{id}")
    public ResponseEntity<ApiListResponse<ChemicalDto>> deleteChemical(@PathVariable Integer id){
        return ResponseEntity.ok(ApiListResponse.ofOne(chemicalServiceI.deleteChemical(id)));
    }
}
