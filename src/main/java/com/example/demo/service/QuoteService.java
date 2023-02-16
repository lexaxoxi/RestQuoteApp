package com.example.demo.service;

import com.example.demo.dto.QuoteCreateDTO;
import com.example.demo.dto.QuoteResponseDTO;
import com.example.demo.dto.QuoteUpdateDTO;
import com.example.demo.exception.AuthException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuoteService {

    QuoteResponseDTO getRandomQuote();

    QuoteResponseDTO createQuote(QuoteCreateDTO quoteCreateDTO) throws AuthException;

    QuoteResponseDTO updateQuote(QuoteUpdateDTO quoteUpdateDTO);

    void removeQuote(Integer quoteId);

    List<QuoteResponseDTO> getQuotes(Pageable pageable);

    List<QuoteResponseDTO> getQuotes();
}
