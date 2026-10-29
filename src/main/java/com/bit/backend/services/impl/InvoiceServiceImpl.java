package com.bit.backend.services.impl;

import com.bit.backend.dtos.InvoiceDto;
import com.bit.backend.entities.*;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.InvoiceMapper;
import com.bit.backend.repositories.*;
import com.bit.backend.services.InvoiceServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceServiceI {

    private final CustomerRepository customerRepository;
    private final TreatmentJobRepository treatmentJobRepository;
    private final ServiceContractRepository serviceContractRepository;
    private final StatusRepository statusRepository;
    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;

    public InvoiceServiceImpl(
            CustomerRepository customerRepository,
            TreatmentJobRepository treatmentJobRepository,
            ServiceContractRepository serviceContractRepository,
            StatusRepository statusRepository,
            InvoiceRepository invoiceRepository,
            InvoiceMapper invoiceMapper) {

        this.customerRepository = customerRepository;
        this.treatmentJobRepository = treatmentJobRepository;
        this.serviceContractRepository = serviceContractRepository;
        this.statusRepository = statusRepository;
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
    }

    @Override
    @Transactional
    public InvoiceDto addInvoice(InvoiceDto invoiceDto) {
        InvoiceEntity entity = invoiceMapper.toInvoiceEntity(invoiceDto);

        entity.setId(null);

        CustomerEntity customer = resolveCustomer(invoiceDto);
        entity.setCustomer(customer);

        TreatmentJobEntity treatmentJob = resolveTreatmentJob(invoiceDto);
        entity.setTreatmentJob(treatmentJob);

        ServiceContractEntity serviceContract = resolveServiceContract(invoiceDto);
        entity.setServiceContract(serviceContract);

        StatusEntity status = resolveStatus(invoiceDto);
        entity.setStatus(status);

        InvoiceEntity saved = invoiceRepository.save(entity);

        // Generate invoice code automatically when not provided
        if (saved.getInvoiceCode() == null || saved.getInvoiceCode().isBlank()) {
            saved.setInvoiceCode("INV-" + Year.now().getValue() + "-" + saved.getId());
            saved = invoiceRepository.save(saved);
        }
        return invoiceMapper.toInvoiceDto(saved);
    }

    @Override
    public List<InvoiceDto> getAllInvoices() {
        return invoiceMapper.toInvoiceDtoList(invoiceRepository.findAll());
    }

    @Override
    public InvoiceDto getInvoiceById(Integer id) {
        InvoiceEntity entity = invoiceRepository.findById(id)
                .orElseThrow(() -> new AppException("Invoice not found", HttpStatus.NOT_FOUND));
        return invoiceMapper.toInvoiceDto(entity);
    }


    @Override
    @Transactional
    public InvoiceDto updateInvoice(Integer id, InvoiceDto invoiceDto) {
        InvoiceEntity existing = invoiceRepository.findById(id)
                .orElseThrow(() -> new AppException("Invoice not found", HttpStatus.NOT_FOUND));

        CustomerEntity customer = resolveCustomer(invoiceDto);
        TreatmentJobEntity treatmentJob = resolveTreatmentJob(invoiceDto);
        ServiceContractEntity serviceContract = resolveServiceContract(invoiceDto);
        StatusEntity status = resolveStatus(invoiceDto);

        existing.setIssueDate(invoiceDto.getIssueDate());
        existing.setDueDate(invoiceDto.getDueDate());
        existing.setSubtotal(invoiceDto.getSubtotal());
        existing.setTaxAmount(invoiceDto.getTaxAmount());
        existing.setTotalAmount(invoiceDto.getTotalAmount());

        existing.setCustomer(customer);
        existing.setTreatmentJob(treatmentJob);
        existing.setServiceContract(serviceContract);
        existing.setStatus(status);

        if (invoiceDto.getInvoiceCode() != null && !invoiceDto.getInvoiceCode().isBlank()) {
            existing.setInvoiceCode(invoiceDto.getInvoiceCode());
        }
        InvoiceEntity updated = invoiceRepository.save(existing);
        return invoiceMapper.toInvoiceDto(updated);
    }

    @Override
    @Transactional
    public InvoiceDto deleteInvoice(Integer id) {
        InvoiceEntity existing = invoiceRepository.findById(id)
                .orElseThrow(() -> new AppException("Invoice not found", HttpStatus.NOT_FOUND));

        InvoiceDto dto = invoiceMapper.toInvoiceDto(existing);
        invoiceRepository.delete(existing);
        return dto;
    }


    /////////////////// Resolvers ////////////////////

    private CustomerEntity resolveCustomer(InvoiceDto dto) {
        if (dto.getCustomer() == null || dto.getCustomer().getId() == null) {
            throw new AppException("Customer ID is required", HttpStatus.BAD_REQUEST);
        }
        return customerRepository.findById(dto.getCustomer().getId())
                .orElseThrow(() -> new AppException("Customer not found", HttpStatus.NOT_FOUND));
    }

    private TreatmentJobEntity resolveTreatmentJob(InvoiceDto dto) {
        if (dto.getTreatmentJob() == null || dto.getTreatmentJob().getId() == null) {
            throw new AppException("Treatment job ID is required", HttpStatus.BAD_REQUEST);
        }
        return treatmentJobRepository.findById(dto.getTreatmentJob().getId())
                .orElseThrow(() -> new AppException("Treatment job not found", HttpStatus.NOT_FOUND));
    }

    private ServiceContractEntity resolveServiceContract(InvoiceDto dto) {
        if (dto.getServiceContract() == null || dto.getServiceContract().getId() == null) {
            throw new AppException("Service contract ID is required", HttpStatus.BAD_REQUEST);
        }
        return serviceContractRepository.findById(dto.getServiceContract().getId())
                .orElseThrow(() -> new AppException("Service contract not found", HttpStatus.NOT_FOUND));
    }

    private StatusEntity resolveStatus(InvoiceDto dto) {

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
        if (!"INVOICE".equals(status.getModuleType())) {
            throw new AppException(
                    "Invalid status for Invoice module",
                    HttpStatus.BAD_REQUEST
            );
        }

        return status;
    }
}
