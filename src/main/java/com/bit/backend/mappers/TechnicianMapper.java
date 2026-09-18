package com.bit.backend.mappers;

import com.bit.backend.dtos.StatusDto;
import com.bit.backend.dtos.TechnicianDto;
import com.bit.backend.entities.StatusEntity;
import com.bit.backend.entities.TechnicianEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface TechnicianMapper {

    // Status mapping
    StatusDto toStatusDto(StatusEntity entity);

    List<StatusDto> toStatusDtoList(List<StatusEntity> entities);

    // Entity to DTO
    @Mapping(target = "status", source = "status")
    @Mapping(target = "user", source = "user")
    TechnicianDto toTechnicianDto(TechnicianEntity entity);

    List<TechnicianDto> toTechnicianDtoList(List<TechnicianEntity> entities);

    // DTO to Entity
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "user", ignore = true)
    TechnicianEntity toTechnicianEntity(TechnicianDto dto);
}
