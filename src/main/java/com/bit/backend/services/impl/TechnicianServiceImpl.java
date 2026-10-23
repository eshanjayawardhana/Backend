package com.bit.backend.services.impl;

import com.bit.backend.dtos.CustomerSiteDto;
import com.bit.backend.dtos.TechnicianDto;
import com.bit.backend.entities.*;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.TechnicianMapper;
import com.bit.backend.repositories.StatusRepository;
import com.bit.backend.repositories.TechnicianRepository;
import com.bit.backend.repositories.UserRepository;
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
    private final UserRepository userRepository;

    public TechnicianServiceImpl(TechnicianMapper technicianMapper, TechnicianRepository technicianRepository, StatusRepository statusRepository, UserRepository userRepository) {
        this.technicianMapper = technicianMapper;
        this.technicianRepository = technicianRepository;
        this.statusRepository = statusRepository;
        this.userRepository = userRepository;
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
        // Set ID to null so the database generates a new ID for the new technician
        entity.setId(null);

        User user = resolveUser(technicianDto);
        entity.setUser(user);

        StatusEntity status = resolveStatus(technicianDto);
        entity.setStatus(status);

        TechnicianEntity saved = technicianRepository.save(entity);
        if(saved.getTechnicianCode() == null || saved.getTechnicianCode().isBlank()){
            saved.setTechnicianCode("TEC-" + saved.getId());
            saved = technicianRepository.save(saved);
        }
        return technicianMapper.toTechnicianDto(saved);
    }

    @Override
    @Transactional
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
    @Transactional
    public TechnicianDto deleteTechnician(Integer id) {
        TechnicianEntity existing = technicianRepository.findById(id)
                .orElseThrow(() -> new AppException("Technician not found", HttpStatus.NOT_FOUND));
        TechnicianDto dto = technicianMapper.toTechnicianDto(existing);
        technicianRepository.delete(existing);
        return dto;
    }

    private StatusEntity resolveStatus(TechnicianDto technicianDto) {
        if (technicianDto.getStatus() == null || technicianDto.getStatus().getId() == null) {
            throw new AppException("Status is required", HttpStatus.BAD_REQUEST);
        }
        return statusRepository.findById(technicianDto.getStatus().getId())
                .orElseThrow(() -> new AppException("Status not found", HttpStatus.BAD_REQUEST));
    }

    private User resolveUser(TechnicianDto technicianDto) {
        if (technicianDto.getUser() == null || technicianDto.getUser().getId() == null) {
            throw new AppException("User is required", HttpStatus.BAD_REQUEST);
        }

        return userRepository.findById(technicianDto.getUser().getId())
                .orElseThrow(() -> new AppException("User not found", HttpStatus.BAD_REQUEST));
    }
}
