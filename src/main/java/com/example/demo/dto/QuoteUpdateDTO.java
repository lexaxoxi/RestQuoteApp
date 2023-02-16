package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record QuoteUpdateDTO(@JsonProperty("id")
                             @NotNull(message = "Attention!!!!! Your message id is empty")
                             Integer quoteId,
                             @JsonProperty("text")
                             @NotBlank(message = "Attention!!!!! Your updated text is empty")
                             String newText) {
}
