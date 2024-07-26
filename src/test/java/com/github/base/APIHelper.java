package com.github.base;


	import com.fasterxml.jackson.core.JsonProcessingException;
	import com.fasterxml.jackson.databind.ObjectMapper;
	import io.restassured.RestAssured;
	import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
	import io.restassured.specification.RequestSpecification;
	import lombok.extern.slf4j.Slf4j;
	import org.apache.http.HttpStatus;
	import com.github.requestpojo.AddDataRequest;
	
	
	import com.github.requestpojo.UpdateDataRequest;
	
	import com.github.utils.EnvironmentDetails;
	import org.testng.Assert;

	import java.util.HashMap;
	import java.util.List;

	@Slf4j
	public class APIHelper {
	    RequestSpecification reqSpec;
	    String token = "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";

	    public APIHelper() {
	        RestAssured.baseURI = EnvironmentDetails.getProperty("baseURL");
	        RestAssured.basePath="/RadhiThamilselvan/SimpleProj";
	        
	    }
	       
	    public Response getData()
	    {
	    	 reqSpec = RestAssured.given().baseUri("https://api.github.com/").basePath("user/repos");
	         reqSpec.header("Authorization","Bearer " +token);
	         reqSpec.contentType(ContentType.JSON);
	         
	         Response response = null;
	         try {
	             response = reqSpec.when().get();
	            
	             
	             response.then().log().all();
	         } catch (Exception e) {
	             Assert.fail("Get data is failing due to :: " + e.getMessage());
	         }
	         return response;
	     
	    }
	    
	    public Response getSingleData()
	    {
	    	 reqSpec = RestAssured.given().baseUri("https://api.github.com/").basePath("repos/RadhiThamilselvan/Simpleproj");
	         reqSpec.header("Authorization","Bearer " +token);
	         reqSpec.contentType(ContentType.JSON);
	         
	         Response response = null;
	         try {
	             response = reqSpec.when().get();
	            
	             //response
	             //response.then().log().all();
	         } catch (Exception e) {
	             Assert.fail("Get data is failing due to :: " + e.getMessage());
	         }
	         return response;
	     
	    }
	    public Response getInvalidSingleData()
	    {
	    	reqSpec = RestAssured.given().baseUri("https://api.github.com/").basePath("repos/RadhiThamilselvan/NoSuchRepo");
	         reqSpec.header("Authorization","Bearer " +token);
	         reqSpec.contentType(ContentType.JSON);
	         
	         Response response = null;
	         try {
	             response = reqSpec.when().get();
	            
	             
	         } catch (Exception e) {
	             Assert.fail("Get data is failing due to :: " + e.getMessage());
	         }
	         return response;
	     
	    }
	    
	    public Response addData(AddDataRequest addDataRequest) {
	        reqSpec = RestAssured.given().baseUri("https://api.github.com/").basePath("user/repos");
	        reqSpec.header("Authorization","Bearer " +token);
	         reqSpec.contentType(ContentType.JSON);
	        Response response = null;
	        try {
	            log.info("Adding below data :: " + new ObjectMapper().writeValueAsString(addDataRequest));
	            System.out.println("Adding below data :: " + new ObjectMapper().writeValueAsString(addDataRequest));
	            
	            reqSpec.body(new ObjectMapper().writeValueAsString(addDataRequest)); //Serializing addData Request POJO classes to byte stream
	            response = reqSpec.when().post();
	            
	        } catch (Exception e) {
	            Assert.fail("Add data functionality is failing due to :: " + e.getMessage());
	        }
	        return response;
	    }
	    
	    public Response UpdateData(UpdateDataRequest updateDataRequest)
	    {
	    	reqSpec = RestAssured.given().baseUri("https://api.github.com/").basePath("repos/RadhiThamilselvan/SimpleProj");
	        reqSpec.header("Authorization","Bearer " +token);
	         reqSpec.contentType(ContentType.JSON);
	        Response response = null;
	        try {
	        System.out.println("Adding below data :: " + new ObjectMapper().writeValueAsString(updateDataRequest));
            
            reqSpec.body(new ObjectMapper().writeValueAsString(updateDataRequest)); //Serializing addData Request POJO classes to byte stream
            response = reqSpec.when().patch();
            
        } catch (Exception e) {
            Assert.fail("Add data functionality is failing due to :: " + e.getMessage());
        }
        return response;
	    	
	    }
	    
	    public Response DeleteData()
	    {
	    	reqSpec = RestAssured.given().baseUri("https://api.github.com/").basePath("repos/RadhiThamilselvan/Practice2");
	        reqSpec.header("Authorization","Bearer " +token);
	         reqSpec.contentType(ContentType.JSON);
	        Response response = null;
	        response = reqSpec.when().delete();
	        return response;
	        
	        
	    }
	    public HashMap<String, String> getHeaders() {
	        HashMap<String, String> headers = new HashMap<>();
	        headers.put("Content-Type", "application/json");
	        headers.put("Authorization","Bearer" +token);
	       
	        
	       
	        return headers;
	    }
}
