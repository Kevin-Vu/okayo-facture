package com.circe.invoice.service.impl;

import com.circe.invoice.enumeration.AuthorityEnum;
import com.circe.invoice.exception.notfound.ClientNotFoundException;
import com.circe.invoice.repository.referential.AuthorityRepository;
import com.circe.invoice.repository.referential.UserRepository;
import com.circe.invoice.dto.client.ClientDto;
import com.circe.invoice.dto.client.CreateClientDto;
import com.circe.invoice.dto.mapper.ClientMapper;
import com.circe.invoice.entity.referential.AuthorityEntity;
import com.circe.invoice.entity.referential.UserEntity;
import com.circe.invoice.service.UserService;
import com.circe.invoice.util.BCryptManagerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service("userDetailsService")
public class UserServiceImpl implements UserService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptManagerUtil bCryptManagerUtil;

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    public AuthorityEntity getAuto(){
        return authorityRepository.findByName(AuthorityEnum.USER.getValue());
    }

    /**
     * Implement a custom Principal for Spring Security, in CurrentUser we have the id of the user
     * @param codeClient : code client
     * @return : CurrentUser which extends Spring's User object
     */
    @Override
    public UserDetails loadUserByUsername(String codeClient){
        return null;
//        UserEntity userEntity;
//        try{
//            userEntity = this.loadClientByCodeClient(codeClient);
//        }catch (ClientNotFoundException e){
//            throw new UsernameNotFoundException("Le code client n'existe pas : " + codeClient);
//        }
//
//        AuthorityEnum authorityEnum = Enums.getIfPresent(AuthorityEnum.class, userEntity.getAuthority().getName()).orNull();
//        if(authorityEnum == null){
//            throw new UsernameNotFoundException("User has no role");
//        }
//
//        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authorityEnum.getValue());
//
//        List<GrantedAuthority> authorities = Collections.singletonList(grantedAuthority);
//        return new CurrentUser(userEntity.getClientCode(), userEntity.getPassword(), authorities, userEntity.getId(), userEntity.getCompanyEntity().getName());
    }

    /**
     * Load a client by its client code
     * @param clientCode : client code
     * @return : ClientEntity
     * @throws ClientNotFoundException
     */
    @Override
    public UserEntity loadClientByCodeClient(String clientCode) throws ClientNotFoundException {
        Optional<UserEntity> clientEntity = userRepository.findByUserCode(clientCode);
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
        Optional<UserEntity> clientEntity = userRepository.findById(id);
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

//        UserEntity userEntity = clientMapper.convert(createClientDto, authorityRepository)
//                                        .setClientCode(ClientUtil.createCodeClient())
//                                        .setPassword(bCryptManagerUtil.getPasswordEncoder().encode(createClientDto.getPassword()))
//                ;
//
//        userRepository.save(userEntity);
    }

    /**
     * Update a client
     * @param clientDto : ClientDto
     * @return : ClientEntity
     */
    @Override
    @Transactional
    public void updateClient(ClientDto clientDto) throws ClientNotFoundException {

//        AuthorityEntity authorityEntity = this.authorityRepository.findByName(clientDto.getAuthority());
//        // TODO EXCEPTION
//
//        UserEntity userEntity = this.loadClientById(clientDto.getId());
//        userEntity.setFirstname(clientDto.getFirstname())
//                    .setLastname(clientDto.getLastname())
//                    .setAuthorityEntity(authorityEntity)
//                    .setEmail(clientDto.getEmail());
//        userRepository.save(userEntity);
    }

}
