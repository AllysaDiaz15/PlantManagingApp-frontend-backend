package com.example.plant_app.Entity;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PlantTest {
    private Plant plant;
    private User user;

    @Before
    public void setUp() {
        plant = new Plant();
        user = new User();
        user.setUserId(1L);
        user.setUsername("testuser");
    }

    @Test
    public void testSetAndGetPlantId() {
        plant.setPlantId(1L);
        assertEquals(Long.valueOf(1L), plant.getPlantId());
    }

    @Test
    public void testSetAndGetPlantName() {
        plant.setPlantName("Monstera");
        assertEquals("Monstera", plant.getPlantName());
    }

    @Test
    public void testSetAndGetDescription() {
        plant.setDescription("A beautiful tropical plant");
        assertEquals("A beautiful tropical plant", plant.getDescription());
    }

    @Test
    public void testSetAndGetCareNotes() {
        plant.setCareNotes("Water weekly");
        assertEquals("Water weekly", plant.getCareNotes());
    }

    @Test
    public void testSetAndGetPreferredWater() {
        plant.setPreferredWater("Filtered water");
        assertEquals("Filtered water", plant.getPreferredWater());
    }

    @Test
    public void testSetAndGetWateringSchedule() {
        plant.setWateringSchedule("Every 7 days");
        assertEquals("Every 7 days", plant.getWateringSchedule());
    }

    @Test
    public void testSetAndGetLastWatered() {
        LocalDate date = LocalDate.of(2024, 1, 15);
        plant.setLastWatered(date);
        assertEquals(date, plant.getLastWatered());
    }

    @Test
    public void testSetAndGetFertilizingSchedule() {
        plant.setFertilizingSchedule("Monthly");
        assertEquals("Monthly", plant.getFertilizingSchedule());
    }

    @Test
    public void testSetAndGetLastFertilized() {
        LocalDate date = LocalDate.of(2024, 1, 10);
        plant.setLastFertilized(date);
        assertEquals(date, plant.getLastFertilized());
    }

    @Test
    public void testSetAndGetRepotted() {
        LocalDate date = LocalDate.of(2023, 6, 1);
        plant.setRepotted(date);
        assertEquals(date, plant.getRepotted());
    }

    @Test
    public void testSetAndGetUser() {
        plant.setUser(user);
        assertEquals(user, plant.getUser());
        assertEquals(Long.valueOf(1L), plant.getUser().getUserId());
    }

    @Test
    public void testNoArgsConstructor() {
        Plant newPlant = new Plant();
        assertNotNull(newPlant);
    }

    @Test
    public void testAllArgsConstructor() {
        LocalDate now = LocalDate.now();
        Plant newPlant = new Plant(1L, user, "Fern", "Green fern", "Keep moist",
                "Tap water", "Every 3 days", now, "Weekly", now, now);

        assertEquals(Long.valueOf(1L), newPlant.getPlantId());
        assertEquals("Fern", newPlant.getPlantName());
        assertEquals(user, newPlant.getUser());
    }
}