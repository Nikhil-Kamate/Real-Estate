package com.crimsonlogic.realestate.service;

import java.util.List;

import com.crimsonlogic.realestate.dto.PropertyDetailsDTO;
import com.crimsonlogic.realestate.exception.PropertyNotFoundException;
import com.crimsonlogic.realestate.model.Property;

public interface PropertyDisplayService {
	
	//Get all the Property
	List<Property> getAllProperties();
	
	//Property with all details
	PropertyDetailsDTO getPropertyWithDetails(String propertyId) throws PropertyNotFoundException;
	
	//get approved property list
	List<Property> getApprovedProperties();
	
	//Approve property
	void approveProperty(String propertyId) throws PropertyNotFoundException;
	
	//Reject property 
    void rejectProperty(String propertyId) throws PropertyNotFoundException;
    
    //get property by user id
    List<Property> getPropertiesByUserId(String userId);
    
    //get approved property 
    List<Property> getApprovedProperties(String userId);
    
    //excluding current user get approved properties
    List<PropertyDetailsDTO> getApprovedPropertiesExcludingUser(String userId);

}
