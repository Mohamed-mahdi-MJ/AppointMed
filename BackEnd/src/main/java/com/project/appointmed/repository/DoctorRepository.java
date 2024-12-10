package com.project.appointmed.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.appointmed.dto.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	List<Doctor> findByCity(String city);
}
