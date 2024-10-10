package com.crimsonlogic.realestate.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crimsonlogic.realestate.dto.BrokerDTO;
import com.crimsonlogic.realestate.dto.BrokerWithUserDetailsDTO;
import com.crimsonlogic.realestate.exception.BrokerNotApprovedException;
import com.crimsonlogic.realestate.exception.BrokerNotFoundException;
import com.crimsonlogic.realestate.exception.UserNotFoundException;
import com.crimsonlogic.realestate.model.Broker;
import com.crimsonlogic.realestate.model.UserDetails;
import com.crimsonlogic.realestate.repository.BrokerRepository;
import com.crimsonlogic.realestate.repository.UserDetailsRepository;
import com.crimsonlogic.realestate.service.BrokerService;

@Service
public class BrokerServiceImpl implements BrokerService {

	@Autowired
	private BrokerRepository brokerRepository;

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@Override
	public Broker saveBroker(BrokerDTO brokerDTO) throws UserNotFoundException {
		Broker broker = new Broker();
		broker.setBrokerageRate(brokerDTO.getBrokerageRate());
		broker.setBrokerageLicenseId(brokerDTO.getBrokerageLicenseId());

		// Fetching the UserDetails based on userdetailsId
		UserDetails userDetails = userDetailsRepository.findById(brokerDTO.getUserdetailsId())
				.orElseThrow(() -> new UserNotFoundException("User not found with ID: " + brokerDTO.getUserdetailsId())); // Handle user not found case
		broker.setUserDetails(userDetails);

		return brokerRepository.save(broker);
	}

	@Override
	public List<BrokerWithUserDetailsDTO> getBrokersWithUserDetails() {
		List<Broker> brokers = brokerRepository.findAll();
		List<BrokerWithUserDetailsDTO> result = new ArrayList<>();

		for (Broker broker : brokers) {
			UserDetails userDetails = broker.getUserDetails();
			BrokerWithUserDetailsDTO dto = new BrokerWithUserDetailsDTO();
			dto.setBrokerId(broker.getBrokerId());
			dto.setUserdetailsId(userDetails.getUserdetailsId());
			dto.setUserdetailsFirstName(userDetails.getUserdetailsFirstName());
			dto.setUserLastName(userDetails.getUserLastName());
			dto.setUserPhoneNo(userDetails.getUserPhoneNo());
			dto.setIsSubscribed(userDetails.getIsSubscribed());
			dto.setUserRole(userDetails.getUserRole() != null ? userDetails.getUserRole().getRole() : null);
			dto.setBrokerageRate(broker.getBrokerageRate());
			dto.setStatus(broker.getStatus());
			dto.setBrokerageLicenseId(broker.getBrokerageLicenseId());

			result.add(dto);
		}

		return result;
	}

	@Override
	public Broker updateBrokerStatus(Long brokerId, String status) throws BrokerNotFoundException {
	    Broker broker = brokerRepository.findById(brokerId)
	            .orElseThrow(() -> new BrokerNotFoundException("Broker not found with ID: " + brokerId));
	    broker.setStatus(status);
	    return brokerRepository.save(broker);
	}

	 @Override
	    public String getBrokerLicenseByUserDetailsId(String userdetailsId) throws BrokerNotApprovedException {
	        // Fetch broker by userdetailsId and status
	        Broker broker = brokerRepository.findByUserDetailsUserdetailsIdAndStatus(userdetailsId, "Approved")
	                .orElseThrow(() -> new BrokerNotApprovedException("Broker is not approved or does not exist"));
	        
	        return broker.getBrokerageLicenseId();
	    }
	 
	 @Override
	 public Broker getBrokerByUserDetailsId(String userdetailsId) throws BrokerNotFoundException {
	        Broker broker = brokerRepository.findByUserDetailsUserdetailsId(userdetailsId);
	        if (broker == null) {
	            throw new BrokerNotFoundException("Broker not found for user ID: " + userdetailsId);
	        }
	        return broker;
	    }

}
