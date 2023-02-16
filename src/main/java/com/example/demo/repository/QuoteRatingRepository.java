package com.example.demo.repository;

import com.example.demo.entity.QuoteRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuoteRatingRepository extends JpaRepository<QuoteRating, Integer> {

    List<QuoteRating> findAllByQuoteId(Integer quoteId);
}
