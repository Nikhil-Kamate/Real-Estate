package com.crimsonlogic.realestate.dto;

import java.time.LocalDate;

public class PaymentRequest {
	 private double paymentAmount;
	 private LocalDate subscriptionExpireDate;

	public LocalDate getSubscriptionExpireDate() {
		return subscriptionExpireDate;
	}

	public void setSubscriptionExpireDate(LocalDate subscriptionExpireDate) {
		this.subscriptionExpireDate = subscriptionExpireDate;
	}

	public double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	 
}
