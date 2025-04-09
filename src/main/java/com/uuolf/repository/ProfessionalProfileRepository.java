package com.uuolf.repository;

import com.uuolf.entity.ProfessionalProfile;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfessionalProfileRepository extends JpaRepository<ProfessionalProfile, Long> {

    @EntityGraph(attributePaths = "specialties")
    List<ProfessionalProfile> findAll();
}
