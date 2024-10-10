package com.crimsonlogic.realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crimsonlogic.realestate.model.UserDetails;

@Repository
public interface  UserDetailsRepository extends JpaRepository<UserDetails, String> {
	
	UserDetails findByUserdetailsId(String userdetailsId);
}
