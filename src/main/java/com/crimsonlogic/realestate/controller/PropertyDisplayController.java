package com.crimsonlogic.realestate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crimsonlogic.realestate.dto.PropertyDetailsDTO;
import com.crimsonlogic.realestate.exception.PropertyNotFoundException;
import com.crimsonlogic.realestate.exception.ResourceNotFoundException;
import com.crimsonlogic.realestate.model.Property;
import com.crimsonlogic.realestate.service.PropertyDisplayService;

@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("/api/propertydisplay")
public class PropertyDisplayController {

	@Autowired
	private PropertyDisplayService propdisSer;

	@GetMapping("/all")
	public ResponseEntity<List<Property>> getAllProperties() {
		List<Property> properties = propdisSer.getAllProperties();
		return ResponseEntity.ok(properties != null ? properties : List.of());
	}

	@GetMapping("/property/details/{propertyId}")
	public ResponseEntity<PropertyDetailsDTO> getPropertyWithDetails(@PathVariable String propertyId)
	        throws ResourceNotFoundException, PropertyNotFoundException {
	    // Fetch all details for the property
	    PropertyDetailsDTO propertyDetailsDTO = propdisSer.getPropertyWithDetails(propertyId);

	    // Return the DTO as a response
	    return ResponseEntity.ok(propertyDetailsDTO);
	}


	@GetMapping("/approved/{userId}")
	public ResponseEntity<List<Property>> getApprovedProperties(@PathVariable String userId) {
		List<Property> approvedProperties = propdisSer.getApprovedProperties(userId);
		return ResponseEntity.ok(approvedProperties != null ? approvedProperties : List.of());
	}

	@PostMapping("/approve/{id}")
	public ResponseEntity<Void> approveProperty(@PathVariable String id) throws PropertyNotFoundException {
		propdisSer.approveProperty(id);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/reject/{id}")
	public ResponseEntity<Void> rejectProperty(@PathVariable String id) throws PropertyNotFoundException {
		propdisSer.rejectProperty(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<List<Property>> getPropertiesByUserId(@PathVariable String id) {
		List<Property> properties = propdisSer.getPropertiesByUserId(id);
		return ResponseEntity.ok(properties != null ? properties : List.of());
	}
	
	 @GetMapping("/approved/exclude/{userId}")
	    public ResponseEntity<List<PropertyDetailsDTO>> getApprovedPropertiesExcludingUser(@PathVariable String userId) {
	        List<PropertyDetailsDTO> approvedProperties = propdisSer.getApprovedPropertiesExcludingUser(userId);

	        if (approvedProperties.isEmpty()) {
	            return ResponseEntity.noContent().build();
	        }

	        return ResponseEntity.ok(approvedProperties);
	    }

}
