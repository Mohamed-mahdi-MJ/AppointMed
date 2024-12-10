package com.project.appointmed.dto;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Doctor extends User {
    private String medicalLicense;
    private String specialization;
    private String clinicName;
    private String address;
    private String city;
    
	public Doctor(Long id, String name, String email, String password, Role role, String phoneNumber, String medicalLicense,
			String specialization, String clinicName, String address, String city) {
		super(id, name, email, password, role, phoneNumber);
		this.medicalLicense = medicalLicense;
		this.specialization = specialization;
		this.clinicName = clinicName;
		this.address = address;
		this.city = city;
	}
    
	
    
}