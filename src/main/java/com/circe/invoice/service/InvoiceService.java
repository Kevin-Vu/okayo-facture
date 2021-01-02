package com.circe.invoice.service;

import com.circe.invoice.dto.designation.CreateDesignationDto;
import com.circe.invoice.dto.invoice.InvoiceDto;
import com.circe.invoice.entity.referential.UserEntity;
import com.circe.invoice.entity.data.InvoiceEntity;

import java.util.List;

public interface InvoiceService {
    InvoiceEntity createFacture(List<CreateDesignationDto> createDesignationDtoList, UserEntity userEntity);
    List<InvoiceDto> getAllFactureForClient(UserEntity userEntity);
}
