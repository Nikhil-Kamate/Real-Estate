package com.crimsonlogic.realestate.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crimsonlogic.realestate.dto.PropertyDetailsDTO;
import com.crimsonlogic.realestate.exception.PropertyNotFoundException;
import com.crimsonlogic.realestate.model.Property;
import com.crimsonlogic.realestate.model.PropertyLocation;
import com.crimsonlogic.realestate.model.RentalDetails;
import com.crimsonlogic.realestate.model.SellDetails;
import com.crimsonlogic.realestate.repository.PropertyRepository;
import com.crimsonlogic.realestate.service.PropertyDisplayService;

@Service
public class PropertyDisplayServiceImpl implements PropertyDisplayService {
	
	private static final Logger logger = LoggerFactory.getLogger(PropertyDisplayServiceImpl.class);


	@Autowired
	private PropertyRepository propertyRepository;

	@Override
	public List<Property> getAllProperties() {
		logger.info("Fetching all properties from the database.");
		List<Property> properties = propertyRepository.findAll(); // Fetches all properties
		logger.info("Number of properties fetched: {}", properties.size());
		return properties;
	}
	
	@Override
	public PropertyDetailsDTO getPropertyWithDetails(String propertyId) throws PropertyNotFoundException {
	    Optional<Property> propertyOpt = propertyRepository.findById(propertyId);

	    if (propertyOpt.isPresent()) {
	        Property property = propertyOpt.get();

	        // Fetch related details
	        PropertyLocation location = property.getLocation(); // Location details
	        RentalDetails rentalDetails = property.getRentalDetails(); // Rental details, if applicable
	        SellDetails sellDetails = property.getSellDetails(); // Sell details, if applicable

	        // Create and return the DTO
	        PropertyDetailsDTO propertyDetailsDTO = new PropertyDetailsDTO(property, rentalDetails, sellDetails, location);

	        return propertyDetailsDTO;
	    } else {
	        throw new PropertyNotFoundException("Property not found with ID: " + propertyId);
	    }
	}

	@Override
	public List<Property> getApprovedProperties() {
		System.out.println("Fetching approved properties from the database.");
		List<Property> approvedProperties = propertyRepository.findByApprovalStatus("Approved");

		if (approvedProperties != null) {
			System.out.println("Number of approved properties fetched: " + approvedProperties.size());
		} else {
			System.out.println("No approved properties found.");
		}

		return approvedProperties;
	}
	
	@Override
	public void approveProperty(String propertyId) throws PropertyNotFoundException {
	    Property property = propertyRepository.findById(propertyId)
	    		.orElseThrow(() -> {
	                logger.error("Property not found with ID: {}", propertyId);
	                return new PropertyNotFoundException("Property not found with ID: " + propertyId);
	            });
	    property.setApprovalStatus("Approved");
	    propertyRepository.save(property);
	    logger.info("Property with ID: {} has been approved.", propertyId);
	}

	@Override
	public void rejectProperty(String propertyId) throws PropertyNotFoundException {
	    Property property = propertyRepository.findById(propertyId)
	    		 .orElseThrow(() -> {
	                 logger.error("Property not found with ID: {}", propertyId);
	                 return new PropertyNotFoundException("Property not found with ID: " + propertyId);
	             });
	    property.setApprovalStatus("Rejected");
	    propertyRepository.save(property);
	    logger.info("Property with ID: {} has been rejected.", propertyId);
	}

	@Override
	public List<Property> getPropertiesByUserId(String userId) {
		return propertyRepository.findByOwner_UserdetailsId(userId);
	}
	
	@Override
	public List<Property> getApprovedProperties(String userId) {
		logger.info("Fetching approved properties from the database, excluding user: {}", userId);
	    List<Property> approvedProperties = propertyRepository.findByApprovalStatusAndOwner_UserdetailsIdNot("Approved", userId);
	    logger.info("Number of approved properties fetched: {}", approvedProperties.size());
	    return approvedProperties;
	}
	
	 @Override
	    public List<PropertyDetailsDTO> getApprovedPropertiesExcludingUser(String userId) {
		 logger.info("Fetching approved properties excluding user ID: {}", userId);
	        

	        List<Property> approvedProperties = propertyRepository.findByApprovalStatusAndOwner_UserdetailsIdNot("Approved", userId);

	        return approvedProperties.stream()
	                .map(property -> {
	                    PropertyDetailsDTO dto = new PropertyDetailsDTO();
	                    dto.setProperty(property);
	                    dto.setRentalDetails(property.getRentalDetails());
	                    dto.setSellDetails(property.getSellDetails());
	                    dto.setLocation(property.getLocation());
	                    return dto;
	                })
	                .collect(Collectors.toList());
	    }

}
