package com.circe.invoice.controller;

import com.circe.invoice.dto.mapper.UserMapper;
import com.circe.invoice.dto.user.CreateUserDto;
import com.circe.invoice.dto.user.UserDto;
import com.circe.invoice.exception.badrequest.UserBadRequestException;
import com.circe.invoice.exception.notfound.UserNotFoundException;
import com.circe.invoice.security.CurrentUser;
import com.circe.invoice.service.UserService;
import com.circe.invoice.util.UserUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    /**
     * Create an UserEntity in database from a CreateUserDto
     *
     * @param createUserDto : CreateUserDto
     *
     * @return : UserDto
     */
    @ApiOperation(value = "Create a new user")
    @PostMapping(value = "/api/auth/client")
    @PreAuthorize("hasAnyAuthority('RIGHT_ADMIN')")
    public ResponseEntity<UserDto> createUser(CurrentUser user, @RequestBody CreateUserDto createUserDto) throws UserBadRequestException {
        if(!UserUtil.checkCreateUserInput(createUserDto))
            throw new UserBadRequestException("CreateUserDto contains bad input");
        return new ResponseEntity<>(userService.createUser(createUserDto), HttpStatus.OK);
    }

    /**
     * Get an User from its id
     *
     * @param id : user id
     *
     * @return : UserDto
     */
    @ApiOperation(value = "Get a user by its id")
    @GetMapping(value = "/api/auth/client")
    public ResponseEntity<UserDto> getUser(CurrentUser user, @RequestParam Integer id) throws UserBadRequestException, UserNotFoundException {
        if(id == null || id < 0)
            throw new UserBadRequestException("Bad id");
        if(UserUtil.isAdmin(user) || user.getId().equals(id))
            new ResponseEntity<>(userService.loadUserById(id), HttpStatus.OK);;
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /**
     * Delete an user by its id
     *
     * @param user : user id
     *
     * @return : HttpStatus
     */
    @ApiOperation(value = "Delete an user")
    @DeleteMapping(value = "/api/auth/client")
    @PreAuthorize("hasAnyAuthority('RIGHT_ADMIN')")
    public ResponseEntity<HttpStatus> deleteUser(CurrentUser user, @RequestParam Integer id) throws UserBadRequestException {
        if(id == null || id < 0)
            throw new UserBadRequestException("Bad id");
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Update an UserEntity from an UserDto
     *
     * @param userDto : UserDto
     *
     * @return : UserDto
     */
    @ApiOperation(value = "Update an user")
    @PutMapping(value = "/api/auth/client")
    public ResponseEntity<UserDto> updateUser(CurrentUser user, @RequestBody UserDto userDto) throws UserBadRequestException, UserNotFoundException {
        if(!UserUtil.checkUserInput(userDto))
            throw new UserBadRequestException("UserDto contains bad input");
        if(UserUtil.isAdmin(user) || user.getId().equals(userDto.getId()))
            new ResponseEntity<>(userService.updateUser(userDto), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }


}
