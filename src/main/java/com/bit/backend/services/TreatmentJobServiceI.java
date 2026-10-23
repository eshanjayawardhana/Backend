package com.bit.backend.services;

import com.bit.backend.dtos.TreatmentJobDto;

import java.util.List;

public interface TreatmentJobServiceI {
    TreatmentJobDto addTreatmentJob(TreatmentJobDto treatmentJobDto);
    List<TreatmentJobDto> getTreatmentJobs();
    TreatmentJobDto getTreatmentJobById(Integer id);
    TreatmentJobDto updateTreatmentJob(Integer id, TreatmentJobDto treatmentJobDto);
    TreatmentJobDto deleteTreatmentJob(Integer id);
}
