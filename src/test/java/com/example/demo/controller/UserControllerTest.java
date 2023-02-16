package com.example.demo.controller;

import com.example.demo.dto.UserRequestDTO;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserService service;

    private UserRequestDTO userRequestDTO;

    @BeforeEach
    void setUp() {
    }

    @Test
    void createAccount() throws Exception {
        userRequestDTO=new UserRequestDTO("login", "mail@mail.ru","password");

        MvcResult mvcResult = mockMvc.perform(post("/api/v1/account/create")
                        .content(objectMapper.writeValueAsString(userRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }

    @ParameterizedTest
    @NullAndEmptySource
    void createAccount_InvalidLogin_then400(String login) throws Exception {
        userRequestDTO=new UserRequestDTO(login, "mail@mail.ru", "password");

        MvcResult mvcResult = mockMvc.perform(post("/api/v1/account/create")
                        .content(objectMapper.writeValueAsString(userRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
    }
    @ParameterizedTest
    @NullAndEmptySource
    void createAccount_InvalidPassword_then400(String pass) throws Exception {
        userRequestDTO=new UserRequestDTO("login","mail@mail.ru",  pass);

        MvcResult mvcResult = mockMvc.perform(post("/api/v1/account/create")
                        .content(objectMapper.writeValueAsString(userRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
    }

}