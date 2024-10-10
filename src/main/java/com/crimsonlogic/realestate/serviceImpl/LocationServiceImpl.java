package com.crimsonlogic.realestate.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crimsonlogic.realestate.exception.ResourceNotFoundException;
import com.crimsonlogic.realestate.model.City;
import com.crimsonlogic.realestate.model.State;
import com.crimsonlogic.realestate.repository.CityRepository;
import com.crimsonlogic.realestate.repository.StateRepository;
import com.crimsonlogic.realestate.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService {
	
	 private static final Logger logger = LoggerFactory.getLogger(LocationServiceImpl.class);

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<State> getAllStates() {
    	logger.info("Fetching all states");
        return stateRepository.findAll();
    }
    
    @Override
    public State addState(State state) {
    	logger.info("Adding new state: {}", state.getName());
        return stateRepository.save(state);
    }

    @Override
    public City addCity(City city) {
    	logger.info("Adding new city: {}", city.getName());
        return cityRepository.save(city);
    }
    
    @Override
    public List<City> getCitiesByState(String stateName) throws ResourceNotFoundException {
    	
    	 List<City> cities = cityRepository.findByStateName(stateName);
         if (cities.isEmpty()) {
             logger.warn("No cities found for state: {}", stateName);
             throw new ResourceNotFoundException("No cities found for state: " + stateName);
         }
         return cities;
    }

    @Override
    public State getStateByCity(String cityName) throws ResourceNotFoundException {
    	 
         City city = cityRepository.findByName(cityName);
         if (city == null) {
             logger.warn("City not found: {}", cityName);
             throw new ResourceNotFoundException("City not found: " + cityName);
         }
         State state = stateRepository.findByName(city.getStateName());
         if (state == null) {
             logger.warn("State not found for city: {}", cityName);
             throw new ResourceNotFoundException("State not found for city: " + cityName);
         }
         return state;
     
    }
}