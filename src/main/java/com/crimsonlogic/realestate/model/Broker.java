package com.crimsonlogic.realestate.model;

import com.crimsonlogic.realestate.util.IdGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Broker {

	@Id
	@Column(name = "broker_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long brokerId;

	@OneToOne
	@JoinColumn(name = "userdetails_id", referencedColumnName = "userdetails_id")
	@JsonIgnore
	private UserDetails userDetails;

	@Column(name = "brokerage_rate")
	private Double brokerageRate;

	@Column(name = "status", length=50)
	private String status = "Pending";

	@Column(name = "brokerage_license_id", unique = true, length=50) 
    private String brokerageLicenseId; 

    @PrePersist
    public void generateId() {
        this.brokerageLicenseId = "BOKLIC-" + IdGenerator.generateRandomID();
    }

	public Long getBrokerId() {
		return brokerId;
	}

	public void setBrokerId(Long brokerId) {
		this.brokerId = brokerId;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public Double getBrokerageRate() {
		return brokerageRate;
	}

	public void setBrokerageRate(Double brokerageRate) {
		this.brokerageRate = brokerageRate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBrokerageLicenseId() {
		return brokerageLicenseId;
	}

	public void setBrokerageLicenseId(String brokerageLicenseId) {
		this.brokerageLicenseId = brokerageLicenseId;
	}
	
	
}
