package com.crimsonlogic.realestate.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.crimsonlogic.realestate.model.ErrorResponse;

public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> resourceNotFoundException(ResourceNotFoundException e, WebRequest request) {
		ErrorResponse err = new ErrorResponse(HttpStatus.NOT_FOUND.value(), LocalDateTime.now(), e.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UserAuthenticationException.class)
	public ResponseEntity<ErrorResponse> userAuthenticationException(UserAuthenticationException e,
			WebRequest request) {
		ErrorResponse err = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), LocalDateTime.now(), e.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(PropertyUploadException.class)
	public ResponseEntity<ErrorResponse> propertyUploadException(PropertyUploadException e, WebRequest request) {
		ErrorResponse err = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now(),
				e.getMessage(), request.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserRegistrationException.class)
	public ResponseEntity<ErrorResponse> userRegistrationException(UserRegistrationException e, WebRequest request) {
		ErrorResponse err = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(), e.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(PropertyNotFoundException.class)
	public ResponseEntity<String> handlePropertyNotFoundException(PropertyNotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BrokerNotApprovedException.class)
	public ResponseEntity<ErrorResponse> handleBrokerNotApprovedException(BrokerNotApprovedException ex,
			WebRequest request) {
		ErrorResponse err = new ErrorResponse(HttpStatus.FORBIDDEN.value(), LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(err, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
		ErrorResponse err = new ErrorResponse(HttpStatus.NOT_FOUND.value(), LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BrokerNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleBrokerNotFoundException(BrokerNotFoundException ex, WebRequest request) {
		ErrorResponse err = new ErrorResponse(HttpStatus.NOT_FOUND.value(), LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}
}
