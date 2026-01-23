package com.example.plant_app.controller;

import com.example.plant_app.Entity.Plant;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.example.plant_app.repository.PlantRepository;

import java.util.List;

@RestController
@RequestMapping(path="/plants")
public class MainController {
    @Autowired // This means to get the bean called plantRepository

    private PlantRepository plantRepository;

    @GetMapping(path="/user/{userId}")
    public List<Plant> getPlantsByUser(@PathVariable Long userId) {
        return plantRepository.findByUserUserId(userId);
    }

    @PostMapping (path="/add") // Map ONLY POST Requests
        public  String addNewPlant(@RequestBody Plant plant) {
        plantRepository.save(plant);
        return "Saved";
    }

    @GetMapping(path="/all")
    public  Iterable<Plant> getAllPlants() {
        return plantRepository.findAll();
    }

    @GetMapping(path="/{id}")
    public Plant getAPlant(@PathVariable Long id) {
        return plantRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public Plant updatePlant(@PathVariable Long id, @Valid @RequestBody Plant updatedPlant) {
        Plant plant = plantRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        plant.setPlantName(updatedPlant.getPlantName());
        plant.setDescription(updatedPlant.getDescription());
        plant.setCareNotes(updatedPlant.getCareNotes());
        plant.setPreferredWater(updatedPlant.getPreferredWater());
        plant.setWateringSchedule(updatedPlant.getWateringSchedule());
        plant.setLastWatered(updatedPlant.getLastWatered());
        plant.setFertilizingSchedule(updatedPlant.getFertilizingSchedule());
        plant.setLastFertilized(updatedPlant.getLastFertilized());
        plant.setRepotted(updatedPlant.getRepotted());

        return plantRepository.save(plant);
    }

    @DeleteMapping("/{id}")
    public String deletePlant(@PathVariable Long id) {
        if (!plantRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        plantRepository.deleteById(id);
        return "Deleted";
    }
}
