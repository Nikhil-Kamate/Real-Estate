package com.crimsonlogic.realestate.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.crimsonlogic.realestate.dto.LoginRequestDTO;
import com.crimsonlogic.realestate.exception.UserAuthenticationException;
import com.crimsonlogic.realestate.model.UserAuthentication;
import com.crimsonlogic.realestate.repository.UserAuthenticationRepository;

@ExtendWith(MockitoExtension.class)
class LoginServiceImplTest {

    @InjectMocks
    private LoginServiceImpl loginService;

    @Mock
    private UserAuthenticationRepository userAuthenticationRepository;

    private LoginRequestDTO loginRequest;

    @BeforeEach
    void setUp() {
        loginRequest = new LoginRequestDTO();
    }

    @Test
    void testLoginSuccessful() throws UserAuthenticationException {
        // Arrange
        String email = "test@example.com";
        String password = "password123";

        UserAuthentication userAuth = new UserAuthentication();
        userAuth.setEmail(email);
        userAuth.setPassword(password);

        loginRequest.setEmail(email);
        loginRequest.setPassword(password);

        when(userAuthenticationRepository.findByEmail(email)).thenReturn(userAuth);

        // Act
        UserAuthentication result = loginService.login(loginRequest);

        // Assert
        assertEquals(userAuth, result);
        verify(userAuthenticationRepository).findByEmail(email);
    }

    @Test
    void testLoginUserNotFound() {
        // Arrange
        String email = "nonexistent@example.com";
        loginRequest.setEmail(email);

        when(userAuthenticationRepository.findByEmail(email)).thenReturn(null);

        // Act & Assert
        UserAuthenticationException exception = assertThrows(UserAuthenticationException.class, () -> {
            loginService.login(loginRequest);
        });

        assertEquals("User Not Found", exception.getMessage());
    }

    @Test
    void testLoginInvalidPassword() {
        // Arrange
        String email = "test@example.com";
        String correctPassword = "password123";
        String incorrectPassword = "wrongPassword";

        UserAuthentication userAuth = new UserAuthentication();
        userAuth.setEmail(email);
        userAuth.setPassword(correctPassword);

        loginRequest.setEmail(email);
        loginRequest.setPassword(incorrectPassword);

        when(userAuthenticationRepository.findByEmail(email)).thenReturn(userAuth);

        // Act & Assert
        UserAuthenticationException exception = assertThrows(UserAuthenticationException.class, () -> {
            loginService.login(loginRequest);
        });

        assertEquals("Invalid email or password", exception.getMessage());
    }
}

