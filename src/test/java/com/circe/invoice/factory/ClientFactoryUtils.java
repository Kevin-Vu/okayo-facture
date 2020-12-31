package com.circe.invoice.factory;

import com.circe.invoice.dto.client.ClientDto;
import com.circe.invoice.dto.client.CreateClientDto;
import com.circe.invoice.enumeration.AuthorityEnum;
import com.circe.invoice.util.ClientUtil;
import org.apache.commons.lang3.RandomStringUtils;

public class ClientFactoryUtils {

    private ClientFactoryUtils(){}

    public static ClientDto generateClientDto(){
        return new ClientDto().setClientCode(ClientUtil.createCodeClient())
                                .setFirstname(RandomStringUtils.randomAlphanumeric(30))
                                .setLastname(RandomStringUtils.randomAlphanumeric(30))
                                .setEmail(RandomStringUtils.randomAlphabetic(15))
                                .setAuthority(AuthorityEnum.USER.getValue());
    }

    public static CreateClientDto generateCreateClientDto(){
        return new CreateClientDto()
                .setFirstname(RandomStringUtils.randomAlphanumeric(30))
                .setLastname(RandomStringUtils.randomAlphanumeric(30))
                .setEmail(RandomStringUtils.randomAlphabetic(15))
                .setPassword(RandomStringUtils.randomAlphanumeric(8))
                .setAuthority(AuthorityEnum.USER.getValue());
    }
}
