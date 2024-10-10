package com.crimsonlogic.realestate.serviceImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import com.crimsonlogic.realestate.dto.RentPropertyDTO;
import com.crimsonlogic.realestate.dto.SellPropertyDTO;
import com.crimsonlogic.realestate.exception.PropertyUploadException;
import com.crimsonlogic.realestate.model.Property;
import com.crimsonlogic.realestate.model.PropertyLocation;
import com.crimsonlogic.realestate.model.RentalDetails;
import com.crimsonlogic.realestate.model.SellDetails;
import com.crimsonlogic.realestate.repository.PropertyLocationRepository;
import com.crimsonlogic.realestate.repository.PropertyRepository;
import com.crimsonlogic.realestate.repository.RentalDetailsRepository;
import com.crimsonlogic.realestate.repository.SellDetailsRepository;

public class PropertyServiceImplTest {

    @Mock
    private PropertyRepository propertyRepository;

    @Mock
    private SellDetailsRepository sellDetailsRepository;

    @Mock
    private RentalDetailsRepository rentalDetailsRepository;

    @Mock
    private PropertyLocationRepository propertyLocationRepository;

    @InjectMocks
    private PropertyServiceImpl propertyService;

    private SellPropertyDTO sellPropertyDTO;
    private RentPropertyDTO rentPropertyDTO;
    private Property mockProperty;
    private SellDetails sellDetails;
    private RentalDetails rentalDetails;
    private PropertyLocation propertyLocation;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize mock property
        mockProperty = new Property();
        mockProperty.setPropertyId("1");
        mockProperty.setListingType("Sell");

        // Initialize mock SellPropertyDTO
        sellPropertyDTO = new SellPropertyDTO();
        sellPropertyDTO.setProperty(mockProperty);

        sellDetails = new SellDetails();
        sellPropertyDTO.setSellDetails(sellDetails);

        propertyLocation = new PropertyLocation();
        sellPropertyDTO.setLocation(propertyLocation);

        // Initialize mock RentPropertyDTO
        rentPropertyDTO = new RentPropertyDTO();
        rentPropertyDTO.setProperty(mockProperty);
        
        rentalDetails = new RentalDetails();
        rentPropertyDTO.setRentalDetails(rentalDetails);

        rentPropertyDTO.setLocation(propertyLocation);
    }

    @Test
    public void testSellProperty() throws PropertyUploadException {
        // Act
        propertyService.sellProperty(sellPropertyDTO);

        // Assert
        verify(propertyRepository).save(mockProperty);
        verify(sellDetailsRepository).save(sellDetails);
        verify(propertyLocationRepository).save(propertyLocation);
    }

    @Test
    public void testRentProperty() throws PropertyUploadException {
        // Act
        propertyService.rentProperty(rentPropertyDTO);

        // Assert
        verify(propertyRepository).save(mockProperty);
        verify(rentalDetailsRepository).save(rentalDetails);
        verify(propertyLocationRepository).save(propertyLocation);
    }

    @Test
    public void testSellPropertyWithImageHandling() throws PropertyUploadException {
        // Arrange
        MultipartFile mockImageFile = null; // skip for image testing, can mock an actual image file in a real scenario
        sellPropertyDTO.setImageFile(mockImageFile);

        // Act
        propertyService.sellProperty(sellPropertyDTO);

        // Assert
        verify(propertyRepository).save(any(Property.class));
        verify(sellDetailsRepository).save(any(SellDetails.class));
        verify(propertyLocationRepository).save(any(PropertyLocation.class));
    }

    @Test
    public void testRentPropertyWithImageHandling() throws PropertyUploadException {
        // Arrange
        MultipartFile mockImageFile = null; // skip for image testing, can mock an actual image file in a real scenario
        rentPropertyDTO.setImageFile(mockImageFile);

        // Act
        propertyService.rentProperty(rentPropertyDTO);

        // Assert
        verify(propertyRepository).save(any(Property.class));
        verify(rentalDetailsRepository).save(any(RentalDetails.class));
        verify(propertyLocationRepository).save(any(PropertyLocation.class));
    }
}
