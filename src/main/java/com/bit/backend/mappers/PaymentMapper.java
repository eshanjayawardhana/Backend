package com.bit.backend.mappers;

import com.bit.backend.dtos.CustomerSiteDto;
import com.bit.backend.dtos.PaymentDto;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.entities.CustomerSiteEntity;
import com.bit.backend.entities.PaymentEntity;
import com.bit.backend.entities.StatusEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface PaymentMapper {

    StatusDto toStatusDto(StatusEntity entity);
    List<StatusDto> toStatusDtoList(List<StatusEntity> entities);

    @Mapping(target = "status", source = "status")
    @Mapping(target = "invoice", source = "invoice")
    PaymentDto toPaymentDto(PaymentEntity paymentEntity);

    List<PaymentDto> toPaymentDtoList(List<PaymentEntity> paymentEntityList);


    @Mapping(target = "status", ignore = true)
    @Mapping(target = "invoice", ignore = true)
    PaymentEntity toPaymentEntity(PaymentDto paymentDto);
}
