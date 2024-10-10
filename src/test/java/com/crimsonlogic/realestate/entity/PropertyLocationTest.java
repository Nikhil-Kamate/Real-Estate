package com.crimsonlogic.realestate.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.crimsonlogic.realestate.model.Property;
import com.crimsonlogic.realestate.model.PropertyLocation;

import static org.junit.jupiter.api.Assertions.*;

class PropertyLocationTest {

    private PropertyLocation propertyLocation;

    @BeforeEach
    void setUp() {
        propertyLocation = new PropertyLocation();
    }

    @Test
    void testSetAndGetLocationId() {
        propertyLocation.setLocationId(1L);
        assertEquals(1L, propertyLocation.getLocationId());
    }

    @Test
    void testSetAndGetProperty() {
        Property property = new Property();
        propertyLocation.setProperty(property);
        assertEquals(property, propertyLocation.getProperty());
    }

    @Test
    void testSetAndGetState() {
        propertyLocation.setState("California");
        assertEquals("California", propertyLocation.getState());
    }

    @Test
    void testSetAndGetCity() {
        propertyLocation.setCity("Los Angeles");
        assertEquals("Los Angeles", propertyLocation.getCity());
    }

    @Test
    void testSetAndGetAddress() {
        propertyLocation.setAddress("123 Main St");
        assertEquals("123 Main St", propertyLocation.getAddress());
    }

    @Test
    void testSetAndGetPincode() {
        propertyLocation.setPincode("90001");
        assertEquals("90001", propertyLocation.getPincode());
    }
}

