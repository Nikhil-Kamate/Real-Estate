package com.crimsonlogic.realestate.service;

import java.util.List;

import com.crimsonlogic.realestate.exception.ResourceNotFoundException;
import com.crimsonlogic.realestate.model.City;
import com.crimsonlogic.realestate.model.State;

public interface LocationService {
	
	//add state
	State addState(State state);
	
	//List the state
	List<State> getAllStates();
	
	//add city
	City addCity(City city);
	
	//list the city based on state
	List<City> getCitiesByState(String stateName) throws ResourceNotFoundException;
	
	//get state based on city
    State getStateByCity(String cityName) throws ResourceNotFoundException;

}
