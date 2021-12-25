package com.bearl.springbootpetfoodtracker.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="name",nullable = false,length = 100)
    private String name;

    @Column(name="age",nullable = false)
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(name="gender",nullable = false)
    private Gender gender;

    @Column(name="weight",nullable = false)
    private Double weight;

    @Column(name="is_sterilised", nullable = false)
    private Boolean isSterilised;

    @Enumerated(EnumType.STRING)
    @Column(name="animal_type",nullable = false)
    private AnimalType animalType;

    @Column(name="daily_food_gram",nullable = false)
    private Double dailyFoodGram;

    @Column(name="create_time", nullable = false)
    private LocalDateTime createTime;

    @Column(name="user_id",nullable = false)
    private Long userId;

}
