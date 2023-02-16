package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record UserRequestDTO(
        @NotBlank(message = "Attention!!!!! Your Login is empty")
        @JsonProperty("login")
        String login,
        @Email(message = "Attention!!!!! You have invalid email")
        @NotNull(message = "Attention!!!!! Your email is empty")
        @JsonProperty("email")
        String email,
        @NotBlank(message = "Attention!!!!! Your Password is empty")
        @JsonProperty("password")
        String password) {
    @Override
    public String toString() {
        return "UserRequestDTO{" +
                "login='" + login + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
