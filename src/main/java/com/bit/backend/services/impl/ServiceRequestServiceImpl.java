package com.bit.backend.services.impl;

import com.bit.backend.dtos.ServiceRequestDto;
import com.bit.backend.entities.*;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.ServiceRequestMapper;
import com.bit.backend.repositories.*;
import com.bit.backend.services.ServiceRequestServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceRequestServiceImpl implements ServiceRequestServiceI {

    private final CustomerRepository customerRepository;
    private final CustomerSiteRepository customerSiteRepository;
    private final PestTypeRepository pestTypeRepository;
    private final ServiceTypeRepository serviceTypeRepository;
    private final StatusRepository statusRepository;
    private final ServiceRequestMapper serviceRequestMapper;
    private final ServiceRequestRepository serviceRequestRepository;

    public ServiceRequestServiceImpl(CustomerRepository customerRepository, CustomerSiteRepository customerSiteRepository, PestTypeRepository pestTypeRepository, ServiceTypeRepository serviceTypeRepository, StatusRepository statusRepository, ServiceRequestMapper serviceRequestMapper, ServiceRequestRepository serviceRequestRepository) {
        this.customerRepository = customerRepository;
        this.customerSiteRepository = customerSiteRepository;
        this.pestTypeRepository = pestTypeRepository;
        this.serviceTypeRepository = serviceTypeRepository;
        this.statusRepository = statusRepository;
        this.serviceRequestMapper = serviceRequestMapper;
        this.serviceRequestRepository = serviceRequestRepository;
    }

    @Override
    @Transactional
    public ServiceRequestDto addServiceRequest(ServiceRequestDto serviceRequestDto) {
        ServiceRequestEntity entity = serviceRequestMapper.toServiceRequestEntity(serviceRequestDto);
        entity.setId(null);

        CustomerEntity customer = resolveCustomer(serviceRequestDto);
        entity.setCustomer(customer);

        StatusEntity status = resolveStatus(serviceRequestDto);
        entity.setStatus(status);

        CustomerSiteEntity customerSite = resolveCustomerSite(serviceRequestDto);
        entity.setCustomerSite(customerSite);

        PestTypeEntity pestType = resolvePestType(serviceRequestDto);
        entity.setPestType(pestType);

        ServiceTypeEntity serviceType = resolveServiceType(serviceRequestDto);
        entity.setServiceType(serviceType);

        ServiceRequestEntity saved = serviceRequestRepository.save(entity);
        if (saved.getRequestCode() == null || saved.getRequestCode().isBlank()){
            saved.setRequestCode("SREQ-" + saved.getId());
            saved = serviceRequestRepository.save(saved);
        }

        return serviceRequestMapper.toServiceRequestDto(saved);
    }

    @Override
    public List<ServiceRequestDto> getServiceRequests() {
        return serviceRequestMapper.toServiceRequestDtoList(serviceRequestRepository.findAll());
    }

    @Override
    public ServiceRequestDto getServiceRequestById(Integer id) {
        ServiceRequestEntity entity = serviceRequestRepository.findById(id)
                .orElseThrow(() -> new AppException("Service Request not found", HttpStatus.NOT_FOUND));
        return serviceRequestMapper.toServiceRequestDto(entity);
    }

    @Override
    @Transactional
    public ServiceRequestDto updateServiceRequest(Integer id, ServiceRequestDto serviceRequestDto) {
        ServiceRequestEntity existing = serviceRequestRepository.findById(id)
                .orElseThrow(() -> new AppException("Service Request not found", HttpStatus.NOT_FOUND));

        StatusEntity status = resolveStatus(serviceRequestDto);
        CustomerEntity customer = resolveCustomer(serviceRequestDto);
        CustomerSiteEntity customerSite = resolveCustomerSite(serviceRequestDto);
        PestTypeEntity pestType = resolvePestType(serviceRequestDto);
        ServiceTypeEntity serviceType = resolveServiceType(serviceRequestDto);
        existing.setRequestDate(serviceRequestDto.getRequestDate());
        existing.setPreferredDate(serviceRequestDto.getPreferredDate());
        existing.setProblemDescription(serviceRequestDto.getProblemDescription());
        existing.setPriority(serviceRequestDto.getPriority());
        existing.setStatus(status);
        existing.setCustomer(customer);
        existing.setCustomerSite(customerSite);
        existing.setPestType(pestType);
        existing.setServiceType(serviceType);

        if (serviceRequestDto.getRequestCode() != null && !serviceRequestDto.getRequestCode().isBlank()) {
            existing.setRequestCode(serviceRequestDto.getRequestCode());
        }

        return serviceRequestMapper.toServiceRequestDto(serviceRequestRepository.save(existing));
    }

    @Override
    @Transactional
    public ServiceRequestDto deleteServiceRequest(Integer id) {
        ServiceRequestEntity existing = serviceRequestRepository.findById(id)
                .orElseThrow(() -> new AppException("Service Request not found", HttpStatus.NOT_FOUND));
        ServiceRequestDto dto = serviceRequestMapper.toServiceRequestDto(existing);
        serviceRequestRepository.delete(existing);
        return dto;
    }

    ///////////////////////////////////////////////

    private StatusEntity resolveStatus(ServiceRequestDto serviceRequestDto) {
        if (serviceRequestDto.getStatus() == null || serviceRequestDto.getStatus().getId() == null) {
            throw new AppException("Status is required", HttpStatus.BAD_REQUEST);
        }
        return statusRepository.findById(serviceRequestDto.getStatus().getId())
                .orElseThrow(() -> new AppException("Status not found", HttpStatus.BAD_REQUEST));
    }

    private CustomerEntity resolveCustomer(ServiceRequestDto serviceRequestDto) {
        if (serviceRequestDto.getCustomer() == null || serviceRequestDto.getCustomer().getId() == null) {
            throw new AppException("Customer is required", HttpStatus.BAD_REQUEST);
        }

        return customerRepository.findById(serviceRequestDto.getCustomer().getId())
                .orElseThrow(() -> new AppException("Customer not found", HttpStatus.BAD_REQUEST));
    }

    private CustomerSiteEntity resolveCustomerSite(ServiceRequestDto serviceRequestDto) {
        if (serviceRequestDto.getCustomerSite() == null || serviceRequestDto.getCustomerSite().getId() == null) {
            throw new AppException("Customer Site is required", HttpStatus.BAD_REQUEST);
        }

        return customerSiteRepository.findById(serviceRequestDto.getCustomerSite().getId())
                .orElseThrow(() -> new AppException("Customer Site not found", HttpStatus.BAD_REQUEST));
    }

    private PestTypeEntity resolvePestType(ServiceRequestDto serviceRequestDto) {
        if (serviceRequestDto.getPestType() == null || serviceRequestDto.getPestType().getId() == null) {
            throw new AppException("Pest Type is required", HttpStatus.BAD_REQUEST);
        }

        return pestTypeRepository.findById(serviceRequestDto.getPestType().getId())
                .orElseThrow(() -> new AppException("Pest Type not found", HttpStatus.BAD_REQUEST));
    }

    private ServiceTypeEntity resolveServiceType(ServiceRequestDto serviceRequestDto) {
        if (serviceRequestDto.getServiceType() == null || serviceRequestDto.getServiceType().getId() == null) {
            throw new AppException("Service Type is required", HttpStatus.BAD_REQUEST);
        }

        return serviceTypeRepository.findById(serviceRequestDto.getServiceType().getId())
                .orElseThrow(() -> new AppException("Service Type not found", HttpStatus.BAD_REQUEST));
    }
}
