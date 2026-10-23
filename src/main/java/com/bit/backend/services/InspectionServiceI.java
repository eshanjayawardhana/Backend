package com.bit.backend.services;

import com.bit.backend.dtos.CustomerDto;
import com.bit.backend.dtos.InspectionDto;

import java.util.List;

public interface InspectionServiceI {
    InspectionDto addInspection(InspectionDto inspectionDto);
    List<InspectionDto> getAllInspections();
    InspectionDto getInspectionById(Integer id);
    InspectionDto updateInspection(Integer id, InspectionDto inspectionDto);
    InspectionDto deleteInspection(Integer id);
}
