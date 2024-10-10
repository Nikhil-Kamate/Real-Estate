package com.crimsonlogic.realestate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crimsonlogic.realestate.model.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
	
	List<City> findByStateName(String stateName);

	City findByName(String name);
}
