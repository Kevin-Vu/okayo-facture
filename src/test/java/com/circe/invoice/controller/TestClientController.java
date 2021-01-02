package com.circe.invoice.controller;

import com.circe.invoice.configuration.BaseTest;

//@RunWith(SpringRunner.class)
//@TestPropertySource(locations = {"classpath:test.properties"})
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
public class TestClientController extends BaseTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private UserService userService;
//
//    private JacksonTester<CreateClientDto> jsonCreateClientDto;
//    private JacksonTester<ClientDto> jsonClientDto;
//
//    @Before
//    public void before() {
//
//        JacksonTester.initFields(this, new ObjectMapper());
//        MockitoAnnotations.initMocks(this);
//    }
//
//
//    /**
//     * Test create client
//     * @throws Exception
//     */
//    @Test
//    @WithUserDetails(value = "CU-AD-SG-585", userDetailsServiceBeanName = "userDetailsService")
//    public void testCreateClient() throws Exception {
//
//        // Given
//        UserEntity userEntity = userService.loadClientByCodeClient("CU-AD-SG-585");
//        CreateClientDto createClientDto = ClientFactoryUtils.generateCreateClientDto()
//                                                            .setCompany(userEntity.getCompanyEntity().getName());
//        String url = "/api/auth/manager/client";
//
//        // When
//        MockHttpServletResponse response = mockMvc.perform(
//                post(url).accept(MediaType.ALL).content(jsonCreateClientDto.write(createClientDto).getJson())
//                        .contentType(MediaType.APPLICATION_JSON)
//        ).andReturn().getResponse();
//
//        // Then
//        Assert.assertEquals(HttpStatus.OK.value(),response.getStatus());
//
//    }
//
//    /**
//     * Test create client with user authority
//     * @throws Exception
//     */
//    @Test
//    @WithUserDetails(value = "CU-MC-SG-455", userDetailsServiceBeanName = "userDetailsService")
//    public void testCreateClientUserAuthority() throws Exception {
//
//        // Given
//        UserEntity userEntity = userService.loadClientByCodeClient("CU-MC-SG-455");
//        CreateClientDto createClientDto = ClientFactoryUtils.generateCreateClientDto()
//                                            .setCompany(userEntity.getCompanyEntity().getName());
//        String url = "/api/auth/manager/client";
//
//        // When
//        MockHttpServletResponse response = mockMvc.perform(
//                post(url).accept(MediaType.ALL).content(jsonCreateClientDto.write(createClientDto).getJson())
//                        .contentType(MediaType.APPLICATION_JSON)
//        ).andReturn().getResponse();
//
//        // Then
//        Assert.assertEquals(HttpStatus.FORBIDDEN.value(),response.getStatus());
//
//    }
//
//
//    /**
//     * Test get client
//     * @throws Exception
//     */
//    @Test
//    @WithUserDetails(value = "CU-AB-SG-333", userDetailsServiceBeanName = "userDetailsService")
//    public void testGetClient() throws Exception {
//
//        // Given
//        String clientCode = "CU-AD-SG-585";
//        HttpHeaders params = new HttpHeaders();
//        params.add("clientCode", clientCode);
//        String url = "/api/auth/client";
//
//        // When
//        MockHttpServletResponse response = mockMvc.perform(
//                get(url).params(params)
//        ).andReturn().getResponse();
//
//        // Then
//        ClientDto clientDto = jsonClientDto.parse(response.getContentAsString()).getObject();
//        UserEntity userEntity = userService.loadClientByCodeClient(clientCode);
//
//        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
//        Assert.assertEquals(userEntity.getId(), clientDto.getId());
//    }
//
//    /**
//     * Test get bad client
//     * @throws Exception
//     */
//    @Test
//    @WithUserDetails(value = "CU-CG-SG-223", userDetailsServiceBeanName = "userDetailsService")
//    public void testGetBadClient() throws Exception {
//
//        // Given
//        String clientCode = "badClientCode";
//        HttpHeaders params = new HttpHeaders();
//        params.add("clientCode", clientCode);
//        String url = "/api/auth/client";
//
//        // When
//        MockHttpServletResponse response = mockMvc.perform(
//                get(url).params(params)
//        ).andReturn().getResponse();
//
//        // Then
//        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
//    }
//
//    /**
//     * Test update client
//     * @throws Exception
//     */
//    @Test
//    @WithUserDetails(value = "CU-AD-SG-585", userDetailsServiceBeanName = "userDetailsService")
//    public void testUpdateClient() throws Exception{
//
//        // Given
//        UserEntity userEntityUpdater = userService.loadClientByCodeClient("CU-AD-SG-585");
//        ClientDto clientDto = ClientFactoryUtils.generateClientDto()
//                .setId(5L)
//                .setCompany(userEntityUpdater.getCompanyEntity().getName());
//        String url = "/api/auth/manager/client";
//
//        // When
//        MockHttpServletResponse response = mockMvc.perform(
//                patch(url).accept(MediaType.ALL).content(jsonClientDto.write(clientDto).getJson())
//                        .contentType(MediaType.APPLICATION_JSON)
//        ).andReturn().getResponse();
//
//        // Then
//        UserEntity userEntity = userService.loadClientById(5L);
//
//        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
//        Assert.assertEquals(userEntity.getId(), clientDto.getId());
//        Assert.assertEquals(userEntity.getFirstname(), clientDto.getFirstname());
//        Assert.assertEquals(userEntity.getLastname(), clientDto.getLastname());
//        Assert.assertEquals(userEntity.getEmail(), clientDto.getEmail());
//    }
//
//    /**
//     * Test update bad client
//     * @throws Exception
//     */
//    @Test
//    @WithUserDetails(value = "CU-AD-SG-585", userDetailsServiceBeanName = "userDetailsService")
//    public void testUpdateBadClient() throws Exception{
//
//        // Given
//        UserEntity userEntity = userService.loadClientByCodeClient("CU-AD-SG-585");
//        ClientDto clientDto = ClientFactoryUtils.generateClientDto()
//                                                .setCompany(userEntity.getCompanyEntity().getName()).setId(-1L);
//
//        // When
//        MockHttpServletResponse response = mockMvc.perform(
//                patch("/api/auth/manager/client").accept(MediaType.ALL).content(jsonClientDto.write(clientDto).getJson())
//                        .contentType(MediaType.APPLICATION_JSON)
//        ).andReturn().getResponse();
//
//        // Then
//        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
//    }
//
//    /**
//     * Test delete client
//     * @throws Exception
//     */
//    @Test
//    @WithUserDetails(value = "CU-XA-RN-064", userDetailsServiceBeanName = "userDetailsService")
//    public void testDeleteClient() throws Exception {
//
//        // Given
//        HttpHeaders params = new HttpHeaders();
//        params.add("clientId", "3");
//
//        // When
//        MockHttpServletResponse response = mockMvc.perform(
//                delete("/api/auth/admin/client").params(params)
//        ).andReturn().getResponse();
//
//        // Then
//        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
//    }
//
//    /**
//     * Test delete bad client
//     * @throws Exception
//     */
//    @Test
//    @WithUserDetails(value = "CU-AB-SG-333", userDetailsServiceBeanName = "userDetailsService")
//    public void testDeleteBadClient() throws Exception {
//
//        // Given
//        HttpHeaders params = new HttpHeaders();
//        params.add("clientId", "-1");
//
//        // When
//        MockHttpServletResponse response = mockMvc.perform(
//                delete("/api/auth/admin/client").params(params)
//        ).andReturn().getResponse();
//
//        // Then
//        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
//    }

}
