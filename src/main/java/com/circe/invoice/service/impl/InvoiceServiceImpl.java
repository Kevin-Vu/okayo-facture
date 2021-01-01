package com.circe.invoice.service.impl;

import com.circe.invoice.configuration.CirceConfiguration;
import com.circe.invoice.repository.data.InvoiceRepository;
import com.circe.invoice.service.InvoiceService;
import com.circe.invoice.dto.designation.CreateDesignationDto;
import com.circe.invoice.dto.invoice.InvoiceDto;
import com.circe.invoice.dto.mapper.DesignationMapper;
import com.circe.invoice.dto.mapper.InvoiceMapper;
import com.circe.invoice.entity.referentiel.UserEntity;
import com.circe.invoice.entity.data.InvoiceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private DesignationMapper designationMapper;

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Autowired
    private CirceConfiguration circeConfiguration;

    @Autowired
    private InvoiceRepository invoiceRepository;


    /**
     * Create facture with several designation for a given client
     * @param createDesignationDtoList : list of CreateDesignationDto
     * @return : FactureEntity
     */
    @Override
    @Transactional
    public InvoiceEntity createFacture(List<CreateDesignationDto> createDesignationDtoList, UserEntity userEntity){

//        // Convert
//        List<DesignationEntity> designationEntityList = designationMapper.convertListCreateDto(createDesignationDtoList);
//
//        float tth = 0;
//        float ttc = 0;
//        float ttva = 0;
//        int qte = 0;
//
//        // Compute tva, ht, ttc
//        for(DesignationEntity entity: designationEntityList){
//            float unitHt = (entity.getUnitPriceNoTaxes() * entity.getQuantity()) * (1 - entity.getDiscount() / 100);
//            float unitTt = unitHt * (1 + entity.getTaxes() / (float) 100);
//            ttva += entity.getTaxes() * entity.getQuantity();
//            qte += entity.getQuantity();
//            ttc += unitTt;
//            tth += unitHt;
//        }
//        if(qte != 0){
//            ttva /= qte;
//        }
//
//        // Create the facture entity
//        InvoiceEntity invoiceEntity = new InvoiceEntity().setClient(userEntity)
//                                                        .setInvoiceDate(Timestamp.valueOf(LocalDateTime.now()))
//                                                        .setTotalNoTaxes(tth)
//                                                        .setTotalWithTaxes(ttc)
//                                                        .setTotalTaxes(ttva)
//                                                        .setDesignations(designationEntityList)
//                                                        .setExpirityDate(Timestamp.valueOf(LocalDateTime.now().plusMonths(okayoConfiguration.getEcheance())));
//
//        return invoiceRepository.save(invoiceEntity);
        return null;
    }

    /**
     * Get the facture (invoice) dto for a given client
     * @param userEntity : ClientEntity
     * @return : list of FactureDto
     */
    @Override
    public List<InvoiceDto> getAllFactureForClient(UserEntity userEntity){
        List<InvoiceEntity> invoiceEntityList = invoiceRepository.findAllByClient(userEntity);
        return invoiceMapper.convertListInvoiceEntity(invoiceEntityList);
    }

}

















