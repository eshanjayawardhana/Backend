package com.bit.backend.services.impl;

import com.bit.backend.dtos.ServiceContractDto;
import com.bit.backend.entities.*;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.ServiceContractMapper;
import com.bit.backend.repositories.*;
import com.bit.backend.services.ServiceContractServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.List;

@Service
public class ServiceContractServiceImpl implements ServiceContractServiceI {

    private final CustomerRepository customerRepository;
    private final CustomerSiteRepository customerSiteRepository;
    private final ServiceTypeRepository serviceTypeRepository;
    private final StatusRepository statusRepository;
    private final ServiceContractRepository serviceContractRepository;
    private final ServiceContractMapper serviceContractMapper;

    public ServiceContractServiceImpl(CustomerRepository customerRepository, CustomerSiteRepository customerSiteRepository, ServiceTypeRepository serviceTypeRepository, StatusRepository statusRepository, ServiceContractRepository serviceContractRepository, ServiceContractMapper serviceContractMapper) {
        this.customerRepository = customerRepository;
        this.customerSiteRepository = customerSiteRepository;
        this.serviceTypeRepository = serviceTypeRepository;
        this.statusRepository = statusRepository;
        this.serviceContractRepository = serviceContractRepository;
        this.serviceContractMapper = serviceContractMapper;
    }

    @Override
    @Transactional
    public ServiceContractDto addServiceContract(ServiceContractDto serviceContractDto) {

        ServiceContractEntity entity = serviceContractMapper.toServiceContractEntity(serviceContractDto);
        entity.setId(null);

        CustomerEntity customer = resolveCustomer(serviceContractDto);
        entity.setCustomer(customer);

        CustomerSiteEntity customerSite = resolveCustomerSite(serviceContractDto);
        entity.setCustomerSite(customerSite);

        ServiceTypeEntity serviceType = resolveServiceType(serviceContractDto);
        entity.setServiceType(serviceType);

        StatusEntity status = resolveStatus(serviceContractDto);
        entity.setStatus(status);

        ServiceContractEntity saved = serviceContractRepository.save(entity);

        if (saved.getContractCode() == null || saved.getContractCode().isBlank()) {

            saved.setContractCode("AMC-" + Year.now().getValue() + "-" + saved.getId());

            saved = serviceContractRepository.save(saved);
        }

        return serviceContractMapper.toServiceContractDto(saved);
    }


    @Override
    public List<ServiceContractDto> getServiceContracts() {
        return serviceContractMapper.toServiceContractDtoList(serviceContractRepository.findAll()
        );
    }

    @Override
    public ServiceContractDto getServiceContractById(Integer id) {
        ServiceContractEntity entity = serviceContractRepository.findById(id)
                .orElseThrow(() -> new AppException("Service Contract not found", HttpStatus.NOT_FOUND));
        return serviceContractMapper.toServiceContractDto(entity);
    }

    @Override
    @Transactional
    public ServiceContractDto updateServiceContract(Integer id, ServiceContractDto serviceContractDto) {
        ServiceContractEntity existing = serviceContractRepository.findById(id)
                .orElseThrow(() -> new AppException("Service Contract not found", HttpStatus.NOT_FOUND));

        CustomerEntity customer = resolveCustomer(serviceContractDto);
        CustomerSiteEntity customerSite = resolveCustomerSite(serviceContractDto);
        ServiceTypeEntity serviceType = resolveServiceType(serviceContractDto);
        StatusEntity status = resolveStatus(serviceContractDto);

        existing.setStartDate(serviceContractDto.getStartDate());
        existing.setEndDate(serviceContractDto.getEndDate());
        existing.setVisitFrequency(serviceContractDto.getVisitFrequency());
        existing.setContractAmount(serviceContractDto.getContractAmount());
        existing.setTerms(serviceContractDto.getTerms());

        existing.setCustomer(customer);
        existing.setCustomerSite(customerSite);
        existing.setServiceType(serviceType);
        existing.setStatus(status);

        if (serviceContractDto.getContractCode() != null && !serviceContractDto.getContractCode().isBlank()) {
            existing.setContractCode(serviceContractDto.getContractCode());
        }
        ServiceContractEntity updated = serviceContractRepository.save(existing);
        return serviceContractMapper.toServiceContractDto(updated);
    }

    @Override
    @Transactional
    public ServiceContractDto deleteServiceContract(Integer id) {
        ServiceContractEntity existing = serviceContractRepository.findById(id)
                .orElseThrow(() -> new AppException("Service Contract not found", HttpStatus.NOT_FOUND));
        ServiceContractDto dto = serviceContractMapper.toServiceContractDto(existing);
        serviceContractRepository.delete(existing);
        return dto;
    }


    /////////////////// Resolvers ////////////////////

    private CustomerEntity resolveCustomer(ServiceContractDto dto) {
        if (dto.getCustomer() == null || dto.getCustomer().getId() == null) {
            throw new AppException("Customer ID is required", HttpStatus.BAD_REQUEST);
        }
        return customerRepository.findById(dto.getCustomer().getId())
                .orElseThrow(() -> new AppException("Customer not found", HttpStatus.NOT_FOUND));
    }

    private CustomerSiteEntity resolveCustomerSite(ServiceContractDto dto) {
        if (dto.getCustomerSite() == null || dto.getCustomerSite().getId() == null) {
            throw new AppException("Customer site ID is required", HttpStatus.BAD_REQUEST);
        }
        return customerSiteRepository.findById(dto.getCustomerSite().getId())
                .orElseThrow(() -> new AppException("Customer site not found", HttpStatus.NOT_FOUND));
    }

    private ServiceTypeEntity resolveServiceType(ServiceContractDto dto) {
        if (dto.getServiceType() == null || dto.getServiceType().getId() == null) {
            throw new AppException("Service type ID is required", HttpStatus.BAD_REQUEST);
        }
        return serviceTypeRepository.findById(dto.getServiceType().getId())
                .orElseThrow(() -> new AppException("Service type not found", HttpStatus.NOT_FOUND));
    }

    private StatusEntity resolveStatus(ServiceContractDto dto) {

        if (dto.getStatus() == null || dto.getStatus().getId() == null) {
            throw new AppException("Status ID is required", HttpStatus.BAD_REQUEST);
        }

        StatusEntity status = statusRepository
                .findById(dto.getStatus().getId())
                .orElseThrow(() -> new AppException(
                        "Status not found",
                        HttpStatus.BAD_REQUEST
                ));

        // Validate the status type
        if (!"SERVICE_CONTRACT".equals(status.getModuleType())) {
            throw new AppException(
                    "Invalid status for Service Contract module",
                    HttpStatus.BAD_REQUEST
            );
        }

        return status;
    }
}
