package com.bit.backend.mappers;

import com.bit.backend.dtos.InspectionDto;
import com.bit.backend.dtos.InvoiceDto;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.entities.InspectionEntity;
import com.bit.backend.entities.InvoiceEntity;
import com.bit.backend.entities.StatusEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface InvoiceMapper {

    StatusDto toStatusDto(StatusEntity entity);
    List<StatusDto> toStatusDtoList(List<StatusEntity> entities);

    @Mapping(target = "status", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "treatmentJob", ignore = true)
    @Mapping(target = "serviceContract", ignore = true)
    InvoiceEntity toInvoiceEntity(InvoiceDto invoiceDto);

    @Mapping(target = "status", source = "status")
    @Mapping(target = "customer", source = "customer")
    @Mapping(target = "treatmentJob", source = "treatmentJob")
    @Mapping(target = "serviceContract", source = "serviceContract")
    InvoiceDto toInvoiceDto(InvoiceEntity invoiceEntity);

    List<InvoiceDto> toInvoiceDtoList(List<InvoiceEntity> invoiceEntityList);
}
