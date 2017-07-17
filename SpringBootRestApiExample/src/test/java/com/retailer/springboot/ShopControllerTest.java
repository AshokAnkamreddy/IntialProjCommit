package com.retailer.springboot;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.results.ResultMatchers;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import ch.qos.logback.core.status.Status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.retailer.springboot.controller.ShopController;
import com.retailer.springboot.model.Shop;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ShopControllerTest {

	private MockMvc mockMvc;
	
	 @Autowired
	    private WebApplicationContext webApplicationContext;

	@MockBean
	private ShopController shopController;

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	Shop shop = new Shop("shopOne", "Pune",  "411021");
	

	String exampleShopJson = "{\"shopName\":\"shopOne\",\"address\":\"Pune\",\"postCode\":\"411021\"}";

	@Test
	public void retrieveDetailsForShop() throws Exception {
		//Shop shop = new Shop("shopOne", "Pune",  "411021");
		shop.setLatitude(1.0);
		shop.setLongitude(1.0);
		List<Shop> shops= new ArrayList<Shop>();
		shops.add(shop);

		Mockito.when(
				shopController.getAllUsers()).thenReturn(shops);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/all").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "[{\"shopName\":\"shopOne\",\"address\":\"Pune\",\"latitude\":1.0,\"longitude\":1.0,\"postCode\":\"411021\",\"distance\":null}]";

		System.out.println(expected);
		System.out.println(result.getResponse().getContentAsString());
		System.out.println(expected.equals(result.getResponse()
				.getContentAsString()));
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), JSONCompareMode.NON_EXTENSIBLE);
		
	}
	
	@Before
	public void setup() {
		 this.mockMvc = webAppContextSetup(webApplicationContext).build();
		
		System.out.println("Befor");
		this.mockMvc = MockMvcBuilders.standaloneSetup(shopController)
				.build();
	}
	
	@Test
    public void aCarGoesIntoTheGarage() throws Exception {
		try{
		Shop shop = new Shop("shopOne", null,  "411021");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(shop );
System.out.println(requestJson);
    RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
			"/api/shops").accept(
					MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_UTF8).content(requestJson);

	
        
	MvcResult result=	mockMvc.perform(requestBuilder)/*.andExpect(MockMvcResultMatchers.status().isBadRequest())*/.andReturn();

		System.out.println(result.getResponse().getContentAsString());
		System.out.println(result.getResponse().getStatus());
		

		org.junit.Assert.assertEquals(200, result.getResponse()
				.getStatus());
		
	
  
		shop.setLatitude(1.0);
		shop.setLongitude(1.0);
		List<Shop> shops= new ArrayList<Shop>();
		shops.add(shop);

		Mockito.when(
				shopController.getAllUsers()).thenReturn(shops);
	RequestBuilder requestBuilder2 = MockMvcRequestBuilders.get(
			"/api/all").accept(
			MediaType.APPLICATION_JSON);
	
	MvcResult result2 = mockMvc.perform(requestBuilder2).andReturn();

	System.out.println(result2.getResponse().getContentAsString());

	Shop shop3=new Gson().fromJson(result2.getResponse().getContentAsString().replace("[", "").replace("]", ""), Shop.class);
	System.out.println(shop3.getShopName());
	org.junit.Assert.assertEquals("shopOne",shop3.getShopName());
	
	
    
	}catch(Exception e){
		e.printStackTrace();
	}
	}
}
