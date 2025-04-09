package com.uuolf.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Usuário que fez o pedido
    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    // Usuário profissional
    @ManyToOne
    @JoinColumn(name = "professional_id")
    private User professional;

    private String description;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    private LocalDateTime createdAt;


}
