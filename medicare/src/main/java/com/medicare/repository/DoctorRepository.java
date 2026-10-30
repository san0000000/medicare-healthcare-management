package com.medicare.repository;

import com.medicare.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    List<Doctor> findBySpecializationIgnoreCase(String specialization);

    List<Doctor> findByNameContainingIgnoreCase(String name);

    Page<Doctor> findBySpecializationIgnoreCase(
        String specialization,
        Pageable pageable
);

}