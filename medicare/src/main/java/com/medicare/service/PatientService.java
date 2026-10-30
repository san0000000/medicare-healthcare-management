
package com.medicare.service;

import com.medicare.exception.DuplicatePatientEmailException;
import com.medicare.dto.PatientRequestDTO;
import com.medicare.dto.PatientResponseDTO;
import com.medicare.exception.DoctorNotFoundException;
import com.medicare.exception.PatientNotFoundException;
import com.medicare.mapper.PatientMapper;
import com.medicare.model.Patient;
import com.medicare.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Sort;
import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // Register a new patient
    public PatientResponseDTO registerPatient(PatientRequestDTO dto) {

        if (patientRepository.existsByEmailIgnoreCase(dto.getEmail())) {
    throw new DuplicatePatientEmailException(
            "Patient email is already registered: " + dto.getEmail()
    );
}

        Patient patient = PatientMapper.toEntity(dto);

        Patient savedPatient = patientRepository.save(patient);

        return PatientMapper.toResponseDTO(savedPatient);
    }

    // Get all patients
    @Transactional(readOnly = true)
    public List<PatientResponseDTO> getAllPatients() {

        return patientRepository.findAll()
                .stream()
                .map(PatientMapper::toResponseDTO)
                .toList();
    }
      public List<PatientResponseDTO> searchPatientsByName(String name) {

    List<Patient> patients =
            patientRepository.findByNameContainingIgnoreCase(name);

    return patients.stream()
            .map(PatientMapper::toResponseDTO)
            .toList();
}
public PatientResponseDTO searchPatientByEmail(String email) {

    Patient patient = patientRepository.findByEmailIgnoreCase(email)
            .orElseThrow(() ->
                    new PatientNotFoundException(
                            "Patient not found with email: " + email
                    ));

    return PatientMapper.toResponseDTO(patient);
}

    // Get patient by ID
    @Transactional(readOnly = true)
    public PatientResponseDTO getPatientById(Long id) {
    Patient patient = patientRepository.findById(id)
            .orElseThrow(() ->
                    new PatientNotFoundException(
                            "Patient not found with ID: " + id));

    return PatientMapper.toResponseDTO(patient);
}

    // Update patient details
    public PatientResponseDTO updatePatient(
            Long id,
            PatientRequestDTO dto) {

        Patient patient = patientRepository.findById(id)
        .orElseThrow(() ->
                new PatientNotFoundException(
                        "Patient not found with ID: " + id));

        boolean emailUsedByAnotherPatient =
                patientRepository.existsByEmailIgnoreCase(dto.getEmail())
                && !patient.getEmail().equalsIgnoreCase(dto.getEmail());

        if (!patient.getEmail().equalsIgnoreCase(dto.getEmail())
        && patientRepository.existsByEmailIgnoreCase(dto.getEmail())) {

    throw new DuplicatePatientEmailException(
            "Patient email is already registered: " + dto.getEmail()
    );
}

        patient.setName(dto.getName());
        patient.setEmail(dto.getEmail());
        patient.setAge(dto.getAge());
        patient.setPhone(dto.getPhone());

        Patient updatedPatient = patientRepository.save(patient);

        return PatientMapper.toResponseDTO(updatedPatient);
    }

    // Delete patient
    public void deletePatient(Long id) {

        Patient patient = patientRepository.findById(id)
        .orElseThrow(() ->
                new PatientNotFoundException(
                        "Patient not found with ID: " + id));

patientRepository.delete(patient);
    }
   public Page<PatientResponseDTO> getPatientsWithPagination(
        int page,
        int size) {

    validatePaginationAndSorting(page, size, "name", "asc");

    Pageable pageable = PageRequest.of(page, size);

    Page<Patient> patientPage =
            patientRepository.findAll(pageable);

    return patientPage.map(PatientMapper::toResponseDTO);
}
   public List<PatientResponseDTO> getPatientsSorted(
        String sortBy,
        String direction) {

    validatePaginationAndSorting(0, 5, sortBy, direction);

    Sort.Direction sortDirection =
            Sort.Direction.fromString(direction);

    Sort sort = Sort.by(sortDirection, sortBy);

    List<Patient> patients = patientRepository.findAll(sort);

    return patients.stream()
            .map(PatientMapper::toResponseDTO)
            .toList();
}
    public Page<PatientResponseDTO> filterPatients(
        String name,
        int page,
        int size,
        String sortBy,
        String direction) {

    validatePaginationAndSorting(page, size, sortBy, direction);

    if (name == null || name.isBlank()) {
        name = "";
    }

    Sort.Direction sortDirection =
            Sort.Direction.fromString(direction);

    Pageable pageable = PageRequest.of(
            page,
            size,
            Sort.by(sortDirection, sortBy)
    );

    Page<Patient> patientPage =
            patientRepository.findByNameContainingIgnoreCase(
                    name, pageable);

    return patientPage.map(PatientMapper::toResponseDTO);
}
    private void validatePaginationAndSorting(
        int page,
        int size,
        String sortBy,
        String direction) {

    if (page < 0) {
        throw new IllegalArgumentException(
                "Page number cannot be negative");
    }

    if (size < 1 || size > 100) {
        throw new IllegalArgumentException(
                "Page size must be between 1 and 100");
    }

    if (!List.of("id", "name", "age", "email")
            .contains(sortBy)) {
        throw new IllegalArgumentException(
                "Invalid sorting field");
    }

    if (!List.of("asc", "desc")
            .contains(direction.toLowerCase())) {
        throw new IllegalArgumentException(
                "Invalid sorting direction");
    }
}
   
}
