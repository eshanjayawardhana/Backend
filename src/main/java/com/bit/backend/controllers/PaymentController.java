package com.bit.backend.controllers;

import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.PaymentDto;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.services.PaymentServiceI;
import com.bit.backend.services.StatusServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class PaymentController {

    private final PaymentServiceI paymentServiceI;
    private final StatusServiceI statusServiceI;

    public PaymentController(
            PaymentServiceI paymentServiceI,
            StatusServiceI statusServiceI) {

        this.paymentServiceI = paymentServiceI;
        this.statusServiceI = statusServiceI;
    }

    @GetMapping("/payment-status")
    public ResponseEntity<ApiListResponse<StatusDto>> getAllPaymentStatus() {
        return ResponseEntity.ok(ApiListResponse.of(statusServiceI.getAllPaymentStatus()));
    }

    @PostMapping("/payment-add")
    public ResponseEntity<ApiListResponse<PaymentDto>> addPayment(
            @RequestBody PaymentDto paymentDto) {

        PaymentDto saved = paymentServiceI.addPayment(paymentDto);

        return ResponseEntity
                .created(URI.create("/api/v1/payment-add/" + saved.getId()))
                .body(ApiListResponse.ofOne(saved));
    }

    @GetMapping("/payments")
    public ResponseEntity<ApiListResponse<PaymentDto>> getAllPayments() {
        return ResponseEntity.ok(
                ApiListResponse.of(
                        paymentServiceI.getAllPayments()
                )
        );
    }

    @GetMapping("/payment/{id}")
    public ResponseEntity<ApiListResponse<PaymentDto>> getPaymentById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                ApiListResponse.ofOne(
                        paymentServiceI.getPaymentById(id)
                )
        );
    }

    @PutMapping("/payment/{id}")
    public ResponseEntity<ApiListResponse<PaymentDto>> updatePayment(
            @PathVariable Integer id,
            @RequestBody PaymentDto paymentDto) {

        return ResponseEntity.ok(
                ApiListResponse.ofOne(
                        paymentServiceI.updatePayment(id, paymentDto)
                )
        );
    }

    @DeleteMapping("/payment/{id}")
    public ResponseEntity<ApiListResponse<PaymentDto>> deletePayment(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                ApiListResponse.ofOne(
                        paymentServiceI.deletePayment(id)
                )
        );
    }
}
