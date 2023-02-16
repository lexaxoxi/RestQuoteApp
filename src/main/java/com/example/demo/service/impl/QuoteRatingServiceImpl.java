package com.example.demo.service.impl;

import com.example.demo.entity.Quote;
import com.example.demo.entity.QuoteRating;
import com.example.demo.exception.EntityAlreadyExistException;
import com.example.demo.repository.QuoteRepository;
import com.example.demo.repository.QuoteRatingRepository;
import com.example.demo.service.QuoteRatingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuoteRatingServiceImpl implements QuoteRatingService {

    private final QuoteRatingRepository quoteRatingRepository;
    private final QuoteRepository quoteRepository;

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void plusRating(Integer quoteId) throws EntityAlreadyExistException {
        Optional<Quote> quote = quoteRepository.findById(quoteId);
        if (quote.isEmpty()) throw new EntityNotFoundException("Attention! Quote id="+quoteId+" not found");

        quote.get().setQuantity(quote.get().getQuantity() + 1);
        quoteRatingRepository.save(new QuoteRating(quote.get()));
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void minusRating(Integer quoteId) throws EntityAlreadyExistException {
        Optional<Quote> quote = quoteRepository.findById(quoteId);
        if (quote.isEmpty()) throw new EntityNotFoundException("Attention!! Quote id="+quoteId+" is not found");

        quote.get().setQuantity(quote.get().getQuantity() - 1);
        quoteRatingRepository.save(new QuoteRating(quote.get()));
    }

    @Override
    @Transactional(readOnly = true)
    public Map<Instant, Integer> getQuantityHistory(Integer quoteId) throws EntityAlreadyExistException {
        Optional<Quote> quote = quoteRepository.findById(quoteId);
        if (quote.isEmpty()) throw new EntityNotFoundException("Attention!!! Quote id="+quoteId+" is not found");

        return quote.get().getRating()
                .stream()
                .collect(Collectors.toMap(
                        QuoteRating::getCreated, QuoteRating::getQuantityAfterRating,
                        (key1, key2) -> key1,
                        TreeMap::new
                ));
    }
}
