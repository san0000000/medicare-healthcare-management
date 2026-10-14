
package com.medicare.controller;

import com.medicare.model.Doctor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final Map<Long, Doctor> doctors = new LinkedHashMap<>();
    private Long nextId = 1L;

    // READ: Get all doctors
    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {

        return ResponseEntity.ok(
            new ArrayList<>(doctors.values())
        );
    }

    // READ: Get one doctor by ID
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(
            @PathVariable Long id) {

        Doctor doctor = doctors.get(id);

        if (doctor == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(doctor);
    }

    // CREATE: Add a doctor
    @PostMapping
    public ResponseEntity<Doctor> addDoctor(
            @RequestBody Doctor doctor) {

        doctor.setId(nextId);
        doctors.put(nextId, doctor);
        nextId++;

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(doctor);
    }

    // UPDATE: Update a doctor
    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(
            @PathVariable Long id,
            @RequestBody Doctor updatedDoctor) {

        if (!doctors.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }

        updatedDoctor.setId(id);
        doctors.put(id, updatedDoctor);

        return ResponseEntity.ok(updatedDoctor);
    }

    // DELETE: Remove a doctor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(
            @PathVariable Long id) {

        if (!doctors.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }

        doctors.remove(id);

        return ResponseEntity.noContent().build();
    }
}
