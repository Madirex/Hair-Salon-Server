package com.madirex.hairsalonserver.controllers.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.madirex.hairsalonserver.dto.login.LoginDTO;
import com.madirex.hairsalonserver.model.Login;
import com.madirex.hairsalonserver.model.User;
import com.madirex.hairsalonserver.model.UserGender;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginRestControllerIntegrationMVCTest {
    private final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    MockMvc mockMvc;
    private final User user = User.builder()
            .id("76b2071a-3f97-4666-bd76-3d3d38ca677d")
            .username("jbatista49")
            .name("Juan")
            .surname("Batista")
            .phoneNumber("678429049")
            .gender(UserGender.Male)
            .email("aocrigane0@slideshare.net")
            .password("t6YsKgG")
            .logins(null)
            .build();

    Login login = Login.builder()
            .id("233149e4-b6f3-4692-ac71-2e8123bc24b2")
            .user(user)
            .instant(null)
            .token("123213412")
            .build();
    @Autowired
    private JacksonTester<LoginDTO> jsonCreateDTO;
    @Autowired
    private JacksonTester<LoginDTO> jsonLoginDTO;

    @Test
    @Order(1)
    void findAllTest() throws Exception {

        MockHttpServletResponse response = mockMvc.perform(
                        get("/rest/logins/")
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOi" +
                                        "JjMTMzNGQ1Ny0xMjBiLTQzN2ItYmFlZi1jZjViNWY2OGNjM2UiLCJpYXQiOjE2NDY1NzY2MzgsImV4cCI6MT" +
                                        "Y0NjY2MzAzOCwibmFtZSI6IkFkbWluIiwicm9sZXMiOiJVU0VSLCBBRE1JTiJ9.HIY1f7O_OWY9SDfnJHkgN" +
                                        "GvqxqbWuJutdF_cnA7ulkwYz-LrDpAFrsFd9MFSQCL7Ms87cqALVqXsV4z0cphRYg"))
                .andReturn().getResponse();

        ObjectMapper mapper = new ObjectMapper();
        List<LoginDTO> myObjects = Arrays.asList(mapper.readValue(response.getContentAsString(), LoginDTO[].class));

        assertAll(
                () -> assertEquals(response.getStatus(), HttpStatus.OK.value()),
                () -> assertEquals(myObjects.get(0).getId(), login.getId()),
                () -> assertEquals(myObjects.get(0).getUser().getUsername(), login.getUser().getUsername()),
                () -> assertEquals(myObjects.get(0).getInstance(), login.getInstant()),
                () -> assertEquals(myObjects.get(0).getToken(), login.getToken())
        );
    }

    @Test
    @Order(2)
    void findByIdTest() throws Exception {

        var response = mockMvc.perform(
                        get("/rest/logins/" + login.getId())
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOi" +
                                        "JjMTMzNGQ1Ny0xMjBiLTQzN2ItYmFlZi1jZjViNWY2OGNjM2UiLCJpYXQiOjE2NDY1NzY2MzgsImV4cCI6MT" +
                                        "Y0NjY2MzAzOCwibmFtZSI6IkFkbWluIiwicm9sZXMiOiJVU0VSLCBBRE1JTiJ9.HIY1f7O_OWY9SDfnJHkgN" +
                                        "GvqxqbWuJutdF_cnA7ulkwYz-LrDpAFrsFd9MFSQCL7Ms87cqALVqXsV4z0cphRYg"))
                .andReturn().getResponse();

        var res = jsonLoginDTO.parseObject(response.getContentAsString());

        assertAll(
                () -> assertEquals(HttpStatus.OK.value(), response.getStatus()),
                () -> assertEquals(res.getId(), login.getId()),
                () -> assertEquals(res.getUser().getUsername(), login.getUser().getUsername()),
                () -> assertEquals(res.getInstance(), login.getInstant()),
                () -> assertEquals(res.getToken(), login.getToken())
        );
    }

    @Test
    @Order(3)
    void deleteTest() throws Exception {

        var response = mockMvc.perform(MockMvcRequestBuilders.delete("/rest/logins/" + login.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOi" +
                        "JjMTMzNGQ1Ny0xMjBiLTQzN2ItYmFlZi1jZjViNWY2OGNjM2UiLCJpYXQiOjE2NDY1NzY2MzgsImV4cCI6MT" +
                        "Y0NjY2MzAzOCwibmFtZSI6IkFkbWluIiwicm9sZXMiOiJVU0VSLCBBRE1JTiJ9.HIY1f7O_OWY9SDfnJHkgN" +
                        "GvqxqbWuJutdF_cnA7ulkwYz-LrDpAFrsFd9MFSQCL7Ms87cqALVqXsV4z0cphRYg")).andReturn().getResponse();

        var res = jsonLoginDTO.parseObject(response.getContentAsString());

        assertAll(
                () -> assertEquals(HttpStatus.OK.value(), response.getStatus()),

                () -> assertEquals(HttpStatus.OK.value(), response.getStatus()),
                () -> assertEquals(res.getUser().getUsername(), login.getUser().getUsername()),
                () -> assertEquals(res.getInstance(), login.getInstant()),
                () -> assertEquals(res.getToken(), login.getToken())
        );
    }
}
