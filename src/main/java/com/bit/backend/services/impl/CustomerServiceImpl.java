package com.bit.backend.services.impl;

import com.bit.backend.dtos.CustomerDto;
import com.bit.backend.dtos.StudentDto;
import com.bit.backend.dtos.TechnicianDto;
import com.bit.backend.entities.CustomerEntity;
import com.bit.backend.entities.StatusEntity;
import com.bit.backend.entities.TechnicianEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.CustomerMapper;
import com.bit.backend.repositories.CustomerRepository;
import com.bit.backend.repositories.StatusRepository;
import com.bit.backend.services.CustomerServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerServiceI {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final StatusRepository statusRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper, StatusRepository statusRepository) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.statusRepository = statusRepository;
    }

    @Override
    @Transactional
    public CustomerDto addCustomer(CustomerDto customerDto) {
        CustomerEntity entity = customerMapper.toCustomerEntity(customerDto);
        entity.setId(null);

        StatusEntity status = resolveStatus(customerDto);
        entity.setStatus(status);

        CustomerEntity saved = customerRepository.save(entity);
        if (saved.getCustomerCode() == null || saved.getCustomerCode().isBlank()){
            saved.setCustomerCode("CUS-" + saved.getId());
            saved = customerRepository.save(saved);
        }

        return customerMapper.toCustomerDto(saved);
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return customerMapper.toCustomerDtoList(customerRepository.findAll());
    }

    @Override
    public CustomerDto getCustomerById(Integer id) {
        CustomerEntity entity = customerRepository.findById(id)
                .orElseThrow(() -> new AppException("Customer not found", HttpStatus.NOT_FOUND));
        return customerMapper.toCustomerDto(entity);
    }

    @Override
    @Transactional
    public CustomerDto updateCustomer(Integer id, CustomerDto customerDto) {
        CustomerEntity existing = customerRepository.findById(id)
                .orElseThrow(() -> new AppException("Customer not found", HttpStatus.NOT_FOUND));

        StatusEntity status = resolveStatus(customerDto);
        existing.setCustomer_name(customerDto.getCustomer_name());
        existing.setCustomer_type(customerDto.getCustomer_type());
        existing.setEmail(customerDto.getEmail());
        existing.setPhone(customerDto.getPhone());
        existing.setNic(customerDto.getNic());
        existing.setBilling_address(customerDto.getBilling_address());
        existing.setStatus(status);

        if (customerDto.getCustomerCode() != null && !customerDto.getCustomerCode().isBlank()) {
            existing.setCustomerCode(customerDto.getCustomerCode());
        }

        return customerMapper.toCustomerDto(customerRepository.save(existing));
    }

    @Override
    @Transactional
    public CustomerDto deleteCustomer(Integer id) {
        CustomerEntity existing = customerRepository.findById(id)
                .orElseThrow(() -> new AppException("customer not found", HttpStatus.NOT_FOUND));
        CustomerDto dto = customerMapper.toCustomerDto(existing);
        customerRepository.delete(existing);
        return dto;
    }


    private StatusEntity resolveStatus(CustomerDto customerDto) {
        if (customerDto.getStatus() == null || customerDto.getStatus().getId() == null) {
            throw new AppException("Status is required", HttpStatus.BAD_REQUEST);
        }
        return statusRepository.findById(customerDto.getStatus().getId())
                .orElseThrow(() -> new AppException("Status not found", HttpStatus.BAD_REQUEST));
    }
}
