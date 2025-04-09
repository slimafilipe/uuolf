package com.uuolf.controller;

import com.uuolf.dto.ServiceRequestDto;
import com.uuolf.entity.ServiceRequest;
import com.uuolf.repository.ServiceRequestRepository;
import com.uuolf.service.ServiceRequestService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/requests")
@RequiredArgsConstructor
public class ServiceRequestController {

    private final ServiceRequestService requestService;

    @PostMapping
    public ResponseEntity<Void> createdRequest(
            @RequestBody ServiceRequestDto dto,
            @AuthenticationPrincipal UserDetails userDetails) {
        requestService.createRequest(userDetails.getUsername(), dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/client")
    public ResponseEntity<List<ServiceRequest>> getClientsRequests(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(requestService.getClientRequest(userDetails.getUsername()));
    }

    @GetMapping("/professional")
    public ResponseEntity<List<ServiceRequest>> getProfessionalRequest(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(requestService.getProfessionalRequest(userDetails.getUsername()));
    }
}
