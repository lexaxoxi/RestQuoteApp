package com.example.demo.service;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.util.Map;

public interface QuoteRatingService {

    void plusRating(Integer quoteId) throws EntityNotFoundException;

    void minusRating(Integer quoteId) throws EntityNotFoundException;

    Map<Instant, Integer> getQuantityHistory(Integer quoteId) throws EntityNotFoundException;
}
