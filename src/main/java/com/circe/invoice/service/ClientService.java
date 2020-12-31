package com.circe.invoice.service;

import com.circe.invoice.exception.notfound.ClientNotFoundException;
import com.circe.invoice.dto.client.ClientDto;
import com.circe.invoice.dto.client.CreateClientDto;
import com.circe.invoice.entity.referentiel.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface ClientService extends UserDetailsService {

    UserEntity loadClientByCodeClient(String codeClient) throws ClientNotFoundException;
    UserEntity loadClientById(Long id) throws ClientNotFoundException;
    void createClient(CreateClientDto clientDto);
    void updateClient(ClientDto clientDto) throws ClientNotFoundException;
    void deleteClientById(Long id, String company) throws ClientNotFoundException;
}