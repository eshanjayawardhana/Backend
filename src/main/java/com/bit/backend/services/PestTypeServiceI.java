package com.bit.backend.services;

import com.bit.backend.dtos.PestTypeDto;

import java.util.List;

public interface PestTypeServiceI {
    PestTypeDto addPestType(PestTypeDto pestTypeDto);

    List<PestTypeDto> getAllPestTypes();

    PestTypeDto getPestTypeById(Integer id);

    PestTypeDto updatePestType(Integer id, PestTypeDto pestTypeDto);

    PestTypeDto deletePestType(Integer id);
}
