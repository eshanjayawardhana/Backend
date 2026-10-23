package com.bit.backend.mappers;


import com.bit.backend.dtos.CustomerSiteDto;
import com.bit.backend.dtos.ServiceRequestDto;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.entities.CustomerSiteEntity;
import com.bit.backend.entities.ServiceRequestEntity;
import com.bit.backend.entities.StatusEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ServiceRequestMapper {

    StatusDto toStatusDto(StatusEntity entity);

    List<StatusDto> toStatusDtoList(List<StatusEntity> entities);

    @Mapping(target = "status", source = "status")
    @Mapping(target = "customer", source = "customer")
    @Mapping(target = "customerSite", source = "customerSite")
    @Mapping(target = "pestType", source = "pestType")
    @Mapping(target = "serviceType", source = "serviceType")
    ServiceRequestDto toServiceRequestDto(ServiceRequestEntity entity);

    List<ServiceRequestDto> toServiceRequestDtoList(List<ServiceRequestEntity> entities);


    @Mapping(target = "status", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "customerSite", ignore = true)
    @Mapping(target = "pestType", ignore = true)
    @Mapping(target = "serviceType", ignore = true)
    ServiceRequestEntity toServiceRequestEntity(ServiceRequestDto dto);
}
