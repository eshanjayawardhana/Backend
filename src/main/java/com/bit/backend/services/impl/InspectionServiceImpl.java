package com.bit.backend.services.impl;

import com.bit.backend.dtos.InspectionDto;
import com.bit.backend.entities.*;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.InspectionMapper;
import com.bit.backend.repositories.*;
import com.bit.backend.services.InspectionServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InspectionServiceImpl implements InspectionServiceI {

    private final StatusRepository statusRepository;
    private final ServiceRequestRepository serviceRequestRepository;
    private final CustomerSiteRepository customerSiteRepository;
    private final TechnicianRepository technicianRepository;
    private final InspectionRepository inspectionRepository;
    private final InspectionMapper inspectionMapper;

    public InspectionServiceImpl(StatusRepository statusRepository, ServiceRequestRepository serviceRequestRepository, CustomerSiteRepository customerSiteRepository, TechnicianRepository technicianRepository, InspectionRepository inspectionRepository, InspectionMapper inspectionMapper) {
        this.statusRepository = statusRepository;
        this.serviceRequestRepository = serviceRequestRepository;
        this.customerSiteRepository = customerSiteRepository;
        this.technicianRepository = technicianRepository;
        this.inspectionRepository = inspectionRepository;
        this.inspectionMapper = inspectionMapper;
    }

    @Override
    @Transactional
    public InspectionDto addInspection(InspectionDto inspectionDto) {
        InspectionEntity entity = inspectionMapper.toInspectionEntity(inspectionDto);
        entity.setId(null);

        StatusEntity status = resolveStatus(inspectionDto);
        entity.setStatus(status);

        ServiceRequestEntity serviceRequest = resolveServiceRequest(inspectionDto);
        entity.setServiceRequest(serviceRequest);

        CustomerSiteEntity customerSite = resolveCustomerSite(inspectionDto);
        entity.setCustomerSite(customerSite);

        TechnicianEntity technician = resolveTechnician(inspectionDto);
        entity.setTechnician(technician);

        InspectionEntity saved = inspectionRepository.save(entity);
        if (saved.getInspectionCode() == null || saved.getInspectionCode().isBlank()){
            saved.setInspectionCode("INS-" + saved.getId());
            saved = inspectionRepository.save(saved);
        }

        return inspectionMapper.toInspectionDto(saved);
    }

    @Override
    public List<InspectionDto> getAllInspections() {
        return inspectionMapper.toInspectionDtoList(inspectionRepository.findAll());
    }

    @Override
    public InspectionDto getInspectionById(Integer id) {
        InspectionEntity entity = inspectionRepository.findById(id)
                .orElseThrow(() -> new AppException("Inspection not found", HttpStatus.NOT_FOUND));
        return inspectionMapper.toInspectionDto(entity);
    }

    @Override
    public InspectionDto updateInspection(Integer id, InspectionDto inspectionDto) {
        InspectionEntity existing = inspectionRepository.findById(id)
                .orElseThrow(() -> new AppException("Inspection not found", HttpStatus.NOT_FOUND));

        StatusEntity status = resolveStatus(inspectionDto);
        CustomerSiteEntity customerSite = resolveCustomerSite(inspectionDto);
        ServiceRequestEntity serviceRequest = resolveServiceRequest(inspectionDto);
        TechnicianEntity technician = resolveTechnician(inspectionDto);

        existing.setInspectionDate(inspectionDto.getInspectionDate());
        existing.setInfestationLevel(inspectionDto.getInfestationLevel());
        existing.setAffectedAreas(inspectionDto.getAffectedAreas());
        existing.setFindings(inspectionDto.getFindings());
        existing.setRecommendation(inspectionDto.getRecommendation());
        existing.setStatus(status);
        existing.setServiceRequest(serviceRequest);
        existing.setCustomerSite(customerSite);
        existing.setTechnician(technician);

        if (inspectionDto.getInspectionCode() != null && !inspectionDto.getInspectionCode().isBlank()) {
            existing.setInspectionCode(inspectionDto.getInspectionCode());
        }

        return inspectionMapper.toInspectionDto(inspectionRepository.save(existing));
    }

    @Override
    public InspectionDto deleteInspection(Integer id) {
        InspectionEntity existing = inspectionRepository.findById(id)
                .orElseThrow(() -> new AppException("Inspection not found", HttpStatus.NOT_FOUND));
        InspectionDto dto = inspectionMapper.toInspectionDto(existing);
        inspectionRepository.delete(existing);
        return dto;
    }

    //////////////////////////////////////////////////////////

    private StatusEntity resolveStatus(InspectionDto inspectionDto) {

        if (inspectionDto.getStatus() == null || inspectionDto.getStatus().getId() == null) {
            throw new AppException("Status is required", HttpStatus.BAD_REQUEST);
        }

        StatusEntity status = statusRepository
                .findById(inspectionDto.getStatus().getId())
                .orElseThrow(() -> new AppException(
                        "Status not found",
                        HttpStatus.BAD_REQUEST
                ));

        // Validate the status type
        if (!"INSPECTION".equals(status.getModuleType())) {
            throw new AppException(
                    "Invalid status for Inspection module",
                    HttpStatus.BAD_REQUEST
            );
        }

        return status;
    }

    private ServiceRequestEntity resolveServiceRequest(InspectionDto inspectionDto) {
        if (inspectionDto.getServiceRequest() == null || inspectionDto.getServiceRequest().getId() == null) {
            throw new AppException("Service request is required", HttpStatus.BAD_REQUEST);
        }
        return serviceRequestRepository.findById(inspectionDto.getServiceRequest().getId())
                .orElseThrow(() -> new AppException("Service request not found", HttpStatus.BAD_REQUEST));
    }

    private CustomerSiteEntity resolveCustomerSite(InspectionDto inspectionDto) {
        if (inspectionDto.getCustomerSite() == null || inspectionDto.getCustomerSite().getId() == null) {
            throw new AppException("Customer site is required", HttpStatus.BAD_REQUEST);
        }
        return customerSiteRepository.findById(inspectionDto.getCustomerSite().getId())
                .orElseThrow(() -> new AppException("Customer site not found", HttpStatus.BAD_REQUEST));
    }

    private TechnicianEntity resolveTechnician(InspectionDto inspectionDto) {
        if (inspectionDto.getTechnician() == null || inspectionDto.getTechnician().getId() == null) {
            throw new AppException("Technician is required", HttpStatus.BAD_REQUEST);
        }
        return technicianRepository.findById(inspectionDto.getTechnician().getId())
                .orElseThrow(() -> new AppException("Technician not found", HttpStatus.BAD_REQUEST));
    }


}
