package com.uuolf.controller;


import com.uuolf.dto.ProfessionalProfileResponse;
import com.uuolf.service.ProfessionalProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/professionals")
@RequiredArgsConstructor
public class ProfessionalSearchController {

    private final ProfessionalProfileService profileService;

    @GetMapping
    public ResponseEntity<List<ProfessionalProfileResponse>> searchProfessionals(
            @RequestParam(required = false) String specialty,
            @RequestParam(required = false) Double latitude,
            @RequestParam(required = false) Double longitude
    ) {
        List<ProfessionalProfileResponse> result = profileService.search(specialty, latitude, longitude);
        return ResponseEntity.ok(result);
    }

}
