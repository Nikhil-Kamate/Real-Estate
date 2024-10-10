package com.crimsonlogic.realestate.serviceImpl;

import com.crimsonlogic.realestate.exception.UserRegistrationException;
import com.crimsonlogic.realestate.model.UserAuthentication;
import com.crimsonlogic.realestate.model.UserDetails;
import com.crimsonlogic.realestate.model.UserRole;
import com.crimsonlogic.realestate.repository.UserAuthenticationRepository;
import com.crimsonlogic.realestate.repository.UserDetailsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCrypt;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserDetailsRepository userDetailsRepository;

    @Mock
    private UserAuthenticationRepository userAuthenticationRepository;

    private UserDetails userDetails;
    private UserAuthentication userAuthentication;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userDetails = new UserDetails();
        userAuthentication = new UserAuthentication();
        userAuthentication.setEmail("test@example.com");
        userAuthentication.setPassword("plaintextpassword");
    }

    @Test
    void testRegisterUserSuccess() throws UserRegistrationException {
        // Mock existing email check
        when(userAuthenticationRepository.existsByEmail(userAuthentication.getEmail())).thenReturn(false);
        // Mock hashing
        when(BCrypt.hashpw(anyString(), anyString())).thenReturn("hashedpassword");
        // Mock save behavior
        when(userDetailsRepository.save(any(UserDetails.class))).thenReturn(userDetails);
        when(userAuthenticationRepository.save(any(UserAuthentication.class))).thenReturn(userAuthentication);

        // Call the service method
        UserDetails savedUserDetails = userService.registerUser(userDetails, userAuthentication);

        // Assertions
        assertNotNull(savedUserDetails);
        assertFalse(savedUserDetails.getIsSubscribed());
        assertEquals("hashedpassword", userAuthentication.getPassword());
        verify(userDetailsRepository).save(userDetails);
        verify(userAuthenticationRepository).save(userAuthentication);
    }

    @Test
    void testRegisterUserEmailAlreadyExists() {
        // Mock email already exists case
        when(userAuthenticationRepository.existsByEmail(userAuthentication.getEmail())).thenReturn(true);

        // Call the service method and assert exception
        UserRegistrationException exception = assertThrows(UserRegistrationException.class, () -> {
            userService.registerUser(userDetails, userAuthentication);
        });

        assertEquals("User with this email already exists.", exception.getMessage());
        // Verify no userDetails or userAuthentication are saved
        verify(userDetailsRepository, never()).save(any());
        verify(userAuthenticationRepository, never()).save(any());
    }

    @Test
    void testRegisterUserThrowsExceptionWhenSavingUserDetails() {
        // Mock no email conflict
        when(userAuthenticationRepository.existsByEmail(userAuthentication.getEmail())).thenReturn(false);
        // Mock save failure for userDetails
        when(userDetailsRepository.save(any(UserDetails.class))).thenThrow(new RuntimeException("Database error"));

        // Call the service method and assert exception
        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.registerUser(userDetails, userAuthentication);
        });

        assertEquals("Database error", exception.getMessage());
        // Verify userAuthentication was not saved
        verify(userAuthenticationRepository, never()).save(any());
    }
}
