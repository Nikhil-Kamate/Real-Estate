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
public class RentalDetails {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentalDetailsId;

    @OneToOne
    @JoinColumn(name = "property_id", nullable = false)
    @JsonIgnore
    private Property property;

    @Column(name = "expected_rent")
    private Double expectedRent;

    @Column(name = "monthly_rent")
    private Double monthlyRent;

    @Column(name = "available_from")
    private LocalDate availableFrom;

    @Column(name = "furnishing_status", length=50)
    private String furnishingStatus; // fully , semi, none

    @Column(name = "parking_details", length=50)
    private String parkingDetails; // bike,car,both,none

	public Long getRentalDetailsId() {
		return rentalDetailsId;
	}

	public void setRentalDetailsId(Long rentalDetailsId) {
		this.rentalDetailsId = rentalDetailsId;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public Double getExpectedRent() {
		return expectedRent;
	}

	public void setExpectedRent(Double expectedRent) {
		this.expectedRent = expectedRent;
	}

	public Double getMonthlyRent() {
		return monthlyRent;
	}

	public void setMonthlyRent(Double monthlyRent) {
		this.monthlyRent = monthlyRent;
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
