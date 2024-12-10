package com.project.appointmed.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.appointmed.dto.Doctor;
import com.project.appointmed.service.DoctorService;

@RestController
@RequestMapping("api/doctor")
public class DoctorController {
	
	@Autowired
    private DoctorService doctorService;
	
	@GetMapping("/profile")
	@PreAuthorize("hasAuthority('ROLE_DOCTOR')")
	public ResponseEntity<?> doctorProfile() {
		try {
	  	   Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	       String username;
	       if (principal instanceof UserDetails) {
	          username = ((UserDetails) principal).getUsername();
	       } else {
	          username = principal.toString();
	       }
	        
	        // Fetch doctor details from the database using the UserService
	        Doctor doctor = doctorService.getDoctorByEmail(username);
	  		System.out.println("Doctor profile called..");
	  		return ResponseEntity.ok(doctor);
	  	}catch(Exception e) {
	  		e.printStackTrace();
	  		return ResponseEntity.badRequest().body(e.getMessage());
	  	}
	}
	
	@GetMapping("/{city}")
	@PreAuthorize("hasAuthority('ROLE_PATIENT')")
	public ResponseEntity<?> doctorByCity(@PathVariable String city) {
		try {
	        List<Doctor> doctors = doctorService.getDoctorsByCity(city);
	  		System.out.println(city);
	  		return ResponseEntity.ok(doctors);
	  	}catch(Exception e) {
	  		e.printStackTrace();
	  		return ResponseEntity.badRequest().body(e.getMessage());
	  	}
	}
}