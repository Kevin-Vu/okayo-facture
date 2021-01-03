package com.circe.invoice.controller;

import com.circe.invoice.dto.mapper.UserMapper;
import com.circe.invoice.security.CurrentUser;
import com.circe.invoice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping(value = "/api/auth/client")
    @PreAuthorize("hasAnyAuthority('RIGHT_ADMIN')")
    public ResponseEntity<String> getClientByCodeClient(CurrentUser user)  {
        return new ResponseEntity<>("defef", HttpStatus.OK);
    }

//    /**
//     * Create a ClientEntity in database from a CreateClientDto
//     * @param createClientDto : CreateClientDto
//     * @return : Response status
//     */
//    @ApiOperation(value = "Créer un nouveau client")
//    @PostMapping(value = "/api/auth/manager/client")
//    public ResponseEntity<HttpStatus> createClient(CurrentUser user, @RequestBody CreateClientDto createClientDto) throws ClientBadRequestException {
//        if(!ClientUtil.checkCreateClientInput(createClientDto) || !ClientUtil.checkUserBelongsToCompany(user, createClientDto.getCompany())){
//            throw new ClientBadRequestException("Le client contient des informations erronés");
//        }
//        if(!ClientUtil.checkUserBelongsToCompany(user, createClientDto.getCompany())){
//            throw new ClientBadRequestException("Impossible de créer un client d'une autre entreprise");
//        }
//        userService.createClient(createClientDto);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    /**
//     * Read a client in database from a given name
//     * @param clientCode : client code
//     * @return : ClientDto
//     * @throws UserNotFoundException
//     */
//    @ApiOperation(value = "Récupère un client par son code client")
//    @GetMapping(value = "/api/auth/client")
//    public ResponseEntity<ClientDto> getClientByCodeClient(CurrentUser user, @RequestParam String clientCode) throws UserNotFoundException, ClientBadRequestException {
//        UserEntity userEntity = userService.loadClientByCodeClient(clientCode);
//        if(!ClientUtil.checkUserBelongsToCompany(user, userEntity)){
//            throw new ClientBadRequestException("Impossible de récupérer un client d'une autre entreprise");
//        }
//      //  ClientDto clientDto = clientMapper.convert(userEntity);
//        return new ResponseEntity<>(null, HttpStatus.OK);
//    }
//
//    /**
//     * Get current client
//     * @param user : CurrentUser
//     * @return : ClientDto
//     * @throws UserNotFoundException
//     * @throws ClientBadRequestException
//     */
//    @ApiOperation(value = "Récupère le client courant")
//    @GetMapping(value = "/api/auth/client/current")
//    public ResponseEntity<ClientDto> getCurrentClient(CurrentUser user) throws UserNotFoundException {
//        UserEntity userEntity = userService.loadClientById(user.getId());
//       // ClientDto clientDto = clientMapper.convert(userEntity);
//        return new ResponseEntity<>(null, HttpStatus.OK);
//    }
//
//
//    /**
//     * Update a client
//     * @param clientDto : ClientDto
//     * @return : Response status
//     * @throws UserNotFoundException
//     */
//    @ApiOperation(value = "Met à jour un client")
//    @PatchMapping(value = "/api/auth/manager/client")
//    public ResponseEntity<HttpStatus> updateClient(CurrentUser user, @RequestBody ClientDto clientDto) throws UserNotFoundException, ClientBadRequestException {
//        if(!ClientUtil.checkClientInput(clientDto)){
//            throw new ClientBadRequestException("Le client contient des informations erronés");
//        }
//        if(!ClientUtil.checkUserBelongsToCompany(user, clientDto.getCompany())){
//            throw new ClientBadRequestException("Impossible de mettre à jour un client d'une autre entreprise");
//        }
//        userService.updateClient(clientDto);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    /**
//     * Delete a client for a given id
//     * @param clientId : id of the client to delete
//     * @return : Response status
//     * @throws UserNotFoundException
//     */
//    @ApiOperation(value = "Supprime un client d'après son id")
//    @DeleteMapping(value = "/api/auth/admin/client")
//    public ResponseEntity<HttpStatus> deleteClientById(CurrentUser user, @RequestParam Long clientId) throws UserNotFoundException {
//        if(clientId == null){
//            throw new UserNotFoundException("Le client est null");
//        }
//      //  userService.deleteClientById(clientId, user.getCompanyName());
//        return new ResponseEntity<>(HttpStatus.OK);
//    }


}
