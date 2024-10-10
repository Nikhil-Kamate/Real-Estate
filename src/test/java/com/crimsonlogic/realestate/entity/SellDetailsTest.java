package com.crimsonlogic.realestate.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.crimsonlogic.realestate.model.Property;
import com.crimsonlogic.realestate.model.SellDetails;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SellDetailsTest {

    private SellDetails sellDetails;

    @BeforeEach
    void setUp() {
        sellDetails = new SellDetails();
    }

    @Test
    void testSetAndGetSellDetailsId() {
        sellDetails.setSellDetailsId(1L);
        assertEquals(1L, sellDetails.getSellDetailsId());
    }

    @Test
    void testSetAndGetProperty() {
        Property property = new Property();
        sellDetails.setProperty(property);
        assertEquals(property, sellDetails.getProperty());
    }

    @Test
    void testSetAndGetExpectedPrice() {
        sellDetails.setExpectedPrice(250000.00);
        assertEquals(250000.00, sellDetails.getExpectedPrice());
    }

    @Test
    void testSetAndGetAvailableFrom() {
        LocalDate date = LocalDate.of(2023, 10, 1);
        sellDetails.setAvailableFrom(date);
        assertEquals(date, sellDetails.getAvailableFrom());
    }

    @Test
    void testSetAndGetFurnishingStatus() {
        sellDetails.setFurnishingStatus("fully");
        assertEquals("fully", sellDetails.getFurnishingStatus());
    }

    @Test
    void testSetAndGetParkingDetails() {
        sellDetails.setParkingDetails("bike");
        assertEquals("bike", sellDetails.getParkingDetails());
    }
}
