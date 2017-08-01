package com.retailer.springboot.dto;

import com.retailer.springboot.model.Shop;

/**
 * The Class ShopOutput.
 */
public class ShopOutput {

	
	/** The current address. */
	private Shop currentAddress;
	
	/** The previous address. */
	private Shop previousAddress;
	
	
	/**
	 * Gets the current address.
	 *
	 * @return the current address
	 */
	public Shop getCurrentAddress() {
		return currentAddress;
	}

	/**
	 * Sets the current address.
	 *
	 * @param currentAddress the new current address
	 */
	public void setCurrentAddress(Shop currentAddress) {
		this.currentAddress = currentAddress;
	}
	

	/**
	 * Gets the previous address.
	 *
	 * @return the previous address
	 */
	public Shop getPreviousAddress() {
		return previousAddress;
	}

	/**
	 * Sets the previous address.
	 *
	 * @param previousAddress the new previous address
	 */
	public void setPreviousAddress(Shop previousAddress) {
		this.previousAddress = previousAddress;
	}

	
	
}
