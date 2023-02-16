package com.example.demo.controller;

import com.example.demo.dto.QuoteCreateDTO;
import com.example.demo.dto.QuoteUpdateDTO;
import com.example.demo.exception.AuthException;
import com.example.demo.service.impl.QuoteServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = QuoteController.class)
class QuoteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private QuoteServiceImpl service;

    @BeforeEach
    void setUp() {
    }

    @Test
    void createQuote() throws Exception {
        QuoteCreateDTO quoteCreateDTO =new QuoteCreateDTO("login", "text");

        MvcResult mvcResult = mockMvc.perform(post("/api/v1/quote/create")
                        .content(objectMapper.writeValueAsString(quoteCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        assertNotNull(mvcResult.getResponse().getContentAsString());
    }

    @Test
    void createQuote_UnauthUser() throws Exception {
        QuoteCreateDTO quoteCreateDTO =new QuoteCreateDTO("login", "text");

        when(service.createQuote(quoteCreateDTO)).thenThrow(AuthException.class);

        MvcResult mvcResult = mockMvc.perform(post("/api/v1/quote/create")
                        .content(objectMapper.writeValueAsString(quoteCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    @Test
    void updateQuote() throws Exception {
        QuoteUpdateDTO req=new QuoteUpdateDTO(1, "newText");

        MvcResult mvcResult = mockMvc.perform(post("/api/v1/quote/update")
                        .content(objectMapper.writeValueAsString(req))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertNotNull(mvcResult.getResponse().getContentAsString());
    }
}