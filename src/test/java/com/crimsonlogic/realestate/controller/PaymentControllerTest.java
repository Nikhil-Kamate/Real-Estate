package com.crimsonlogic.realestate.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.crimsonlogic.realestate.dto.PaymentRequest;
import com.crimsonlogic.realestate.service.PaymentService;

public class PaymentControllerTest {

    @InjectMocks
    private PaymentController paymentController;

    @Mock
    private PaymentService paymentService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(paymentController).build();
    }

    @Test
    void testProcessPaymentSuccessful() throws Exception {
        // Arrange
        String userId = "UID-123";
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setPaymentAmount(100.0);
        paymentRequest.setSubscriptionExpireDate(LocalDate.now().plusDays(30));

        when(paymentService.processPayment(userId, 100.0, paymentRequest.getSubscriptionExpireDate()))
                .thenReturn("Payment successful and subscription activated!");

        // Act & Assert
        mockMvc.perform(post("/api/payment/process/{userId}", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"paymentAmount\":100.0,\"subscriptionExpireDate\":\"2024-11-06\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Payment successful and subscription activated!"));
    }

    @Test
    void testProcessPaymentUserNotFound() throws Exception {
        // Arrange
        String userId = "UID-456";
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setPaymentAmount(100.0);
        paymentRequest.setSubscriptionExpireDate(LocalDate.now().plusDays(30));

        when(paymentService.processPayment(userId, 100.0, paymentRequest.getSubscriptionExpireDate()))
                .thenReturn("User not found.");

        // Act & Assert
        mockMvc.perform(post("/api/payment/process/{userId}", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"paymentAmount\":100.0,\"subscriptionExpireDate\":\"2024-11-06\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("User not found."));
    }
}
