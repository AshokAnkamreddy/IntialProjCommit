package com.retailer.springboot.controller;

public class FailedNearByShops extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FailedNearByShops(String message) {
		super(message);
	}
	
}
