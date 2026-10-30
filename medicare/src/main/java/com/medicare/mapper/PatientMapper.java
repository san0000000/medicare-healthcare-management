
package com.medicare.mapper;

import com.medicare.dto.PatientRequestDTO;
import com.medicare.dto.PatientResponseDTO;
import com.medicare.model.Patient;

public class PatientMapper {

    private PatientMapper() {
        // Prevent creating objects of this utility class.
    }

    public static Patient toEntity(PatientRequestDTO dto) {

        Patient patient = new Patient();

        patient.setName(dto.getName());
        patient.setEmail(dto.getEmail());
        patient.setAge(dto.getAge());
        patient.setPhone(dto.getPhone());

        return patient;
    }

    public static PatientResponseDTO toResponseDTO(Patient patient) {

        return new PatientResponseDTO(
                patient.getId(),
                patient.getName(),
                patient.getEmail(),
                patient.getAge(),
                patient.getPhone()
        );
    }
}
