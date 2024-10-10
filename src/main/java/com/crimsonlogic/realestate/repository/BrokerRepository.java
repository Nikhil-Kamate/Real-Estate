package com.crimsonlogic.realestate.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crimsonlogic.realestate.model.Broker;

@Repository
public interface BrokerRepository  extends JpaRepository<Broker, Long> {
	
	Optional<Broker> findByUserDetailsUserdetailsIdAndStatus(String userdetailsId, String status);
	
	Broker findByUserDetailsUserdetailsId(String userdetailsId);
}
