package com.crimsonlogic.realestate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crimsonlogic.realestate.model.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, String>{
	
	List<Property> findByApprovalStatus(String approvalStatus);
	
	List<Property> findByOwner_UserdetailsId(String userdetailsId);
	
	List<Property> findByApprovalStatusAndOwner_UserdetailsIdNot(String approvalStatus, String userId);

}
