package com.bit.backend.mappers;

import com.bit.backend.dtos.StatusDto;
import com.bit.backend.dtos.TreatmentJobDto;
import com.bit.backend.entities.StatusEntity;
import com.bit.backend.entities.TreatmentJobEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface TreatmentJobMapper {

    StatusDto toStatusDto(StatusEntity entity);
    List<StatusDto> toStatusDtoList(List<StatusEntity> entities);

    @Mapping(target = "status", ignore = true)
    @Mapping(target = "serviceRequest", ignore = true)
    @Mapping(target = "inspection", ignore = true)
    @Mapping(target = "serviceContract", ignore = true)
    @Mapping(target = "customerSite", ignore = true)
    @Mapping(target = "technician", ignore = true)
    @Mapping(target = "serviceType", ignore = true)
    TreatmentJobEntity toTreatmentJobEntity(TreatmentJobDto treatmentJobDto);

    @Mapping(target = "status", source = "status")
    @Mapping(target = "serviceRequest", source = "serviceRequest")
    @Mapping(target = "inspection", source = "inspection")
    @Mapping(target = "serviceContract", source = "serviceContract")
    @Mapping(target = "customerSite", source = "customerSite")
    @Mapping(target = "technician", source = "technician")
    @Mapping(target = "serviceType", source = "serviceType")
    TreatmentJobDto toTreatmentJobDto(TreatmentJobEntity treatmentJobEntity);

    List<TreatmentJobDto> toTreatmentJobDtoList(List<TreatmentJobEntity> treatmentJobEntityList);
}
