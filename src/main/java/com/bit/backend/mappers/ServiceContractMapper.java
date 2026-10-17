package com.bit.backend.mappers;

import com.bit.backend.dtos.ServiceContractDto;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.entities.ServiceContractEntity;
import com.bit.backend.entities.StatusEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ServiceContractMapper {

    StatusDto toStatusDto(StatusEntity entity);
    List<StatusDto> toStatusDtoList(List<StatusEntity> entities);

    @Mapping(target = "status", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "customerSite", ignore = true)
    @Mapping(target = "serviceType", ignore = true)
    ServiceContractEntity toServiceContractEntity(ServiceContractDto serviceContractDto);

    @Mapping(target = "status", source = "status")
    @Mapping(target = "customer", source = "customer")
    @Mapping(target = "customerSite", source = "customerSite")
    @Mapping(target = "serviceType", source = "serviceType")
    ServiceContractDto toServiceContractDto(ServiceContractEntity saved);

    List<ServiceContractDto> toServiceContractDtoList(List<ServiceContractEntity> serviceContractEntityList);
}
