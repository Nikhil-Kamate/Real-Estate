package com.crimsonlogic.realestate.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.crimsonlogic.realestate.model.State;

import static org.junit.jupiter.api.Assertions.*;

class StateTest {

    private State state;

    @BeforeEach
    void setUp() {
        state = new State();
    }

    @Test
    void testSetId() {
        Long expectedId = 1L;
        state.setId(expectedId);
        assertEquals(expectedId, state.getId(), "The ID should match the expected value.");
    }

    @Test
    void testSetName() {
        String expectedName = "California";
        state.setName(expectedName);
        assertEquals(expectedName, state.getName(), "The name should match the expected value.");
    }

    @Test
    void testGetNameWhenNotSet() {
        assertNull(state.getName(), "The name should be null when not set.");
    }

    @Test
    void testGetIdWhenNotSet() {
        assertNull(state.getId(), "The ID should be null when not set.");
    }

    @Test
    void testNameNotNull() {
        state.setName("Texas");
        assertNotNull(state.getName(), "The name should not be null after setting it.");
    }
}

