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

    public StatusService(StatusRepository statusRepository, StudentMapper studentMapper, CustomerMapper customerMapper, TechnicianMapper technicianMapper, CustomerSiteMapper customerSiteMapper, PestTypeMapper pestTypeMapper, ServiceTypeMapper serviceTypeMapper, ServiceRequestMapper serviceRequestMapper, ChemicalMapper chemicalMapper) {
        this.statusRepository = statusRepository;
        this.studentMapper = studentMapper;
        this.customerMapper = customerMapper;
        this.technicianMapper = technicianMapper;
        this.customerSiteMapper = customerSiteMapper;
        this.pestTypeMapper = pestTypeMapper;
        this.serviceTypeMapper = serviceTypeMapper;
        this.serviceRequestMapper = serviceRequestMapper;
        this.chemicalMapper = chemicalMapper;
    }

    @Override
    public List<StatusDto> getAllStatus() {
        return studentMapper.toStatusDtoList(statusRepository.findAll());
    }

    @Override
    public List<StatusDto> getAllTechnicianStatus() {
        return technicianMapper.toStatusDtoList((statusRepository.findAll()));
    }

    @Override
    public List<StatusDto> getAllCustomerStatus() {
        return customerMapper.toStatusDtoList(statusRepository.findAll());

    }

    @Override
    public List<StatusDto> getAllCustomerSiteStatus() {
        return customerSiteMapper.toStatusDtoList(statusRepository.findAll());

    }

    @Override
    public List<StatusDto> getAllPestTypeStatus() {
        return pestTypeMapper.toStatusDtoList(statusRepository.findAll());

    }

    @Override
    public List<StatusDto> getAllServiceTypeStatus() {
        return serviceTypeMapper.toStatusDtoList(statusRepository.findAll());
    }

    @Override
    public List<StatusDto> getAllServiceRequestStatus() {
        return serviceRequestMapper.toStatusDtoList(statusRepository.findAll());

    }

    @Override
    public List<StatusDto> getAllChemicalStatus() {
        return chemicalMapper.toStatusDtoList(statusRepository.findAll());
    }

}
