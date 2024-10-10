package com.crimsonlogic.realestate.dto;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import com.crimsonlogic.realestate.model.Property;
import com.crimsonlogic.realestate.model.PropertyLocation;
import com.crimsonlogic.realestate.model.SellDetails;

public class SellPropertyDTO {

	private Property property;
	private SellDetails sellDetails = new SellDetails();
	private PropertyLocation location = new PropertyLocation();
	private MultipartFile imageFile;
	private LocalDate availableFrom;
	
	public LocalDate getAvailableFrom() {
		return availableFrom;
	}

	public void setAvailableFrom(LocalDate availableFrom) {
		this.availableFrom = availableFrom;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public SellDetails getSellDetails() {
		return sellDetails;
	}

	public void setSellDetails(SellDetails sellDetails) {
		this.sellDetails = sellDetails;
	}

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}

	public PropertyLocation getLocation() {
		return location;
	}

	public void setLocation(PropertyLocation location) {
		this.location = location;
	}
	

}
