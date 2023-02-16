package com.example.demo.impl;

import com.example.demo.dto.QuoteCreateDTO;
import com.example.demo.exception.AuthException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.impl.QuoteServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class QuoteServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private QuoteServiceImpl quoteService;

    @Test
    void createQuote_unauthorized_throwException() {
        when(userRepository.findByLogin(any())).thenReturn(Optional.empty());

        assertThrows(AuthException.class, () -> quoteService.createQuote(new QuoteCreateDTO("login", "text")));

    }
}