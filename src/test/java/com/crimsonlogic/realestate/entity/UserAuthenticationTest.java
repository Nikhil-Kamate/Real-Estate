package com.crimsonlogic.realestate.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.crimsonlogic.realestate.model.UserAuthentication;
import com.crimsonlogic.realestate.model.UserDetails;
import com.crimsonlogic.realestate.model.UserRole;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UserAuthenticationTest {

    private UserAuthentication userAuthentication;
    private UserDetails userDetails;
    private UserRole userRole;

    @BeforeEach
    void setUp() {
        userAuthentication = new UserAuthentication();
        userDetails = mock(UserDetails.class); // Mocking UserDetails for association
        userRole = mock(UserRole.class); // Mocking UserRole for association
    }

    @Test
    void testSetAndGetUserId() {
        Long expectedUserId = 1L;
        userAuthentication.setUserId(expectedUserId);
        
        assertEquals(expectedUserId, userAuthentication.getUserId(), "User ID should match.");
    }

    @Test
    void testSetAndGetEmail() {
        String expectedEmail = "test@example.com";
        userAuthentication.setEmail(expectedEmail);
        
        assertEquals(expectedEmail, userAuthentication.getEmail(), "Email should match.");
    }

    @Test
    void testSetAndGetPassword() {
        String expectedPassword = "securepassword";
        userAuthentication.setPassword(expectedPassword);
        
        assertEquals(expectedPassword, userAuthentication.getPassword(), "Password should match.");
    }

    @Test
    void testSetAndGetUserDetails() {
        userAuthentication.setUserDetails(userDetails);
        
        assertEquals(userDetails, userAuthentication.getUserDetails(), "UserDetails should match.");
    }

    @Test
    void testSetAndGetUserRole() {
        userAuthentication.setUserRole(userRole);
        
        assertEquals(userRole, userAuthentication.getUserRole(), "UserRole should match.");
    }
}
