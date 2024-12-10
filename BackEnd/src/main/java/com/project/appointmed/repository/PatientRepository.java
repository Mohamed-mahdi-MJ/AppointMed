package com.project.appointmed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.appointmed.dto.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    	
}