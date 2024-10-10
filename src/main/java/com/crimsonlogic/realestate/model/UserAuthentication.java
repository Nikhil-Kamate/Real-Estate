package com.crimsonlogic.realestate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class UserAuthentication {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_auth_seq")
	@SequenceGenerator(name = "user_auth_seq", sequenceName = "user_auth_seq", allocationSize = 1)
	private Long userId;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "password")
	private String password;

	@OneToOne
	@JoinColumn(name = "user_details_id")
	private UserDetails userDetails;

	@ManyToOne
	@JoinColumn(name = "user_role_id") // Reference to UserRole
	@JsonIgnore
	private UserRole userRole; // Link to UserRole for authentication checks
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	
	
}
