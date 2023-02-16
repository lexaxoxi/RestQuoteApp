package com.example.demo.repository;


import com.example.demo.entity.Quote;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuoteRepository extends JpaRepository<Quote, Integer> {
    @Override
    @EntityGraph(value = "Quote.rating", type = EntityGraph.EntityGraphType.FETCH)
    Optional<Quote> findById(Integer integer);
}
