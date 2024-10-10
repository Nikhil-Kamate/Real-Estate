package com.crimsonlogic.realestate.dto;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import com.crimsonlogic.realestate.model.Property;
import com.crimsonlogic.realestate.model.PropertyLocation;
import com.crimsonlogic.realestate.model.RentalDetails;

public class RentPropertyDTO {
	
	private Property property;
    private RentalDetails rentalDetails = new RentalDetails();
    private PropertyLocation location  = new PropertyLocation();
    private MultipartFile imageFile;
    private LocalDate availableFrom;
    
   
	public Property getProperty() {
		return property;
	}
	public void setProperty(Property property) {
		this.property = property;
	}
	public RentalDetails getRentalDetails() {
		return rentalDetails;
	}
	public void setRentalDetails(RentalDetails rentalDetails) {
		this.rentalDetails = rentalDetails;
	}
	public PropertyLocation getLocation() {
		return location;
	}
	public void setLocation(PropertyLocation location) {
		this.location = location;
	}
	public MultipartFile getImageFile() {
		return imageFile;
	}
	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}
	public LocalDate getAvailableFrom() {
		return availableFrom;
	}
	public void setAvailableFrom(LocalDate availableFrom) {
		this.availableFrom = availableFrom;
	}
    
    

}
