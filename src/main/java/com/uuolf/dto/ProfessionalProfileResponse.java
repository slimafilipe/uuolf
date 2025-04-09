package com.uuolf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionalProfileResponse {

    private String name;
    private String email;
    private String bio;
    private List<String> specialties;
    private Double latitude;
    private Double longitude;
}
