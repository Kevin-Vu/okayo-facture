package com.okayo.facture.service;

import com.okayo.facture.dto.designation.CreateDesignationDto;
import com.okayo.facture.dto.invoice.InvoiceDto;
import com.okayo.facture.entity.referentiel.UserEntity;
import com.okayo.facture.entity.data.InvoiceEntity;

import java.util.List;

public interface InvoiceService {
    InvoiceEntity createFacture(List<CreateDesignationDto> createDesignationDtoList, UserEntity userEntity);
    List<InvoiceDto> getAllFactureForClient(UserEntity userEntity);
}
