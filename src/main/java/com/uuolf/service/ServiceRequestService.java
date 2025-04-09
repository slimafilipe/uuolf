package com.uuolf.service;

import com.uuolf.dto.ServiceRequestDto;
import com.uuolf.entity.RequestStatus;
import com.uuolf.entity.ServiceRequest;
import com.uuolf.entity.User;
import com.uuolf.repository.ServiceRequestRepository;
import com.uuolf.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceRequestService {

    private final ServiceRequestRepository requestRepository;
    private final UserRepository userRepository;

    public void createRequest(String clientEmail, ServiceRequestDto dto) {
        User client = userRepository.findByEmail(clientEmail)
                .orElseThrow(()-> new RuntimeException("Cliente não encontrado"));

        User professional = userRepository.findById(dto.getProfessionalId())
                .orElseThrow(()-> new RuntimeException("Profissional não encontrado"));

        ServiceRequest request = ServiceRequest.builder()
                .client(client)
                .professional(professional)
                .description(dto.getDescription())
                .status(RequestStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();

        requestRepository.save(request);
    }

    public List<ServiceRequest> getClientRequest(String clientEmail) {
        User client = userRepository.findByEmail(clientEmail)
                .orElseThrow();
        return  requestRepository.findByClient(client);
    }

    public List<ServiceRequest> getProfessionalRequest(String email) {
        User professional = userRepository.findByEmail(email)
                .orElseThrow();
        return requestRepository.findByProfessional(professional);
    }
}
