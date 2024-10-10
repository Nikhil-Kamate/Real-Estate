package com.crimsonlogic.realestate.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.crimsonlogic.realestate.dto.RentPropertyDTO;
import com.crimsonlogic.realestate.dto.SellPropertyDTO;
import com.crimsonlogic.realestate.service.PropertyService;

public class PropertyControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PropertyService propertyService;

    @InjectMocks
    private PropertyController propertyController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(propertyController).build();
    }

    @Test
    public void testSellPropertySuccess() throws Exception {
        // Arrange
        doNothing().when(propertyService).sellProperty(any(SellPropertyDTO.class));

        // Act & Assert
        mockMvc.perform(post("/api/property/sell")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .flashAttr("sellPropertyDTO", new SellPropertyDTO())) // Simulate @ModelAttribute
                .andExpect(status().isOk())
                .andExpect(content().string("Property listed successfully!"));
    }

    @Test
    public void testRentPropertySuccess() throws Exception {
        // Arrange
        doNothing().when(propertyService).rentProperty(any(RentPropertyDTO.class));

        // Act & Assert
        mockMvc.perform(post("/api/property/rent")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .flashAttr("rentPropertyDTO", new RentPropertyDTO())) // Simulate @ModelAttribute
                .andExpect(status().isOk())
                .andExpect(content().string("Property listed for rent successfully!"));
    }

    
}
