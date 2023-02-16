package com.example.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;



@Entity
@Table(name = "quote_rating")
@Data
@NoArgsConstructor
public class QuoteRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    @Column(name = "ratingTime")
    private Instant created;

    @Column(name = "new_quantity")
    private int quantityAfterRating;
    @JoinColumn(name = "quote_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Quote quote;

    public QuoteRating(Quote quote) {
        this.quote = quote;
        quantityAfterRating=quote.getQuantity();
    }
}
