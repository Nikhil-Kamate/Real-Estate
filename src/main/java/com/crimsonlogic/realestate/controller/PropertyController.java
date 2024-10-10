package com.crimsonlogic.realestate.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crimsonlogic.realestate.dto.RentPropertyDTO;
import com.crimsonlogic.realestate.dto.SellPropertyDTO;
import com.crimsonlogic.realestate.exception.PropertyUploadException;
import com.crimsonlogic.realestate.service.PropertyService;

@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("/api/property")
public class PropertyController {
	
	@Autowired
	private PropertyService propertyService;
	
	@PostMapping("/sell")
	public ResponseEntity<String> sellProperty(@ModelAttribute SellPropertyDTO sellPropertyDTO) throws IOException, PropertyUploadException {
		propertyService.sellProperty(sellPropertyDTO);
		return ResponseEntity.ok("Property listed successfully!");
	}

	@PostMapping("/rent")
	public ResponseEntity<String> rentProperty(@ModelAttribute RentPropertyDTO rentPropertyDTO) throws PropertyUploadException {
		propertyService.rentProperty(rentPropertyDTO);
		return ResponseEntity.ok("Property listed for rent successfully!");
	}
}
