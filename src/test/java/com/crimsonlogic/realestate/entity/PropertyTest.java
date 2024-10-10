package com.crimsonlogic.realestate.entity;

import com.crimsonlogic.realestate.model.Property;
import com.crimsonlogic.realestate.model.PropertyLocation;
import com.crimsonlogic.realestate.model.RentalDetails;
import com.crimsonlogic.realestate.model.SellDetails;
import com.crimsonlogic.realestate.model.UserDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PropertyTest {

    private Property property;

    @BeforeEach
    void setUp() {
        property = new Property();
    }

    @Test
    void testGenerateId() {
        property.generateId();
        assertNotNull(property.getPropertyId());
        assertTrue(property.getPropertyId().startsWith("PID-"));
    }

    @Test
    void testSetAndGetOwner() {
        UserDetails owner = new UserDetails();
        property.setOwner(owner);
        assertEquals(owner, property.getOwner());
    }

    @Test
    void testSetAndGetListAs() {
        property.setListAs("broker");
        assertEquals("broker", property.getListAs());
    }

    @Test
    void testSetAndGetBrokerLicence() {
        property.setBrokerLicence("BR123");
        assertEquals("BR123", property.getBrokerLicence());
    }

    @Test
    void testSetAndGetPropertyType() {
        property.setPropertyType("Apartment");
        assertEquals("Apartment", property.getPropertyType());
    }

    @Test
    void testSetAndGetBhkType() {
        property.setBhkType("3 BHK");
        assertEquals("3 BHK", property.getBhkType());
    }

    @Test
    void testSetAndGetBuiltUpArea() {
        property.setBuiltUpArea(1200.5f);
        assertEquals(1200.5f, property.getBuiltUpArea());
    }

    @Test
    void testSetAndGetPropertyStatus() {
        property.setPropertyStatus("NotAvailable");
        assertEquals("NotAvailable", property.getPropertyStatus());
    }

    @Test
    void testSetAndGetApprovalStatus() {
        property.setApprovalStatus("Approved");
        assertEquals("Approved", property.getApprovalStatus());
    }

    @Test
    void testSetAndGetPropertyImagePath() {
        property.setPropertyimagePath("/images/property.jpg");
        assertEquals("/images/property.jpg", property.getPropertyimagePath());
    }

    @Test
    void testSetAndGetAmenities() {
        property.setAmenities(Arrays.asList("Pool", "Gym", "Garden"));
        assertEquals(Arrays.asList("Pool", "Gym", "Garden"), property.getAmenities());
    }

    @Test
    void testSetAndGetPropertyDescription() {
        property.setPropertyDescription("Beautiful apartment with sea view.");
        assertEquals("Beautiful apartment with sea view.", property.getPropertyDescription());
    }

    @Test
    void testSetAndGetListingType() {
        property.setListingType("Rent");
        assertEquals("Rent", property.getListingType());
    }

    @Test
    void testSetAndGetRentalDetails() {
        RentalDetails rentalDetails = new RentalDetails();
        property.setRentalDetails(rentalDetails);
        assertEquals(rentalDetails, property.getRentalDetails());
    }

    @Test
    void testSetAndGetSellDetails() {
        SellDetails sellDetails = new SellDetails();
        property.setSellDetails(sellDetails);
        assertEquals(sellDetails, property.getSellDetails());
    }

    @Test
    void testSetAndGetLocation() {
        PropertyLocation location = new PropertyLocation();
        property.setLocation(location);
        assertEquals(location, property.getLocation());
    }
}
