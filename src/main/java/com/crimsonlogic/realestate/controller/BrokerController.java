package com.crimsonlogic.realestate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crimsonlogic.realestate.dto.BrokerDTO;
import com.crimsonlogic.realestate.dto.BrokerWithUserDetailsDTO;
import com.crimsonlogic.realestate.exception.BrokerNotApprovedException;
import com.crimsonlogic.realestate.exception.BrokerNotFoundException;
import com.crimsonlogic.realestate.exception.UserNotFoundException;
import com.crimsonlogic.realestate.model.Broker;
import com.crimsonlogic.realestate.service.BrokerService;

@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("/api/brokers")
public class BrokerController {

	@Autowired
	private BrokerService brokerService;

	@PostMapping("/save")
	public ResponseEntity<Broker> createBroker(@RequestBody BrokerDTO brokerDTO) throws UserNotFoundException {
		Broker savedBroker = brokerService.saveBroker(brokerDTO);
		return new ResponseEntity<>(savedBroker, HttpStatus.CREATED);
	}

	@GetMapping("/details")
	public ResponseEntity<List<BrokerWithUserDetailsDTO>> getBrokersWithUserDetails() {
		List<BrokerWithUserDetailsDTO> brokers = brokerService.getBrokersWithUserDetails();
		return new ResponseEntity<>(brokers, HttpStatus.OK);
	}

	@PutMapping("/approve/{brokerId}")
	public ResponseEntity<Broker> approveBroker(@PathVariable Long brokerId) throws BrokerNotFoundException {
		Broker updatedBroker = brokerService.updateBrokerStatus(brokerId, "Approved");
		return new ResponseEntity<>(updatedBroker, HttpStatus.OK);
	}
	
	 @GetMapping("/license/{userdetailsId}")
	    public ResponseEntity<String> getBrokerLicenseByUserDetailsId(@PathVariable String userdetailsId) throws BrokerNotApprovedException {
	        String brokerLicenseId = brokerService.getBrokerLicenseByUserDetailsId(userdetailsId);
	        return new ResponseEntity<>(brokerLicenseId, HttpStatus.OK);
	    }
	 
	 @GetMapping("/check/{userdetailsId}")
	 public ResponseEntity<?> checkBrokerRegistration(@PathVariable String userdetailsId) {
	     try {
	         Broker broker = brokerService.getBrokerByUserDetailsId(userdetailsId);
	         return ResponseEntity.ok(broker); // If broker is found, return 200 OK with broker details
	     } catch (BrokerNotFoundException e) {
	         // Return a custom message instead of 404 when the broker is not found
	         return ResponseEntity.status(HttpStatus.OK)
	                              .body("No broker found with user details ID: " + userdetailsId);
	     }
	 }


}
