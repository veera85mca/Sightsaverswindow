package com.pellu.sight.screenshot;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.pellu.sight.initialize.Driverinitialize;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


import io.appium.java_client.android.Activity;

public class Failedscreen extends Driverinitialize{

public static String getmyscreen(String dateform) throws IOException
{
	File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	String imagepath=System.getProperty("user.dir")+File.separator+"Patientregreports"+File.separator+dateform+".png";
	File des=new File(imagepath);
	FileUtils.copyFile(src, des);
	return imagepath;
}
public static void createtestreports(String impath, String error)
{
	log.log(LogStatus.FAIL, error);
	String pngfile=log.addScreenCapture(impath);
	log.log(LogStatus.FAIL, "Test case Failed", pngfile);
	//Activity ac=new Activity("", "md5833264e69c260a889d421f0375023639.WorklistActivity");
	//driver.startActivity(ac);
}
public static void createsuccessreport(String name)
{
	log.log(LogStatus.PASS, "Test case Passed", name);
}
public static void createskippedreport(String name)
{
	log.log(LogStatus.SKIP, "Test case skipped", name);
}
}
