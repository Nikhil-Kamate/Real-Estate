package com.crimsonlogic.realestate.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crimsonlogic.realestate.model.Property;
import com.crimsonlogic.realestate.model.RentalDetails;

@Repository
public interface RentalDetailsRepository extends JpaRepository<RentalDetails, Long>{
	Optional<RentalDetails> findByProperty(Property property);
}
