package com.retailer.springboot;
 
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.retailer.springboot.model.Shop;
 

public class SpringBootRestTestClient {
 
    public static final String REST_SERVICE_URI = "http://localhost:8080/SpringBootRestApi/api";
     
    /* GET */
    @SuppressWarnings("unchecked")
    private static void listAllShops(){
        System.out.println("Testing listAllShops API-----------");
         
        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> shopMap = restTemplate.getForObject(REST_SERVICE_URI+"/shop/", List.class);
         
        if(shopMap!=null){
            for(LinkedHashMap<String, Object> map : shopMap){
                System.out.println("Shop : id="+map.get("id")+", Name="+map.get("shopNamw")+", address="+map.get("address")+", latitude="+map.get("latitude")+", longitude="+map.get("longitude"));;
            }
        }else{
            System.out.println("No shop exist----------");
        }
    }
     
   
    /* POST */
    private static void createShop() {
        System.out.println("Testing create Shop API----------");
        RestTemplate restTemplate = new RestTemplate();
        Shop shop = new Shop();
        shop.setShopName("shopOne");
        shop.setAddress("Mumbai");
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/shops/", shop, Shop.class);
        System.out.println("Location : "+uri.toASCIIString());
    }
 
   
 
   
 
 
    public static void main(String args[]){
        listAllShops();
        createShop();
        listAllShops();
        createShop();
       listAllShops();
       
    }
}