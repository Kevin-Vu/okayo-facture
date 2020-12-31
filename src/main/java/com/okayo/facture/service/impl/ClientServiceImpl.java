package com.okayo.facture.service.impl;

import com.google.common.base.Enums;
import com.okayo.facture.dto.client.ClientDto;
import com.okayo.facture.dto.client.CreateClientDto;
import com.okayo.facture.dto.mapper.ClientMapper;
import com.okayo.facture.entity.referentiel.AuthorityEntity;
import com.okayo.facture.entity.referentiel.UserEntity;
import com.okayo.facture.enumeration.OkayoAuthorityEnum;
import com.okayo.facture.exception.notfound.ClientNotFoundException;
import com.okayo.facture.repository.AuthorityRepository;
import com.okayo.facture.repository.ClientRepository;
import com.okayo.facture.security.CurrentUser;
import com.okayo.facture.service.ClientService;
import com.okayo.facture.util.BCryptManagerUtil;
import com.okayo.facture.util.ClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service("userDetailsService")
public class ClientServiceImpl implements ClientService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BCryptManagerUtil bCryptManagerUtil;

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    public AuthorityEntity getAuto(){
        return authorityRepository.findByName(OkayoAuthorityEnum.USER.getValue());
    }

    /**
     * Implement a custom Principal for Spring Security, in CurrentUser we have the id of the user
     * @param codeClient : code client
     * @return : CurrentUser which extends Spring's User object
     */
    @Override
    public UserDetails loadUserByUsername(String codeClient){
        UserEntity userEntity;
        try{
            userEntity = this.loadClientByCodeClient(codeClient);
        }catch (ClientNotFoundException e){
            throw new UsernameNotFoundException("Le code client n'existe pas : " + codeClient);
        }

        OkayoAuthorityEnum okayoAuthorityEnum = Enums.getIfPresent(OkayoAuthorityEnum.class, userEntity.getAuthorityEntity().getName()).orNull();
        if(okayoAuthorityEnum == null){
            throw new UsernameNotFoundException("User has no role");
        }

        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(okayoAuthorityEnum.getValue());

        List<GrantedAuthority> authorities = Collections.singletonList(grantedAuthority);
        return new CurrentUser(userEntity.getClientCode(), userEntity.getPassword(), authorities, userEntity.getId(), userEntity.getCompanyEntity().getName());
    }

    /**
     * Load a client by its client code
     * @param clientCode : client code
     * @return : ClientEntity
     * @throws ClientNotFoundException
     */
    @Override
    public UserEntity loadClientByCodeClient(String clientCode) throws ClientNotFoundException {
        Optional<UserEntity> clientEntity = clientRepository.findByClientCode(clientCode);
        if(!clientEntity.isPresent()){
            throw new ClientNotFoundException("Le code client n'existe pas : " + clientCode);
        }
        return clientEntity.get();
    }

    /**
     * Load a client by its id
     * @param id : id
     * @return : ClientEntity
     * @throws ClientNotFoundException
     */
    @Override
    public UserEntity loadClientById(Long id) throws ClientNotFoundException {
        Optional<UserEntity> clientEntity = clientRepository.findById(id);
        if(!clientEntity.isPresent()){
            throw new ClientNotFoundException("Le client avec l'id n'existe pas : " + id);
        }
        return clientEntity.get();
    }

    /**
     * Create a client
     * @param createClientDto : CreateClientDto
     * @return : ClientEntity
     */
    @Override
    @Transactional
    public void createClient(CreateClientDto createClientDto){

        UserEntity userEntity = clientMapper.convert(createClientDto, authorityRepository)
                                        .setClientCode(ClientUtil.createCodeClient())
                                        .setPassword(bCryptManagerUtil.getPasswordEncoder().encode(createClientDto.getPassword()))
                ;

        clientRepository.save(userEntity);
    }

    /**
     * Update a client
     * @param clientDto : ClientDto
     * @return : ClientEntity
     */
    @Override
    @Transactional
    public void updateClient(ClientDto clientDto) throws ClientNotFoundException {

        AuthorityEntity authorityEntity = this.authorityRepository.findByName(clientDto.getAuthority());
        // TODO EXCEPTION

        UserEntity userEntity = this.loadClientById(clientDto.getId());
        userEntity.setFirstname(clientDto.getFirstname())
                    .setLastname(clientDto.getLastname())
                    .setAuthorityEntity(authorityEntity)
                    .setEmail(clientDto.getEmail());
        clientRepository.save(userEntity);
    }

    /**
     * Delete client
     * @param id : id
     */
    @Override
    @Transactional
    public void deleteClientById(Long id, String company) throws ClientNotFoundException {
        if(clientRepository.existsClientEntityByIdAndCompanyEntityName(id, company)){
            clientRepository.deleteById(id);
        }else{
            throw new ClientNotFoundException(String.format("Le client avec l'id %d n'existe pas", id));
        }
    }

}
