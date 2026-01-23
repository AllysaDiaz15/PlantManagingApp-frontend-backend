package com.example.plant_app.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Plant {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long plantId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotBlank(message = "Plant name is required")
    @Size(max = 50, message = "Name of the Plant should be under 50 characters")
    private String plantName;

    @Size(max = 500,  message = "Description must be under 500 characters")
    private String description;

    @Size(max = 500,  message = "Notes must be under 500 characters")
    private String careNotes;

    private String preferredWater;
    private String wateringSchedule;

    @PastOrPresent(message = "Last watered cannot be in the future")
    private LocalDate lastWatered;

    private String fertilizingSchedule;

    @PastOrPresent(message = "Last fertilized cannot be in the future")
    private LocalDate lastFertilized;

    @PastOrPresent(message = "Last repotted cannot be in the future")
    private LocalDate repotted;
}