package com.example.demo.controller;


import com.example.demo.dto.QuoteCreateDTO;
import com.example.demo.dto.QuoteResponseDTO;
import com.example.demo.dto.QuoteUpdateDTO;
import com.example.demo.service.QuoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/v1/quote", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
@Api(value = "CRUD for quotes")
public class QuoteController {
    public static final byte QUOTES_PER_PAGE = 10;
    private final QuoteService service;


    @GetMapping(value = "get")
    @ApiOperation(value = "Get paged quotes")
    public List<QuoteResponseDTO> getPagedQuotes(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "TOP") Order order){
        Pageable pageable = switch (order) {
            case TOP -> PageRequest.of(page, QUOTES_PER_PAGE, Sort.by("quantity").descending());
            case FLOP -> PageRequest.of(page, QUOTES_PER_PAGE, Sort.by("quantity").ascending());
        };

        return service.getQuotes(pageable);
    }

    @PostMapping(value = "create")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create quote")
    public QuoteResponseDTO createQuote(
            @Valid
            @RequestBody
                    QuoteCreateDTO quoteCreateDTO) {
        log.info("Create quote request:{}", quoteCreateDTO.toString());
        return service.createQuote(quoteCreateDTO);
    }

    @PostMapping(value = "update")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update quote text")
    public QuoteResponseDTO updateQuote(
            @Valid @RequestBody QuoteUpdateDTO quoteUpdateDTO) {
        log.info("Update quote request:{}", quoteUpdateDTO.toString());

        return service.updateQuote(quoteUpdateDTO);
    }

    @GetMapping(value = "{quoteId}/remove", consumes = MediaType.ALL_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Remove quote")
    public void removeQuote(
            @PathVariable Integer quoteId) {
        log.info("Remove quote request: id={}", quoteId.toString());

        service.removeQuote(quoteId);
    }

    @GetMapping(value = "random", consumes = MediaType.ALL_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get random quote")
    public QuoteResponseDTO getRandomQuote() {
        return service.getRandomQuote();
    }

    enum Order {
        TOP,
        FLOP
    }
}
