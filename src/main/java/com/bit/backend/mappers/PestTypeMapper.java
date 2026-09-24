package com.bit.backend.mappers;

import com.bit.backend.dtos.PestTypeDto;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.entities.PestTypeEntity;
import com.bit.backend.entities.StatusEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface PestTypeMapper {

    // Entity to DTO
    PestTypeDto toPestTypeDto(PestTypeEntity entity);

    List<PestTypeDto> toPestTypeDtoList(List<PestTypeEntity> entities);

    // DTO to Entity
    PestTypeEntity toPestTypeEntity(PestTypeDto dto);

    List<StatusDto> toStatusDtoList(List<StatusEntity> entities);
}
