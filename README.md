# Demo spring boot project for Retailer Application

Retailer application which stores shop names, address and location by calling google api. Get nearby shops near the user.

# Prerequisites

  - Java 8
  - Gradle or Maven


### Services
#### getAllShops
  Location -  http://localhost:8080/SpringBootRestApi/api/shops/
  Method - post
    Get Service to get all the shops in database.
#### Input - Json 
     {
       "shopName":"shopOne",
       "address":"Bavdhan,Pune",
       "postCode":"411021"
      }
   Checks whether already shop is there with this name.If not add shop to database.
 If it is there updates the address. Calls google api to get latitude and longitude by passong the input address.
 
##### Output
    If shop is added gives the details of the shop. If shop is updated gives the object of current address and previous address.
   If shop updated
   {
    "data": {
        "currentAddress": {
            "shopName": "shopTwo",
            "address": "Pune",
            "latitude": 18.5204303,
            "longitude": 73.8567437,
            "postCode": null
        },
        "previousAddress": {
            "shopName": "shopTwo",
            "address": "Mumbai",
            "latitude": 19.0759837,
            "longitude": 72.8776559,
            "postCode": null
        }
    },
    "success": true,
    "message": "Address got updated."
}
    
#### get Shops near by
  Location -  http://localhost:8080/SpringBootRestApi/api/getshopsnearby/
  Method - Post
    Get Service to get all the nearby shops with in 20 km radius.
#### Input - Json 
    {
    "latitude":19.0760,
    "longitude":72.8777
    }
   Takes this input, send all the shops in the 20 km radius of this address.
 
##### Sample Output
    
   {
    "total": 1,
    "data": [
        {
            "shopName": "shopTwo",
            "address": "Mumbai",
            "latitude": 19.0759837,
            "longitude": 72.8776559,
            "postCode": null
        }
    ],
    "success": true
}


### Installing and run
 
 - built by gradle or maven
 
 In case any changes to code
 Build by grdle:
 >gradle tasks
 >gradle assemble
 >gradle build
 
 Or by maven
 >mvn install
 
 
Go to target SpringBootRestApiExample\target folder

Run SpringBootRestApiExample-1.0.0.jar file by using command

#####  > java -jar SpringBootRestApiExample-1.0.0.jar

Will be Initialized in spring boot with embedded tomcat server at port 8080
Make sure port 8080 already not in user.


### Testing

##### open postman rest client

### 1.
Location - http://localhost:8080/SpringBootRestApi/api/shops/
Method - Post
Headres - Content-Type : application/json
Input json -  
    {
        "shopName": "shopTwo",
        "address": "Mumbai",
       "postCode":"411021"
    }
    
output json - for existing shop name
  {
    "data": {
        "currentAddress": {
            "shopName": "shopTwo",
            "address": "Mumbai",
            "latitude": 19.0759837,
            "longitude": 72.8776559,
            "postCode": null
        },
        "previousAddress": {
            "shopName": "shopTwo",
            "address": "Pune",
            "latitude": 18.5204303,
            "longitude": 73.8567437,
            "postCode": null
        }
    },
    "success": true,
    "message": "Address got updated."
}
  
 #### 2
 Location - http://localhost:8080/SpringBootRestApi/api/all
 Method - Get
 Output -
 [
    {
        "shopName": "shopTwo",
        "address": "Mumbai",
        "latitude": 19.0759837,
        "longitude": 72.8776559,
        "postCode": null
    }
]

#### 3 
Location - http://localhost:8080/SpringBootRestApi/api/getshopsnearby/
Method - post
Headers - Content-Type : application/json
Input json -
    {
    "address":"Mumbai"
   
    }

Output json - 

  {
    "total": 1,
    "data": [
        {
            "shopName": "shopTwo",
            "address": "Mumbai",
            "latitude": 19.0759837,
            "longitude": 72.8776559,
            "postCode": null
        }
    ],
    "success": true
}

Cab be test by saving a shop from pune address. Then pass any other city latitude and longitude far from pune. Then no results nearby come.

---If you give latitude and longitude from with in 20 km radius near by shops will come.

---Images for testing added in zip project folder

--- Unit test cases written while developing in src/test/java which is main method to test add and get services can be run from         command prompt or eclipse


### logging

   ----Log file created in root directory with the name application.log
