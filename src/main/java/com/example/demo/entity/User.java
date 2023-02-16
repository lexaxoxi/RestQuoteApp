package com.example.demo.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "users", indexes = {@Index(name = "id_login", columnList = "login", unique = true),
        @Index(name = "id_email", columnList = "email", unique = true)})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraph(name = "User.quotes", attributeNodes = @NamedAttributeNode("quoteList"))
public class User {
    public User(String login) {
        this.login = login;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email")
    private String email;
    @Column(name = "login")
    private String login;

    @CreationTimestamp
    @Column(name = "createdTime")
    private Instant createdTime;

    @Column(name = "password")
    @ToString.Exclude
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "postedBy", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Quote> quoteList;
}
