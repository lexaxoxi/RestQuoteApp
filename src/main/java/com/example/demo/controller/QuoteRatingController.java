package com.example.demo.controller;


import com.example.demo.service.QuoteRatingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping(value = "api/v1/quote", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
@Api(value = "Quote rating")
public class QuoteRatingController {
    private final QuoteRatingService service;

    @PostMapping(value = "{quoteId}/plus", consumes = MediaType.ALL_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Plus rating quote")
    public void plusRating(@PathVariable int quoteId) {
        service.plusRating(quoteId);
    }

    @PostMapping(value = "{quoteId}/minus", consumes = MediaType.ALL_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Minus rating quote")
    public void minusRating(@PathVariable int quoteId) {
        service.minusRating(quoteId);
    }

    @GetMapping(value = "{quoteId}/history", consumes = MediaType.ALL_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get history of quote ")
    public Map<Instant, Integer> getQuantityHistory(@PathVariable int quoteId) {
        return service.getQuantityHistory(quoteId);
    }

}
