package com.crimsonlogic.realestate.serviceImpl;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.crimsonlogic.realestate.dto.RentPropertyDTO;
import com.crimsonlogic.realestate.dto.SellPropertyDTO;
import com.crimsonlogic.realestate.exception.PropertyUploadException;
import com.crimsonlogic.realestate.model.Property;
import com.crimsonlogic.realestate.model.PropertyLocation;
import com.crimsonlogic.realestate.model.RentalDetails;
import com.crimsonlogic.realestate.model.SellDetails;
import com.crimsonlogic.realestate.repository.PropertyLocationRepository;
import com.crimsonlogic.realestate.repository.PropertyRepository;
import com.crimsonlogic.realestate.repository.RentalDetailsRepository;
import com.crimsonlogic.realestate.repository.SellDetailsRepository;
import com.crimsonlogic.realestate.service.PropertyService;

import jakarta.transaction.Transactional;

@Service
public class PropertyServiceImpl implements PropertyService {
	
    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private SellDetailsRepository sellDetailsRepository;

    @Autowired
    private RentalDetailsRepository rentalDetailsRepository;

    @Autowired
    private PropertyLocationRepository propertyLocationRepository;

    @Override
    @Transactional
    public void sellProperty(SellPropertyDTO sellPropertyDTO) throws PropertyUploadException {
        Property property = sellPropertyDTO.getProperty();
        MultipartFile imageFile = sellPropertyDTO.getImageFile();
        
        property.setListingType("Sell");
        
        if (imageFile != null && !imageFile.isEmpty()) {
            handleImageUpload(imageFile, property);
        }
        
        propertyRepository.save(property);
        SellDetails sellDetails = sellPropertyDTO.getSellDetails();
        PropertyLocation location = sellPropertyDTO.getLocation();
        
        sellDetails.setProperty(property);
        location.setProperty(property);
        
        sellDetailsRepository.save(sellDetails);
        propertyLocationRepository.save(location);
        
    }

    @Override
    @Transactional
    public void rentProperty(RentPropertyDTO rentPropertyDTO) throws PropertyUploadException {
        Property property = rentPropertyDTO.getProperty();
        MultipartFile imageFile = rentPropertyDTO.getImageFile();
        
        property.setListingType("Rent");

        if (imageFile != null && !imageFile.isEmpty()) {
            handleImageUpload(imageFile, property);
        }

        propertyRepository.save(property);
        RentalDetails rentalDetails = rentPropertyDTO.getRentalDetails();
        PropertyLocation location = rentPropertyDTO.getLocation();

        rentalDetails.setProperty(property);
        location.setProperty(property);

        rentalDetailsRepository.save(rentalDetails);
        propertyLocationRepository.save(location);
    }

    private void handleImageUpload(MultipartFile imageFile, Property property) throws PropertyUploadException {
        try {
            File uploadsDir = new File(uploadPath);
            if (!uploadsDir.exists()) {
                uploadsDir.mkdirs();
            }

            String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
            File serverFile = new File(uploadsDir, fileName);
            imageFile.transferTo(serverFile);
            property.setPropertyimagePath(fileName); // Store only the name
            
        } catch (IOException e) {
            throw new PropertyUploadException("Failed to store image: " + e.getMessage(), e);
        }
    }
}
