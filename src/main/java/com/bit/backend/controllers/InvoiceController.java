package com.bit.backend.controllers;

import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.InvoiceDto;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.services.InvoiceServiceI;
import com.bit.backend.services.StatusServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class InvoiceController {

    private final InvoiceServiceI invoiceServiceI;
    private final StatusServiceI statusServiceI;

    public InvoiceController(InvoiceServiceI invoiceServiceI, StatusServiceI statusServiceI) {
        this.invoiceServiceI = invoiceServiceI;
        this.statusServiceI = statusServiceI;
    }

    @GetMapping("/invoice-status")
    public ResponseEntity<ApiListResponse<StatusDto>> getAllInvoiceStatus() {
        return ResponseEntity.ok(ApiListResponse.of(statusServiceI.getAllInvoiceStatus()));
    }

    @PostMapping("/invoice-add")
    public ResponseEntity<ApiListResponse<InvoiceDto>> addInvoice(@RequestBody InvoiceDto invoiceDto) {
        InvoiceDto saved = invoiceServiceI.addInvoice(invoiceDto);
        return ResponseEntity.created(URI.create("/api/v1/invoice-add/" + saved.getId()))
                .body(ApiListResponse.ofOne(saved));
    }

    @GetMapping("/invoices")
    public ResponseEntity<ApiListResponse<InvoiceDto>> getAllInvoices() {
        return ResponseEntity.ok(ApiListResponse.of(invoiceServiceI.getAllInvoices()));
    }

    @GetMapping("/invoice/{id}")
    public ResponseEntity<ApiListResponse<InvoiceDto>> getInvoiceById(@PathVariable Integer id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(invoiceServiceI.getInvoiceById(id)));
    }

    @PutMapping("/invoice/{id}")
    public ResponseEntity<ApiListResponse<InvoiceDto>> updateInvoice(
            @PathVariable Integer id,
            @RequestBody InvoiceDto invoiceDto) {

        return ResponseEntity.ok(ApiListResponse.ofOne(invoiceServiceI.updateInvoice(id, invoiceDto)));
    }

    @DeleteMapping("/invoice/{id}")
    public ResponseEntity<ApiListResponse<InvoiceDto>> deleteInvoice(@PathVariable Integer id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(invoiceServiceI.deleteInvoice(id)));
    }
}
