package com.circe.invoice.controller;

import com.circe.invoice.dto.client.ClientDto;
import com.circe.invoice.dto.client.CreateClientDto;
import com.circe.invoice.entity.referentiel.UserEntity;
import com.circe.invoice.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.circe.invoice.configuration.BaseTest;
import com.circe.invoice.factory.ClientFactoryUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = {"classpath:test.properties"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TestClientController extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientService clientService;

    private JacksonTester<CreateClientDto> jsonCreateClientDto;
    private JacksonTester<ClientDto> jsonClientDto;

    @Before
    public void before() {

        JacksonTester.initFields(this, new ObjectMapper());
        MockitoAnnotations.initMocks(this);
    }


    /**
     * Test create client
     * @throws Exception
     */
    @Test
    @WithUserDetails(value = "CU-AD-SG-585", userDetailsServiceBeanName = "userDetailsService")
    public void testCreateClient() throws Exception {

        // Given
        UserEntity userEntity = clientService.loadClientByCodeClient("CU-AD-SG-585");
        CreateClientDto createClientDto = ClientFactoryUtils.generateCreateClientDto()
                                                            .setCompany(userEntity.getCompanyEntity().getName());
        String url = "/api/auth/manager/client";

        // When
        MockHttpServletResponse response = mockMvc.perform(
                post(url).accept(MediaType.ALL).content(jsonCreateClientDto.write(createClientDto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        // Then
        Assert.assertEquals(HttpStatus.OK.value(),response.getStatus());

    }

    /**
     * Test create client with user authority
     * @throws Exception
     */
    @Test
    @WithUserDetails(value = "CU-MC-SG-455", userDetailsServiceBeanName = "userDetailsService")
    public void testCreateClientUserAuthority() throws Exception {

        // Given
        UserEntity userEntity = clientService.loadClientByCodeClient("CU-MC-SG-455");
        CreateClientDto createClientDto = ClientFactoryUtils.generateCreateClientDto()
                                            .setCompany(userEntity.getCompanyEntity().getName());
        String url = "/api/auth/manager/client";

        // When
        MockHttpServletResponse response = mockMvc.perform(
                post(url).accept(MediaType.ALL).content(jsonCreateClientDto.write(createClientDto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        // Then
        Assert.assertEquals(HttpStatus.FORBIDDEN.value(),response.getStatus());

    }


    /**
     * Test get client
     * @throws Exception
     */
    @Test
    @WithUserDetails(value = "CU-AB-SG-333", userDetailsServiceBeanName = "userDetailsService")
    public void testGetClient() throws Exception {

        // Given
        String clientCode = "CU-AD-SG-585";
        HttpHeaders params = new HttpHeaders();
        params.add("clientCode", clientCode);
        String url = "/api/auth/client";

        // When
        MockHttpServletResponse response = mockMvc.perform(
                get(url).params(params)
        ).andReturn().getResponse();

        // Then
        ClientDto clientDto = jsonClientDto.parse(response.getContentAsString()).getObject();
        UserEntity userEntity = clientService.loadClientByCodeClient(clientCode);

        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assert.assertEquals(userEntity.getId(), clientDto.getId());
    }

    /**
     * Test get bad client
     * @throws Exception
     */
    @Test
    @WithUserDetails(value = "CU-CG-SG-223", userDetailsServiceBeanName = "userDetailsService")
    public void testGetBadClient() throws Exception {

        // Given
        String clientCode = "badClientCode";
        HttpHeaders params = new HttpHeaders();
        params.add("clientCode", clientCode);
        String url = "/api/auth/client";

        // When
        MockHttpServletResponse response = mockMvc.perform(
                get(url).params(params)
        ).andReturn().getResponse();

        // Then
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    /**
     * Test update client
     * @throws Exception
     */
    @Test
    @WithUserDetails(value = "CU-AD-SG-585", userDetailsServiceBeanName = "userDetailsService")
    public void testUpdateClient() throws Exception{

        // Given
        UserEntity userEntityUpdater = clientService.loadClientByCodeClient("CU-AD-SG-585");
        ClientDto clientDto = ClientFactoryUtils.generateClientDto()
                .setId(5L)
                .setCompany(userEntityUpdater.getCompanyEntity().getName());
        String url = "/api/auth/manager/client";

        // When
        MockHttpServletResponse response = mockMvc.perform(
                patch(url).accept(MediaType.ALL).content(jsonClientDto.write(clientDto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        // Then
        UserEntity userEntity = clientService.loadClientById(5L);

        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assert.assertEquals(userEntity.getId(), clientDto.getId());
        Assert.assertEquals(userEntity.getFirstname(), clientDto.getFirstname());
        Assert.assertEquals(userEntity.getLastname(), clientDto.getLastname());
        Assert.assertEquals(userEntity.getEmail(), clientDto.getEmail());
    }

    /**
     * Test update bad client
     * @throws Exception
     */
    @Test
    @WithUserDetails(value = "CU-AD-SG-585", userDetailsServiceBeanName = "userDetailsService")
    public void testUpdateBadClient() throws Exception{

        // Given
        UserEntity userEntity = clientService.loadClientByCodeClient("CU-AD-SG-585");
        ClientDto clientDto = ClientFactoryUtils.generateClientDto()
                                                .setCompany(userEntity.getCompanyEntity().getName()).setId(-1L);

        // When
        MockHttpServletResponse response = mockMvc.perform(
                patch("/api/auth/manager/client").accept(MediaType.ALL).content(jsonClientDto.write(clientDto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        // Then
        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    /**
     * Test delete client
     * @throws Exception
     */
    @Test
    @WithUserDetails(value = "CU-XA-RN-064", userDetailsServiceBeanName = "userDetailsService")
    public void testDeleteClient() throws Exception {

        // Given
        HttpHeaders params = new HttpHeaders();
        params.add("clientId", "3");

        // When
        MockHttpServletResponse response = mockMvc.perform(
                delete("/api/auth/admin/client").params(params)
        ).andReturn().getResponse();

        // Then
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    /**
     * Test delete bad client
     * @throws Exception
     */
    @Test
    @WithUserDetails(value = "CU-AB-SG-333", userDetailsServiceBeanName = "userDetailsService")
    public void testDeleteBadClient() throws Exception {

        // Given
        HttpHeaders params = new HttpHeaders();
        params.add("clientId", "-1");

        // When
        MockHttpServletResponse response = mockMvc.perform(
                delete("/api/auth/admin/client").params(params)
        ).andReturn().getResponse();

        // Then
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

}