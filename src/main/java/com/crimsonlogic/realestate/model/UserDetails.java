package com.crimsonlogic.realestate.model;

import com.crimsonlogic.realestate.util.IdGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class UserDetails {
	
	@Id
    @Column(name = "userdetails_id")
    private String userdetailsId;

    @Column(name = "userdetails_firstname" , length=100)
    private String userdetailsFirstName;

    @Column(name = "userdetails_lastname", length=100)
    private String userLastName;

    @Column(name = "userdetails_phoneno", length=10)
    private String userPhoneNo;

    @Column(name = "userdetails_issubscribed")
    private Boolean isSubscribed;

    @ManyToOne
    @JoinColumn(name = "user_role_id") 
    @JsonIgnore // Prevent serialization of userRole to avoid circular reference
    private UserRole userRole;

    @PrePersist
    public void generateId() {
        this.userdetailsId = "UID-" + IdGenerator.generateRandomID();
    }

	public String getUserdetailsId() {
		return userdetailsId;
	}

	public void setUserdetailsId(String userdetailsId) {
		this.userdetailsId = userdetailsId;
	}

	public String getUserdetailsFirstName() {
		return userdetailsFirstName;
	}

	public void setUserdetailsFirstName(String userdetailsFirstName) {
		this.userdetailsFirstName = userdetailsFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserPhoneNo() {
		return userPhoneNo;
	}

	public void setUserPhoneNo(String userPhoneNo) {
		this.userPhoneNo = userPhoneNo;
	}

	public Boolean getIsSubscribed() {
		return isSubscribed;
	}

	public void setIsSubscribed(Boolean isSubscribed) {
		this.isSubscribed = isSubscribed;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
    
    
}
