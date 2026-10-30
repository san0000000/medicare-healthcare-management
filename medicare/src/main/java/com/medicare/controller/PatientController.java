
package com.medicare.controller;

import com.medicare.dto.PatientRequestDTO;
import com.medicare.dto.PatientResponseDTO;
import com.medicare.service.PatientService;
import org.springframework.data.domain.Page;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // Register a new patient
    @PostMapping
    public ResponseEntity<PatientResponseDTO> registerPatient(
            @Valid @RequestBody PatientRequestDTO dto) {

        PatientResponseDTO patient =
                patientService.registerPatient(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(patient);
    }

    // Get all patients
    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {

        return ResponseEntity.ok(patientService.getAllPatients());
    }

    // Get patient by ID
    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> getPatientById(
            @PathVariable Long id) {

        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    // Update patient
    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(
            @PathVariable Long id,
            @Valid @RequestBody PatientRequestDTO dto) {

        return ResponseEntity.ok(
                patientService.updatePatient(id, dto)
        );
    }

    // Delete patient
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(
            @PathVariable Long id) {

        patientService.deletePatient(id);

        return ResponseEntity.noContent().build();
    }
    @GetMapping("/search")
public ResponseEntity<List<PatientResponseDTO>> searchPatientsByName(
        @RequestParam String name) {

    List<PatientResponseDTO> patients =
            patientService.searchPatientsByName(name);

    return ResponseEntity.ok(patients);
    }
    @GetMapping("/search-by-email")
public ResponseEntity<PatientResponseDTO> searchPatientByEmail(
        @RequestParam String email) {

    PatientResponseDTO patient =
            patientService.searchPatientByEmail(email);

    return ResponseEntity.ok(patient);
    }
    @GetMapping("/page")
public ResponseEntity<Page<PatientResponseDTO>> getPatientsWithPagination(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size) {

    Page<PatientResponseDTO> patients =
            patientService.getPatientsWithPagination(page, size);

    return ResponseEntity.ok(patients);
    }
    @GetMapping("/sort")
public ResponseEntity<List<PatientResponseDTO>> getPatientsSorted(
        @RequestParam(defaultValue = "name") String sortBy,
        @RequestParam(defaultValue = "asc") String direction) {

    List<PatientResponseDTO> patients =
            patientService.getPatientsSorted(sortBy, direction);

    return ResponseEntity.ok(patients);
    }
    @GetMapping("/filter")
public ResponseEntity<Page<PatientResponseDTO>> filterPatients(
        @RequestParam(defaultValue = "") String name,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestParam(defaultValue = "name") String sortBy,
        @RequestParam(defaultValue = "asc") String direction) {

    Page<PatientResponseDTO> patients =
            patientService.filterPatients(
                    name, page, size, sortBy, direction);

    return ResponseEntity.ok(patients);
    }
}
