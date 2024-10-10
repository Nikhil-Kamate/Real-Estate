package com.crimsonlogic.realestate.serviceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.crimsonlogic.realestate.dto.BrokerDTO;
import com.crimsonlogic.realestate.dto.BrokerWithUserDetailsDTO;
import com.crimsonlogic.realestate.exception.BrokerNotFoundException;
import com.crimsonlogic.realestate.exception.UserNotFoundException;
import com.crimsonlogic.realestate.model.Broker;
import com.crimsonlogic.realestate.model.UserDetails;
import com.crimsonlogic.realestate.repository.BrokerRepository;
import com.crimsonlogic.realestate.repository.UserDetailsRepository;

@ExtendWith(MockitoExtension.class)
public class BrokerServiceImplTest {

    @InjectMocks
    private BrokerServiceImpl brokerService;

    @Mock
    private BrokerRepository brokerRepository;

    @Mock
    private UserDetailsRepository userDetailsRepository;

    private UserDetails userDetails;
    private Broker broker;

    @BeforeEach
    void setUp() {
        userDetails = new UserDetails();
        userDetails.setUserdetailsId("UID-123");
        userDetails.setUserdetailsFirstName("John");
        userDetails.setUserLastName("Doe");
        userDetails.setUserPhoneNo("1234567890");
        userDetails.setIsSubscribed(true);
        
        broker = new Broker();
        broker.setBrokerId(1L);
        broker.setBrokerageRate(2.5);
        broker.setStatus("Active");
        broker.setUserDetails(userDetails);
    }

    @Test
    void testSaveBroker() throws UserNotFoundException {
        // Arrange
        BrokerDTO brokerDTO = new BrokerDTO();
        brokerDTO.setBrokerageRate(2.5);
        brokerDTO.setUserdetailsId("UID-123");
        
        when(userDetailsRepository.findById("UID-123")).thenReturn(Optional.of(userDetails));
        when(brokerRepository.save(any(Broker.class))).thenReturn(broker);

        // Act
        Broker savedBroker = brokerService.saveBroker(brokerDTO);

        // Assert
        assertNotNull(savedBroker);
        assertEquals(2.5, savedBroker.getBrokerageRate());
        assertEquals("UID-123", savedBroker.getUserDetails().getUserdetailsId());
        verify(brokerRepository).save(any(Broker.class));
    }

    @Test
    void testGetBrokersWithUserDetails() {
        // Arrange
        brokerRepository.save(broker);
        when(brokerRepository.findAll()).thenReturn(Arrays.asList(broker));

        // Act
        List<BrokerWithUserDetailsDTO> brokersWithUserDetails = brokerService.getBrokersWithUserDetails();

        // Assert
        assertEquals(1, brokersWithUserDetails.size());
        BrokerWithUserDetailsDTO dto = brokersWithUserDetails.get(0);
        assertEquals(broker.getBrokerId(), dto.getBrokerId());
        assertEquals(userDetails.getUserdetailsId(), dto.getUserdetailsId());
        assertEquals(userDetails.getUserdetailsFirstName(), dto.getUserdetailsFirstName());
    }

    @Test
    void testUpdateBrokerStatus() throws BrokerNotFoundException {
        // Arrange
        when(brokerRepository.findById(1L)).thenReturn(Optional.of(broker));
        when(brokerRepository.save(broker)).thenReturn(broker);

        // Act
        Broker updatedBroker = brokerService.updateBrokerStatus(1L, "Inactive");

        // Assert
        assertEquals("Inactive", updatedBroker.getStatus());
        verify(brokerRepository).save(broker);
    }

    @Test
    void testSaveBroker_UserNotFound() {
        // Arrange
        BrokerDTO brokerDTO = new BrokerDTO();
        brokerDTO.setBrokerageRate(2.5);
        brokerDTO.setUserdetailsId("UID-123");

        when(userDetailsRepository.findById("UID-123")).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            brokerService.saveBroker(brokerDTO);
        });
        assertEquals("User not found", exception.getMessage());
    }

    @Test
    void testUpdateBrokerStatus_BrokerNotFound() {
        // Arrange
        when(brokerRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            brokerService.updateBrokerStatus(1L, "Inactive");
        });
        assertEquals("Broker not found", exception.getMessage());
    }
}
