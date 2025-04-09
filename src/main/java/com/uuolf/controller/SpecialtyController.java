package com.uuolf.controller;

import com.uuolf.entity.Specialty;
import com.uuolf.repository.SpecialtyRepository;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/specialties")
@RequiredArgsConstructor
public class SpecialtyController {

    private final SpecialtyRepository specialtyRepository;

    @PostMapping
    public ResponseEntity<Specialty> create(@RequestBody Specialty specialty) {
        return ResponseEntity.ok(specialtyRepository.save(specialty));
    }

    @GetMapping
    public ResponseEntity<List<Specialty>> list() {
        return ResponseEntity.ok(specialtyRepository.findAll());
    }
}
