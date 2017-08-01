package com.retailer.springboot.controller;

import java.io.IOException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ShopControllerExceptionHandler {

	public static final Logger logger = LoggerFactory
			.getLogger(ShopControllerExceptionHandler.class);
	
		@ExceptionHandler(value = { ShopCreationException.class })
	    protected ResponseEntity<String> handleConflict(Exception ex, WebRequest request) {
			ex.printStackTrace();
			logger.error("Error while adding a shop. Stack trace="
					+ Arrays.toString(ex.getStackTrace()) + ".Error Message ="
					+ ex.getMessage());
	        return new ResponseEntity<String>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	    }
		
		@ExceptionHandler(value = { FailedNearByShops.class })
	    protected ResponseEntity<String> handleError(Exception ex, WebRequest request) {
	        return new ResponseEntity<String>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	    }
		
		@ExceptionHandler(value = { IOException.class })
	    protected ResponseEntity<String> handleIo(Exception ex, WebRequest request) {
			ex.printStackTrace();
			logger.error("Error while calling a google api. Stack trace="
					+ Arrays.toString(ex.getStackTrace()) + ".Error Message ="
					+ ex.getMessage());
	        return new ResponseEntity<String>("Error in google service.",HttpStatus.INTERNAL_SERVER_ERROR);
	    }
}

