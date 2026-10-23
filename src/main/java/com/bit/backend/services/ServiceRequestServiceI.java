package com.bit.backend.services;

import com.bit.backend.dtos.ServiceRequestDto;

import java.util.List;

public interface ServiceRequestServiceI {
    ServiceRequestDto addServiceRequest(ServiceRequestDto serviceRequestDto);

    List<ServiceRequestDto> getServiceRequests();

    ServiceRequestDto getServiceRequestById(Integer id);

    ServiceRequestDto updateServiceRequest(Integer id, ServiceRequestDto serviceRequestDto);

    ServiceRequestDto deleteServiceRequest(Integer id);
}
