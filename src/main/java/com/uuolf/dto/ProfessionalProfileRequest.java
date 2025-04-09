package com.uuolf.dto;

import java.util.List;

public record ProfessionalProfileRequest(
        String bio,
        List<Long> specialtiesIds,
        Double latitude,
        Double longetude
) {}
