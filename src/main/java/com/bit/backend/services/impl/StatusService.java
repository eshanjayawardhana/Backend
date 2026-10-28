package com.bit.backend.services.impl;

import com.bit.backend.dtos.StatusDto;
import com.bit.backend.mappers.*;
import com.bit.backend.repositories.StatusRepository;
import com.bit.backend.services.StatusServiceI;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService implements StatusServiceI {

    private final StatusRepository statusRepository;
    private final StudentMapper studentMapper;
    private final CustomerMapper customerMapper;
    private final TechnicianMapper technicianMapper;
    private final CustomerSiteMapper customerSiteMapper;
    private final PestTypeMapper pestTypeMapper;
    private final ServiceTypeMapper serviceTypeMapper;
    private final ServiceRequestMapper serviceRequestMapper;
    private final ChemicalMapper chemicalMapper;
    private final InspectionMapper inspectionMapper;
    private final TreatmentJobMapper treatmentJobMapper;
    private final ServiceContractMapper serviceContractMapper;
    private final InvoiceMapper invoiceMapper;
    private final PaymentMapper paymentMapper;

    public StatusService(StatusRepository statusRepository, StudentMapper studentMapper, CustomerMapper customerMapper, TechnicianMapper technicianMapper, CustomerSiteMapper customerSiteMapper, PestTypeMapper pestTypeMapper, ServiceTypeMapper serviceTypeMapper, ServiceRequestMapper serviceRequestMapper, ChemicalMapper chemicalMapper, InspectionMapper inspectionMapper, TreatmentJobMapper treatmentJobMapper, ServiceContractMapper serviceContractMapper, InvoiceMapper invoiceMapper, PaymentMapper paymentMapper) {
        this.statusRepository = statusRepository;
        this.studentMapper = studentMapper;
        this.customerMapper = customerMapper;
        this.technicianMapper = technicianMapper;
        this.customerSiteMapper = customerSiteMapper;
        this.pestTypeMapper = pestTypeMapper;
        this.serviceTypeMapper = serviceTypeMapper;
        this.serviceRequestMapper = serviceRequestMapper;
        this.chemicalMapper = chemicalMapper;
        this.inspectionMapper = inspectionMapper;
        this.treatmentJobMapper = treatmentJobMapper;
        this.serviceContractMapper = serviceContractMapper;
        this.invoiceMapper = invoiceMapper;
        this.paymentMapper = paymentMapper;
    }


    @Override
    public List<StatusDto> getAllStatus() {
        return studentMapper.toStatusDtoList(statusRepository.findAll());
    }

    @Override
    public List<StatusDto> getAllTechnicianStatus() {
        return technicianMapper.toStatusDtoList(
                statusRepository.findByModuleType("TECHNICIAN")
        );
    }

    @Override
    public List<StatusDto> getAllCustomerStatus() {
        return customerMapper.toStatusDtoList(
                statusRepository.findByModuleType("GENERAL")
        );
    }

    @Override
    public List<StatusDto> getAllCustomerSiteStatus() {
        return customerSiteMapper.toStatusDtoList(
                statusRepository.findByModuleType("GENERAL")
        );
    }

    @Override
    public List<StatusDto> getAllPestTypeStatus() {
        return pestTypeMapper.toStatusDtoList(
                statusRepository.findByModuleType("GENERAL")
        );
    }

    @Override
    public List<StatusDto> getAllServiceTypeStatus() {
        return serviceTypeMapper.toStatusDtoList(
                statusRepository.findByModuleType("GENERAL")
        );
    }

    @Override
    public List<StatusDto> getAllServiceRequestStatus() {
        return serviceRequestMapper.toStatusDtoList(
                statusRepository.findByModuleType("SERVICE_REQUEST")
        );
    }

    @Override
    public List<StatusDto> getAllChemicalStatus() {
        return chemicalMapper.toStatusDtoList(
                statusRepository.findByModuleType("GENERAL")
        );
    }

    @Override
    public List<StatusDto> getAllInspectionStatus() {
        return inspectionMapper.toStatusDtoList(
                statusRepository.findByModuleType("INSPECTION")
        );
    }

    @Override
    public List<StatusDto> getAllTreatmentJobStatus() {
        return treatmentJobMapper.toStatusDtoList(
                statusRepository.findByModuleType("TREATMENT_JOB")
        );
    }

    @Override
    public List<StatusDto> getAllServiceContractStatus() {
        return serviceContractMapper.toStatusDtoList(
                statusRepository.findByModuleType("SERVICE_CONTRACT")
        );
    }

    @Override
    public List<StatusDto> getAllInvoiceStatus() {
        return invoiceMapper.toStatusDtoList(
                statusRepository.findByModuleType("INVOICE")
        );
    }

    @Override
    public List<StatusDto> getAllPaymentStatus() {
        return paymentMapper.toStatusDtoList(
                statusRepository.findByModuleType("PAYMENT")
        );
    }


}
