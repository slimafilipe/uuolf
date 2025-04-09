package com.uuolf.controller;

import com.uuolf.dto.ProfessionalProfileRequest;
import com.uuolf.service.ProfessionalProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
