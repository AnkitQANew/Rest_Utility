package com.org.Tests;

import java.io.IOException;
import java.util.ResourceBundle;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.org.Base.RestUtilities;
import com.org.Base.Setup;

import junit.framework.Assert;

public class TestCase_UserManagement_Operations extends RestUtilities
{

	
	
	//RestUtilities objRestUtilities;
    TestCase_UserManagement_Operations objTestCase_UserCreation;
	//ResourceBundle objResourceBundle;
	SoftAssert objsoftassert;
	int API_StatusCode;
	RestUtilities objRestUtilities = new RestUtilities();
	Setup objSetup = new Setup();
	
	
	
	
	//  TC_001 -- creating a new user and verifying the response code.
	
	@Test(priority=1)
	public void TC_001() throws IOException 
	{
		boolean TC_Status = false;
		//RestUtilities objRestUtilities = new RestUtilities();
		//extent.createTest("--TC_001--") ;
		String FirstName=objSetup.Read_File("first_name");
		String MiddleName=objSetup.Read_File("middle_name");
		String LastName=objSetup.Read_File("last_name");
		String Date_of_Birth=objSetup.Read_File("date_of_birth");
		
		API_StatusCode=objRestUtilities.Add_New_User(FirstName,MiddleName,LastName,Date_of_Birth);
		
		
		//objsoftassert.assertEquals(API_StatusCode, 201);
		Assert.assertEquals(201, API_StatusCode);
		
		System.out.println("TC_001 is passed and the response code is   "+API_StatusCode);
		System.out.println("                                                            ");
			
	}
	
    // TC_002 -- Fetching the any specific user details based on the Used_id
	@Test(priority=2)
	public void TC_002() throws InterruptedException, IOException 
	{   
		
		
		String exixting_user= objSetup.Read_File("Active_User_ID");
		
		Integer UserID = Integer.valueOf(exixting_user);
		
		System.out.println("This is TC_002 and Existing user id is ---"+UserID);
		
		API_StatusCode=objRestUtilities.Get_Specific_User_Details(UserID);
		
		Assert.assertEquals(200, API_StatusCode);
		
		System.out.println("TC_002 has passed and response code is  "+API_StatusCode);
		
		System.out.println("                                                            ");

	}
	
	
	// TC 003 -- Updating all the details of an existing user  with valid user_ID
	@Test(priority=3)
	public void TC_003() throws IOException
	{	
		
		String exixting_user= objSetup.Read_File("Active_User_ID");
		String FirstName=objSetup.Read_File("first_name_New");
		String MiddleName=objSetup.Read_File("middle_name_New");
		String LastName=objSetup.Read_File("last_name_New");
		String Date_of_Birth=objSetup.Read_File("date_of_birth_New");
		
		API_StatusCode=objRestUtilities.Updaet_User_Details(exixting_user,FirstName,MiddleName,LastName,Date_of_Birth);
		
		Assert.assertEquals(200, API_StatusCode);
		
		System.out.println("TC_003 has passed and response code is  "+API_StatusCode);
		
	}
	
	    
	      //////////////////// TC 004 -- Deleting any existing user  with valid user_ID ///////////////////////////////
	    @Test(priority=4)
	    public void TC_004() throws IOException 
	    {
		
	    	String exixting_userID= objSetup.Read_File("Active_User_ID2");
	    	
	    	API_StatusCode=objRestUtilities.Delete_User(exixting_userID);
	    	
	    	Assert.assertEquals(200, API_StatusCode);
	    	
	    	
	    	System.out.println("TC_004 has passed and response code is  "+API_StatusCode);

	    	System.out.println("                                                            ");
	    }
	    
	    
	    
////////////////////TC 005 -- Creating a new user and taking the request body from the JSON file ///////////////////////////////
	    
	         @Test(priority=5)
	         public void TC_005() throws IOException 
	         
	         {
	        	 
	         API_StatusCode= objRestUtilities.User_Creation_through_JSON();
             
	         Assert.assertEquals(201, API_StatusCode);
		    	
		    	
		     System.out.println("TC_005 has passed and response code is  "+API_StatusCode);
		     
		     System.out.println("                                                            ");
			
	        	
		     
			 }
	         
        ///////////////////TC 006 --Fetching the multiple user details based on there user IDs  ///////////////////////////////
	 	    
	         
	         @Test(priority=6)
	         public void TC_006() throws IOException 
	    
	         {
	        	 
	        	 //String UserDetail_URL=objSetup.Read_File("GetSpecificUser_Endpoint");
	        	 String [] data =objSetup.Read_Properties("Active_IDs");
	        	 
	        	 //System.out.println(data);
	        	 
	        	 objRestUtilities.get_MultipleUser_Status(data);
	        	 
	         } 
	        	 
	        
	        	 
	        	 
	        	 
	        	 
	        	 
	        	 
	        	 
	         
}
	

