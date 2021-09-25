package com.org.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import org.slf4j.LoggerFactory;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestUtilities extends Setup
{
  
	public   static RequestSpecification REQ_SPC;
	public   static JSONObject obj_json;
	public    Response API_RESP;
	String   user_details;
	String   Invalid_user_details;
	public   int   NewlyCreated_UserID;
	//ResourceBundle objResourceBundle;

	  /*public static ExtentHtmlReporter HtmlReporter;
	  public static ExtentReports extent;
	  public static ExtentTest    test;
	  */
	
	
	
	      Setup objSetup = new Setup();
	
	
	      public int Add_New_User(String FIRST_NAME,String MIDDLE_NAME,String LAST_NAME, String DATE_OF_BIRTH) throws IOException  
	
	     {	
	    	
	    	  
	    	  //ResourceBundle objResourceBundle = ResourceBundle objResourceBundle.get 
	    	  
	    	  REQ_SPC=RestAssured.given();
	    	  
	    	  REQ_SPC.header("Content-type","application/json");
	    	  
	    	  obj_json = new JSONObject();
	    	  obj_json.put("first_name", FIRST_NAME);
	    	  obj_json.put("middle_name", MIDDLE_NAME);
	    	  obj_json.put("last_name",LAST_NAME);
	    	  obj_json.put("date_of_birth", DATE_OF_BIRTH);
	    	  
	    	  REQ_SPC.body(obj_json.toString());
	    	  
	    	 //String URI= objResourceBundle.getString("StudentDetails_URI");
	    	  
	    	  String create_new_entry=objSetup.Read_File("AddNewEntity_Endpoint");
	    	  
	    	  
	    	  
	    	  API_RESP=REQ_SPC.post(create_new_entry);
	    	  
	    	  int response_code=API_RESP.getStatusCode();
	    	  
	    	  System.out.println("New User is created and the response code is   "+response_code);
	    	  
	    	  String response_body=API_RESP.asString();
	    	  
	    	  System.out.println("New User is created and the response body is    "+response_body);
	    	  
	    	  //Thread.sleep(3000);
	    	  
	    	 
	    	  return response_code;
	    	  
	    	 
		
	     }
	      
	      public int Get_Specific_User_Details(int user_id) throws InterruptedException, IOException 
	      {
                  // System.out.println("*****"+user_id+"*******************");
                   
              String userID = Integer.toString(user_id);
	    	 
	    	  String GetUserDetails_URL=objSetup.Read_File("GetSpecificUser_Endpoint");
	    	  
	    	  
	    	  
	    	  String SpecificUserDetails =GetUserDetails_URL+userID;
	    	  
	    	  System.out.println("the updated URI is  "+SpecificUserDetails);
	    	  
	    	  
	    	  API_RESP=RestAssured.get(SpecificUserDetails);
	    	  
	    	  int response_code=API_RESP.getStatusCode();
	    	  
	    	  System.out.println("API is fetching the existing user details and the status code is  "+response_code);
	    	  
	    	  String response_body=API_RESP.asString();
	    	  
	    	  Thread.sleep(3000);
	    	  
	    	  System.out.println("API is fetching the existing user details and the response body is "+response_body);
	    	  
	    	  Thread.sleep(3000);
	    	  return response_code;
		  }
	      
	      
	         public int Updaet_User_Details(String Uid_id,String FIRST_NAME,String MIDDLE_NAME,String LAST_NAME, String DATE_OF_BIRTH) throws IOException 
	         {	
	        	 //user_details = StudentDetails_URI+Uid_id;
	        	 
	        	 String GetUserDetails_URL =objSetup.Read_File("Update_existingEntity_Endpoint");
	        	 
	        	 String SpecificUserDetails =GetUserDetails_URL+Uid_id;
		    	  
		    	 System.out.println("the updated URI is  "+SpecificUserDetails);
		    	  
	        	 
	        	 //System.out.println("the updated URI is  "+user_details);
	        	 
	        	 REQ_SPC=RestAssured.given();
	        	 
	        	 REQ_SPC.header("Content-type","application/json");
	        	 
	        	  obj_json = new JSONObject();
	        	  obj_json.put("id", Uid_id);
		    	  obj_json.put("first_name", FIRST_NAME);
		    	  obj_json.put("middle_name", MIDDLE_NAME);
		    	  obj_json.put("last_name",LAST_NAME);
		    	  obj_json.put("date_of_birth", DATE_OF_BIRTH);
		    	  
		    	  REQ_SPC.body(obj_json.toString());
		    	  
		    	  
		    	  API_RESP=REQ_SPC.put(SpecificUserDetails);
		    	  
		    	 
		    	  
		    	  int response_code=API_RESP.getStatusCode();
		    	  
		    	  if(response_code ==200)
		    	  {
		    	  System.out.println("API is updating the  existing user details and the status code is  "+response_code);
		    	  
		    	   System.out.println("The Response Body is   "+API_RESP.asString());
		    	  
		    	  }
		    	  
		    	  else 
		    	  { 
		    		  System.out.println("The user is not exist in the DB and API returns the serpense code   "+response_code);
		    	  }
		    	  
		    	  
		    	  
	        	  return response_code;
			 }
	      
	      
	         /*public boolean Get_Specific_User_Details_with_Invalid_USERID(String Invalid_UserID) 
	         {	
	        	 
		    	 
			 */
	         
	         public int Delete_User(String User_ID) throws IOException 
	         
	         
	         {
	        	  REQ_SPC=RestAssured.given();
		    	  
		    	  REQ_SPC.header("Content-type","application/json");
	        	 
	        	 
                 String GetUserDetails_URL =objSetup.Read_File("Delete_Entry_Endpoint");
	        	 
	        	 String SpecificUserDetails =GetUserDetails_URL+User_ID;
		    	  
		    	 System.out.println("the updated URI is  "+SpecificUserDetails); 
		    	 
		    	 API_RESP=REQ_SPC.delete(SpecificUserDetails);
		    	  
		    	 
		    	  
		    	  int response_code=API_RESP.getStatusCode();
		    	  
		    	  if(response_code ==200)
		    	  {
		    	  System.out.println("API is deleting the  existing user  and the status code is  "+response_code);
		    	  
		    	   System.out.println("The Response Body is   "+API_RESP.asString());
		    	  
		    	  }
		    	  
		    	  else 
		    	  { 
		    		  System.out.println("The user is not exist in the DB and API returns the serpense code   "+response_code);
		    	  }
	        	 
	        	 
		    	  return response_code;
	         }
	      
	         
	         
	         
	         
	      //_ - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //  
	     //_ - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //    
	    //_ - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //    
	   //_ - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - // 
	  //_ - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - // 
	 //_ - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - // 
	         
	         
	         public int User_Creation_through_JSON() throws IOException 
	         
	        {    
	        	 String create_new_entry=objSetup.Read_File("AddNewEntity_Endpoint");
	        	 
	        	 
	        	 REQ_SPC=RestAssured.given();
		    	  
		    	 REQ_SPC.header("Content-type","application/json");
		    	 
		    	 File objfile = new File(".//CreateUser.json");
		         
		    	 REQ_SPC.body(objfile);
		    	 
		    	 API_RESP =REQ_SPC.post(create_new_entry);
		    	 
		    	 int response_code =API_RESP.getStatusCode();
		    	 
		    	 if(response_code ==201)
		    	  {
		    	  System.out.println("User creation is suscessful through external file and the response code is --   "+response_code);
		    	  
		    	   System.out.println("The Response Body is   "+API_RESP.asString());
		    	  
		    	  }
		    	  
		    	  else 
		    	  { 
		    		  System.out.println("The user is not exist in the DB and API returns the serpense code   "+response_code);
		    	  }
	        	 
		    	 
		    	 
		    	 return response_code;
		    	 
		    	 
			}
	         
	         
	         
	         
	         public void  get_MultipleUser_Status(String[] data) throws IOException 
	         {    
	        	 
	        	  String base_url=objSetup.Read_File("GetSpecificUser_Endpoint");
	        	  //String [] data =objSetup.Read_Properties("Active_IDs");
	        	  
	        	  
	        	  /*for(int i=0;i<data.length;i++)
	        		  
	        	  {
	        		  System.out.println("the IDs ar   "+data[i]);
	        		  
	        	  }
	        	  */
	        	  for(int i=0;i<data.length;i++)
             {
	        		  
	      		    String URI=base_url+data[i];
	      		    
	      		    System.out.println("The URI is    "+URI);
	      		    
	      		    
	      		    API_RESP = RestAssured.get(URI);
	      		   
	      		   int response_code =API_RESP.getStatusCode();
	      		  
	      		 if(response_code ==200)
		    	  {
		    	  System.out.println("User is present in the system    and the response code is  "+response_code);
		    	  
		    	   System.out.println("The Response Body is   "+API_RESP.asString());
		    	  
		    	  }
		    	  
	      		 else {
	      			 
	      			 
	      			   System.out.println("User is not present in the system");
	      		       }
                 }
	        	  
	        	  System.out.println("TC_006 has passed and response code is   ");
	        	  
	        	  System.out.println("                                                            ");
	        	 
			 }
	         
	         
	}
