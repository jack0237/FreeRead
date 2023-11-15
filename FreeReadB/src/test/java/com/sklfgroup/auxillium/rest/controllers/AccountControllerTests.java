package com.sklfgroup.auxillium.rest.controllers;

import com.sklfgroup.auxillium.AccountFixtures;
import com.sklfgroup.auxillium.services.impl.AccountServiceImpl;
import com.sklfgroup.auxillium.dao.entities.account.AccountEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
@ActiveProfiles("test")
@Slf4j
public class AccountControllerTests {
    private static final String URL_TEMPLATE = "/lightenergy/accounts";
    @InjectMocks
    private AccountController accountController;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AccountServiceImpl accountService;

    @Test
    void should_throw_exception_when_blank_firstname() throws Exception {
        String requestJson = "{\"firstname\":\"\",\"lastname\":\"edima\",\"email\":\"samantha.edima@yopmail.com\"}";
        when(accountService.save(any(AccountEntity.class))).thenAnswer(i -> i.getArguments()[0]);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URL_TEMPLATE)
                .content(requestJson)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getContentAsString()).contains(HttpStatus.BAD_REQUEST.name()).contains("firstname is required");
    }

    @Test
    void should_throw_exception_when_blank_lastname() throws Exception {
        String requestJson = "{\"firstname\":\"samantha\",\"lastname\":\"\",\"email\":\"samantha.edima@yopmail.com\"}";
        when(accountService.save(any(AccountEntity.class))).thenAnswer(i -> i.getArguments()[0]);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URL_TEMPLATE)
                .content(requestJson)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getContentAsString()).contains(HttpStatus.BAD_REQUEST.name()).contains("lastname is required");
    }

    @Test
    void should_throw_exception_when_blank_email() throws Exception {
        String requestJson = "{\"firstname\":\"samantha\",\"lastname\":\"edima\",\"email\":\"\"}";
        when(accountService.save(any(AccountEntity.class))).thenAnswer(i -> i.getArguments()[0]);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URL_TEMPLATE)
                .content(requestJson)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getContentAsString()).contains(HttpStatus.BAD_REQUEST.name()).contains("email is required");
    }

    @Test
    void should_throw_exception_when_invalid_email() throws Exception {
        String requestJson = "{\"firstname\":\"samantha\",\"lastname\":\"edima\",\"email\":\"samantha.edima.yopmail.com\"}";
        when(accountService.save(any(AccountEntity.class))).thenAnswer(i -> i.getArguments()[0]);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URL_TEMPLATE)
                .content(requestJson)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getContentAsString()).contains(HttpStatus.BAD_REQUEST.name()).contains("email must be a well-formed email address");
    }

    @Test
    void should_create_account_when_valid_input() throws Exception {
        String requestJson = "{\"firstname\":\"samantha\",\"lastname\":\"edima\",\"email\":\"samantha.edima@yopmail.com\"}";
        log.info(requestJson + "#######################################");
        when(accountService.save(any(AccountEntity.class))).thenReturn(AccountFixtures.generateCreateAccountDataResponse());
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URL_TEMPLATE)
                .content(requestJson)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString())
                .isNotNull()
                .contains("EDIMA")
                .contains("Samantha")
                .contains("samantha.edima@yopmail.com")
                .contains("uuid");
    }

    @Test
    void should_throw_exception_when_blank_connexion_email() throws Exception {
        String requestJson = "{\"email\":\"\",\"password\":\"azertyuiop\"}";
        log.info(requestJson + "#######################################");

        when(accountService.authentificateAccount(any(AccountEntity.class))).thenAnswer(i -> i.getArguments()[0]);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URL_TEMPLATE+"/login")
                .content(requestJson)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        log.info(requestJson + "#######################################");

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getContentAsString()).contains(HttpStatus.BAD_REQUEST.name()).contains("email is required");
    }

    @Test
    void should_throw_exception_when_blank_connexion_password() throws Exception {
        String requestJson = "{\"email\":\"landrysop007@gmail.com\",\"password\":\"\"}";
        log.info(requestJson + "#######################################");
       // when(accountService.authentificateAccount(any(AccountEntity.class))).thenAnswer(i -> i.getArguments()[0]);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URL_TEMPLATE + "/login")
                .content(requestJson)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();


        MockHttpServletResponse response = result.getResponse();

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getContentAsString()).contains(HttpStatus.BAD_REQUEST.name()).contains("password is required");
    }

    @Test
    void should_connect_account_when_valid_input() throws Exception {
        String requestJson = "{\"email\":\"landrysop007@gmail.com\",\"password\":\"password\"}";
        when(accountService.authentificateAccount(any(AccountEntity.class))).thenReturn(AccountFixtures.generateConnexionAccountResponse());

        // when(accountService.authentificateAccount(any(AccountEntity.class))).thenAnswer(i -> i.getArguments()[0]);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URL_TEMPLATE + "/login")
                .content(requestJson)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .isNotNull()
                .contains("EDIMA")
                .contains("Samantha")
                .contains("samantha.edima@yopmail.com")
                .contains("uuid");
    }
}
