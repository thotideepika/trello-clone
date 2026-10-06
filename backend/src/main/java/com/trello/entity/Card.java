package com.trello.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cards")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Card {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private Integer position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "list_id", nullable = false)
    private BoardList list;
}