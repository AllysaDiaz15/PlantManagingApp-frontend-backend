package com.example.plant_app.Entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private User user;

    @Before
    public void setUp() {
        user = new User();
    }

    @Test
    public void testSetAndGetUserId() {
        user.setUserId(1L);
        assertEquals(Long.valueOf(1L), user.getUserId());
    }

    @Test
    public void testSetAndGetUsername() {
        user.setUsername("testuser");
        assertEquals("testuser", user.getUsername());
    }

    @Test
    public void testSetAndGetEmail() {
        user.setEmail("test@example.com");
        assertEquals("test@example.com", user.getEmail());
    }

    @Test
    public void testSetAndGetPassword() {
        user.setPassword("securepassword123");
        assertEquals("securepassword123", user.getPassword());
    }

    @Test
    public void testNoArgsConstructor() {
        User newUser = new User();
        assertNotNull(newUser);
    }

    @Test
    public void testAllArgsConstructor() {
        User newUser = new User(1L, "john", "john@example.com", "password123");

        assertEquals(Long.valueOf(1L), newUser.getUserId());
        assertEquals("john", newUser.getUsername());
        assertEquals("john@example.com", newUser.getEmail());
        assertEquals("password123", newUser.getPassword());
    }

    @Test
    public void testUsernameMinLength() {
        user.setUsername("abc");  // Minimum 3 characters
        assertEquals(3, user.getUsername().length());
    }

    @Test
    public void testUsernameMaxLength() {
        String longUsername = "a".repeat(30);  // Maximum 30 characters
        user.setUsername(longUsername);
        assertEquals(30, user.getUsername().length());
    }

}