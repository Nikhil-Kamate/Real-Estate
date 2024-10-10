package com.crimsonlogic.realestate.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.crimsonlogic.realestate.model.UserDetails;
import com.crimsonlogic.realestate.model.UserRole;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

class UserRoleTest {

    private UserRole userRole;
    private List<UserDetails> userDetails;

    @BeforeEach
    void setUp() {
        userRole = new UserRole();
        userDetails = new ArrayList<>(); // Initializing the userDetails list
    }

    @Test
    void testSetAndGetId() {
        Long expectedId = 1L;
        userRole.setId(expectedId);
        
        assertEquals(expectedId, userRole.getId(), "UserRole ID should match.");
    }

    @Test
    void testSetAndGetRole() {
        String expectedRole = "Admin";
        userRole.setRole(expectedRole);
        
        assertEquals(expectedRole, userRole.getRole(), "UserRole should match.");
    }

    @Test
    void testSetAndGetUserDetails() {
        UserDetails userDetails1 = mock(UserDetails.class);
        this.userDetails.add(userDetails1);
        userRole.setUserDetails(userDetails);
        
        assertEquals(userDetails, userRole.getUserDetails(), "UserDetails list should match.");
    }
}

