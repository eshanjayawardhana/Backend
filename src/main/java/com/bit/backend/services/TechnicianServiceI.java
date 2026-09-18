package com.bit.backend.services;

import com.bit.backend.dtos.TechnicianDto;

import java.util.List;

public interface TechnicianServiceI {
    TechnicianDto addTechnician(TechnicianDto technicianDto);
    List<TechnicianDto> getAllTechnicians();
    TechnicianDto getTechnicianById(Integer id);
    TechnicianDto updateTechnician(Integer id, TechnicianDto technicianDto);
    TechnicianDto deleteTechnician(Integer id);
}
