package com.project.appointmed.service;

import org.springframework.stereotype.Service;

import com.project.appointmed.dto.Appointment;
import com.project.appointmed.dto.TimeSlot;
import com.project.appointmed.repository.AppointmentRepository;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final TimeSlotService timeSlotService;

    public AppointmentService(AppointmentRepository appointmentRepository, TimeSlotService timeSlotService) {
        this.appointmentRepository = appointmentRepository;
        this.timeSlotService = timeSlotService;
    }

    public Appointment createAppointment(Appointment appointment) {
//    	TimeSlot timeSlot = timeSlotService.getTimeSlotById(appointment.getTimeSlot().getId());
//    	timeSlotService.decreaseAvailability(timeSlot);
        return appointmentRepository.save(appointment);
    }
    
    public Appointment cancelAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new IllegalStateException("Appointment not found with ID: " + appointmentId));

        appointment.setStatus("CANCELLED");
//        TimeSlot timeSlot = timeSlotService.getTimeSlotById(appointment.getTimeSlot().getId());
//    	timeSlotService.increaseAvailability(timeSlot);
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAppointmentsForPatient(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    public List<Appointment> getAppointmentsForDoctor(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }
}
