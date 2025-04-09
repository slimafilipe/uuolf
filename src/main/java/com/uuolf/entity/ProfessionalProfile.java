package com.uuolf.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data

public class ProfessionalProfile {

    @Id @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String bio;

    @ManyToMany
    @JoinTable(name = "professional_specialties",
    joinColumns = @JoinColumn(name = "profile_id"),
    inverseJoinColumns = @JoinColumn(name = "specialties_id"))
    private List<Specialty> specialties;

    private Double latitude;
    private Double longetude;
}
