package com.example.healthcare.repository;

import com.example.healthcare.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByEmail(String email);

    @Query("SELECT p FROM Patient p WHERE p.email = :email AND p.password = :password")
    Patient findByEmailAndPassword(String email, String password);
}
