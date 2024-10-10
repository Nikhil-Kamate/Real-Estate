package com.crimsonlogic.realestate.service;

import java.time.LocalDate;

public interface PaymentService {
	
	public String processPayment(String userId, double paymentAmount, LocalDate subscriptionExpireDate);
}
