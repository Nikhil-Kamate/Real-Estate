package com.crimsonlogic.realestate.serviceImpl;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crimsonlogic.realestate.model.Payment;
import com.crimsonlogic.realestate.model.UserDetails;
import com.crimsonlogic.realestate.repository.PaymentRepository;
import com.crimsonlogic.realestate.repository.UserDetailsRepository;
import com.crimsonlogic.realestate.service.PaymentService;


@Service
public class PaymentServiceImpl implements PaymentService {

	// Assuming you have a Payment repository injected
	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private UserDetailsRepository userRepository;
	
	@Override
	public String processPayment(String userId, double paymentAmount, LocalDate subscriptionExpireDate) {
	    Optional<UserDetails> user = userRepository.findById(userId);

	    if (user.isPresent()) {
	        // Create payment
	        Payment payment = new Payment();
	        payment.setUserDetails(user.get());
	        payment.setPaymentDate(LocalDate.now());
	        payment.setPaymentAmount(paymentAmount);
	        payment.setSubscriptionExpireDate(subscriptionExpireDate); // Set the expiration date

	        // Save payment
	        paymentRepository.save(payment);

	        // Update user's subscription status
	        user.get().setIsSubscribed(true);
	        userRepository.save(user.get());

	        return "Payment successful and subscription activated!";
	    } else {
	        return "User not found.";
	    }
	}

}
