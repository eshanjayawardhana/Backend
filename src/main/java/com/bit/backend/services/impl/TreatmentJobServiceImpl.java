package com.bit.backend.services.impl;

import com.bit.backend.dtos.ServiceRequestDto;
import com.bit.backend.dtos.TreatmentJobDto;
import com.bit.backend.entities.*;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.TreatmentJobMapper;
import com.bit.backend.repositories.*;
import com.bit.backend.services.TreatmentJobServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TreatmentJobServiceImpl implements TreatmentJobServiceI {

    private final ServiceRequestRepository serviceRequestRepository;
    private final InspectionRepository inspectionRepository;
    private final ServiceContractRepository serviceContractRepository;
    private final CustomerSiteRepository customerSiteRepository;
    private final TechnicianRepository technicianRepository;
    private final ServiceTypeRepository serviceTypeRepository;
    private final StatusRepository statusRepository;
    private final TreatmentJobRepository treatmentJobRepository;
    private final TreatmentJobMapper treatmentJobMapper;

    public TreatmentJobServiceImpl(ServiceRequestRepository serviceRequestRepository, InspectionRepository inspectionRepository, ServiceContractRepository serviceContractRepository, CustomerSiteRepository customerSiteRepository, TechnicianRepository technicianRepository, ServiceTypeRepository serviceTypeRepository, StatusRepository statusRepository, TreatmentJobRepository treatmentJobRepository, TreatmentJobMapper treatmentJobMapper) {
        this.serviceRequestRepository = serviceRequestRepository;
        this.inspectionRepository = inspectionRepository;
        this.serviceContractRepository = serviceContractRepository;
        this.customerSiteRepository = customerSiteRepository;
        this.technicianRepository = technicianRepository;
        this.serviceTypeRepository = serviceTypeRepository;
        this.statusRepository = statusRepository;
        this.treatmentJobRepository = treatmentJobRepository;
        this.treatmentJobMapper = treatmentJobMapper;
    }

    @Override
    @Transactional
    public TreatmentJobDto addTreatmentJob(TreatmentJobDto treatmentJobDto) {
        TreatmentJobEntity entity = treatmentJobMapper.toTreatmentJobEntity(treatmentJobDto);

        entity.setId(null);

        ServiceRequestEntity serviceRequest = resolveServiceRequest(treatmentJobDto);
        entity.setServiceRequest(serviceRequest);

        InspectionEntity inspection = resolveInspection(treatmentJobDto);
        entity.setInspection(inspection);

        ServiceContractEntity serviceContract = resolveServiceContract(treatmentJobDto);
        entity.setServiceContract(serviceContract);

        CustomerSiteEntity customerSite = resolveCustomerSite(treatmentJobDto);
        entity.setCustomerSite(customerSite);

        TechnicianEntity technician = resolveTechnician(treatmentJobDto);
        entity.setTechnician(technician);

        ServiceTypeEntity serviceType = resolveServiceType(treatmentJobDto);
        entity.setServiceType(serviceType);

        StatusEntity status = resolveStatus(treatmentJobDto);
        entity.setStatus(status);

        TreatmentJobEntity saved = treatmentJobRepository.save(entity);

        if (saved.getJobCode() == null || saved.getJobCode().isBlank()) {
            saved.setJobCode("JOB-" + java.time.Year.now().getValue() + "-" + saved.getId());
            saved = treatmentJobRepository.save(saved);
        }

        return treatmentJobMapper.toTreatmentJobDto(saved);
    }

    @Override
    public List<TreatmentJobDto> getTreatmentJobs() {
        return treatmentJobMapper.toTreatmentJobDtoList(treatmentJobRepository.findAll());
    }

    @Override
    public TreatmentJobDto getTreatmentJobById(Integer id) {
        TreatmentJobEntity entity = treatmentJobRepository.findById(id)
                .orElseThrow(() -> new AppException("Treatment Job not found", HttpStatus.NOT_FOUND));
        return treatmentJobMapper.toTreatmentJobDto(entity);
    }

    @Override
    @Transactional
    public TreatmentJobDto updateTreatmentJob(Integer id, TreatmentJobDto treatmentJobDto) {
        TreatmentJobEntity existing = treatmentJobRepository.findById(id)
                        .orElseThrow(() -> new AppException("Treatment Job not found", HttpStatus.NOT_FOUND));

        ServiceRequestEntity serviceRequest = resolveServiceRequest(treatmentJobDto);
        InspectionEntity inspection = resolveInspection(treatmentJobDto);
        ServiceContractEntity serviceContract = resolveServiceContract(treatmentJobDto);
        CustomerSiteEntity customerSite = resolveCustomerSite(treatmentJobDto);
        TechnicianEntity technician = resolveTechnician(treatmentJobDto);
        ServiceTypeEntity serviceType = resolveServiceType(treatmentJobDto);
        StatusEntity status = resolveStatus(treatmentJobDto);

        existing.setScheduledDate(treatmentJobDto.getScheduledDate());
        existing.setCompletedDate(treatmentJobDto.getCompletedDate());
        existing.setWorkDone(treatmentJobDto.getWorkDone());
        existing.setHoursSpent(treatmentJobDto.getHoursSpent());
        existing.setCustomerSignature(treatmentJobDto.getCustomerSignature());

        existing.setServiceRequest(serviceRequest);
        existing.setInspection(inspection);
        existing.setServiceContract(serviceContract);
        existing.setCustomerSite(customerSite);
        existing.setTechnician(technician);
        existing.setServiceType(serviceType);
        existing.setStatus(status);

        if (treatmentJobDto.getJobCode() != null && !treatmentJobDto.getJobCode().isBlank()) {
            existing.setJobCode(treatmentJobDto.getJobCode());
        }

        return treatmentJobMapper.toTreatmentJobDto(treatmentJobRepository.save(existing));

    }

    @Override
    @Transactional
    public TreatmentJobDto deleteTreatmentJob(Integer id) {
        TreatmentJobEntity existing = treatmentJobRepository.findById(id)
                .orElseThrow(() -> new AppException("Treatment Job not found", HttpStatus.NOT_FOUND));
        TreatmentJobDto dto = treatmentJobMapper.toTreatmentJobDto(existing);
        treatmentJobRepository.delete(existing);
        return dto;
    }


                  /////////////////// Resolvers ////////////////////

    private ServiceRequestEntity resolveServiceRequest(TreatmentJobDto dto) {
        if (dto.getServiceRequest() == null || dto.getServiceRequest().getId() == null) {
            throw new AppException("Service request ID is required", HttpStatus.BAD_REQUEST);
        }
        return serviceRequestRepository.findById(dto.getServiceRequest().getId())
                .orElseThrow(() -> new AppException("Service request not found", HttpStatus.NOT_FOUND));
    }

    private InspectionEntity resolveInspection(TreatmentJobDto dto) {
        if (dto.getInspection() == null || dto.getInspection().getId() == null) {
            throw new AppException("Inspection ID is required", HttpStatus.BAD_REQUEST);
        }
        return inspectionRepository.findById(dto.getInspection().getId())
                .orElseThrow(() -> new AppException("Inspection not found", HttpStatus.NOT_FOUND));
    }

    private ServiceContractEntity resolveServiceContract(TreatmentJobDto dto) {
        if (dto.getServiceContract() == null || dto.getServiceContract().getId() == null) {
            throw new AppException("Service Contract is required", HttpStatus.BAD_REQUEST);
        }
        return serviceContractRepository.findById(dto.getServiceContract().getId())
                .orElseThrow(() -> new AppException("Service contract not found", HttpStatus.NOT_FOUND));
    }

    private CustomerSiteEntity resolveCustomerSite(TreatmentJobDto dto) {
        if (dto.getCustomerSite() == null || dto.getCustomerSite().getId() == null) {
            throw new AppException("Customer site is required", HttpStatus.BAD_REQUEST);
        }
        return customerSiteRepository.findById(dto.getCustomerSite().getId())
                .orElseThrow(() -> new AppException("Customer site not found", HttpStatus.NOT_FOUND));
    }

    private TechnicianEntity resolveTechnician(TreatmentJobDto dto) {
        if (dto.getTechnician() == null || dto.getTechnician().getId() == null) {
            throw new AppException("Technician is required", HttpStatus.BAD_REQUEST);
        }
        return technicianRepository.findById(dto.getTechnician().getId())
                .orElseThrow(() -> new AppException("Technician not found", HttpStatus.NOT_FOUND));
    }

    private ServiceTypeEntity resolveServiceType(TreatmentJobDto dto) {
        if (dto.getServiceType() == null || dto.getServiceType().getId() == null) {
            throw new AppException("Service type is required", HttpStatus.BAD_REQUEST);
        }
        return serviceTypeRepository.findById(dto.getServiceType().getId())
                .orElseThrow(() -> new AppException("Service type not found", HttpStatus.NOT_FOUND));
    }

    private StatusEntity resolveStatus(TreatmentJobDto dto) {
        if (dto.getStatus() == null || dto.getStatus().getId() == null) {
            throw new AppException("Status is required", HttpStatus.BAD_REQUEST);
        }
        return statusRepository.findById(dto.getStatus().getId())
                .orElseThrow(() -> new AppException("Status not found", HttpStatus.NOT_FOUND));
    }

}
