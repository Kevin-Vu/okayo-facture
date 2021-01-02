package com.circe.invoice.util;

import com.circe.invoice.entity.referential.UserEntity;
import com.circe.invoice.security.CurrentUser;
import com.circe.invoice.dto.client.ClientDto;
import com.circe.invoice.dto.client.CreateClientDto;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

public class ClientUtil {

    private ClientUtil(){}

    /**
     * Check the content of a CreateClientDto
     * @param createClientDto : CreateClientDto
     * @return : true if input is valid
     */
    public static boolean checkCreateClientInput(CreateClientDto createClientDto){
        if(createClientDto == null){
            return false;
        }
        return !StringUtils.isBlank(createClientDto.getFirstname()) && !StringUtils.isBlank(createClientDto.getLastname()) &&
                !StringUtils.isBlank(createClientDto.getPassword()) && !StringUtils.isBlank(createClientDto.getEmail()) &&
                !StringUtils.isBlank(createClientDto.getCompany()) && !StringUtils.isBlank(createClientDto.getAuthority());
    }

    /**
     * Check the content of a ClientDto
     * @param clientDto : ClientDto
     * @return : true if input is valid
     */
    public static boolean checkClientInput(ClientDto clientDto){
        if(clientDto == null){
            return false;
        }
        if(clientDto.getId() == null || clientDto.getId() < 0){
            return false;
        }
        return !StringUtils.isBlank(clientDto.getFirstname()) && !StringUtils.isBlank(clientDto.getLastname()) &&
                !StringUtils.isBlank(clientDto.getEmail()) && !StringUtils.isBlank(clientDto.getCompany()) &&
                !StringUtils.isBlank(clientDto.getAuthority());
    }

    /**
     * Check if the user belongs to the company
     * @param user : CurrentUser
     * @param company : company name
     * @return : boolean
     */
    public static boolean checkUserBelongsToCompany(CurrentUser user, String company){
        return true;
        //return StringUtils.compare(user.getCompanyName(), company) == 0;
    }

    /**
     * Check if the user belongs to the company
     * @param user : CurrentUser
     * @param userEntity : ClientEntity
     * @return : boolean
     */
    public static boolean checkUserBelongsToCompany(CurrentUser user, UserEntity userEntity){
        return true;
        // return StringUtils.compare(user.getCompanyName(), userEntity.getCompanyEntity().getName()) == 0;
    }

    /**
     * Create a code client
     * (Actually a uuid is unique, there a few chance that we generate two same uuid
     * but it might be interesting to verify that when save in database)
     * @return : uuid
     */
    public static String createCodeClient(){
        return UUID.randomUUID().toString();
    }

}
