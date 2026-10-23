package com.bit.backend.services.impl;

import com.bit.backend.dtos.PestTypeDto;
import com.bit.backend.entities.PestTypeEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.PestTypeMapper;
import com.bit.backend.repositories.PestTypeRepository;
import com.bit.backend.services.PestTypeServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PestTypeServiceImpl implements PestTypeServiceI {

    private final PestTypeRepository pestTypeRepository;
    private final PestTypeMapper pestTypeMapper;

    public PestTypeServiceImpl(PestTypeRepository pestTypeRepository, PestTypeMapper pestTypeMapper) {
        this.pestTypeRepository = pestTypeRepository;
        this.pestTypeMapper = pestTypeMapper;
    }

    @Override
    public PestTypeDto addPestType(PestTypeDto pestTypeDto) {
        PestTypeEntity entity = pestTypeMapper.toPestTypeEntity(pestTypeDto);
        entity.setId(null);

        return pestTypeMapper.toPestTypeDto(pestTypeRepository.save(entity));
    }

    @Override
    public List<PestTypeDto> getAllPestTypes() {
        return pestTypeMapper.toPestTypeDtoList(pestTypeRepository.findAll());
    }

    @Override
    public PestTypeDto getPestTypeById(Integer id) {
        PestTypeEntity entity = pestTypeRepository.findById(id)
                .orElseThrow(() -> new AppException("Pest Type not found", HttpStatus.NOT_FOUND));
        return pestTypeMapper.toPestTypeDto(entity);
    }

    @Override
    public PestTypeDto updatePestType(Integer id, PestTypeDto pestTypeDto) {
        PestTypeEntity existing = pestTypeRepository.findById(id)
                .orElseThrow(() -> new AppException("Pest Type not found", HttpStatus.NOT_FOUND));

        existing.setPestName(pestTypeDto.getPestName());
        existing.setDescription(pestTypeDto.getDescription());
        existing.setSeverityLevel(pestTypeDto.getSeverityLevel());

        return pestTypeMapper.toPestTypeDto(pestTypeRepository.save(existing));
    }

    @Override
    public PestTypeDto deletePestType(Integer id) {
        PestTypeEntity existing = pestTypeRepository.findById(id)
                .orElseThrow(() -> new AppException("Pest Type not found", HttpStatus.NOT_FOUND));
        PestTypeDto dto = pestTypeMapper.toPestTypeDto(existing);
        pestTypeRepository.delete(existing);
        return dto;
    }
}
