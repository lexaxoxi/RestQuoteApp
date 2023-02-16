package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record QuoteResponseDTO(
        @JsonProperty("id")
        Integer id,
        @JsonProperty("user")
        String userLogin,
        @JsonProperty("text")
        String text,
        @JsonProperty("createTime")
        Instant createTime,
        @JsonProperty("updateTime")
        Instant updateTime,
        @JsonProperty("quantity")
        Integer quantity) {
}