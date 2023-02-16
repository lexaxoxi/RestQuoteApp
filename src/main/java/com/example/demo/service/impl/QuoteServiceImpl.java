package com.example.demo.service.impl;

import com.example.demo.dto.QuoteCreateDTO;
import com.example.demo.dto.QuoteResponseDTO;
import com.example.demo.dto.QuoteUpdateDTO;
import com.example.demo.entity.Quote;
import com.example.demo.entity.User;
import com.example.demo.exception.AuthException;
import com.example.demo.repository.QuoteRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.QuoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {
    private final QuoteRepository repository;
    private final UserRepository userRepository;

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<QuoteResponseDTO> getQuotes(Pageable pageable) {
        return repository.findAll(pageable)
                .stream()
                .map(Quote::toDTO)
                .toList();
    }

    @Override
    public List<QuoteResponseDTO> getQuotes() {
        return repository.findAll()
                .stream()
                .map(Quote::toDTO)
                .toList();
    }
    @Override
    @Transactional(readOnly = true)
    public QuoteResponseDTO getRandomQuote() {
        List<Quote> quotes = repository.findAll();

        return quotes.get(new Random().nextInt(quotes.size())).toDTO();
    }
    @Override
    @Transactional
    public QuoteResponseDTO createQuote(QuoteCreateDTO quoteCreateDTO) throws com.example.demo.exception.AuthException {
        Optional<User> user = userRepository.findByLogin(quoteCreateDTO.userLogin());

        if (user.isEmpty()) throw new AuthException("User not authorized");
        Quote quote = Quote.fromDTO(quoteCreateDTO);
        quote.setPostedBy(user.get());
        return repository.save(quote).toDTO();
    }

    @Override
    @Transactional
    public QuoteResponseDTO updateQuote(QuoteUpdateDTO quoteUpdateDTO) {
        Quote quote = entityManager.find(Quote.class, quoteUpdateDTO.quoteId());
        if (quote == null) throw new EntityNotFoundException("Quote not found");

        quote.setText(quoteUpdateDTO.newText());
        quote.setUpdateTime(Instant.now());

        entityManager.merge(quote);
        entityManager.flush();
        return quote.toDTO();
    }

    @Override
    @Transactional
    public void removeQuote(Integer quoteId) {
        Quote quote = entityManager.find(Quote.class, quoteId);
        if (quote == null) throw new EntityNotFoundException("Quote not found");

        entityManager.remove(quote);
        entityManager.flush();
    }

}
