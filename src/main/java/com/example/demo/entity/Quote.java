package com.example.demo.entity;

import com.example.demo.dto.QuoteCreateDTO;
import com.example.demo.dto.QuoteResponseDTO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "quotes")
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
@NamedEntityGraph(name = "Quote.rating", attributeNodes = @NamedAttributeNode("rating"))
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "text")
    private String text;

    @Column(name = "quantity")
    private int quantity;

    @CreationTimestamp
    @Column(name = "createTime")
    private Instant createTime;

    @UpdateTimestamp
    @Column(name = "updateTime")
    private Instant updateTime;

    @JoinColumn(name = "postedBy", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User postedBy;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "quote", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<QuoteRating> rating;


    public QuoteResponseDTO toDTO() {
        return new QuoteResponseDTO(id, postedBy.getLogin(), text,createTime,updateTime, quantity);
    }

    public static Quote fromDTO(QuoteCreateDTO dto){
         return Quote.builder()
                 .text(dto.text())
                 .postedBy(new User(dto.userLogin()))
                 .build();
    }
}
