package com.bit.backend.mappers;


import com.bit.backend.dtos.ChemicalDto;
import com.bit.backend.dtos.CustomerSiteDto;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.entities.ChemicalEntity;
import com.bit.backend.entities.CustomerSiteEntity;
import com.bit.backend.entities.StatusEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ChemicalMapper {

    StatusDto toStatusDto(StatusEntity entity);

    List<StatusDto> toStatusDtoList(List<StatusEntity> entities);

    @Mapping(target = "status", source = "status")
    ChemicalDto toChemicalDto(ChemicalEntity entity);

    List<ChemicalDto> toChemicalDtoList(List<ChemicalEntity> entities);


    @Mapping(target = "status", ignore = true)
    ChemicalEntity toChemicalEntity(ChemicalDto dto);
}
