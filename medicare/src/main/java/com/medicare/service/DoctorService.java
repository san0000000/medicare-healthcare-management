package com.medicare.service;

import com.medicare.dto.DoctorRequestDTO;
import com.medicare.dto.DoctorResponseDTO;
import com.medicare.exception.DoctorNotFoundException;
import com.medicare.mapper.DoctorMapper;
import com.medicare.model.Doctor;
import com.medicare.repository.DoctorRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@Service
@Transactional
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    // Get all doctors
    @Transactional(readOnly = true)
    public List<DoctorResponseDTO> getAllDoctors() {

        return doctorRepository.findAll()
                .stream()
                .map(DoctorMapper::toResponseDTO)
                .toList();
    }

    // Get doctor by ID
    @Transactional(readOnly = true)
    public DoctorResponseDTO getDoctorById(Long id) {

        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() ->
                        new DoctorNotFoundException(
                                "Doctor not found with ID: " + id
                        )
                );

        return DoctorMapper.toResponseDTO(doctor);
    }

    // Create a new doctor
    public DoctorResponseDTO saveDoctor(DoctorRequestDTO dto) {

        Doctor doctor = DoctorMapper.toEntity(dto);

        Doctor savedDoctor = doctorRepository.save(doctor);

        return DoctorMapper.toResponseDTO(savedDoctor);
    }

    // Update an existing doctor
    public DoctorResponseDTO updateDoctor(
            Long id,
            DoctorRequestDTO dto) {

        Doctor existingDoctor = doctorRepository.findById(id)
                .orElseThrow(() ->
                        new DoctorNotFoundException(
                                "Doctor not found with ID: " + id
                        )
                );

        existingDoctor.setName(dto.getName());
        existingDoctor.setSpecialization(dto.getSpecialization());
        existingDoctor.setExperience(dto.getExperience());

        Doctor updatedDoctor = doctorRepository.save(existingDoctor);

        return DoctorMapper.toResponseDTO(updatedDoctor);
    }

    // Delete a doctor
    public void deleteDoctor(Long id) {

        if (!doctorRepository.existsById(id)) {
            throw new DoctorNotFoundException(
                    "Doctor not found with ID: " + id
            );
        }

        doctorRepository.deleteById(id);
    }
    @Transactional(readOnly = true)
public List<DoctorResponseDTO> searchDoctorsBySpecialization(String specialization) {

    return doctorRepository
            .findBySpecializationIgnoreCase(specialization)
            .stream()
            .map(DoctorMapper::toResponseDTO)
            .toList();
        }
        @Transactional(readOnly = true)
public List<DoctorResponseDTO> searchDoctorsByName(String name) {

    return doctorRepository
            .findByNameContainingIgnoreCase(name)
            .stream()
            .map(DoctorMapper::toResponseDTO)
            .toList();
        } 
        @Transactional(readOnly = true)
public List<DoctorResponseDTO> getAllDoctorsSorted(String sortBy, String direction) {

    Sort sort = direction.equalsIgnoreCase("desc")
            ? Sort.by(sortBy).descending()
            : Sort.by(sortBy).ascending();

    return doctorRepository.findAll(sort)
            .stream()
            .map(DoctorMapper::toResponseDTO)
            .toList();
        }
        @Transactional(readOnly = true)
public Page<DoctorResponseDTO> getAllDoctorsPaginated(int page, int size) {

    Pageable pageable = PageRequest.of(page, size);

    return doctorRepository.findAll(pageable)
            .map(DoctorMapper::toResponseDTO);
          }
          @Transactional(readOnly = true)
public Page<DoctorResponseDTO> filterDoctors(
        String specialization,
        int page,
        int size,
        String sortBy,
        String direction) {

    Sort sort = direction.equalsIgnoreCase("desc")
            ? Sort.by(sortBy).descending()
            : Sort.by(sortBy).ascending();

    Pageable pageable = PageRequest.of(page, size, sort);

    return doctorRepository
            .findBySpecializationIgnoreCase(specialization, pageable)
            .map(DoctorMapper::toResponseDTO);
        }

}