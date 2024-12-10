package com.project.appointmed.service;

import org.springframework.stereotype.Service;

import com.project.appointmed.dto.Appointment;
import com.project.appointmed.dto.TimeSlot;
import com.project.appointmed.repository.AppointmentRepository;
import com.project.appointmed.repository.TimeSlotRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class TimeSlotService {

    private final TimeSlotRepository timeSlotRepository;
    private final AppointmentRepository appointmentRepository;

    public TimeSlotService(TimeSlotRepository timeSlotRepository, AppointmentRepository appointmentRepository) {
        this.timeSlotRepository = timeSlotRepository;
		this.appointmentRepository = appointmentRepository;
    }
    
    public TimeSlot getTimeSlotById(Long id) {
        return timeSlotRepository.findById(id).orElseThrow(() -> new IllegalStateException("Time slot not found"));
    }

    public TimeSlot createTimeSlot(TimeSlot timeSlot) {
        return timeSlotRepository.save(timeSlot);
    }
    
    public List<TimeSlot> getAvailableTimeSlotsByDoctorIdAndDate(Long doctorId, LocalDate date) {
    	// Step 1: Get all predefined time slots
        List<TimeSlot> allTimeSlots = timeSlotRepository.findAll();

        // Step 2: Get all appointments for this doctor and date
        List<Appointment> appointments = appointmentRepository.findByDoctorIdAndDate(doctorId, date);

        // Step 3: Filter out the time slots that are already booked
        List<TimeSlot> availableTimeSlots = allTimeSlots.stream()
            .filter(slot -> appointments.stream()
                .noneMatch(appointment -> appointment.getTimeSlot().getId().equals(slot.getId())))
            .toList();

        // Step 4: Return the available time slots
        return availableTimeSlots;
    }

//    public List<TimeSlot> getTimeSlotsForDoctor(Long doctorId) {
//        return timeSlotRepository.findByDoctorId(doctorId);
//    }
    
//    public TimeSlot decreaseAvailability(TimeSlot timeSlot) {
//    	System.out.println(timeSlot.getAvailability());
//        if (timeSlot.getAvailability() > 0) {
//            timeSlot.setAvailability(timeSlot.getAvailability() - 1);
//            return timeSlotRepository.save(timeSlot);
//        } else {
//            throw new IllegalStateException("No available slots left for this time.");
//        }
//    }
//    
//    public void increaseAvailability(TimeSlot timeSlot) {
//        timeSlot.setAvailability(timeSlot.getAvailability() + 1);
//        timeSlotRepository.save(timeSlot);
//    }
}
