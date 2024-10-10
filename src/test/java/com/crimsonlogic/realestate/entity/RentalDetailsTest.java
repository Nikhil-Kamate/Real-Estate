package com.crimsonlogic.realestate.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.crimsonlogic.realestate.model.Property;
import com.crimsonlogic.realestate.model.RentalDetails;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RentalDetailsTest {

    private RentalDetails rentalDetails;

    @BeforeEach
    void setUp() {
        rentalDetails = new RentalDetails();
    }

    @Test
    void testSetAndGetRentalDetailsId() {
        rentalDetails.setRentalDetailsId(1L);
        assertEquals(1L, rentalDetails.getRentalDetailsId());
    }

    @Test
    void testSetAndGetProperty() {
        Property property = new Property();
        rentalDetails.setProperty(property);
        assertEquals(property, rentalDetails.getProperty());
    }

    @Test
    void testSetAndGetExpectedRent() {
        rentalDetails.setExpectedRent(1500.00);
        assertEquals(1500.00, rentalDetails.getExpectedRent());
    }

    @Test
    void testSetAndGetMonthlyRent() {
        rentalDetails.setMonthlyRent(1200.00);
        assertEquals(1200.00, rentalDetails.getMonthlyRent());
    }

    @Test
    void testSetAndGetAvailableFrom() {
        LocalDate date = LocalDate.of(2023, 10, 1);
        rentalDetails.setAvailableFrom(date);
        assertEquals(date, rentalDetails.getAvailableFrom());
    }

    @Test
    void testSetAndGetFurnishingStatus() {
        rentalDetails.setFurnishingStatus("fully");
        assertEquals("fully", rentalDetails.getFurnishingStatus());
    }

    @Test
    void testSetAndGetParkingDetails() {
        rentalDetails.setParkingDetails("both");
        assertEquals("both", rentalDetails.getParkingDetails());
    }
}

