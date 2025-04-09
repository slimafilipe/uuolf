package com.uuolf.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class Order {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private User client;

    @ManyToOne
    private User professional;

    private String description;
    private LocalDateTime requestedAt;
    private Status status;
}
