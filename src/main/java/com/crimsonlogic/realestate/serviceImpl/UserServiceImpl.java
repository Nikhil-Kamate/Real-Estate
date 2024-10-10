package com.crimsonlogic.realestate.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.crimsonlogic.realestate.dto.EditUserProfileDTO;
import com.crimsonlogic.realestate.exception.UserRegistrationException;
import com.crimsonlogic.realestate.model.UserAuthentication;
import com.crimsonlogic.realestate.model.UserDetails;
import com.crimsonlogic.realestate.model.UserRole;
import com.crimsonlogic.realestate.repository.UserAuthenticationRepository;
import com.crimsonlogic.realestate.repository.UserDetailsRepository;
import com.crimsonlogic.realestate.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private UserAuthenticationRepository userAuthenticationRepository;
    
    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public UserDetails registerUser(UserDetails userDetails, UserAuthentication userAuth) throws UserRegistrationException {

        if (userAuthenticationRepository.existsByEmail(userAuth.getEmail())) {
            logger.error("User registration failed: User with email {} already exists", userAuth.getEmail());
            throw new UserRegistrationException("User with this email already exists.");
        }

        // Hash the user's password before saving it
        String hashedPassword = hashPassword(userAuth.getPassword());
        userAuth.setPassword(hashedPassword);

        UserRole userRole = new UserRole();
        userRole.setId(2L); // Assuming 2L is the default user role

        userDetails.setUserRole(userRole);
        userAuth.setUserRole(userRole);

        userDetails.setIsSubscribed(false);
        UserDetails savedUserDetails = userDetailsRepository.save(userDetails);

        userAuth.setUserDetails(savedUserDetails);
        userAuthenticationRepository.save(userAuth);

        logger.info("User registered successfully");

        return savedUserDetails;
    }  
    
    @Override
    public UserDetails editUserProfile(EditUserProfileDTO editUserProfileDTO) throws UserRegistrationException {
        // Fetch the UserDetails by ID
        UserDetails existingUserDetails = userDetailsRepository.findById(editUserProfileDTO.getUserdetailsId())
                .orElseThrow(() -> new UserRegistrationException("User not found"));

        // Update UserDetails fields if provided
        if (editUserProfileDTO.getFirstName() != null) {
            existingUserDetails.setUserdetailsFirstName(editUserProfileDTO.getFirstName());
        }
        if (editUserProfileDTO.getLastName() != null) {
            existingUserDetails.setUserLastName(editUserProfileDTO.getLastName());
        }
        if (editUserProfileDTO.getPhoneNo() != null) {
            existingUserDetails.setUserPhoneNo(editUserProfileDTO.getPhoneNo());
        }

        // Fetch the corresponding UserAuthentication by userDetails
        UserAuthentication existingUserAuth = userAuthenticationRepository.findByUserDetails(existingUserDetails)
                .orElseThrow(() -> new UserRegistrationException("Authentication details not found"));

        // Update email if provided and if not already in use
        if (editUserProfileDTO.getEmail() != null && !existingUserAuth.getEmail().equals(editUserProfileDTO.getEmail())) {
            if (userAuthenticationRepository.existsByEmail(editUserProfileDTO.getEmail())) {
                throw new UserRegistrationException("Email already exists.");
            }
            existingUserAuth.setEmail(editUserProfileDTO.getEmail());
        }

        // Hash the new password if provided and update it
        if (editUserProfileDTO.getPassword() != null && !editUserProfileDTO.getPassword().isEmpty()) {
            String hashedPassword = hashPassword(editUserProfileDTO.getPassword());
            existingUserAuth.setPassword(hashedPassword);
        }

        // Save updated UserDetails and UserAuthentication
        UserDetails updatedUserDetails = userDetailsRepository.save(existingUserDetails);
        userAuthenticationRepository.save(existingUserAuth);

        logger.info("User profile updated successfully");

        return updatedUserDetails;
    }

}

