package com.bit.backend.mappers;

import com.bit.backend.dtos.InspectionDto;
import com.bit.backend.dtos.ServiceRequestDto;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.entities.InspectionEntity;
import com.bit.backend.entities.ServiceRequestEntity;
import com.bit.backend.entities.StatusEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface InspectionMapper {

    StatusDto toStatusDto(StatusEntity entity);
    List<StatusDto> toStatusDtoList(List<StatusEntity> entities);

    @Mapping(target = "status", ignore = true)
    @Mapping(target = "serviceRequest", ignore = true)
    @Mapping(target = "customerSite", ignore = true)
    @Mapping(target = "technician", ignore = true)
    InspectionEntity toInspectionEntity(InspectionDto dto);

    @Mapping(target = "status", source = "status")
    @Mapping(target = "serviceRequest", source = "serviceRequest")
    @Mapping(target = "customerSite", source = "customerSite")
    @Mapping(target = "technician", source = "technician")
    InspectionDto toInspectionDto(InspectionEntity entity);

    List<InspectionDto> toInspectionDtoList(List<InspectionEntity> entities);


}
