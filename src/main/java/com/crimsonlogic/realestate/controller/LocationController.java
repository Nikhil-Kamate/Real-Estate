package com.crimsonlogic.realestate.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crimsonlogic.realestate.exception.ResourceNotFoundException;
import com.crimsonlogic.realestate.model.City;
import com.crimsonlogic.realestate.model.State;
import com.crimsonlogic.realestate.service.LocationService;

@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("/api/locations")
public class LocationController {
	
	private static final Logger logger = LoggerFactory.getLogger(LocationController.class);
    
    @Autowired
    private LocationService locationService;

    @GetMapping("/states")
    public ResponseEntity<List<State>> getAllStates() {
        return ResponseEntity.ok(locationService.getAllStates());
    }

    @PostMapping("/states")
    public ResponseEntity<State> addState(@RequestBody State state) {
        State createdState = locationService.addState(state);
        return new ResponseEntity<>(createdState, HttpStatus.CREATED);
    }

    @PostMapping("/cities")
    public ResponseEntity<City> addCity(@RequestBody City city) {
        City savedCity = locationService.addCity(city);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCity);
    }
    
    @GetMapping("/cities/{stateName}")
    public ResponseEntity<List<City>> getCitiesByState(@PathVariable String stateName) throws ResourceNotFoundException {
    	logger.info("Fetching cities for state: {}", stateName);
    	List<City> cities = locationService.getCitiesByState(stateName);
        return ResponseEntity.ok(cities);
    }

    @GetMapping("/states/city/{cityName}")
    public ResponseEntity<State> getStateByCity(@PathVariable String cityName) throws ResourceNotFoundException {
    	logger.info("Fetching state for city: {}", cityName);
    	State state = locationService.getStateByCity(cityName);
        if (state != null) {
            return ResponseEntity.ok(state);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
