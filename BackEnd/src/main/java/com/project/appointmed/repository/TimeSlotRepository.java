package com.project.appointmed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.appointmed.dto.TimeSlot;

import java.util.List;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
//    List<TimeSlot> findByDoctorId(Long doctorId);
}
