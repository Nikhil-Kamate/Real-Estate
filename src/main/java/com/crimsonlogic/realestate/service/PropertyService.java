package com.crimsonlogic.realestate.service;

import com.crimsonlogic.realestate.dto.RentPropertyDTO;
import com.crimsonlogic.realestate.dto.SellPropertyDTO;
import com.crimsonlogic.realestate.exception.PropertyUploadException;

public interface PropertyService {
	
	//add property for sell
	void sellProperty(SellPropertyDTO sellPropertyDTO) throws PropertyUploadException;
	
	//add property for rent
	void rentProperty(RentPropertyDTO rentPropertyDTO) throws PropertyUploadException;
}
