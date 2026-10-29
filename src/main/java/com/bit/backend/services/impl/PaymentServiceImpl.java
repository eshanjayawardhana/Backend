package com.bit.backend.services.impl;

import com.bit.backend.dtos.PaymentDto;
import com.bit.backend.entities.InvoiceEntity;
import com.bit.backend.entities.PaymentEntity;
import com.bit.backend.entities.StatusEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.PaymentMapper;
import com.bit.backend.repositories.InvoiceRepository;
import com.bit.backend.repositories.PaymentRepository;
import com.bit.backend.repositories.StatusRepository;
import com.bit.backend.services.PaymentServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentServiceI {

    private final InvoiceRepository invoiceRepository;
    private final StatusRepository statusRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public PaymentServiceImpl(
            InvoiceRepository invoiceRepository,
            StatusRepository statusRepository,
            PaymentRepository paymentRepository,
            PaymentMapper paymentMapper) {

        this.invoiceRepository = invoiceRepository;
        this.statusRepository = statusRepository;
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
    }
    @Override
    @Transactional
    public PaymentDto addPayment(PaymentDto paymentDto) {

        PaymentEntity entity = paymentMapper.toPaymentEntity(paymentDto);
        entity.setId(null);

        InvoiceEntity invoice = resolveInvoice(paymentDto);
        entity.setInvoice(invoice);

        StatusEntity status = resolveStatus(paymentDto);
        entity.setStatus(status);

        PaymentEntity saved = paymentRepository.save(entity);

        // Generate ReferenceNo automatically when not provided
        if (saved.getReferenceNo() == null || saved.getReferenceNo().isBlank()) {
            saved.setReferenceNo("PAY-" + Year.now().getValue() + "-" + saved.getId());
            saved = paymentRepository.save(saved);
        }

        return paymentMapper.toPaymentDto(saved);
    }

    @Override
    public List<PaymentDto> getAllPayments() {
        return paymentMapper.toPaymentDtoList(paymentRepository.findAll());
    }

    @Override
    public PaymentDto getPaymentById(Integer id) {

        PaymentEntity entity = paymentRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        "Payment not found",
                        HttpStatus.NOT_FOUND
                ));

        return paymentMapper.toPaymentDto(entity);
    }

    @Override
    @Transactional
    public PaymentDto updatePayment(Integer id, PaymentDto paymentDto) {

        PaymentEntity existing = paymentRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        "Payment not found",
                        HttpStatus.NOT_FOUND
                ));

        InvoiceEntity invoice = resolveInvoice(paymentDto);
        StatusEntity status = resolveStatus(paymentDto);

        existing.setPaymentDate(paymentDto.getPaymentDate());
        existing.setAmount(paymentDto.getAmount());
        existing.setPaymentMethod(paymentDto.getPaymentMethod());
        existing.setReferenceNo(paymentDto.getReferenceNo());

        existing.setInvoice(invoice);
        existing.setStatus(status);

        PaymentEntity updated = paymentRepository.save(existing);

        return paymentMapper.toPaymentDto(updated);
    }

    @Override
    @Transactional
    public PaymentDto deletePayment(Integer id) {

        PaymentEntity existing = paymentRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        "Payment not found",
                        HttpStatus.NOT_FOUND
                ));

        PaymentDto dto = paymentMapper.toPaymentDto(existing);

        paymentRepository.delete(existing);

        return dto;
    }


    /////////////////// Resolvers ////////////////////

    private InvoiceEntity resolveInvoice(PaymentDto dto) {

        if (dto.getInvoice() == null || dto.getInvoice().getId() == null) {
            throw new AppException(
                    "Invoice ID is required",
                    HttpStatus.BAD_REQUEST
            );
        }

        return invoiceRepository.findById(dto.getInvoice().getId())
                .orElseThrow(() -> new AppException(
                        "Invoice not found",
                        HttpStatus.NOT_FOUND
                ));
    }

    private StatusEntity resolveStatus(PaymentDto dto) {

        if (dto.getStatus() == null || dto.getStatus().getId() == null) {
            throw new AppException("Status is required", HttpStatus.BAD_REQUEST);
        }

        StatusEntity status = statusRepository
                .findById(dto.getStatus().getId())
                .orElseThrow(() -> new AppException(
                        "Status not found",
                        HttpStatus.BAD_REQUEST
                ));

        // Validate the status type
        if (!"PAYMENT".equals(status.getModuleType())) {
            throw new AppException(
                    "Invalid status for Payment module",
                    HttpStatus.BAD_REQUEST
            );
        }

        return status;
    }
}
