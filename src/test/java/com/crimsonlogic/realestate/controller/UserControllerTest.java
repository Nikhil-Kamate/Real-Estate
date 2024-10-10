package com.crimsonlogic.realestate.controller;

import com.crimsonlogic.realestate.dto.UserRegistrationDTO;
import com.crimsonlogic.realestate.exception.UserRegistrationException;
import com.crimsonlogic.realestate.model.UserAuthentication;
import com.crimsonlogic.realestate.model.UserDetails;
import com.crimsonlogic.realestate.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private UserRegistrationDTO registrationDTO;
    private UserDetails userDetails;
    private UserAuthentication userAuthentication;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userDetails = new UserDetails();
        userAuthentication = new UserAuthentication();
        registrationDTO = new UserRegistrationDTO();
        registrationDTO.setUserDetails(userDetails);
        registrationDTO.setUserAuth(userAuthentication);
    }

    @Test
    void testRegisterUserSuccess() throws UserRegistrationException {
        when(userService.registerUser(userDetails, userAuthentication)).thenReturn(userDetails);

        ResponseEntity<UserDetails> response = userController.registerUser(registrationDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userDetails, response.getBody());
        verify(userService).registerUser(userDetails, userAuthentication);
    }

    @Test
    void testRegisterUserThrowsException() throws UserRegistrationException {
        when(userService.registerUser(userDetails, userAuthentication)).thenThrow(new UserRegistrationException("User with this email already exists."));

        UserRegistrationException exception = assertThrows(UserRegistrationException.class, () -> {
            userController.registerUser(registrationDTO);
        });

        assertEquals("User with this email already exists.", exception.getMessage());
        verify(userService).registerUser(userDetails, userAuthentication);
    }
}
