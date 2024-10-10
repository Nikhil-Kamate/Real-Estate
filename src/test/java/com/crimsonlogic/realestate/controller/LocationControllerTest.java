package com.crimsonlogic.realestate.controller;

import com.crimsonlogic.realestate.exception.ResourceNotFoundException;
import com.crimsonlogic.realestate.model.City;
import com.crimsonlogic.realestate.model.State;
import com.crimsonlogic.realestate.service.LocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LocationControllerTest {

    @InjectMocks
    private LocationController locationController;

    @Mock
    private LocationService locationService;

    private State state;
    private City city;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        state = new State();
        state.setId(1L);
        state.setName("California");

        city = new City();
        city.setId(1L);
        city.setName("Los Angeles");
        city.setStateName("California");
    }

    @Test
    void testGetAllStates() {
        when(locationService.getAllStates()).thenReturn(Arrays.asList(state));

        ResponseEntity<List<State>> response = locationController.getAllStates();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
        assertEquals(1, response.getBody().size());
        assertEquals("California", response.getBody().get(0).getName());
    }

    @Test
    void testAddState() {
        when(locationService.addState(state)).thenReturn(state);

        ResponseEntity<State> response = locationController.addState(state);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("California", response.getBody().getName());
    }

    @Test
    void testAddCity() {
        when(locationService.addCity(city)).thenReturn(city);

        ResponseEntity<City> response = locationController.addCity(city);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Los Angeles", response.getBody().getName());
    }

    @Test
    void testGetCitiesByState() throws ResourceNotFoundException {
        when(locationService.getCitiesByState("California")).thenReturn(Arrays.asList(city));

        ResponseEntity<List<City>> response = locationController.getCitiesByState("California");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
        assertEquals(1, response.getBody().size());
        assertEquals("Los Angeles", response.getBody().get(0).getName());
    }

    @Test
    void testGetCitiesByStateNotFound() throws ResourceNotFoundException {
        when(locationService.getCitiesByState("NonExistentState")).thenThrow(new ResourceNotFoundException("No cities found for state: NonExistentState"));

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            locationController.getCitiesByState("NonExistentState");
        });

        assertEquals("No cities found for state: NonExistentState", exception.getMessage());
    }

    @Test
    void testGetStateByCity() throws ResourceNotFoundException {
        when(locationService.getStateByCity("Los Angeles")).thenReturn(state);

        ResponseEntity<State> response = locationController.getStateByCity("Los Angeles");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("California", response.getBody().getName());
    }

    @Test
    void testGetStateByCityNotFound() throws ResourceNotFoundException {
        when(locationService.getStateByCity("NonExistentCity")).thenThrow(new ResourceNotFoundException("City not found: NonExistentCity"));

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            locationController.getStateByCity("NonExistentCity");
        });

        assertEquals("City not found: NonExistentCity", exception.getMessage());
    }
}
