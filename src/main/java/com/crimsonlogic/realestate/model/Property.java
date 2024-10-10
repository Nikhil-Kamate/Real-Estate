package com.crimsonlogic.realestate.model;

import java.util.List;

import com.crimsonlogic.realestate.util.IdGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Property {

	@Id
	@Column (name="property_id")
	private String propertyId;

	@ManyToOne
	@JoinColumn(name = "owner_id", nullable = false)
	private UserDetails owner; // Reference to UserDetails entity

	@Column(name = "list_as", nullable = false)
	private String listAs; // "owner" or "broker"

	@Column(name = "broker_licence")
	private String brokerLicence; // Only applicable if user is a broker

	@Column(name = "property_type", nullable = false)
	private String propertyType;

	@Column(name = "bhk_type")
	private String bhkType;

	@Column(name = "built_up_area", nullable = false)
	private Float builtUpArea;

	@Column(name = "property_status", nullable = false)
	private String propertyStatus = "Available"; // Available, NotAvailable, etc.

	@Column(name = "approval_status", nullable = false)
	private String approvalStatus = "Pending"; // Approved, Rejected, Pending, etc.
	
	@Column(name ="image_path")
	private String propertyimagePath;
	
	@ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "property_amenities", joinColumns = @JoinColumn(name = "property_id"))
    @Column(name = "amenity")
    private List<String> amenities; // List of amenities

	@Column(name = "property_description", nullable = false)
	private String propertyDescription;
	
	@Column(name = "listing_type")
    private String listingType;

	@OneToOne(mappedBy = "property", cascade = CascadeType.ALL,  orphanRemoval = true)
	@JsonIgnore
	private RentalDetails rentalDetails;

	@OneToOne(mappedBy = "property", cascade = CascadeType.ALL,  orphanRemoval = true)
	@JsonIgnore
	private SellDetails sellDetails;

	@OneToOne(mappedBy = "property", cascade = CascadeType.ALL,  orphanRemoval = true)
	@JsonIgnore
	private PropertyLocation location;

	@PrePersist
	public void generateId() {
		this.propertyId = "PID-" + IdGenerator.generateRandomID();
	}

	public String getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	public UserDetails getOwner() {
		return owner;
	}

	public void setOwner(UserDetails owner) {
		this.owner = owner;
	}

	public String getListAs() {
		return listAs;
	}

	public void setListAs(String listAs) {
		this.listAs = listAs;
	}

	public String getBrokerLicence() {
		return brokerLicence;
	}

	public void setBrokerLicence(String brokerLicence) {
		this.brokerLicence = brokerLicence;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public String getBhkType() {
		return bhkType;
	}

	public void setBhkType(String bhkType) {
		this.bhkType = bhkType;
	}

	public Float getBuiltUpArea() {
		return builtUpArea;
	}

	public void setBuiltUpArea(Float builtUpArea) {
		this.builtUpArea = builtUpArea;
	}

	public String getPropertyStatus() {
		return propertyStatus;
	}

	public void setPropertyStatus(String propertyStatus) {
		this.propertyStatus = propertyStatus;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getPropertyimagePath() {
		return propertyimagePath;
	}

	public void setPropertyimagePath(String propertyimagePath) {
		this.propertyimagePath = propertyimagePath;
	}

	public List<String> getAmenities() {
		return amenities;
	}

	public void setAmenities(List<String> amenities) {
		this.amenities = amenities;
	}

	public String getPropertyDescription() {
		return propertyDescription;
	}

	public void setPropertyDescription(String propertyDescription) {
		this.propertyDescription = propertyDescription;
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

	public String getListingType() {
		return listingType;
	}

	public void setListingType(String listingType) {
		this.listingType = listingType;
	}
	
	
}
