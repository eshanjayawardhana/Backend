package com.bit.backend.services.impl;

import com.bit.backend.dtos.CustomerSiteDto;
import com.bit.backend.dtos.ServiceTypeDto;
import com.bit.backend.entities.*;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.ServiceTypeMapper;
import com.bit.backend.repositories.PestTypeRepository;
import com.bit.backend.repositories.ServiceTypeRepository;
import com.bit.backend.repositories.StatusRepository;
import com.bit.backend.services.ServiceTypeI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceTypeImpl implements ServiceTypeI {

    private final ServiceTypeRepository serviceTypeRepository;
    private final ServiceTypeMapper serviceTypeMapper;
    private final StatusRepository statusRepository;
    private final PestTypeRepository pestTypeRepository;

    public ServiceTypeImpl(ServiceTypeRepository serviceTypeRepository, ServiceTypeMapper serviceTypeMapper, StatusRepository statusRepository, PestTypeRepository pestTypeRepository) {
        this.serviceTypeRepository = serviceTypeRepository;
        this.serviceTypeMapper = serviceTypeMapper;
        this.statusRepository = statusRepository;
        this.pestTypeRepository = pestTypeRepository;
    }

    @Override
    public ServiceTypeDto addServiceType(ServiceTypeDto serviceTypeDto) {
        ServiceTypeEntity entity = serviceTypeMapper.toServiceTypeEntity(serviceTypeDto);
        entity.setId(null);

        PestTypeEntity pestType = resolvePestType(serviceTypeDto);
        entity.setPestType(pestType);

        StatusEntity status = resolveStatus(serviceTypeDto);
        entity.setStatus(status);

        ServiceTypeEntity saved = serviceTypeRepository.save(entity);
        if (saved.getServiceCode() == null || saved.getServiceCode().isBlank()){
            saved.setServiceCode("ST-" + saved.getId());
            saved = serviceTypeRepository.save(saved);
        }

        return serviceTypeMapper.toServiceTypeDto(saved);
    }

    @Override
    public List<ServiceTypeDto> getAllServiceTypes() {
        return serviceTypeMapper.toServiceTypeDtoList(serviceTypeRepository.findAll());

    }

    @Override
    public ServiceTypeDto getServiceTypeById(Integer id) {
        ServiceTypeEntity entity = serviceTypeRepository.findById(id)
                .orElseThrow(() -> new AppException("Service Type not found", HttpStatus.NOT_FOUND));
        return serviceTypeMapper.toServiceTypeDto(entity);
    }

    @Override
    public ServiceTypeDto updateServiceType(Integer id, ServiceTypeDto serviceTypeDto) {
        ServiceTypeEntity existing = serviceTypeRepository.findById(id)
                .orElseThrow(() -> new AppException("Service Type not found", HttpStatus.NOT_FOUND));

        StatusEntity status = resolveStatus(serviceTypeDto);
        PestTypeEntity pestType = resolvePestType(serviceTypeDto);
        existing.setServiceName(serviceTypeDto.getServiceName());
        existing.setDescription(serviceTypeDto.getDescription());
        existing.setBasePrice(serviceTypeDto.getBasePrice());
        existing.setEstimatedDurationHrs(serviceTypeDto.getEstimatedDurationHrs());
        existing.setPestType(pestType);
        existing.setStatus(status);

        if (serviceTypeDto.getServiceCode() != null && !serviceTypeDto.getServiceCode().isBlank()) {
            existing.setServiceCode(serviceTypeDto.getServiceCode());
        }

        return serviceTypeMapper.toServiceTypeDto(serviceTypeRepository.save(existing));
    }

    @Override
    public ServiceTypeDto deleteServiceType(Integer id) {
        ServiceTypeEntity existing = serviceTypeRepository.findById(id)
                .orElseThrow(() -> new AppException("Service Type not found", HttpStatus.NOT_FOUND));
        ServiceTypeDto dto = serviceTypeMapper.toServiceTypeDto(existing);
        serviceTypeRepository.delete(existing);
        return dto;
    }

    private StatusEntity resolveStatus(ServiceTypeDto serviceTypeDto) {
        if (serviceTypeDto.getStatus() == null || serviceTypeDto.getStatus().getId() == null) {
            throw new AppException("Status is required", HttpStatus.BAD_REQUEST);
        }
        return statusRepository.findById(serviceTypeDto.getStatus().getId())
                .orElseThrow(() -> new AppException("Service Type not found", HttpStatus.BAD_REQUEST));
    }

    private PestTypeEntity resolvePestType(ServiceTypeDto serviceTypeDto) {
        if (serviceTypeDto.getPestType() == null || serviceTypeDto.getPestType().getId() == null) {
            throw new AppException("Pest Type is required", HttpStatus.BAD_REQUEST);
        }

        return pestTypeRepository.findById(serviceTypeDto.getPestType().getId())
                .orElseThrow(() -> new AppException("Pest Type not found", HttpStatus.BAD_REQUEST));
    }
}
