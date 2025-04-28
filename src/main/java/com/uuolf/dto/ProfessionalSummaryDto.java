package com.uuolf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProfessionalSummaryDto {
    private Long id;
    private String name;
    private String bio;
    private List<String> specialties;
}
