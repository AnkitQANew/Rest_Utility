package com.org.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Setup 

{   
    public static ExtentHtmlReporter objExtentHtmlReporter;
	
	public static ExtentReports objExtentsReport;
	
	public static ExtentTest objExtentTest;
	
	public SoftAssert objsoftassert;
	
	

        
        
	public String Read_File(String Keyname) throws IOException 
    {
    	FileInputStream objFileInputStream = new FileInputStream(".//config.properties"); 
    	
    	Properties objProperties = new Properties();
    	
    	objProperties.load(objFileInputStream);
    	
    	String key_value=objProperties.getProperty(Keyname);
        
    	return key_value;
	} 
       
        
	
	
	public String[]  Read_Properties(String Keyname) throws IOException 
	  {
		  
		  FileInputStream objFileInputStream = new FileInputStream(".//config.properties"); 
	    	
	    	Properties objProperties = new Properties();
	    	
	    	objProperties.load(objFileInputStream);
	    	
	    	String [] user_ids=objProperties.getProperty(Keyname).split(";");
	        
	         return user_ids ;
	  }    
        
        
        
        public void Verificatin() 
        {	
            objsoftassert= new SoftAssert();
            
  		}
	
}
