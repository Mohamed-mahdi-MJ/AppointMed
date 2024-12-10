package com.project.appointmed.service;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.project.appointmed.dto.Doctor;
import com.project.appointmed.dto.Patient;
import com.project.appointmed.dto.User;
import com.project.appointmed.repository.DoctorRepository;
import com.project.appointmed.repository.PatientRepository;
import com.project.appointmed.repository.UserRepository;


@Service
public class DoctorService {
	
	private final DoctorRepository doctorRepository;
	private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DoctorService(DoctorRepository doctorRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    public Doctor getDoctorByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> 
            new IllegalStateException("User not found")
        );

        if (user instanceof Doctor) {
            return (Doctor) user;
        } else {
            throw new IllegalStateException("User is not a doctor");
        }
    }
    
    public List<Doctor> getDoctorsByCity(String city) {
    	return doctorRepository.findByCity(city);
    }
        
    public User addDoctor(Doctor doctor) throws IllegalStateException {
        // 1. Check if the email already exists
        if (userRepository.findByEmail(doctor.getEmail()).isPresent()) {
            throw new IllegalStateException("Email is already registered.");
        }

        // 2. Validate email format
        if (!isValidEmail(doctor.getEmail())) {
            throw new IllegalStateException("Invalid email format.");
        }

        // 3. Validate password strength
        if (!isValidPassword(doctor.getPassword())) {
            throw new IllegalStateException("Password must be at least 8 characters, contain one number, one letter, and one special character.");
        }

        // 4. Encrypt the password
        doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));

        // 5. Save the user to the database
        return doctorRepository.save(doctor);
    }

    // Email validation using regex
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        return StringUtils.hasText(email) && pattern.matcher(email).matches();
    }

    // Password strength validation
    private boolean isValidPassword(String password) {
        if (password.length() < 8) {
            return false;
        }
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=]).{8,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        return pattern.matcher(password).matches();
    }
}