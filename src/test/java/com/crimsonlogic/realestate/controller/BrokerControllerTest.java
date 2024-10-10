package com.crimsonlogic.realestate.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.crimsonlogic.realestate.dto.BrokerDTO;
import com.crimsonlogic.realestate.dto.BrokerWithUserDetailsDTO;
import com.crimsonlogic.realestate.model.Broker;
import com.crimsonlogic.realestate.service.BrokerService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BrokerControllerTest {

    @InjectMocks
    private BrokerController brokerController;

    @Mock
    private BrokerService brokerService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private BrokerDTO brokerDTO;
    private Broker broker;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(brokerController).build();
        objectMapper = new ObjectMapper();

        // Initialize test data
        brokerDTO = new BrokerDTO();
        brokerDTO.setBrokerageRate(2.5);
        brokerDTO.setUserdetailsId("UID-123");

        broker = new Broker();
        broker.setBrokerId(1L);
        broker.setBrokerageRate(2.5);
        broker.setBrokerageLicenseId("BOKLIC-123");
    }

    @Test
    void testCreateBroker() throws Exception {
        // Arrange
        when(brokerService.saveBroker(any(BrokerDTO.class))).thenReturn(broker);

        // Act & Assert
        mockMvc.perform(post("/api/brokers/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(brokerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.brokerId").value(1L))
                .andExpect(jsonPath("$.brokerageRate").value(2.5));
    }

    @Test
    void testGetBrokersWithUserDetails() throws Exception {
        // Arrange
        BrokerWithUserDetailsDTO brokerWithUserDetailsDTO = new BrokerWithUserDetailsDTO();
        brokerWithUserDetailsDTO.setBrokerId(1L);
        brokerWithUserDetailsDTO.setBrokerageRate(2.5);
        List<BrokerWithUserDetailsDTO> brokers = Arrays.asList(brokerWithUserDetailsDTO);

        when(brokerService.getBrokersWithUserDetails()).thenReturn(brokers);

        // Act & Assert
        mockMvc.perform(get("/api/brokers/details")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].brokerId").value(1L))
                .andExpect(jsonPath("$[0].brokerageRate").value(2.5));
    }

    @Test
    void testApproveBroker() throws Exception {
        // Arrange
        when(brokerService.updateBrokerStatus(1L, "Approved")).thenReturn(broker);

        // Act & Assert
        mockMvc.perform(put("/api/brokers/approve/{brokerId}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brokerId").value(1L))
                .andExpect(jsonPath("$.brokerageRate").value(2.5));
    }
}
