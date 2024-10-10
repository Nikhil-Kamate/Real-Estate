package com.crimsonlogic.realestate.entity;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.crimsonlogic.realestate.model.Broker;
import com.crimsonlogic.realestate.model.UserDetails;

public class BrokerTest {

    private Broker broker;

    @BeforeEach
    void setUp() {
        broker = new Broker();
        broker.setBrokerageRate(2.5);
        broker.setStatus("Active");
        broker.setUserDetails(new UserDetails()); // Assuming UserDetails is already set up
    }

    @Test
    void testBrokerCreation() {
        assertNotNull(broker);
        assertEquals(2.5, broker.getBrokerageRate());
        assertEquals("Active", broker.getStatus());
    }

    @Test
    void testBrokerageLicenseIdGenerated() {
        broker.generateId(); // Simulating the @PrePersist behavior
        assertNotNull(broker.getBrokerageLicenseId());
        assertEquals("BOKLIC-", broker.getBrokerageLicenseId().substring(0, 7));
    }
}

