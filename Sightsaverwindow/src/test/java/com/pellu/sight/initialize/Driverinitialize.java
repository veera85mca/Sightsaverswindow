package com.pellu.sight.initialize;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Driverinitialize 
{
public static AndroidDriver driver=null;
public static ExtentReports report=null;
public static ExtentTest log=null;
public static String reportpath;

public static void initiatedrive(String patientreg) throws MalformedURLException
{
	  DesiredCapabilities cap=new DesiredCapabilities();
	  cap.setCapability("platformName", "Android");
	  cap.setCapability("deviceName", "Lenova PB1-750M");
	  cap.setCapability("platformVersion", "6.0.1");
	  cap.setCapability("appPackage", "Sightsavers.Sightsavers");
	  cap.setCapability("appActivity", "md52d7fde6ba0664a6789700d1cc1513c89.MainActivity");
	  driver=new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), cap);
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	 
	  reportpath=patientreg;
	  if(reportpath.equalsIgnoreCase("Full patient registration"))
	  {
		  report=new ExtentReports(System.getProperty("user.dir")+File.separator+"Patientregreports"+File.separator+"Patientregistration.html");
	  }else if (reportpath.equalsIgnoreCase("Patient and screenning")) {
		  report=new ExtentReports(System.getProperty("user.dir")+File.separator+"Multiregisreports"+File.separator+"Registerscreen.html");
	  }else if (reportpath.equalsIgnoreCase("Casesheet")) {
		  report=new ExtentReports(System.getProperty("user.dir")+File.separator+"Multicasereports"+File.separator+"Registerscreen.html");
	  }else if (reportpath.equalsIgnoreCase("Glass prescription")) {
		  report=new ExtentReports(System.getProperty("user.dir")+File.separator+"Multiglassreports"+File.separator+"Registerscreen.html");
	  }
}	
}
