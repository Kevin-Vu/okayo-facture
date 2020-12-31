package com.circe.invoice.controller;

import com.circe.invoice.dto.designation.CreateDesignationDto;
import com.circe.invoice.dto.invoice.InvoiceDto;
import com.circe.invoice.entity.referentiel.UserEntity;
import com.circe.invoice.exception.badrequest.DesignationBadRequestException;
import com.circe.invoice.exception.notfound.ClientNotFoundException;
import com.circe.invoice.security.CurrentUser;
import com.circe.invoice.service.ClientService;
import com.circe.invoice.service.InvoiceService;
import com.circe.invoice.util.DesignationUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private ClientService clientService;


    /**
     * Create a facture (invoice) for a client
     * @param user : CurrentUser
     * @param createDesignationDtoList : list of CreateDesignationDto
     * @return : response status
     */
    @ApiOperation(value = "Créé une nouvelle facture pour un client à partir de son id et d'une liste de désignations")
    @PostMapping(value = "/api/auth/manager/facture")
    public ResponseEntity<HttpStatus> createFacture(CurrentUser user, @RequestBody List<CreateDesignationDto> createDesignationDtoList) throws ClientNotFoundException, DesignationBadRequestException {
        if(!DesignationUtil.checkListCreateDesignationInput(createDesignationDtoList)){
            throw new DesignationBadRequestException("Les designations contiennent des mauvaises informations");
        }

        UserEntity userEntity = clientService.loadClientById(user.getId());
        invoiceService.createFacture(createDesignationDtoList, userEntity);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * Get all facture for a given client
     * @param user : CurrentUser
     * @return : list of FactureDto
     * @throws ClientNotFoundException
     */
    @ApiOperation(value = "Récupère toutes les factures d'un client")
    @GetMapping(value = "/api/auth/facture")
    public ResponseEntity<List<InvoiceDto>> getAllFactureForClient(CurrentUser user) throws ClientNotFoundException {
        UserEntity userEntity = clientService.loadClientById(user.getId());
        List<InvoiceDto> invoiceDtoList = invoiceService.getAllFactureForClient(userEntity);

        return new ResponseEntity<>(invoiceDtoList, HttpStatus.OK);
    }


}
