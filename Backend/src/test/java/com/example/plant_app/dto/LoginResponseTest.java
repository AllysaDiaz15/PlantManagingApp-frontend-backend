package com.example.plant_app.dto;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginResponseTest {
    private LoginResponse loginResponse;

    @Test
    public void testAllArgsConstructor() {
        loginResponse = new LoginResponse(1L, "testuser", "token123");
        assertEquals(Long.valueOf(1L), loginResponse.getUserId());
        assertEquals("testuser", loginResponse.getUsername());
        assertEquals("token123", loginResponse.getToken());
    }

    @Test
    public void testGetUserId() {
        loginResponse = new LoginResponse(5L, "john", "abc");
        assertEquals(Long.valueOf(5L), loginResponse.getUserId());
    }

    @Test
    public void testGetUsername() {
        loginResponse = new LoginResponse(1L, "jane", "xyz");
        assertEquals("jane", loginResponse.getUsername());
    }

    @Test
    public void testGetToken() {
        loginResponse = new LoginResponse(1L, "user", "mytoken");
        assertEquals("mytoken", loginResponse.getToken());
    }

}