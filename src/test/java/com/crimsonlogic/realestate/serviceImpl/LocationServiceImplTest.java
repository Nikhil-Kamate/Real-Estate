package com.crimsonlogic.realestate.serviceImpl;

import com.crimsonlogic.realestate.exception.ResourceNotFoundException;
import com.crimsonlogic.realestate.model.City;
import com.crimsonlogic.realestate.model.State;
import com.crimsonlogic.realestate.repository.CityRepository;
import com.crimsonlogic.realestate.repository.StateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LocationServiceImplTest {

    @InjectMocks
    private LocationServiceImpl locationService;

    @Mock
    private StateRepository stateRepository;

    @Mock
    private CityRepository cityRepository;

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
        when(stateRepository.findAll()).thenReturn(Arrays.asList(state));

        List<State> states = locationService.getAllStates();

        assertFalse(states.isEmpty());
        assertEquals(1, states.size());
        assertEquals("California", states.get(0).getName());
    }

    @Test
    void testAddState() {
        when(stateRepository.save(state)).thenReturn(state);

        State savedState = locationService.addState(state);

        assertNotNull(savedState);
        assertEquals("California", savedState.getName());
    }

    @Test
    void testAddCity() {
        when(cityRepository.save(city)).thenReturn(city);

        City savedCity = locationService.addCity(city);

        assertNotNull(savedCity);
        assertEquals("Los Angeles", savedCity.getName());
    }

    @Test
    void testGetCitiesByState() throws ResourceNotFoundException {
        when(cityRepository.findByStateName("California")).thenReturn(Arrays.asList(city));

        List<City> cities = locationService.getCitiesByState("California");

        assertFalse(cities.isEmpty());
        assertEquals(1, cities.size());
        assertEquals("Los Angeles", cities.get(0).getName());
    }

    @Test
    void testGetCitiesByStateNotFound() {
        when(cityRepository.findByStateName("NonExistentState")).thenReturn(new ArrayList<>());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            locationService.getCitiesByState("NonExistentState");
        });

        assertEquals("No cities found for state: NonExistentState", exception.getMessage());
    }

    @Test
    void testGetStateByCity() throws ResourceNotFoundException {
        when(cityRepository.findByName("Los Angeles")).thenReturn(city);
        when(stateRepository.findByName("California")).thenReturn(state);

        State foundState = locationService.getStateByCity("Los Angeles");

        assertNotNull(foundState);
        assertEquals("California", foundState.getName());
    }

    @Test
    void testGetStateByCityNotFound() {
        when(cityRepository.findByName("NonExistentCity")).thenReturn(null);

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            locationService.getStateByCity("NonExistentCity");
        });

        assertEquals("City not found: NonExistentCity", exception.getMessage());
    }

    @Test
    void testGetStateByCityWithNoStateFound() {
        when(cityRepository.findByName("Los Angeles")).thenReturn(city);
        when(stateRepository.findByName("California")).thenReturn(null);

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            locationService.getStateByCity("Los Angeles");
        });

        assertEquals("State not found for city: Los Angeles", exception.getMessage());
    }
}
