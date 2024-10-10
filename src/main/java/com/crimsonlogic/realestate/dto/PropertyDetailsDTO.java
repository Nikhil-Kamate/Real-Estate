package com.crimsonlogic.realestate.dto;

import com.crimsonlogic.realestate.model.Property;
import com.crimsonlogic.realestate.model.PropertyLocation;
import com.crimsonlogic.realestate.model.RentalDetails;
import com.crimsonlogic.realestate.model.SellDetails;

public class PropertyDetailsDTO {

	private Property property;
	private RentalDetails rentalDetails;
	private SellDetails sellDetails;
	private PropertyLocation location;
	
	 public PropertyDetailsDTO() {}

	    public PropertyDetailsDTO(Property property, RentalDetails rentalDetails, SellDetails sellDetails, PropertyLocation location) {
	        this.property = property;
	        this.rentalDetails = rentalDetails;
	        this.sellDetails = sellDetails;
	        this.location = location;
	    }

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

		public SellDetails getSellDetails() {
			return sellDetails;
		}

		public void setSellDetails(SellDetails sellDetails) {
			this.sellDetails = sellDetails;
		}

		public PropertyLocation getLocation() {
			return location;
		}

		public void setLocation(PropertyLocation location) {
			this.location = location;
		}
	    
	    

}
