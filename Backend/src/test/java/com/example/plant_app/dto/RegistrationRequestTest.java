package com.example.plant_app.dto;

import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegistrationRequestTest {
    private RegistrationRequest request;

    @Before
    public void setUp() {
        request = new RegistrationRequest();
    }

    @Test
    public void testSetAndGetUsername() {
        request.setUsername("newuser");
        assertEquals("newuser", request.getUsername());
    }

    @Test
    public void testSetAndGetEmail() {
        request.setEmail("test@example.com");
        assertEquals("test@example.com", request.getEmail());
    }

    @Test
    public void testSetAndGetPassword() {
        request.setPassword("password123");
        assertEquals("password123", request.getPassword());
    }

    @Test
    public void testNoArgsConstructor() {
        RegistrationRequest req = new RegistrationRequest();
        assertNotNull(req);
    }

    @Test
    public void testAllArgsConstructor() {
        RegistrationRequest req = new RegistrationRequest("john", "john@test.com", "secret");
        assertEquals("john", req.getUsername());
        assertEquals("john@test.com", req.getEmail());
        assertEquals("secret", req.getPassword());
    }

}