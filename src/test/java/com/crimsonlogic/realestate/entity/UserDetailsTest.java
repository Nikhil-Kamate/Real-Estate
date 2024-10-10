package com.crimsonlogic.realestate.entity;

import com.crimsonlogic.realestate.model.UserDetails;
import com.crimsonlogic.realestate.model.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserDetailsTest {

    private UserDetails userDetails;
    private UserRole userRole;

    @BeforeEach
    void setUp() {
        userDetails = new UserDetails();
        userRole = mock(UserRole.class); // Mocking UserRole for association
    }

    @Test
    void testGenerateId() {
        // Call the method to simulate pre-persist actions
        userDetails.generateId();

        assertNotNull(userDetails.getUserdetailsId(), "Userdetails ID should be generated and not null.");
        assertTrue(userDetails.getUserdetailsId().startsWith("UID-"), "Userdetails ID should start with 'UID-'.");
    }

    @Test
    void testSetAndGetUserdetailsFirstName() {
        String expectedFirstName = "John";
        userDetails.setUserdetailsFirstName(expectedFirstName);
        
        assertEquals(expectedFirstName, userDetails.getUserdetailsFirstName(), "First name should match.");
    }

    @Test
    void testSetAndGetUserLastName() {
        String expectedLastName = "Doe";
        userDetails.setUserLastName(expectedLastName);
        
        assertEquals(expectedLastName, userDetails.getUserLastName(), "Last name should match.");
    }

    @Test
    void testSetAndGetUserPhoneNo() {
        String expectedPhoneNo = "123-456-7890";
        userDetails.setUserPhoneNo(expectedPhoneNo);
        
        assertEquals(expectedPhoneNo, userDetails.getUserPhoneNo(), "Phone number should match.");
    }

    @Test
    void testSetAndGetIsSubscribed() {
        userDetails.setIsSubscribed(true);
        
        assertTrue(userDetails.getIsSubscribed(), "Subscription status should be true.");
        
        userDetails.setIsSubscribed(false);
        
        assertFalse(userDetails.getIsSubscribed(), "Subscription status should be false.");
    }

    @Test
    void testSetAndGetUserRole() {
        userDetails.setUserRole(userRole);
        
        assertEquals(userRole, userDetails.getUserRole(), "User role should match.");
    }
}

