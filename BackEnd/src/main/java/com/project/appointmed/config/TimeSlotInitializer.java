package com.project.appointmed.config;

import com.project.appointmed.dto.TimeSlot;
import com.project.appointmed.repository.TimeSlotRepository;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TimeSlotInitializer {

    private final TimeSlotRepository timeSlotRepository;

    public TimeSlotInitializer(TimeSlotRepository timeSlotRepository) {
        this.timeSlotRepository = timeSlotRepository;
    }

    @PostConstruct
    public void init() {
        // Check if time slots already exist in the database to avoid duplicates
        if (timeSlotRepository.count() == 0) {
            // Predefine the time slots (e.g., "09:00 AM", "09:30 AM", etc.)
        	List<String> times = List.of(
        		    "09.00 AM - 09.30 AM",
        		    "09.30 AM - 10.00 AM",
        		    "10.00 AM - 10.30 AM",
        		    "10.30 AM - 11.00 AM",
        		    "11.00 AM - 11.30 AM",
        		    "11.30 AM - 12.00 PM",
        		    "12.00 PM - 12.30 PM",
        		    "12.30 PM - 01.00 PM",
        		    "01.00 PM - 01.30 PM",
        		    "01.30 PM - 02.00 PM"
        		);

            // Map the times to TimeSlot objects and save them in the repository
            List<TimeSlot> timeSlots = times.stream()
                .map(time -> new TimeSlot(null, time)) // Create a TimeSlot object
                .collect(Collectors.toList());

            // Save all time slots to the database
            timeSlotRepository.saveAll(timeSlots);
        }
    }
}
