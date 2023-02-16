package com.example.demo.service;

import com.example.demo.dto.UserRequestDTO;
import com.example.demo.exception.EntityAlreadyExistException;

public interface UserService {

    void create(UserRequestDTO userDTO) throws EntityAlreadyExistException;


}
