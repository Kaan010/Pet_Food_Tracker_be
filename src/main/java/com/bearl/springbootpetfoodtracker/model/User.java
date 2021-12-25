package com.bearl.springbootpetfoodtracker.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="users")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false, length = 100)
    private String username;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name="name", nullable = false, length = 100)
    private String name;

    @Column(name="phonenumber", nullable = false, length = 100)
    private String phoneNumber;

     @Column(name="email", nullable = false, length = 100)
    private String email;

     @Column(name="currentfoodgram", nullable = false)
    private Double currentFoodGram;

    @Column(name="create_time", nullable = false)
    private LocalDateTime createTime;

}
