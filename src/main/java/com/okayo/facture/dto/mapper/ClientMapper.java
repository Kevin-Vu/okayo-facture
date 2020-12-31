package com.okayo.facture.dto.mapper;

import com.okayo.facture.dto.client.ClientDto;
import com.okayo.facture.dto.client.CreateClientDto;
import com.okayo.facture.entity.referentiel.AuthorityEntity;
import com.okayo.facture.entity.referentiel.UserEntity;
import com.okayo.facture.repository.AuthorityRepository;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(source = "authorityEntity.name", target = "authority")
    @Mapping(source = "companyEntity.name", target = "company")
    ClientDto convert(UserEntity userEntity);

    UserEntity convert(CreateClientDto createClientDto, @Context AuthorityRepository authorityRepository);

    @AfterMapping
    default void after(CreateClientDto createClientDto, @MappingTarget UserEntity userEntity, @Context AuthorityRepository authorityRepository){
        AuthorityEntity authorityEntity = authorityRepository.findByName(createClientDto.getAuthority());
        if(authorityEntity != null){
            userEntity.setAuthorityEntity(authorityEntity);
        }
    }


}
