package com.project.appointmed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.appointmed.dto.Patient;
import com.project.appointmed.service.PatientService;

@RestController
@RequestMapping("api/patient")
public class PatientController {
	
	@Autowired
    private PatientService patientService;
	
	@GetMapping("/profile")
	@PreAuthorize("hasAuthority('ROLE_PATIENT')")
	public ResponseEntity<?> patientProfile() {
		try {
	  	   Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	       String username;
	       if (principal instanceof UserDetails) {
	          username = ((UserDetails) principal).getUsername();
	       } else {
	          username = principal.toString();
	       }
	        
	        // Fetch patient details from the database using the UserService
	        Patient patient = patientService.getPatientByEmail(username);
	  		System.out.println("profile called..");
	  		return ResponseEntity.ok(patient);
	  	}catch(Exception e) {
	  		e.printStackTrace();
	  		return ResponseEntity.badRequest().body(e.getMessage());
	  	}
	}
}
