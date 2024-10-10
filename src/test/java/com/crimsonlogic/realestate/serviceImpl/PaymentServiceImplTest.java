package com.crimsonlogic.realestate.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.crimsonlogic.realestate.model.Payment;
import com.crimsonlogic.realestate.model.UserDetails;
import com.crimsonlogic.realestate.repository.PaymentRepository;
import com.crimsonlogic.realestate.repository.UserDetailsRepository;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private UserDetailsRepository userRepository;

    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        userDetails = new UserDetails();
        userDetails.setUserdetailsId("UID-123");
        userDetails.setIsSubscribed(false);
    }

    @Test
    void testProcessPaymentSuccessful() {
        // Arrange
        when(userRepository.findById("UID-123")).thenReturn(Optional.of(userDetails));
        
        double paymentAmount = 100.0;
        LocalDate subscriptionExpireDate = LocalDate.now().plusDays(30);

        // Act
        String result = paymentService.processPayment("UID-123", paymentAmount, subscriptionExpireDate);

        // Assert
        assertEquals("Payment successful and subscription activated!", result);
        verify(paymentRepository).save(any(Payment.class)); // Verify payment is saved
        verify(userRepository).save(userDetails); // Verify user is updated
        assertEquals(true, userDetails.getIsSubscribed()); // Verify subscription status is updated
    }

    @Test
    void testProcessPaymentUserNotFound() {
        // Arrange
        when(userRepository.findById("UID-456")).thenReturn(Optional.empty());

        // Act
        String result = paymentService.processPayment("UID-456", 100.0, LocalDate.now().plusDays(30));

        // Assert
        assertEquals("User not found.", result);
        verify(paymentRepository, never()).save(any(Payment.class)); // Ensure payment is not saved
        verify(userRepository, never()).save(any(UserDetails.class)); // Ensure user is not updated
    }
}
