package com.uuolf.repository;

import com.uuolf.entity.ServiceRequest;
import com.uuolf.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {
    List<ServiceRequest> findByClient(User client);
    List<ServiceRequest> findByProfessional(User professional);
}
