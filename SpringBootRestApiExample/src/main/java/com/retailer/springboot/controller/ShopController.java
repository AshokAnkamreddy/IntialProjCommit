package com.retailer.springboot.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderResult;
import com.retailer.springboot.dto.ShopOutput;
import com.retailer.springboot.model.Shop;
import com.retailer.springboot.service.ShopRepository;
import com.retailer.springboot.util.AdressConverter;
import com.retailer.springboot.util.LatLng;
import com.retailer.springboot.util.PointF;
import com.retailer.springboot.util.PositionCalculator;
import com.retailer.springboot.util.Utils;

/*@Author: Ashok Ankamreddy
version-1.0
Shop Api has add shop, get all shops , get near by shop apis
*/

/**
 * The Class ShopController.
 */
@RestController
@RequestMapping("/api")
public class ShopController {

	/** The Constant logger. */
	public static final Logger logger = LoggerFactory
			.getLogger(ShopController.class);

	/** The json utils. */
	@Autowired
	private Utils jsonUtils;

	/** The shop repository. */
	@Autowired
	private ShopRepository shopRepository;

	
	/**
	 * Creates the shop.
	 *
	 * @param shop the shop
	 * @return the string
	 * @throws ShopCreationException 
	 * @throws IOException 
	 */
	/*Post method to add shop ,If shop already exists updates the shop address from google api
	 * @parameter - Shop*/
	@RequestMapping(value = "/shops", method = RequestMethod.POST)
	public ResponseEntity<String> createShop(@RequestBody Shop shop) throws ShopCreationException, IOException {
		
			if(shop.getShopName()==null || shop.getAddress()==null){
				throw new ShopCreationException("shop name and address are mandatory.");
			}
			try{
			Shop oldShop = shopRepository.findByShopName(shop.getShopName());

			if (oldShop == null) {

				//call google service
				LatLng latLng = getLatLng(shop.getAddress());

				shop.setLatitude(latLng.getLatitude());
				shop.setLongitude(latLng.getLongitude());

				//save shop
				shopRepository.save(shop);
				logger.info("new shop added with name=" + shop.getShopName());
				return new ResponseEntity<String>(jsonUtils.objectMapperSuccess(shop, "new Shop added."),HttpStatus.CREATED);
			} else {
				synchronized (oldShop) {
					ShopOutput shopOutput = new ShopOutput();
					Shop previousShopAddress = new Shop(oldShop.getShopName(),
							oldShop.getAddress(), oldShop.getLatitude(),
							oldShop.getLongitude(), oldShop.getPostCode());
					shopOutput.setPreviousAddress(previousShopAddress);
					//calling google service
					LatLng latLng = getLatLng(shop.getAddress());

					oldShop.setLatitude(latLng.getLatitude());
					oldShop.setLongitude(latLng.getLongitude());
					oldShop.setAddress(shop.getAddress());
					//update shop
					shopRepository.save(oldShop);
					logger.info(oldShop.getShopName()
							+ " shop address updated.");
					shopOutput.setCurrentAddress(oldShop);
					return new ResponseEntity<String>(jsonUtils.objectMapperSuccess(shopOutput,
							"Address got updated."),HttpStatus.OK);
				}
			}
			}catch(Exception e){
				throw new ShopCreationException("Shop cannot be created.");
			}
		
	}

	/*Method to call the google service and returns latitude and longitude
	 * Input parameter - address
	 * response - latitude and longitude*/
	
	/**
	 * Gets the lat lng.
	 *
	 * @param address the address
	 * @return the lat lng
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public LatLng getLatLng(String address) throws IOException {
		logger.info("Sending request to google maps api for the address "
				+ address);
		AdressConverter ac = new AdressConverter();
		GeocodeResponse response = ac.convertToLatlong(address);
		List<GeocoderResult> results = response.getResults();

		BigDecimal latitude = results.get(0).getGeometry().getLocation()
				.getLat();
		BigDecimal longitude = results.get(0).getGeometry().getLocation()
				.getLng();

		
		LatLng latLng = new LatLng();
		latLng.setLatitude(latitude.doubleValue());
		latLng.setLongitude(longitude.doubleValue());
		return latLng;
	}

	
	/*Get method to get all the shops*/
	
	/**
	 * Gets the all users.
	 *
	 * @return the all users
	 */
	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Shop> getAllUsers() {
		// This returns a JSON or XML with the users
		logger.info("Fetching all shops.");
		return shopRepository.findAll();
	}

	/**
	 * Near by shops.
	 *
	 * @param latLng the lat lng
	 * @return the string
	 */
	/*Post method which takes latitude and longitude of user and finds 20 km nearby shops
	 * Input parameter-Latitude and longitude*/
	@RequestMapping(value = "/getshopsnearkm/", method = RequestMethod.POST)
	public @ResponseBody String nearByShops(@RequestBody LatLng latLng) {
		try {
			PointF center = new PointF(latLng.getLatitude(),
					latLng.getLongitude());

			int radius = 20000;

			logger.info("Getting near by shops for the latitude"
					+ latLng.getLatitude() + " Longitude="
					+ latLng.getLongitude() + " With in 20 km raidus");
			PositionCalculator pc = new PositionCalculator();

			final double mult = 1.1; // mult = 1.1; is more reliable
			PointF p1 = pc.calculateDerivedPosition(center, mult * radius, 0);
			PointF p2 = pc.calculateDerivedPosition(center, mult * radius, 90);
			PointF p3 = pc.calculateDerivedPosition(center, mult * radius, 180);
			PointF p4 = pc.calculateDerivedPosition(center, mult * radius, 270);

			List<Shop> shops = shopRepository
					.findNearBy(p3.x, p1.x, p2.y, p4.y);

			return jsonUtils.objectMapperListToJson(shops);
		} catch (Exception e) {
			e.printStackTrace();
			return jsonUtils.objectMapperError("Error");
		}

	}
	
	/** To fetch the neaar by shops by his address
	 * Near by shops for user.
	 *
	 * @param latLng the lat lng
	 * @return the string
	 * @throws FailedNearByShops 
	 */
	@RequestMapping(value = "/getshopsnearby", method = RequestMethod.POST)
	public ResponseEntity<String> nearByShopsForUser(@RequestBody LatLng address) throws FailedNearByShops {
		try {
			LatLng latLng = getLatLng(address.getAddress());
			
			PointF userLoc = new PointF(latLng.getLatitude(),
					latLng.getLongitude());

			

			logger.info("Getting near by shops for the latitude"
					+ latLng.getLatitude() + " Longitude="
					+ latLng.getLongitude() + " by distance.");
			PositionCalculator pc = new PositionCalculator();

			

			Iterable<Shop> shops = shopRepository.findAll();
			
			//calculating and sorting shops by distance from the user
			List<Shop> allShops = new ArrayList<Shop>();
			
			for(Shop shop1: shops){
				PointF shopLoc=new PointF(shop1.getLatitude(), shop1.getLongitude());
				double distance=pc.getDistanceBetweenTwoPoints(userLoc, shopLoc);
				shop1.setDistance(distance);
				allShops.add(shop1);
			}
			Collections.sort(allShops,new Comparator<Shop>() {

				@Override
				public int compare(Shop s1, Shop s2) {
					
					return s1.getDistance().compareTo(s2.getDistance());
				}
			});

			return new ResponseEntity<String>(jsonUtils.objectMapperListToJson(allShops, allShops.size()),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FailedNearByShops("Service not availabel right now.");
		}

	}

}
