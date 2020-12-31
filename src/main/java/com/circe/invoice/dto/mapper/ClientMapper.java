package com.circe.invoice.dto.mapper;

import com.circe.invoice.entity.referentiel.AuthorityEntity;
import com.circe.invoice.entity.referentiel.UserEntity;
import com.circe.invoice.repository.AuthorityRepository;
import com.circe.invoice.dto.client.ClientDto;
import com.circe.invoice.dto.client.CreateClientDto;
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
