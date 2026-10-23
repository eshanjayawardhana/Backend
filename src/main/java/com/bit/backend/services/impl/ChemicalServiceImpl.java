package com.bit.backend.services.impl;

import com.bit.backend.dtos.ChemicalDto;
import com.bit.backend.dtos.CustomerDto;
import com.bit.backend.entities.ChemicalEntity;
import com.bit.backend.entities.CustomerEntity;
import com.bit.backend.entities.StatusEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.ChemicalMapper;
import com.bit.backend.repositories.ChemicalRepository;
import com.bit.backend.repositories.StatusRepository;
import com.bit.backend.services.ChemicalServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChemicalServiceImpl implements ChemicalServiceI {

    private final ChemicalRepository chemicalRepository;
    private final ChemicalMapper chemicalMapper;
    private final StatusRepository statusRepository;

    public ChemicalServiceImpl(ChemicalRepository chemicalRepository, ChemicalMapper chemicalMapper, StatusRepository statusRepository) {
        this.chemicalRepository = chemicalRepository;
        this.chemicalMapper = chemicalMapper;
        this.statusRepository = statusRepository;
    }

    @Override
    @Transactional
    public ChemicalDto addChemical(ChemicalDto chemicalDto) {
        ChemicalEntity entity = chemicalMapper.toChemicalEntity(chemicalDto);
        entity.setId(null);

        StatusEntity status = resolveStatus(chemicalDto);
        entity.setStatus(status);

        ChemicalEntity saved = chemicalRepository.save(entity);
        if (saved.getChemicalCode() == null || saved.getChemicalCode().isBlank()){
            saved.setChemicalCode("CHEM-" + saved.getId());
            saved = chemicalRepository.save(saved);
        }

        return chemicalMapper.toChemicalDto(saved);
    }

    @Override
    public List<ChemicalDto> getAllChemicals() {
        return chemicalMapper.toChemicalDtoList(chemicalRepository.findAll());
    }

    @Override
    public ChemicalDto getChemicalById(Integer id) {
        ChemicalEntity entity = chemicalRepository.findById(id)
                .orElseThrow(() -> new AppException("Chemical not found", HttpStatus.NOT_FOUND));
        return chemicalMapper.toChemicalDto(entity);
    }

    @Override
    @Transactional
    public ChemicalDto updateChemical(Integer id, ChemicalDto chemicalDto) {
        ChemicalEntity existing = chemicalRepository.findById(id)
                .orElseThrow(() -> new AppException("Chemical not found", HttpStatus.NOT_FOUND));

        StatusEntity status = resolveStatus(chemicalDto);
        existing.setChemicalName(chemicalDto.getChemicalName());
        existing.setActiveIngredient(chemicalDto.getActiveIngredient());
        existing.setUnit(chemicalDto.getUnit());
        existing.setStockQuantity(chemicalDto.getStockQuantity());
        existing.setReorderLevel(chemicalDto.getReorderLevel());
        existing.setExpiryDate(chemicalDto.getExpiryDate());
        existing.setSafetyNotes(chemicalDto.getSafetyNotes());
        existing.setStatus(status);

        if (chemicalDto.getChemicalCode() != null && !chemicalDto.getChemicalCode().isBlank()) {
            existing.setChemicalCode(chemicalDto.getChemicalCode());
        }

        return chemicalMapper.toChemicalDto(chemicalRepository.save(existing));
    }

    @Override
    @Transactional
    public ChemicalDto deleteChemical(Integer id) {
        ChemicalEntity existing = chemicalRepository.findById(id)
                .orElseThrow(() -> new AppException("chemical not found", HttpStatus.NOT_FOUND));
        ChemicalDto dto = chemicalMapper.toChemicalDto(existing);
        chemicalRepository.delete(existing);
        return dto;
    }

    private StatusEntity resolveStatus(ChemicalDto chemicalDto) {
        if (chemicalDto.getStatus() == null || chemicalDto.getStatus().getId() == null) {
            throw new AppException("Status is required", HttpStatus.BAD_REQUEST);
        }
        return statusRepository.findById(chemicalDto.getStatus().getId())
                .orElseThrow(() -> new AppException("Status not found", HttpStatus.BAD_REQUEST));
    }
}
