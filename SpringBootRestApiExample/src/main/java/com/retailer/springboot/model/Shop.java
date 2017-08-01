package com.retailer.springboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * The Class Shop.
 */
@Entity
public class Shop {

	
	/*@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;*/
	
	/** The shop name. */
	@Id
	private String shopName;
	
	/** The address. */
	private String address;
	
	/** The latitude. */
	private Double latitude;
	
	/** The longitude. */
	private Double longitude;
	
	/** The post code. */
	private String postCode;
	
	private transient Double distance;
	
	
	
	public Double getDistance() {
		return distance;
	}


	public void setDistance(Double distance) {
		this.distance = distance;
	}


	/**
	 * Instantiates a new shop.
	 */
	public Shop(){
		
	}
	

	/**
	 * Instantiates a new shop.
	 *
	 * @param shopName the shop name
	 * @param address the address
	 */
	public Shop( String shopName, String address) {
		
		this.shopName = shopName;
		this.address = address;
	}
public Shop( String shopName, String address,String pincode) {
		
		this.shopName = shopName;
		this.address = address;
		this.postCode=pincode;
	}
	

	/**
	 * Instantiates a new shop.
	 *
	 * @param shopName the shop name
	 * @param address the address
	 * @param latitude the latitude
	 * @param longitude the longitude
	 * @param postCode the post code
	 */
	public Shop(String shopName, String address, Double latitude,
			Double longitude, String postCode) {
		this.shopName = shopName;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.postCode = postCode;
	}


	/**
	 * Gets the post code.
	 *
	 * @return the post code
	 */
	public String getPostCode() {
		return postCode;
	}


	/**
	 * Sets the post code.
	 *
	 * @param postCode the new post code
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}


	/**
	 * Gets the latitude.
	 *
	 * @return the latitude
	 */
	public Double getLatitude() {
		return latitude;
	}


	/**
	 * Sets the latitude.
	 *
	 * @param latitude the new latitude
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}


	/**
	 * Gets the longitude.
	 *
	 * @return the longitude
	 */
	public Double getLongitude() {
		return longitude;
	}


	/**
	 * Sets the longitude.
	 *
	 * @param longitude the new longitude
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}


	/*public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}*/

	/**
	 * Gets the shop name.
	 *
	 * @return the shop name
	 */
	public String getShopName() {
		return shopName;
	}

	/**
	 * Sets the shop name.
	 *
	 * @param shopName the new shop name
	 */
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
