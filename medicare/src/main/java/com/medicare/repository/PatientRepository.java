
package com.medicare.repository;
import java.util.Optional;
import com.medicare.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    boolean existsByEmailIgnoreCase(String email);
    List<Patient> findByNameContainingIgnoreCase(String name); 
    Optional<Patient> findByEmailIgnoreCase(String email);
    Page<Patient> findByNameContainingIgnoreCase(
        String name, Pageable pageable);
}
