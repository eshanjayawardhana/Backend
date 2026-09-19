package com.bit.backend.mappers;

import com.bit.backend.dtos.CustomerDto;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.entities.CustomerEntity;
import com.bit.backend.entities.StatusEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface CustomerMapper {

    StatusDto toStatusDto(StatusEntity entity);

    List<StatusDto> toStatusDtoList(List<StatusEntity> entities);

    @Mapping(target = "status", ignore = true)
    CustomerEntity toCustomerEntity(CustomerDto customerDto);

    @Mapping(target = "status", source = "status")
    CustomerDto toCustomerDto(CustomerEntity customerEntity);

    List<CustomerDto> toCustomerDtoList(List<CustomerEntity> customerEntities);
}
