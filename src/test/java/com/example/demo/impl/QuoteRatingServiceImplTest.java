package com.example.demo.impl;

import com.example.demo.entity.Quote;
import com.example.demo.entity.QuoteRating;
import com.example.demo.entity.User;
import com.example.demo.repository.QuoteRepository;
import com.example.demo.repository.QuoteRatingRepository;
import com.example.demo.service.impl.QuoteRatingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuoteRatingServiceImplTest {

    @Mock
    private QuoteRatingRepository quoteRatingRepository;
    @Mock
    private QuoteRepository quoteRepository;
    @InjectMocks
    private QuoteRatingServiceImpl quoteRatingService;

    private Quote quote;
    private QuoteRating quoteRating;

    @BeforeEach
    void setUp() {
        quoteRating =new QuoteRating();
        quote = Quote.builder()
                .id(1)
                .text("Text")
                .postedBy(new User("login"))
                .rating(List.of(quoteRating))
                .build();
    }

    @Test
    void upRating() {
        when(quoteRepository.findById(quote.getId())).thenReturn(Optional.of(quote));

        quoteRatingService.plusRating(quote.getId());

        assertEquals(1, quote.getQuantity());
    }

    @Test
    void downRating() {
        when(quoteRepository.findById(quote.getId())).thenReturn(Optional.of(quote));

        quoteRatingService.minusRating(quote.getId());

        assertEquals(-1, quote.getQuantity());
    }

}