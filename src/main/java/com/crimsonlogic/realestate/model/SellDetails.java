package com.crimsonlogic.realestate.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class SellDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sellDetailsId;

	@OneToOne
	@JoinColumn(name = "property_id", nullable = false)
	@JsonIgnore
	private Property property;

	@Column(name = "expected_price")
	private Double expectedPrice;

	@Column(name = "available_from")
	private LocalDate availableFrom;

	@Column(name = "furnishing_status")
	private String furnishingStatus;

	@Column(name = "parking_details")
	private String parkingDetails;

	public Long getSellDetailsId() {
		return sellDetailsId;
	}

	public void setSellDetailsId(Long sellDetailsId) {
		this.sellDetailsId = sellDetailsId;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public Double getExpectedPrice() {
		return expectedPrice;
	}

	public void setExpectedPrice(Double expectedPrice) {
		this.expectedPrice = expectedPrice;
	}

	public LocalDate getAvailableFrom() {
		return availableFrom;
	}

	public void setAvailableFrom(LocalDate availableFrom) {
		this.availableFrom = availableFrom;
	}

	public String getFurnishingStatus() {
		return furnishingStatus;
	}

	public void setFurnishingStatus(String furnishingStatus) {
		this.furnishingStatus = furnishingStatus;
	}

	public String getParkingDetails() {
		return parkingDetails;
	}

	public void setParkingDetails(String parkingDetails) {
		this.parkingDetails = parkingDetails;
	}
	
	
}
