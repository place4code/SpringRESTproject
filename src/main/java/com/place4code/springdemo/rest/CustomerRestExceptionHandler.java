package com.place4code.springdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExceptionHandler {
	
	@ExceptionHandler
	ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException e) {
		
		CustomerErrorResponse response = new CustomerErrorResponse(e.getMessage(), 
				HttpStatus.NOT_FOUND.value(), 
				System.currentTimeMillis());
		
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler
	ResponseEntity<CustomerErrorResponse> handleAnother(Exception e) {
		
		CustomerErrorResponse response = new CustomerErrorResponse(e.getMessage(), 
				HttpStatus.BAD_REQUEST.value(), 
				System.currentTimeMillis());
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		
	}

}
