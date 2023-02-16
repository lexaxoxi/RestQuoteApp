package com.example.demo.impl;

import com.example.demo.dto.UserRequestDTO;
import com.example.demo.entity.User;
import com.example.demo.exception.EntityAlreadyExistException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder bCryptPasswordEncoder;
    @InjectMocks
    private UserServiceImpl userService;
    private UserRequestDTO userRequestDTO;

    @BeforeEach
    void setUp() {
        when(bCryptPasswordEncoder.encode(anyString())).thenReturn("password");
        userRequestDTO = new UserRequestDTO("login", "mail@mail.ru", "password");
    }

    @Test
    void create() {
        when(userRepository.findByLoginOrEmail(userRequestDTO.login(), userRequestDTO.email())).thenReturn(Optional.empty());

        userService.create(userRequestDTO);
    }

    @Test
    void create_accountExist_thenThrowException() {
        when(userRepository.findByLoginOrEmail(userRequestDTO.login(), userRequestDTO.email())).thenReturn(Optional.of(
                User.builder()
                        .login(userRequestDTO.login())
                        .password(userRequestDTO.password())
                        .build()));


        assertThrows(EntityAlreadyExistException.class, () -> userService.create(userRequestDTO));
    }
}