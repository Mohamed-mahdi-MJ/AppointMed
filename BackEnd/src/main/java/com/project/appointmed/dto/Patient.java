package com.project.appointmed.dto;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Patient extends User {
    private int age;
    private String gender;
    
	public Patient(Long id, String name, String email, String password, Role role, String phoneNumber,
			int age, String gender) {
		super(id, name, email, password, role, phoneNumber);
		this.age = age;
		this.gender = gender;
	}	
	
}
