package com.example.plant_app.dto;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LoginRequestTest {

    private LoginRequest loginRequest;

    @Before
    public void setUp(){
        loginRequest = new LoginRequest();
    }

    @Test
    public void shouldCreateLoginRequest(){

        loginRequest.setUsername("testUser");
        loginRequest.setPassword("testPass");

        assertEquals("testUser", loginRequest.getUsername());
        assertEquals("testPass", loginRequest.getPassword());
    }

    @Test
    public void testNoArgsConstructor() {
        LoginRequest request = new LoginRequest();
        assertNotNull(request);
    }

    @Test
    public void testAllArgsConstructor() {
        LoginRequest request = new LoginRequest("john", "secret123");
        assertEquals("john", request.getUsername());
        assertEquals("secret123", request.getPassword());
    }
}