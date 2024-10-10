package com.crimsonlogic.realestate.service;

import java.util.List;

import com.crimsonlogic.realestate.dto.BrokerDTO;
import com.crimsonlogic.realestate.dto.BrokerWithUserDetailsDTO;
import com.crimsonlogic.realestate.exception.BrokerNotApprovedException;
import com.crimsonlogic.realestate.exception.BrokerNotFoundException;
import com.crimsonlogic.realestate.exception.UserNotFoundException;
import com.crimsonlogic.realestate.model.Broker;

public interface BrokerService {
	
	//Save the Broker Details
	Broker saveBroker(BrokerDTO brokerDTO)throws UserNotFoundException;
	
	//Print the list with userdetails and with broker details
	List<BrokerWithUserDetailsDTO> getBrokersWithUserDetails();
	
	//update broker status
	Broker updateBrokerStatus(Long brokerId, String status)throws BrokerNotFoundException;
	
	//get the BrokerLicenseId based on UserdetailsId
	String getBrokerLicenseByUserDetailsId(String userdetailsId) throws BrokerNotApprovedException;
	
	Broker getBrokerByUserDetailsId(String userdetailsId) throws BrokerNotFoundException;
}
