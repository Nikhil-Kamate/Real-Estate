package com.crimsonlogic.realestate.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crimsonlogic.realestate.model.Property;
import com.crimsonlogic.realestate.model.PropertyLocation;

@Repository
public interface PropertyLocationRepository extends JpaRepository<PropertyLocation, Long> {
	Optional<PropertyLocation> findByProperty(Property property);
}
