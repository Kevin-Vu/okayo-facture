package com.circe.invoice.factory;

import com.circe.invoice.dto.user.UserDto;
import com.circe.invoice.dto.user.CreateUserDto;

public class ClientFactoryUtils {

    private ClientFactoryUtils(){}

    public static UserDto generateClientDto(){
        return new UserDto()/*.setClientCode(ClientUtil.createCodeClient())
                                .setFirstname(RandomStringUtils.randomAlphanumeric(30))
                                .setLastname(RandomStringUtils.randomAlphanumeric(30))
                                .setEmail(RandomStringUtils.randomAlphabetic(15))
                                .setAuthority(AuthorityEnum.USER.getValue())*/;
    }

    public static CreateUserDto generateCreateClientDto(){
        return new CreateUserDto()/*
                .setFirstname(RandomStringUtils.randomAlphanumeric(30))
                .setLastname(RandomStringUtils.randomAlphanumeric(30))
                .setEmail(RandomStringUtils.randomAlphabetic(15))
                .setPassword(RandomStringUtils.randomAlphanumeric(8))
                .setAuthority(AuthorityEnum.USER.getValue())*/;
    }
}
