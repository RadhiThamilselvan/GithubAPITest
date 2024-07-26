package com.github.tests.CRUD;

import java.util.List;


import com.github.utils.EnvironmentDetails;

import org.apache.http.HttpStatus;
import com.github.utils.ExtentReportsUtility;

import com.github.requestpojo.AddDataRequest;
import com.github.requestpojo.UpdateDataRequest;
import com.github.responsepojo.AddDataResponse;
import com.github.utils.JsonSchemaValidate;
import com.github.utils.TestDataUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.base.BaseTest;
import com.github.javafaker.Faker;
import com.github.responsepojo.GetDataResponse;
import com.github.responsepojo.Owner;
import com.github.responsepojo.UpdateDataResponse;
import com.github.base.APIHelper;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

public class ValidateGetAddUpdateDeleteData extends BaseTest{
	APIHelper apiHelper;
    String name,description,homepage,privatee;
    private Faker faker;
    ExtentReportsUtility report=ExtentReportsUtility.getInstance();

    @BeforeClass
    public void beforeClass() {
        faker = new Faker();
        apiHelper = new APIHelper();
       
    }

    @Test(priority = 1, description = "Get single data functionality")
    public void validateGetSingleRepo() {

        Response data = apiHelper.getSingleData();
        GetDataResponse getDataResponse = data.getBody().as(new TypeRef<GetDataResponse>() {
        });
        Assert.assertEquals(data.getStatusCode(), HttpStatus.SC_OK, "Get data functionality is working as expected.");
        Assert.assertEquals(getDataResponse.full_name,EnvironmentDetails.getProperty("full_name"));
        Assert.assertEquals(getDataResponse.default_branch,"master");
        Assert.assertEquals(data.getContentType(),"application/json; charset=utf-8");
        System.out.print(getDataResponse.toString());
        
     }
    @Test(priority = 2, description = "Get all data functionality")
    public void validateGetAllRepo() {
	   Response response=apiHelper.getData();
	   List<GetDataResponse> getDataResponseList = response.getBody().as(new TypeRef<List<GetDataResponse>>() {
       });
        System.out.print(apiHelper.getData().asPrettyString());
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Get data functionality is not working as expected.");
        System.out.println("total number of repos:"+getDataResponseList.size());
        for(GetDataResponse response1:getDataResponseList)
        {
        	if(response1.getPrivatee().equals("false")) {
        		System.out.println(response1.toString());
        	}
        }
        Assert.assertEquals(response.getContentType(),"application/json; charset=utf-8");
     }

    @Test(priority = 3, description = "Get invalid data functionality")
    public void validatInvalidSingleRepo() {
        
        Response data = apiHelper.getInvalidSingleData();
        GetDataResponse getDataResponse = data.getBody().as(new TypeRef<GetDataResponse>() {
        });
        Assert.assertEquals(data.getStatusCode(), HttpStatus.SC_NOT_FOUND, "invalid data functionality is working as expected.");
       
        System.out.print(data.body().asString());
        Assert.assertEquals(TestDataUtils.getProperty("message"),"Not Found");
        
     }
    @Test(priority = 4, description = "Addrepo data functionality")
    public void validateAddDataFunctionality() {
        name = "Good morning1"; 
        description = "This is your first repoo1";
        homepage="https://github.com";
        privatee="false";
        AddDataRequest addDataRequest = AddDataRequest.builder().name(name).description(description).homepage(homepage).privatee(privatee).build();
        Response response = apiHelper.addData(addDataRequest);
     
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED, "Add data functionality is not working as expected.");
        System.out.println(response.getStatusCode()); 
        System.out.println("Login:"+response.as(AddDataResponse.class).owner.getLogin()); 
        System.out.println("User:"+response.as(AddDataResponse.class).owner.getType()); 
       
     

    }
    @Test(priority = 5, description = "Add invalid repo functionality")
    public void validateAddInvalidDataFunctionality() {
        name = "HelloGithubHiii"; 
        description = "This is your first repoo";
        homepage="https://github.com";
        privatee="false";
        AddDataRequest addDataRequest = AddDataRequest.builder().name(name).description(description).homepage(homepage).privatee(privatee).build();
        Response response = apiHelper.addData(addDataRequest);
        
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_UNPROCESSABLE_ENTITY, "Invalid Data");
        System.out.println(response.asPrettyString());
   
        
     

    }
    @Test(priority = 6, description = "Update data functionality")
    public void validateUpdateDataFunctionality() {
        name = "SimpleProject123"; 
        description = "my repository created using apis after updatee";
        
        privatee="false";
        UpdateDataRequest updateDataRequest = UpdateDataRequest.builder().name(name).description(description).privatee(privatee).build();
        Response response = apiHelper.UpdateData(updateDataRequest);
     
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Update data functionality is not working as expected.");
        Assert.assertEquals(response.as(UpdateDataResponse.class).name,name); 
    }
    @Test(priority=7,description="delete repo functionality")
    public void validateDeleteRepo()
    {
    	 Response response = apiHelper.DeleteData();
    	 Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NOT_FOUND, "Delete data functionality is not working as expected.");
         System.out.println(response.body().asString());
    	 
    }
       
     

    
    
}
