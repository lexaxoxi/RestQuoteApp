package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public record QuoteCreateDTO(
        @JsonProperty("user")
        @NotBlank(message = "Attention!!!!! Your User is empty")
        String userLogin,
        @JsonProperty("text")
        @NotBlank(message = "Attention! Your Quote is empty")
        String text) {
}
