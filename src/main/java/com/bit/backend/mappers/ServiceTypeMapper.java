package com.bit.backend.mappers;

import com.bit.backend.dtos.CustomerSiteDto;
import com.bit.backend.dtos.ServiceTypeDto;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.entities.CustomerSiteEntity;
import com.bit.backend.entities.ServiceTypeEntity;
import com.bit.backend.entities.StatusEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ServiceTypeMapper {

    StatusDto toStatusDto(StatusEntity entity);

    List<StatusDto> toStatusDtoList(List<StatusEntity> entities);

    @Mapping(target = "status", source = "status")
    @Mapping(target = "pestType", source = "pestType")
    ServiceTypeDto toServiceTypeDto(ServiceTypeEntity entity);

    List<ServiceTypeDto> toServiceTypeDtoList(List<ServiceTypeEntity> entities);


    @Mapping(target = "status", ignore = true)
    @Mapping(target = "pestType", ignore = true)
    ServiceTypeEntity toServiceTypeEntity(ServiceTypeDto dto);
}
