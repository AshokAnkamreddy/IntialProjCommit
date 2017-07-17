package com.retailer.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * The Class SpringBootRestApiApp.
 */
@SpringBootApplication(scanBasePackages={"com.retailer.springboot"})
public class SpringBootRestApiApp {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestApiApp.class, args);
	}
}
