package com.bit.backend.services;

import com.bit.backend.dtos.ServiceTypeDto;

import java.util.List;

public interface ServiceTypeI {
    ServiceTypeDto addServiceType(ServiceTypeDto serviceTypeDto);

    List<ServiceTypeDto> getAllServiceTypes();

    ServiceTypeDto getServiceTypeById(Integer id);

    ServiceTypeDto updateServiceType(Integer id, ServiceTypeDto serviceTypeDto);

    ServiceTypeDto deleteServiceType(Integer id);
}
