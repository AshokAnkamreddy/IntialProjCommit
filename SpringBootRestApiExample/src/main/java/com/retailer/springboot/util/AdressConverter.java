package com.retailer.springboot.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.net.URLEncoder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.code.geocoder.model.GeocodeResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class AdressConverter.
 */
public class AdressConverter
    {

	/** The Constant URL. */
	private static final String URL = "http://maps.googleapis.com/maps/api/geocode/json";

	/**
	 * Convert to latlong.
	 *
	 * @param fullAddress the full address
	 * @return the geocode response
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public GeocodeResponse convertToLatlong(String fullAddress) throws IOException
	    {

		java.net.URL url = new java.net.URL(URL + "?address=" + URLEncoder.encode(fullAddress, "UTF-8")
			+ "&sensor=false");
		URLConnection conn = url.openConnection();

		InputStream in = conn.getInputStream();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		GeocodeResponse response = (GeocodeResponse) mapper.readValue(in, GeocodeResponse.class);
		in.close();
		return response;

	    }

	/**
	 * Convert to address.
	 *
	 * @param latLongString the lat long string
	 * @return the geocode response
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public GeocodeResponse convertToAddress(String latLongString) throws IOException
	    {
		java.net.URL url = new java.net.URL(URL + "?latlng=" + URLEncoder.encode(latLongString, "UTF-8")
			+ "&sensor=false");
		// Open the Connection
		URLConnection conn = url.openConnection();

		InputStream in = conn.getInputStream();
		ObjectMapper mapper = new ObjectMapper();
		GeocodeResponse response = (GeocodeResponse) mapper.readValue(in, GeocodeResponse.class);
		in.close();
		return response;

	    }
    }
