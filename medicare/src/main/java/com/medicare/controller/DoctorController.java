package com.medicare.controller;

import com.medicare.dto.DoctorRequestDTO;
import com.medicare.dto.DoctorResponseDTO;
import com.medicare.service.DoctorService;
import org.springframework.data.domain.Page;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // Get all doctors
    @GetMapping
    public ResponseEntity<List<DoctorResponseDTO>> getAllDoctors() {

        List<DoctorResponseDTO> doctors =
                doctorService.getAllDoctors();

        return ResponseEntity.ok(doctors);
    }

    // Get doctor by ID
    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> getDoctorById(
            @PathVariable Long id) {

        DoctorResponseDTO doctor =
                doctorService.getDoctorById(id);

        return ResponseEntity.ok(doctor);
    }

    // Create a new doctor
    @PostMapping
    public ResponseEntity<DoctorResponseDTO> createDoctor(
            @Valid @RequestBody DoctorRequestDTO doctorRequestDTO) {

        DoctorResponseDTO savedDoctor =
                doctorService.saveDoctor(doctorRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedDoctor);
    }

    // Update an existing doctor
    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> updateDoctor(
            @PathVariable Long id,
            @Valid @RequestBody DoctorRequestDTO doctorRequestDTO) {

        DoctorResponseDTO updatedDoctor =
                doctorService.updateDoctor(id, doctorRequestDTO);

        return ResponseEntity.ok(updatedDoctor);
    }

    // Delete a doctor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(
            @PathVariable Long id) {

        doctorService.deleteDoctor(id);

        return ResponseEntity.noContent().build();
    }
    @GetMapping("/search")
public ResponseEntity<List<DoctorResponseDTO>> searchDoctors(
        @RequestParam String specialization) {

    List<DoctorResponseDTO> doctors =
            doctorService.searchDoctorsBySpecialization(specialization);

    return ResponseEntity.ok(doctors);
    }
    @GetMapping("/search-by-name")
public ResponseEntity<List<DoctorResponseDTO>> searchDoctorsByName(
        @RequestParam String name) {

    List<DoctorResponseDTO> doctors =
            doctorService.searchDoctorsByName(name);

    return ResponseEntity.ok(doctors);
    }
    @GetMapping("/sort")
public ResponseEntity<List<DoctorResponseDTO>> getSortedDoctors(
        @RequestParam(defaultValue = "name") String sortBy,
        @RequestParam(defaultValue = "asc") String direction) {

    List<DoctorResponseDTO> doctors =
            doctorService.getAllDoctorsSorted(sortBy, direction);

    return ResponseEntity.ok(doctors);
     }
     @GetMapping("/paginated")
public ResponseEntity<Page<DoctorResponseDTO>> getPaginatedDoctors(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {

    Page<DoctorResponseDTO> doctors =
            doctorService.getAllDoctorsPaginated(page, size);

    return ResponseEntity.ok(doctors);
    }
    @GetMapping("/filter")
public ResponseEntity<Page<DoctorResponseDTO>> filterDoctors(
        @RequestParam String specialization,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestParam(defaultValue = "name") String sortBy,
        @RequestParam(defaultValue = "asc") String direction) {

    Page<DoctorResponseDTO> doctors =
            doctorService.filterDoctors(
                    specialization,
                    page,
                    size,
                    sortBy,
                    direction
            );

    return ResponseEntity.ok(doctors);
    }
}