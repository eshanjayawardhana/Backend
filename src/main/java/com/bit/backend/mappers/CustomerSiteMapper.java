package com.bit.backend.mappers;

import com.bit.backend.dtos.CustomerSiteDto;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.dtos.TechnicianDto;
import com.bit.backend.entities.CustomerSiteEntity;
import com.bit.backend.entities.StatusEntity;
import com.bit.backend.entities.TechnicianEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface CustomerSiteMapper {

    StatusDto toStatusDto(StatusEntity entity);

    List<StatusDto> toStatusDtoList(List<StatusEntity> entities);

    @Mapping(target = "status", source = "status")
    @Mapping(target = "customer", source = "customer")
    CustomerSiteDto toCustomerSiteDto(CustomerSiteEntity entity);

    List<CustomerSiteDto> toCustomerSiteDtoList(List<CustomerSiteEntity> entities);


    @Mapping(target = "status", ignore = true)
    @Mapping(target = "customer", ignore = true)
    CustomerSiteEntity toCustomerSiteEntity(CustomerSiteDto dto);
}
