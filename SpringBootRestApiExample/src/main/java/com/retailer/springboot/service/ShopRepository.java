package com.retailer.springboot.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.retailer.springboot.model.Shop;

/**
 * The Interface ShopRepository.
 */
public interface ShopRepository extends CrudRepository<Shop, Long>{

	/**
	 * Find by shop name.
	 *
	 * @param shopName the shop name
	 * @return the shop
	 */
	Shop findByShopName(String shopName);
	
	/**
	 * Find near by.
	 *
	 * @param pointOne the point one
	 * @param pointTwo the point two
	 * @param pointThree the point three
	 * @param pointFour the point four
	 * @return the list
	 */
	@Query("SELECT p FROM Shop p WHERE latitude>:pointOne and latitude<:pointTwo and longitude<:pointThree and longitude>:pointFour")
    public List<Shop> findNearBy(@Param("pointOne") Double pointOne,@Param("pointTwo") Double pointTwo,@Param("pointThree") Double pointThree,@Param("pointFour")Double pointFour);
	
	
}
