package com.circe.invoice.service;

import com.circe.invoice.exception.notfound.UserNotFoundException;
import com.circe.invoice.dto.client.ClientDto;
import com.circe.invoice.dto.client.CreateClientDto;
import com.circe.invoice.entity.referential.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserEntity loadUserByCode(String userCode) throws UserNotFoundException;
    UserEntity loadClientById(Integer id) throws UserNotFoundException;
    void createClient(CreateClientDto clientDto);
    void updateClient(ClientDto clientDto) throws UserNotFoundException;
}
