package com.crimsonlogic.realestate.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.crimsonlogic.realestate.model.City;

import static org.junit.jupiter.api.Assertions.*;

class CityTest {

    private City city;

    @BeforeEach
    void setUp() {
        city = new City();
    }

    @Test
    void testSetId() {
        Long expectedId = 1L;
        city.setId(expectedId);
        assertEquals(expectedId, city.getId(), "The ID should match the expected value.");
    }

    @Test
    void testSetName() {
        String expectedName = "Los Angeles";
        city.setName(expectedName);
        assertEquals(expectedName, city.getName(), "The name should match the expected value.");
    }

    @Test
    void testSetStateName() {
        String expectedStateName = "California";
        city.setStateName(expectedStateName);
        assertEquals(expectedStateName, city.getStateName(), "The state name should match the expected value.");
    }

    @Test
    void testGetNameWhenNotSet() {
        assertNull(city.getName(), "The name should be null when not set.");
    }

    @Test
    void testGetStateNameWhenNotSet() {
        assertNull(city.getStateName(), "The state name should be null when not set.");
    }

    @Test
    void testGetIdWhenNotSet() {
        assertNull(city.getId(), "The ID should be null when not set.");
    }

    @Test
    void testNameNotNull() {
        city.setName("San Francisco");
        assertNotNull(city.getName(), "The name should not be null after setting it.");
    }

    @Test
    void testStateNameNotNull() {
        city.setStateName("Texas");
        assertNotNull(city.getStateName(), "The state name should not be null after setting it.");
    }
}

