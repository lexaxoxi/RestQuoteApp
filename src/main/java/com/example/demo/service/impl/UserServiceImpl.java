package com.example.demo.service.impl;

import com.example.demo.dto.UserRequestDTO;
import com.example.demo.entity.User;
import com.example.demo.exception.EntityAlreadyExistException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void create(UserRequestDTO userDTO) throws EntityAlreadyExistException {
        User user = User.builder()
                .login(userDTO.login())
                .password(encoder.encode(userDTO.password()))
                .email(userDTO.email())
                .build();

        if (userRepository.findByLoginOrEmail(user.getLogin(), user.getEmail()).isPresent())
            throw new EntityAlreadyExistException(String.format("User with login=%s or email=%s already exist",userDTO.login(), user.getEmail()));

        userRepository.save(user);
    }
}
