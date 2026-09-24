package com.bit.backend.services.impl;

import com.bit.backend.dtos.StatusDto;
import com.bit.backend.mappers.CustomerMapper;
import com.bit.backend.mappers.CustomerSiteMapper;
import com.bit.backend.mappers.StudentMapper;
import com.bit.backend.mappers.TechnicianMapper;
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

    public StatusService(StatusRepository statusRepository, StudentMapper studentMapper, CustomerMapper customerMapper, TechnicianMapper technicianMapper, CustomerSiteMapper customerSiteMapper) {
        this.statusRepository = statusRepository;
        this.studentMapper = studentMapper;
        this.customerMapper = customerMapper;
        this.technicianMapper = technicianMapper;
        this.customerSiteMapper = customerSiteMapper;
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

}
