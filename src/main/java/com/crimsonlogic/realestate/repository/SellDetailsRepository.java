package com.crimsonlogic.realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crimsonlogic.realestate.model.SellDetails;

@Repository
public interface SellDetailsRepository extends JpaRepository<SellDetails, Long> {

}
