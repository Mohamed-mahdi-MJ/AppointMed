package com.project.appointmed.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.project.appointmed.dto.TimeSlot;
import com.project.appointmed.service.TimeSlotService;

import jakarta.websocket.server.PathParam;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/timeslot")
public class TimeSlotController {

    private final TimeSlotService timeSlotService;

    public TimeSlotController(TimeSlotService timeSlotService) {
        this.timeSlotService = timeSlotService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_DOCTOR')")
    public ResponseEntity<?> createTimeSlot(@RequestBody TimeSlot timeSlot) {
        try {
            TimeSlot createdTimeSlot = timeSlotService.createTimeSlot(timeSlot);
            return new ResponseEntity<>(createdTimeSlot, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/doctor")
    @PreAuthorize("hasAnyAuthority('ROLE_PATIENT', 'ROLE_DOCTOR')")
    public ResponseEntity<?> getAvailableTimeSlotsByDoctorIdAndDate(@RequestParam Long doctorId, @RequestParam LocalDate date) {
        try {
            List<TimeSlot> timeSlots = timeSlotService.getAvailableTimeSlotsByDoctorIdAndDate(doctorId, date);
            return ResponseEntity.ok(timeSlots);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}