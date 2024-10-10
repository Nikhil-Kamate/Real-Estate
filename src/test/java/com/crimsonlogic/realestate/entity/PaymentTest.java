package com.crimsonlogic.realestate.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.crimsonlogic.realestate.model.Payment;
import com.crimsonlogic.realestate.model.UserDetails;

class PaymentTest {

    private Payment payment;

    @BeforeEach
    void setUp() {
        payment = new Payment();
    }

    @Test
    void testGetAndSetPaymentId() {
        payment.setPaymentId(1);
        assertEquals(1, payment.getPaymentId());
    }

    @Test
    void testGetAndSetUserDetails() {
        UserDetails userDetails = new UserDetails();
        userDetails.setUserdetailsFirstName("John");
        userDetails.setUserLastName("Doe");

        payment.setUserDetails(userDetails);
        assertEquals(userDetails, payment.getUserDetails());
    }

    @Test
    void testGetAndSetPaymentDate() {
        LocalDate date = LocalDate.now();
        payment.setPaymentDate(date);
        assertEquals(date, payment.getPaymentDate());
    }

    @Test
    void testGetAndSetPaymentAmount() {
        payment.setPaymentAmount(100.0);
        assertEquals(100.0, payment.getPaymentAmount());
    }

    @Test
    void testGetAndSetSubscriptionExpireDate() {
        LocalDate expireDate = LocalDate.now().plusDays(30);
        payment.setSubscriptionExpireDate(expireDate);
        assertEquals(expireDate, payment.getSubscriptionExpireDate());
    }

    @Test
    void testDefaultConstructor() {
        assertNull(payment.getUserDetails());
        assertNull(payment.getPaymentDate());
        assertNull(payment.getPaymentAmount());
        assertNull(payment.getSubscriptionExpireDate());
    }
}

