package com.example.plant_app.security;

import net.bytebuddy.asm.Advice;
import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JwtUtilTest {

    private JwtUtil jwtUtil;

    @Before
    public void setUp() {
        jwtUtil = new JwtUtil();
    }

    @Test
    public void testGenerateToken_returnsNonNullToken() {
        String token = jwtUtil.generateToken(1L, "");
        assertNotNull("Token should not be null",token);
    }

    @Test
    public void testGenerateToken_ReturnsNonEmptyToken() {
        String token = jwtUtil.generateToken(1L, "testuser");
        assertFalse(token.isEmpty(), "Token should not be empty");
        System.out.println("Passed testGenerateToken_ReturnsNonEmptyToken test");
    }

    @Test
    public void testGetUsername_ReturnsCorrectUsername() {
        String token = jwtUtil.generateToken(1L, "testuser");
        String username = jwtUtil.getUsername(token);
        assertEquals("testuser", username,"Username should match");
    }

    @Test
    public void testGetUserId_ReturnsCorrectUserId() {
        String token = jwtUtil.generateToken(1L, "testuser");
        Long userId = jwtUtil.getUserId(token);
        assertEquals(Long.valueOf(1L), userId, "UserId should match");
    }

    @Test
    public void testIsValid_ReturnsTrueForValidToken() {
        String token = jwtUtil.generateToken(1L, "testuser");
        assertTrue(jwtUtil.isValid(token), "Valid token should return true");
    }
    @Test
    public void testIsValid_ReturnsFalseForInvalidToken() {
        assertFalse(jwtUtil.isValid("invalid.token.here"),"Invalid token should return false");
    }

    @Test
    public void testIsValid_ReturnsFalseForEmptyToken() {
        assertFalse( jwtUtil.isValid(""), "Empty token should return false");
    }

    @Test
    public void testIsValid_ReturnsFalseForNullToken() {
        assertFalse( jwtUtil.isValid(null), "Null token should return false");
    }

    @Test
    public void testGenerateToken_DifferentUsersGetDifferentTokens() {
        String token1 = jwtUtil.generateToken(1L, "user1");
        String token2 = jwtUtil.generateToken(2L, "user2");
        assertNotEquals("Different users should get different tokens", token1, token2);
    }
}