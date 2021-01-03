package com.circe.invoice.util;

import com.circe.invoice.dto.user.CreateUserDto;
import com.circe.invoice.dto.user.UserDto;
import org.apache.commons.lang3.StringUtils;

public class UserUtil {

    private UserUtil(){}

    /**
     * Check the content of a CreateUserDto
     * @param createUserDto : CreateUserDto
     * @return : true if input is valid
     */
    public static boolean checkCreateUserInput(CreateUserDto createUserDto){
        if(createUserDto == null){
            return false;
        }
        return !StringUtils.isBlank(createUserDto.getFirstname()) && !StringUtils.isBlank(createUserDto.getLastname()) &&
                !StringUtils.isBlank(createUserDto.getPassword()) && !StringUtils.isBlank(createUserDto.getEmail()) &&
                !StringUtils.isBlank(createUserDto.getLangCode()) && !StringUtils.isBlank(createUserDto.getAuthority());
    }

    /**
     * Check the content of a UserDto
     * @param userDto : UserDto
     * @return : true if input is valid
     */
    public static boolean checkUserInput(UserDto userDto){
        if(userDto == null){
            return false;
        }
        if(userDto.getId() == null || userDto.getId() < 0){
            return false;
        }
        return !StringUtils.isBlank(userDto.getFirstname()) && !StringUtils.isBlank(userDto.getLastname()) &&
                !StringUtils.isBlank(userDto.getEmail()) && !StringUtils.isBlank(userDto.getUserCode()) &&
                !StringUtils.isBlank(userDto.getAuthority());
    }

}
