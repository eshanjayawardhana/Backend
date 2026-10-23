package com.bit.backend.services;

import com.bit.backend.dtos.ServiceContractDto;

import java.util.List;

public interface ServiceContractServiceI {
    ServiceContractDto addServiceContract(ServiceContractDto serviceContractDto);
    List<ServiceContractDto> getServiceContracts();
    ServiceContractDto getServiceContractById(Integer id);
    ServiceContractDto updateServiceContract(Integer id, ServiceContractDto serviceContractDto);
    ServiceContractDto deleteServiceContract(Integer id);
}
