package com.medicare.mapper;

import com.medicare.dto.DoctorRequestDTO;
import com.medicare.dto.DoctorResponseDTO;
import com.medicare.model.Doctor;

public class DoctorMapper {

    // Convert request DTO into Doctor entity
    public static Doctor toEntity(DoctorRequestDTO dto) {

        Doctor doctor = new Doctor();

        doctor.setName(dto.getName());
        doctor.setSpecialization(dto.getSpecialization());
        doctor.setExperience(dto.getExperience());

        return doctor;
    }

    // Convert Doctor entity into response DTO
    public static DoctorResponseDTO toResponseDTO(Doctor doctor) {

        return new DoctorResponseDTO(
                doctor.getId(),
                doctor.getName(),
                doctor.getSpecialization(),
                doctor.getExperience()
        );
    }
}