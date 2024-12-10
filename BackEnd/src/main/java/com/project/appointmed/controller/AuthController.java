package com.project.appointmed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import com.project.appointmed.dto.AuthRequest;
import com.project.appointmed.dto.Doctor;
import com.project.appointmed.dto.Patient;
import com.project.appointmed.dto.Role;
import com.project.appointmed.service.DoctorService;
import com.project.appointmed.service.JwtService;
import com.project.appointmed.service.PatientService;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    
    @Autowired
    private PatientService patientService;
    
    @Autowired
    private DoctorService doctorService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/addPatient")
    public ResponseEntity<?> addPatient(@RequestBody Patient patient) {
    	try {
    		return ResponseEntity.ok(patientService.addPatient(patient));
    	} catch(Exception e) {
    		return ResponseEntity.badRequest().body(e.getMessage());
    	}
    }
    
    @PostMapping("/addDoctor")
    public ResponseEntity<?> addDoctor(@RequestBody Doctor doctor) {
    	try {
    		return ResponseEntity.ok(doctorService.addDoctor(doctor));
    	} catch(Exception e) {
    		return ResponseEntity.badRequest().body(e.getMessage());
    	}
    }

    @PostMapping("/generateToken")
    public ResponseEntity<?> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        try {
        	Authentication authentication = authenticationManager.authenticate(
        			new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            if (authentication.isAuthenticated()) {
            	String token = jwtService.generateToken(authRequest.getUsername());
            	return ResponseEntity.ok(token);
            } else {
            	throw new UsernameNotFoundException("Invalid user request!");
            }
        }catch(Exception e) {
        	e.printStackTrace();
        	return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}