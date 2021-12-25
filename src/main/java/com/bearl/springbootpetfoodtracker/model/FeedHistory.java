package com.bearl.springbootpetfoodtracker.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "feed_history")
public class FeedHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "pet_id", nullable = false)
    private Long petId;

    @Column(name = "food_gram", nullable = false)
    private Double foodGram;

    @Column(name="feed_time", nullable = false)
    private LocalDateTime feedTime;
}
