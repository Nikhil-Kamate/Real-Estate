package com.crimsonlogic.realestate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crimsonlogic.realestate.dto.PaymentRequest;
import com.crimsonlogic.realestate.service.PaymentService;

@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("/api/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@PostMapping("/process/{userId}")
	public ResponseEntity<String> processPayment(@PathVariable String userId, @RequestBody PaymentRequest paymentRequest) {
	    String result = paymentService.processPayment(userId, paymentRequest.getPaymentAmount(), paymentRequest.getSubscriptionExpireDate());
	    return ResponseEntity.ok(result);
	}


}
