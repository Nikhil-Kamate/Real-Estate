package com.crimsonlogic.realestate.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crimsonlogic.realestate.model.UserAuthentication;
import com.crimsonlogic.realestate.model.UserDetails;

@Repository
public interface UserAuthenticationRepository extends JpaRepository<UserAuthentication, Long> {
	
	UserAuthentication findByEmail(String email);
	
	boolean existsByEmail(String email);
	
	Optional<UserAuthentication> findByUserDetails(UserDetails userDetails);
}
