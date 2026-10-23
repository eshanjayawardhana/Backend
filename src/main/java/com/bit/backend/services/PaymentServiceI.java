package com.bit.backend.services;

import com.bit.backend.dtos.PaymentDto;

import java.util.List;

public interface PaymentServiceI {
    PaymentDto addPayment(PaymentDto paymentDto);
    List<PaymentDto> getAllPayments();
    PaymentDto getPaymentById(Integer id);
    PaymentDto updatePayment(Integer id, PaymentDto paymentDto);
    PaymentDto deletePayment(Integer id);
}
