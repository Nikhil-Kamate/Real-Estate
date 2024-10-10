package com.crimsonlogic.realestate.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.crimsonlogic.realestate.dto.PropertyDetailsDTO;
import com.crimsonlogic.realestate.model.Property;
import com.crimsonlogic.realestate.service.PropertyDisplayService;

public class PropertyDisplayControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PropertyDisplayService propdisSer;

    @InjectMocks
    private PropertyDisplayController propertyDisplayController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(propertyDisplayController).build();
    }

    @Test
    public void testGetAllProperties() throws Exception {
        // Arrange
        List<Property> properties = List.of(new Property());
        when(propdisSer.getAllProperties()).thenReturn(properties);

        // Act & Assert
        mockMvc.perform(get("/api/propertydisplay/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetPropertyWithDetailsSuccess() throws Exception {
        // Arrange
        PropertyDetailsDTO detailsDTO = new PropertyDetailsDTO();
        when(propdisSer.getPropertyWithDetails(anyString())).thenReturn(detailsDTO);

        // Act & Assert
        mockMvc.perform(get("/api/propertydisplay/property/details/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetApprovedPropertiesSuccess() throws Exception {
        // Arrange
        List<Property> approvedProperties = List.of(new Property());
        when(propdisSer.getApprovedProperties(anyString())).thenReturn(approvedProperties);

        // Act & Assert
        mockMvc.perform(get("/api/propertydisplay/approved/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testApprovePropertySuccess() throws Exception {
        // Arrange
        doNothing().when(propdisSer).approveProperty(anyString());

        // Act & Assert
        mockMvc.perform(post("/api/propertydisplay/approve/1"))
                .andExpect(status().isOk());
    }
    
    @Test
    public void testRejectPropertySuccess() throws Exception {
        // Arrange
        doNothing().when(propdisSer).rejectProperty(anyString());

        // Act & Assert
        mockMvc.perform(post("/api/propertydisplay/reject/1"))
                .andExpect(status().isOk());
    }
    
    @Test
    public void testGetPropertiesByUserId() throws Exception {
        // Arrange
        List<Property> properties = List.of(new Property());
        when(propdisSer.getPropertiesByUserId(anyString())).thenReturn(properties);

        // Act & Assert
        mockMvc.perform(get("/api/propertydisplay/user/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetApprovedPropertiesExcludingUserSuccess() throws Exception {
        // Arrange
        List<PropertyDetailsDTO> approvedProperties = List.of(new PropertyDetailsDTO());
        when(propdisSer.getApprovedPropertiesExcludingUser(anyString())).thenReturn(approvedProperties);

        // Act & Assert
        mockMvc.perform(get("/api/propertydisplay/approved/exclude/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetApprovedPropertiesExcludingUserNoContent() throws Exception {
        // Arrange
        when(propdisSer.getApprovedPropertiesExcludingUser(anyString())).thenReturn(List.of());

        // Act & Assert
        mockMvc.perform(get("/api/propertydisplay/approved/exclude/1"))
                .andExpect(status().isNoContent());
    }
}
