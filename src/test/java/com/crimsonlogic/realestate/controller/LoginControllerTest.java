package com.crimsonlogic.realestate.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.crimsonlogic.realestate.dto.LoginRequestDTO;
import com.crimsonlogic.realestate.exception.UserAuthenticationException;
import com.crimsonlogic.realestate.model.UserAuthentication;
import com.crimsonlogic.realestate.model.UserDetails; 
import com.crimsonlogic.realestate.model.UserRole; 
import com.crimsonlogic.realestate.service.LoginService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {

    @InjectMocks
    private LoginController loginController;

    @Mock
    private LoginService loginService;

    private LoginRequestDTO loginRequest;

    @BeforeEach
    void setUp() {
        loginRequest = new LoginRequestDTO();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("password123");
    }

    @Test
    void testLoginSuccessful() throws UserAuthenticationException {
        // Arrange
        UserAuthentication userAuth = new UserAuthentication();
        userAuth.setEmail("test@example.com");
        userAuth.setPassword("password123");
        
        // Mock UserRole
        UserRole userRole = new UserRole();
        userRole.setRole("USER_ROLE"); // Set the role name

        // Mock UserDetails
        UserDetails userDetails = new UserDetails();
        userDetails.setUserdetailsFirstName("Test");
        userDetails.setUserLastName("User");
        userDetails.setUserPhoneNo("1234567890");

        userAuth.setUserRole(userRole);
        userAuth.setUserDetails(userDetails);

        when(loginService.login(any(LoginRequestDTO.class))).thenReturn(userAuth);

        // Act
        ResponseEntity<Map<String, Object>> response = loginController.login(loginRequest);

        // Assert
        Map<String, Object> expectedResponse = new HashMap<>();
        expectedResponse.put("email", userAuth.getEmail());
        expectedResponse.put("userRole", userAuth.getUserRole().getRole());
        expectedResponse.put("userDetails", userAuth.getUserDetails());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
        verify(loginService).login(loginRequest);
    }

    @Test
    void testLoginUserNotFound() throws UserAuthenticationException {
        // Arrange
        when(loginService.login(any(LoginRequestDTO.class))).thenThrow(new UserAuthenticationException("User Not Found"));

        // Act
        ResponseEntity<Map<String, Object>> response = loginController.login(loginRequest);

        // Assert
        Map<String, Object> expectedResponse = new HashMap<>();
        expectedResponse.put("error", "User Not Found");

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
        verify(loginService).login(loginRequest);
    }

    @Test
    void testLoginInvalidPassword() throws UserAuthenticationException {
        // Arrange
        when(loginService.login(any(LoginRequestDTO.class))).thenThrow(new UserAuthenticationException("Invalid email or password"));

        // Act
        ResponseEntity<Map<String, Object>> response = loginController.login(loginRequest);

        // Assert
        Map<String, Object> expectedResponse = new HashMap<>();
        expectedResponse.put("error", "Invalid email or password");

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
        verify(loginService).login(loginRequest);
    }
}
