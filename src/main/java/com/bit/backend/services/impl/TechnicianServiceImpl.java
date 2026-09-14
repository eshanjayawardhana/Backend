package com.bit.backend.services.impl;

import com.bit.backend.dtos.TechnicianDto;
import com.bit.backend.entities.TechnicianEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.TechnicianMapper;
import com.bit.backend.repositories.StatusRepository;
import com.bit.backend.repositories.TechnicianRepository;
import com.bit.backend.services.TechnicianServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TechnicianServiceImpl implements TechnicianServiceI {

    private final TechnicianMapper technicianMapper;
    private final TechnicianRepository technicianRepository;
    private final StatusRepository statusRepository;

    public TechnicianServiceImpl(TechnicianMapper technicianMapper, TechnicianRepository technicianRepository, StatusRepository statusRepository) {
        this.technicianMapper = technicianMapper;
        this.technicianRepository = technicianRepository;
        this.statusRepository = statusRepository;
    }

    @Override
    public List<TechnicianDto> getAllTechnicians() {
        return technicianMapper.toTechnicianDtoList(technicianRepository.findAll());
    }

    @Override
    public TechnicianDto getTechnicianById(Integer id) {
        TechnicianEntity entity = technicianRepository.findById(id)
                .orElseThrow(() -> new AppException("Technician not found", HttpStatus.NOT_FOUND));
        return technicianMapper.toTechnicianDto(entity);
    }

    @Override
    @Transactional
    public TechnicianDto addTechnician(TechnicianDto technicianDto) {
        TechnicianEntity entity = technicianMapper.toTechnicianEntity(technicianDto);
        entity.setId(null);

        TechnicianEntity saved = technicianRepository.save(entity);
        if(saved.getTechnicianCode() == null || saved.getTechnicianCode().isBlank()){
            saved.setTechnicianCode("TECH-" + saved.getId());
            saved = technicianRepository.save(saved);
        }
        return technicianMapper.toTechnicianDto(saved);
    }

    @Override
    public TechnicianDto updateTechnician(Integer id, TechnicianDto technicianDto) {
        TechnicianEntity existing = technicianRepository.findById(id)
                .orElseThrow(() -> new AppException("Technician not found", HttpStatus.NOT_FOUND));

        existing.setFullName(technicianDto.getFullName());
        existing.setPhone(technicianDto.getPhone());
        existing.setLicenseNo(technicianDto.getLicenseNo());
        existing.setSpecialization(technicianDto.getSpecialization());

        if (technicianDto.getTechnicianCode() != null && !technicianDto.getTechnicianCode().isBlank()) {
            existing.setTechnicianCode(technicianDto.getTechnicianCode());
        }

        return technicianMapper.toTechnicianDto(technicianRepository.save(existing));
    }

    @Override
    public TechnicianDto deleteTechnician(Integer id) {
        TechnicianEntity existing = technicianRepository.findById(id)
                .orElseThrow(() -> new AppException("Technician not found", HttpStatus.NOT_FOUND));
        TechnicianDto dto = technicianMapper.toTechnicianDto(existing);
        technicianRepository.delete(existing);
        return dto;
    }


}
