
package com.medicare.controller;

import com.medicare.model.Doctor;
import com.medicare.service.DoctorService;
import jakarta.validation.Valid;
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

    // 1. Get all doctors
    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    // 2. Get doctor by ID
    @GetMapping("/{id}")
public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
    Doctor doctor = doctorService.getDoctorById(id);
    return ResponseEntity.ok(doctor);
}

    // 3. Add a new doctor
    @PostMapping
    public ResponseEntity<Doctor> createDoctor(
          @Valid  @RequestBody Doctor doctor) {

        Doctor savedDoctor = doctorService.saveDoctor(doctor);

        return ResponseEntity.status(201).body(savedDoctor);
    }

    // 4. Update an existing doctor
    @PutMapping("/{id}")
public ResponseEntity<Doctor> updateDoctor(
        @PathVariable Long id,
        @Valid @RequestBody Doctor updatedDoctor) {

    Doctor existingDoctor = doctorService.getDoctorById(id);

    updatedDoctor.setId(id);
    Doctor savedDoctor = doctorService.saveDoctor(updatedDoctor);

    return ResponseEntity.ok(savedDoctor);
}

    // 5. Delete a doctor
    @DeleteMapping("/{id}")
public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {

    doctorService.getDoctorById(id);
    doctorService.deleteDoctor(id);

    return ResponseEntity.noContent().build();
}
}
