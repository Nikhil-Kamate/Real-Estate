package com.crimsonlogic.realestate.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.crimsonlogic.realestate.dto.PropertyDetailsDTO;
import com.crimsonlogic.realestate.exception.PropertyNotFoundException;
import com.crimsonlogic.realestate.model.Property;
import com.crimsonlogic.realestate.model.PropertyLocation;
import com.crimsonlogic.realestate.model.RentalDetails;
import com.crimsonlogic.realestate.model.SellDetails;
import com.crimsonlogic.realestate.repository.PropertyRepository;

public class PropertyDisplayServiceImplTest {

    @Mock
    private PropertyRepository propertyRepository;

    @InjectMocks
    private PropertyDisplayServiceImpl propertyDisplayService;

    private Property mockProperty;
    private PropertyDetailsDTO mockPropertyDetailsDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        
        // Initialize mock property
        mockProperty = new Property();
        mockProperty.setPropertyId("1");
        mockProperty.setApprovalStatus("Approved");

        // Initialize mock property details
        PropertyLocation location = new PropertyLocation();
        RentalDetails rentalDetails = new RentalDetails();
        SellDetails sellDetails = new SellDetails();
        mockPropertyDetailsDTO = new PropertyDetailsDTO(mockProperty, rentalDetails, sellDetails, location);
    }

    @Test
    public void testGetAllProperties() {
        // Arrange
        when(propertyRepository.findAll()).thenReturn(List.of(mockProperty));

        // Act
        List<Property> properties = propertyDisplayService.getAllProperties();

        // Assert
        assertEquals(1, properties.size());
        assertEquals(mockProperty, properties.get(0));
        verify(propertyRepository).findAll();
    }

    @Test
    public void testGetPropertyWithDetailsSuccess() throws PropertyNotFoundException {
        // Arrange
        when(propertyRepository.findById(anyString())).thenReturn(Optional.of(mockProperty));

        // Act
        PropertyDetailsDTO propertyDetailsDTO = propertyDisplayService.getPropertyWithDetails("1");

        // Assert
        assertEquals(mockProperty, propertyDetailsDTO.getProperty());
        verify(propertyRepository).findById("1");
    }

    @Test
    public void testGetPropertyWithDetailsFailure() {
        // Arrange
        when(propertyRepository.findById(anyString())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(PropertyNotFoundException.class, () -> {
            propertyDisplayService.getPropertyWithDetails("1");
        });
        verify(propertyRepository).findById("1");
    }

    @Test
    public void testGetApprovedProperties() {
        // Arrange
        when(propertyRepository.findByApprovalStatus("Approved")).thenReturn(List.of(mockProperty));

        // Act
        List<Property> approvedProperties = propertyDisplayService.getApprovedProperties();

        // Assert
        assertEquals(1, approvedProperties.size());
        verify(propertyRepository).findByApprovalStatus("Approved");
    }

    @Test
    public void testApprovePropertySuccess() throws PropertyNotFoundException {
        // Arrange
        when(propertyRepository.findById(anyString())).thenReturn(Optional.of(mockProperty));

        // Act
        propertyDisplayService.approveProperty("1");

        // Assert
        assertEquals("Approved", mockProperty.getApprovalStatus());
        verify(propertyRepository).save(mockProperty);
    }

    @Test
    public void testApprovePropertyFailure() {
        // Arrange
        when(propertyRepository.findById(anyString())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(PropertyNotFoundException.class, () -> {
            propertyDisplayService.approveProperty("1");
        });
        verify(propertyRepository).findById("1");
    }

    @Test
    public void testRejectPropertySuccess() throws PropertyNotFoundException {
        // Arrange
        when(propertyRepository.findById(anyString())).thenReturn(Optional.of(mockProperty));

        // Act
        propertyDisplayService.rejectProperty("1");

        // Assert
        assertEquals("Rejected", mockProperty.getApprovalStatus());
        verify(propertyRepository).save(mockProperty);
    }

    @Test
    public void testRejectPropertyFailure() {
        // Arrange
        when(propertyRepository.findById(anyString())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(PropertyNotFoundException.class, () -> {
            propertyDisplayService.rejectProperty("1");
        });
        verify(propertyRepository).findById("1");
    }

    @Test
    public void testGetPropertiesByUserId() {
        // Arrange
        when(propertyRepository.findByOwner_UserdetailsId(anyString())).thenReturn(List.of(mockProperty));

        // Act
        List<Property> properties = propertyDisplayService.getPropertiesByUserId("user1");

        // Assert
        assertEquals(1, properties.size());
        assertEquals(mockProperty, properties.get(0));
        verify(propertyRepository).findByOwner_UserdetailsId("user1");
    }

    @Test
    public void testGetApprovedPropertiesExcludingUser() {
        // Arrange
        when(propertyRepository.findByApprovalStatusAndOwner_UserdetailsIdNot(anyString(), anyString()))
                .thenReturn(List.of(mockProperty));

        // Act
        List<PropertyDetailsDTO> approvedProperties = propertyDisplayService.getApprovedPropertiesExcludingUser("user1");

        // Assert
        assertEquals(1, approvedProperties.size());
        assertEquals(mockProperty, approvedProperties.get(0).getProperty());
        verify(propertyRepository).findByApprovalStatusAndOwner_UserdetailsIdNot("Approved", "user1");
    }
}
