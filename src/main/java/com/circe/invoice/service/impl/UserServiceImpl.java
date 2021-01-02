package com.circe.invoice.service.impl;

import com.circe.invoice.entity.referential.RightEntity;
import com.circe.invoice.enumeration.AuthorityEnum;
import com.circe.invoice.exception.notfound.UserNotFoundException;
import com.circe.invoice.repository.referential.AuthorityRepository;
import com.circe.invoice.repository.referential.UserRepository;
import com.circe.invoice.dto.client.ClientDto;
import com.circe.invoice.dto.client.CreateClientDto;
import com.circe.invoice.dto.mapper.ClientMapper;
import com.circe.invoice.entity.referential.AuthorityEntity;
import com.circe.invoice.entity.referential.UserEntity;
import com.circe.invoice.security.CurrentUser;
import com.circe.invoice.service.UserService;
import com.circe.invoice.util.BCryptManagerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
     * Implement a custom Principal for Spring Security, in CurrentUser we have the id of the user and its email
     * @param userCode : user code
     * @return : CurrentUser which extends Spring's User object
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userCode){
        UserEntity userEntity;
        try{
            userEntity = this.loadUserByCode(userCode);
        }catch (UserNotFoundException e){
            throw new UsernameNotFoundException(e.getMessage());
        }

        List<RightEntity> rights = userEntity.getAuthority()
                .getRightEntities();
        List<GrantedAuthority> authorities = rights.stream()
                                                .map(r -> new SimpleGrantedAuthority(r.getName()))
                                                .collect(Collectors.toList());

        return new CurrentUser(userEntity.getUserCode(), userEntity.getPassword(), authorities, userEntity.getId(), userEntity.getEmail(),
                    userEntity.getPwdExpirationDate(), userEntity.getPwdAccessStart(), userEntity.getPwdAccessEnd());
    }

    /**
     * Load a User by its user code
     * @param userCode : user code
     * @return : UserEntity
     * @throws UserNotFoundException
     */
    @Override
    public UserEntity loadUserByCode(String userCode) throws UserNotFoundException {
        Optional<UserEntity> clientEntity = userRepository.findByUserCode(userCode);
        if(!clientEntity.isPresent()){
            throw new UserNotFoundException("User doesn't exist : " + userCode);
        }
        return clientEntity.get();
    }

    /**
     * Load a client by its id
     * @param id : id
     * @return : ClientEntity
     * @throws UserNotFoundException
     */
    @Override
    public UserEntity loadClientById(Integer id) throws UserNotFoundException {
        UserEntity clientEntity = userRepository.findById(id).orElse(null);
        if(clientEntity != null){
            throw new UserNotFoundException("Le client avec l'id n'existe pas : " + id);
        }
        return clientEntity;
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
    public void updateClient(ClientDto clientDto) throws UserNotFoundException {

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
