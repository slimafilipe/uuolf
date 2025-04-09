package com.uuolf.service;

import com.uuolf.dto.ProfessionalProfileRequest;
import com.uuolf.dto.ProfessionalProfileResponse;
import com.uuolf.entity.ProfessionalProfile;
import com.uuolf.entity.Specialty;
import com.uuolf.entity.User;
import com.uuolf.repository.ProfessionalProfileRepository;
import com.uuolf.repository.SpecialtyRepository;
import com.uuolf.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.awt.geom.Point2D.distance;

@Service
@RequiredArgsConstructor
public class ProfessionalProfileService {

    private final ProfessionalProfileRepository professionalProfileRepository;
    private final SpecialtyRepository specialtyRepository;
    private final UserRepository userRepository;

    public void createProfile(String email, ProfessionalProfileRequest request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("Usuário não encontrado"));

        List<Specialty> specialties = specialtyRepository.findAllById(request.specialtiesIds());

        ProfessionalProfile profile = new ProfessionalProfile();
        profile.setUser(user);
        profile.setBio(request.bio());
        profile.setSpecialties(specialties);
        profile.setLatitude(request.latitude());
        profile.setLongetude(request.longetude());

        professionalProfileRepository.save(profile);
    }

    public List<ProfessionalProfileResponse> search(String specialty, Double lat, Double lng) {
        List<ProfessionalProfile> profiles = professionalProfileRepository.findAll();

        return profiles.stream().filter(p-> specialty ==null || p.getSpecialties().stream()
                .anyMatch(s-> s.getName().equalsIgnoreCase(specialty)))
                .sorted(Comparator.comparingDouble(p-> {
                    if (lat == null || lng == null) return 0;
                    return distance(p.getLatitude(), p.getLongetude(), lat, lng);
                }))
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private double distance(double lat1, double lon1, double lat2, double lon2) {
        //Fórmula de Haversine(distância em km)
        double R = 6371;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return R * c;
    }

    private ProfessionalProfileResponse toResponse(ProfessionalProfile profile) {
        return new ProfessionalProfileResponse(
        profile.getUser().getName(),
        profile.getUser().getEmail(),
        profile.getBio(),
        profile.getSpecialties().stream().map(Specialty::getName).toList(),
        profile.getLatitude(),
        profile.getLongetude()
        );
    }
}
