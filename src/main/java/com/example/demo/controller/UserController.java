package com.example.demo.controller;


import com.example.demo.dto.UserRequestDTO;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/v1/account", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
@Api("Create new User")
public class UserController {
    private final UserService userService;

    @PostMapping(value = "create")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Create new account")
    public void createAccount(
            @Valid @RequestBody UserRequestDTO userRequestDTO) {
        log.info("Create account request:{}", userRequestDTO.toString());

        userService.create(userRequestDTO);
    }
}
