package com.uuolf.controller;

import com.uuolf.dto.ProfessionalProfileRequest;
import com.uuolf.dto.ProfessionalSummaryDto;
import com.uuolf.service.ProfessionalProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfessionalProfileController {

    private final ProfessionalProfileService profileService;

    @PostMapping
    public ResponseEntity<Void> createProfile(
            @RequestBody ProfessionalProfileRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {

        profileService.createProfile(userDetails.getUsername(), request);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ProfessionalSummaryDto>> getAllProfessionals() {
        List<ProfessionalSummaryDto> list = profileService.getAllProfessionalSummary();
        return ResponseEntity.ok(list);
    }
}
