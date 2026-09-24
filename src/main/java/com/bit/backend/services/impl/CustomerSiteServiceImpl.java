package com.bit.backend.services.impl;

import com.bit.backend.dtos.CustomerSiteDto;
import com.bit.backend.entities.CustomerEntity;
import com.bit.backend.entities.CustomerSiteEntity;
import com.bit.backend.entities.StatusEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.CustomerSiteMapper;
import com.bit.backend.repositories.CustomerRepository;
import com.bit.backend.repositories.CustomerSiteRepository;
import com.bit.backend.repositories.StatusRepository;
import com.bit.backend.services.CustomerSiteServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerSiteServiceImpl implements CustomerSiteServiceI {

    private final CustomerSiteRepository customerSiteRepository;
    private final CustomerSiteMapper customerSiteMapper;
    private final StatusRepository statusRepository;
    private final CustomerRepository customerRepository;

    public CustomerSiteServiceImpl(CustomerSiteRepository customerSiteRepository, CustomerSiteMapper customerSiteMapper, StatusRepository statusRepository, CustomerRepository customerRepository) {
        this.customerSiteRepository = customerSiteRepository;
        this.customerSiteMapper = customerSiteMapper;
        this.statusRepository = statusRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public CustomerSiteDto addCustomerSite(CustomerSiteDto customerSiteDto) {
        CustomerSiteEntity entity = customerSiteMapper.toCustomerSiteEntity(customerSiteDto);
        entity.setId(null);

        CustomerEntity customer = resolveCustomer(customerSiteDto);
        entity.setCustomer(customer);

        StatusEntity status = resolveStatus(customerSiteDto);
        entity.setStatus(status);

        CustomerSiteEntity saved = customerSiteRepository.save(entity);
        if (saved.getSiteCode() == null || saved.getSiteCode().isBlank()){
            saved.setSiteCode("SITE-" + saved.getId());
            saved = customerSiteRepository.save(saved);
        }

        return customerSiteMapper.toCustomerSiteDto(saved);
    }

    @Override
    public List<CustomerSiteDto> getAllCustomerSites() {
        return customerSiteMapper.toCustomerSiteDtoList(customerSiteRepository.findAll());
    }

    @Override
    public CustomerSiteDto getCustomerSiteById(Integer id) {
        CustomerSiteEntity entity = customerSiteRepository.findById(id)
                .orElseThrow(() -> new AppException("Customer Site not found", HttpStatus.NOT_FOUND));
        return customerSiteMapper.toCustomerSiteDto(entity);
    }

    @Override
    @Transactional
    public CustomerSiteDto updateCustomerSite(Integer id, CustomerSiteDto customerSiteDto) {
        CustomerSiteEntity existing = customerSiteRepository.findById(id)
                .orElseThrow(() -> new AppException("Customer Site not found", HttpStatus.NOT_FOUND));

        StatusEntity status = resolveStatus(customerSiteDto);
        existing.setSiteName(customerSiteDto.getSiteName());
        existing.setAddress(customerSiteDto.getAddress());
        existing.setCity(customerSiteDto.getCity());
        existing.setContactPerson(customerSiteDto.getContactPerson());
        existing.setContactPhone(customerSiteDto.getContactPhone());
        existing.setSiteNotes(customerSiteDto.getSiteNotes());
        existing.setStatus(status);

        if (customerSiteDto.getSiteCode() != null && !customerSiteDto.getSiteCode().isBlank()) {
            existing.setSiteCode(customerSiteDto.getSiteCode());
        }

        return customerSiteMapper.toCustomerSiteDto(customerSiteRepository.save(existing));
    }

    @Override
    @Transactional
    public CustomerSiteDto deleteCustomerSite(Integer id) {
        CustomerSiteEntity existing = customerSiteRepository.findById(id)
                .orElseThrow(() -> new AppException("Customer site not found", HttpStatus.NOT_FOUND));
        CustomerSiteDto dto = customerSiteMapper.toCustomerSiteDto(existing);
        customerSiteRepository.delete(existing);
        return dto;
    }

    private StatusEntity resolveStatus(CustomerSiteDto customerSiteDto) {
        if (customerSiteDto.getStatus() == null || customerSiteDto.getStatus().getId() == null) {
            throw new AppException("Status is required", HttpStatus.BAD_REQUEST);
        }
        return statusRepository.findById(customerSiteDto.getStatus().getId())
                .orElseThrow(() -> new AppException("Status not found", HttpStatus.BAD_REQUEST));
    }

    private CustomerEntity resolveCustomer(CustomerSiteDto customerSiteDto) {
        if (customerSiteDto.getCustomer() == null || customerSiteDto.getCustomer().getId() == null) {
            throw new AppException("Customer is required", HttpStatus.BAD_REQUEST);
        }

        return customerRepository.findById(customerSiteDto.getCustomer().getId())
                .orElseThrow(() -> new AppException("Customer not found", HttpStatus.BAD_REQUEST));
    }
}
