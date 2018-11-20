package com.pellu.sight.patientinfopage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.TouchAction;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import com.pellu.sight.initialize.Driverinitialize;
import com.relevantcodes.extentreports.LogStatus;


import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.touch.offset.PointOption;

public class Patienttestpage extends Driverinitialize{

public static FileInputStream fileid;
public static Properties probid;
public static String dropdownloca="android.widget.CheckedTextView";
public static String listviewloca="android.widget.ListView";
public static String scrollclasswindow="android.widget.ScrollView";
public static SoftAssert asser=null;
public static String screencode;
private static int bmiweight;
private static double bmiheight;
private static double bmiresultvalue;
private static String vaxpath1="//android.widget.Spinner[@resource-id='Sightsavers.Sightsavers:id/";
private static String vaxpath2="']//android.widget.TextView[@index='0']";
private static String patientjob;
private static String roadacc;
private static String education;


public static void login_user(String user) throws IOException
{	
	idprob();
	getidlocator("Sight_Login_username").clear();
	getidlocator("Sight_Login_username").sendKeys(user);
}
public static void login_pass(String pass)
{
	hidekey();
	getidlocator("Sight_Login_password").sendKeys(pass);
}
public static void login_provider(String provider)
{
	hidekey();
	getidlocator("Sight_Login_provider").clear();
	getidlocator("Sight_Login_provider").sendKeys(provider);
	hidekey();
	getidlocator("Sight_Login_Loginbutton").click();
}
public static void campdetail_modeltype(String modeltype)
{
	WebDriverWait wait=new WebDriverWait(driver, 35);
	wait.until(ExpectedConditions.visibilityOf(getidlocator("Sight_campdetails_camplocation")));
	if(modeltype.equalsIgnoreCase("Static Centre"))
	{
		getidlocator("Sight_campdetails_Staticcentre").click();
	}else if (modeltype.equalsIgnoreCase("Camp")) {
		getidlocator("Sight_campdetails_camp").click();
	}
}
public static void campdetail_camplocation(String location)
{
	getidlocator("Sight_campdetails_camplocation").click();
	dropdown(location);
}
public static void campdetail_campdate(String camedate)
{
	getidlocator("Sight_campdetails_campdate").click();
	dropdown(camedate);
}
public static void campdetail_Remarks(String postponed, String postdate, String remark)
{
	if(postponed.equalsIgnoreCase("Yes"))
	{
		getidlocator("Sight_campdetails_postponedyes").click();
		getidlocator("Sight_campdetails_remarks").sendKeys(remark);
	}
	getidlocator("Sight_campdetails_savebutton").click();
	WebDriverWait wait1=new WebDriverWait(driver, 35);
	wait1.until(ExpectedConditions.visibilityOfElementLocated(By.className("android.widget.ImageButton")));
	driver.findElement(By.className("android.widget.ImageButton")).click();
	scrollbyelement("android.widget.FrameLayout", "Settings");
	driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='Settings']")).click();
	if(!getidlocator("Sight_Menu_setting_UIDmanual_check").isSelected())
	{
	getidlocator("Sight_Menu_setting_UIDmanual_check").click();
	}
	getidlocator("Sight_Menu_setting_Save").click();
}
public static void worklistregistration()
{
	/*Activity a=new Activity("PellucidCampManagement.PellucidCampManagement", "md5833264e69c260a889d421f0375023639.WorklistActivity");
	driver.startActivity(a);*/
	WebDriverWait wait1=new WebDriverWait(driver, 35);
	wait1.until(ExpectedConditions.visibilityOfElementLocated(By.className("android.widget.ImageButton")));
	driver.findElement(By.className("android.widget.ImageButton")).click();
	driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='Registration']")).click();
}
//patient registration details
public static void PatientUID(String UID)
{
	getidlocator("Sight_worklist_Patient_registration_UID").sendKeys(UID);	
}
public static void patientname(String name)
{
	getidlocator("Sight_worklist_Patient_registration_name").sendKeys(name);
}
public static void patientage(String age)
{
	getidlocator("Sight_worklist_Patient_registration_age").sendKeys(age);
}
public static void patientgender(String gender)
{
	if(gender.equalsIgnoreCase("Male"))
	{
		getidlocator("Sight_worklist_Patient_registration_sex_male").click();
	}else if (gender.equalsIgnoreCase("Female")) {
		getidlocator("Sight_worklist_Patient_registration_sex_female").click();
	}
}
public static void patientmobileno(String mobileno)
{
	getidlocator("Sight_worklist_Patient_registration_mobileno").sendKeys(mobileno);
}
public static void patientaddress(String address)
{
	getidlocator("Sight_worklist_Patient_registration_sex").click();
	getidlocator("Sight_worklist_Patient_registration_address").sendKeys(address);
}
public static void patientstate(String state) throws InterruptedException
{
	asser=new SoftAssert();
	hidekey();
	//scrollbyelement(scrollclasswindow, "Educational Qualification");
	//WebElement ele=getidlocator("Camp_worklist_Patient_registration_state");
	String[] sta=state.split("\\s+"); 
	getidlocator("Sight_worklist_Patient_registration_state").sendKeys(sta[0]);
	Thread.sleep(2000);
	//new PointOption<>();
	//new TouchAction<>(driver).press(PointOption.point(x+15, y+35));
	//new TouchActions(driver).down(x+15, y+35).release().perform();
	/*driver.pressKeyCode(AndroidKeyCode.KEYCODE_PAGE_DOWN);
	Thread.sleep(2000);
	driver.pressKeyCode(AndroidKeyCode.KEYCODE_PAGE_DOWN);
	driver.pressKeyCode(AndroidKeyCode.KEYCODE_TAB);
	Thread.sleep(2000);
	driver.pressKeyCode(AndroidKeyCode.KEYCODE_PAGE_DOWN);
	Thread.sleep(2000);*/
	getidlocator("Sight_worklist_Patient_registration_district_text").click();
	Thread.sleep(2000);
	asser.assertEquals("State: "+getidlocator("Sight_worklist_Patient_registration_state").getText(), "State: "+state);
	//driver.pressKeyCode(AndroidKeyCode.ENTER);
}
public static void patientdistrict(String district) throws InterruptedException
{
	hidekey();
	String[] dis=district.split("\\s+");
	getidlocator("Sight_worklist_Patient_registration_district").sendKeys(dis[0]);
	Thread.sleep(2000);
	/*driver.pressKeyCode(AndroidKeyCode.KEYCODE_PAGE_DOWN);
	driver.pressKeyCode(AndroidKeyCode.KEYCODE_TAB);
	driver.pressKeyCode(AndroidKeyCode.ENTER);*/
	//driver.pressKeyCode(AndroidKeyCode.KEYCODE_PAGE_DOWN);
	//driver.pressKeyCode(AndroidKeyCode.KEYCODE_MOVE_END);
	driver.findElement(By.xpath("//android.widget.TextView[@text='Taluk']")).click();
	Thread.sleep(2000);
	asser.assertEquals("District: "+getidlocator("Sight_worklist_Patient_registration_district").getText(), "District: "+district);
}
public static void patienttaluk(String taluk)
{
	getidlocator("Sight_worklist_Patient_registration_taluk").sendKeys(taluk);
	hidekey();
}
public static void patientaadhar(String aadhar)
{
	getidlocator("Sight_worklist_Patient_registration_aadhar").sendKeys(aadhar);
	hidekey();
}
public static void patientlicenseno(String licenseno)
{
	getidlocator("Sight_worklist_Patient_registration_license").sendKeys(licenseno);
	hidekey();
}
public static void patientrenewalmonth(String month) throws InterruptedException
{
	getidlocator("Sight_worklist_Patient_registration_Month").click();
	scrollbyelementlist(listviewloca, month);
}
public static void patientrenewalyear(String year)
{
	getidlocator("Sight_worklist_Patient_registration_Year").sendKeys(year);
	getidlocator("Sight_worklist_Patient_registration_job_text").click();
}
public static void patientjob(String job, String years)
{
	patientjob=job;
	getidlocator("Sight_worklist_Patient_registration_job").click();
	dropdown(job);
	if(job.equalsIgnoreCase("Driver"))
	{
		getidlocator("Sight_worklist_Patient_registration_job_drivingyears").sendKeys(years);
	}
}
public static void patienteducationquali(String studies, String others)
{
	education=studies;
	getidlocator("Sight_worklist_Patient_registration_education").click();
	String scrollcontain="new UiSelector().className(\""+listviewloca+"\")";
	String scrollele="new UiSelector().textContains(\""+studies+"\")";
	driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable("+scrollcontain+").scrollIntoView("+scrollele+")")).click();
	if(studies.equalsIgnoreCase("Others"))
	{
		getidlocator("Sight_worklist_Patient_registration_education_others").sendKeys(others);
	}
}
public static void patientmonthsemployee(String month)
{
	getidlocator("Sight_worklist_Patient_registration_monthsemployee").sendKeys(month);
	hidekey();
}
public static void patienttypevehicle(String vehicle)
{
	getidlocator("Sight_worklist_Patient_registration_typevehicle").click();
	dropdown(vehicle);
}
public static void patienttyperoute(String route)
{
	getidlocator("Sight_worklist_Patient_registration_typeroute").click();
	dropdown(route);
}
public static void patientcaste(String caste)
{
	getidlocator("Sight_worklist_Patient_registration_caste").click();
	dropdown(caste);
}
public static void patientincome(String income)
{
	getidlocator("Sight_worklist_Patient_registration_income").click();
	dropdown(income);
	if(patientjob.equalsIgnoreCase("driver") || education.equalsIgnoreCase("Never went to school/can read") || education.equalsIgnoreCase("Never went to school/cannot read") || education.equalsIgnoreCase("Others"))
	{
		scrollbyelement(scrollclasswindow, "Save and Proceed");
	}
}
public static void patientvehicleinsur(String vehicleinsur)
{
	if(patientjob.equalsIgnoreCase("driver"))
	{
	radiobutton(getidlocator("Sight_worklist_Patient_registration_vehicleinsurance_yes"), getidlocator("Sight_worklist_Patient_registration_vehicleinsurance_no"), vehicleinsur, "vehicleinsurence: ");
	}
}
public static void patientlifeinsur(String lifeinsur)
{
	radiobutton(getidlocator("Sight_worklist_Patient_registration_lifeinsurance_yes"), getidlocator("Sight_worklist_Patient_registration_lifeinsurance_no"), lifeinsur, "lifeinsurence: ");
}
public static void patienthealthinsur(String healthinsur)
{
	getidlocator("Sight_worklist_Patient_registration_healthinsurance").click();
	dropdown(healthinsur);
}
public static void patientknowcamp(String knowcamp)
{
	getidlocator("Sight_worklist_Patient_registration_knowaboutcamp").click();
	dropdown(knowcamp);
}
public static void patientinfo_save()
{
	getidlocator("Sight_worklist_Patient_registration_Saveandproceed").click();
}




//medical examination details
public static void mediexam_bpSystolic(String bpsystolic)
{
	getidlocator("Sight_medicalexamination_bloodpressure_Systolic").sendKeys(bpsystolic);
	hidekey();
}
public static void mediexam_bloodsugar(String sugar)
{
	getidlocator("Sight_medicalexamination_bloodsugar").sendKeys(sugar);
	hidekey();
}
public static void BMIweight(String weight)
{
	bmiweight=Integer.parseInt(weight);
	getidlocator("Sight_BMI_Weight").sendKeys(weight);
	hidekey();
}
public static void BMIheight(String height)
{
	bmiheight=Double.parseDouble(height);
	getidlocator("Sight_BMI_Height").sendKeys(height);
	hidekey();
	String scrollcontain="new UiSelector().className(\""+scrollclasswindow+"\")";
	String scrollele="new UiSelector().resourceIdMatches(\"Sightsavers.Sightsavers:id/txt_ScreeningResponseCode\")";
	driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable("+scrollcontain+").scrollIntoView("+scrollele+")"));	
}

//Screening test details
public static void screenresponse(String responsecode)
{
screencode=responsecode;
switch (responsecode) {
case "A - Screening negative Asymptomatic":
	getidlocator("Sight_Screenresponsecode_A").click();
	break;
case "B - Screening negative but Symptomatic":
	getidlocator("Sight_Screenresponsecode_B").click();
	break;
case "C - Only Distance vision problem":
	getidlocator("Sight_Screenresponsecode_C").click();
	break;
case "D - Only Near vision problem":
	getidlocator("Sight_Screenresponsecode_D").click();
	break;
case "E - Only Color vision problem":
	getidlocator("Sight_Screenresponsecode_E").click();
	break;
case "F - Both Distance and Near vision problem":
	getidlocator("Sight_Screenresponsecode_F").click();
	break;
case "G - Both Distance and Color vision problem":
	getidlocator("Sight_Screenresponsecode_G").click();
	break;
case "H - Both Near and Color vision problem":
	getidlocator("Sight_Screenresponsecode_H").click();
	break;
}
String scrollcontain="new UiSelector().className(\""+scrollclasswindow+"\")";
String scrollele="new UiSelector().resourceIdMatches(\"Sightsavers.Sightsavers:id/LL_MIQuestions\")";
driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable("+scrollcontain+").scrollIntoView("+scrollele+")"));	

/*	if(!getidlocator("Sight_Screen_Monthlyincome_familysupportearn_Text").isDisplayed())
{
	String scrollcontain2="new UiSelector().className(\""+scrollclasswindow+"\")";
	String scrollele2="new UiSelector().resourceIdMatches(\"Sightsavers.Sightsavers:id/rdo_CodeA\")";
	driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable("+scrollcontain2+").scrollIntoView("+scrollele2+")"));
	
	String scrollcontain1="new UiSelector().className(\""+scrollclasswindow+"\")";
	String scrollele1="new UiSelector().resourceIdMatches(\"Sightsavers.Sightsavers:id/LL_MIQuestions\")";
	driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable("+scrollcontain1+").scrollIntoView("+scrollele1+")"));
}*/

}


//Monthly Income Questions
public static void Monthlyquestions_salarycalculate(String salary)
{
	getidlocator("Sight_Screen_Monthlyincome_salarycalcualted").click();
	dropdown(salary);
}
public static void Monthlyquestions_holdbackamount(String amount)
{
	getidlocator("Sight_Screen_Monthlyincome_holdbackamount").sendKeys(amount);
	hidekey();
}
public static void Monthlyquestions_notemplyeemonth(String month)
{
	getidlocator("Sight_Screen_Monthlyincome_notemployee_month").click();
	dropdown(month);
}
public static void Monthlyquestions_nonworkingmonth(String month)
{
	getidlocator("Sight_Screen_Monthlyincome_nonworking_month").click();
	dropdown(month);
}
public static void Monthlyquestions_alteremplyee(String alteremp)
{
	radiobuttonNA(getidlocator("Sight_Screen_Monthlyincome_alteremployee_Yes"), getidlocator("Sight_Screen_Monthlyincome_alteremployee_No"), getidlocator("Sight_Screen_Monthlyincome_alteremployee_NA"), alteremp, "Alteremployee: ");
}
public static void Monthlyquestions_alterskill(String skill)
{
	radiobuttonNA(getidlocator("Sight_Screen_Monthlyincome_alterskills_Yes"), getidlocator("Sight_Screen_Monthlyincome_alterskills_No"), getidlocator("Sight_Screen_Monthlyincome_alterskills_NA"), skill, "Alterskill: ");
}
public static void Monthlyquestions_earnsupport(String earnsupport)
{
	radiobuttonNA(getidlocator("Sight_Screen_Monthlyincome_familysupportearn_Yes"), getidlocator("Sight_Screen_Monthlyincome_familysupportearn_No"), getidlocator("Sight_Screen_Monthlyincome_familysupportearn_NA"), earnsupport, "Familysupportearn: ");
	if(patientjob.equalsIgnoreCase("driver"))
	{
	String scrollcontain="new UiSelector().className(\""+scrollclasswindow+"\")";
	String scrollele="new UiSelector().resourceId(\"Sightsavers.Sightsavers:id/txt_judgingdistances\")";
	driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable("+scrollcontain+").scrollIntoView("+scrollele+")"));
	}else {
		String scrollcontain="new UiSelector().className(\""+scrollclasswindow+"\")";
		String scrollele="new UiSelector().resourceId(\"Sightsavers.Sightsavers:id/txtView_DistantObjects\")";
		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable("+scrollcontain+").scrollIntoView("+scrollele+")"));		
	}
}



//General Questions
public static void general1_medicalcheckup(String general1) throws InterruptedException
{
	radiobuttonNA(getidlocator("Sight_Screening_general_medicalcheckup_Yes"), getidlocator("Sight_Screening_general_medicalcheckup_No"), getidlocator("Sight_Screening_general_medicalcheckup_NA"), general1, "medicalcheckup: ");
}
public static void general2_diabetes(String general2)
{
	radiobuttonNA(getidlocator("Sight_Screening_general_diabetes_Yes"), getidlocator("Sight_Screening_general_diabetes_No"), getidlocator("Sight_Screening_general_diabetes_NA"), general2, "diabetes: ");
}
public static void general3_hypertention(String general3)
{
	radiobuttonNA(getidlocator("Sight_Screening_general_hypertension_Yes"), getidlocator("Sight_Screening_general_hypertension_No"), getidlocator("Sight_Screening_general_hypertension_NA"), general3, "hypertention: ");
}
public static void general4_smoke(String general4)
{
	radiobuttonNA(getidlocator("Sight_Screening_general_smoke_Yes"), getidlocator("Sight_Screening_general_smoke_No"), getidlocator("Sight_Screening_general_smoke_NA"), general4, "smoke: ");
}
public static void general5_alcohol(String general5)
{
	radiobuttonNA(getidlocator("Sight_Screening_general_alcohol_Yes"), getidlocator("Sight_Screening_general_alcohol_No"), getidlocator("Sight_Screening_general_alcohol_NA"), general5, "alcohol: ");
}
public static void general6_eyeexamin(String general6)
{
	radiobuttonNA(getidlocator("Sight_Screening_general_eyeexamination_Yes"), getidlocator("Sight_Screening_general_eyeexamination_No"), getidlocator("Sight_Screening_general_eyeexamination_NA"), general6, "eyeexamin: ");
}
public static void general7_distanceobject(String general7)
{
	radiobuttonNA(getidlocator("Sight_Screening_general_distantobjects_Yes"), getidlocator("Sight_Screening_general_distantobjects_No"), getidlocator("Sight_Screening_general_distantobjects_NA"), general7, "distanceobject: ");
}
public static void general8_distancedriving(String general8)
{
	if(patientjob.equalsIgnoreCase("driver"))
	{
	radiobuttonNA(getidlocator("Sight_Screening_general_distancedriving_Yes"), getidlocator("Sight_Screening_general_distancedriving_No"), getidlocator("Sight_Screening_general_distancedriving_NA"), general8, "distancedriving: ");
	}
}
public static void general9_trafficlightcolors(String general9) throws InterruptedException
{
	if(patientjob.equalsIgnoreCase("driver"))
	{
	String scrollcontain="new UiSelector().className(\""+scrollclasswindow+"\")";
	String scrollele="new UiSelector().resourceId(\"Sightsavers.Sightsavers:id/rdb_Govt\")";
	driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable("+scrollcontain+").scrollIntoView("+scrollele+")"));
	Thread.sleep(2000);
	radiobuttonNA(getidlocator("Sight_Screening_general_trafficlightcolors_Yes"), getidlocator("Sight_Screening_general_trafficlightcolors_No"), getidlocator("Sight_Screening_general_trafficlightcolors_NA"), general9, "trafficlightcolors: ");
	}
}
public static void general10_nightdriving(String general10)
{
	if(patientjob.equalsIgnoreCase("driver"))
	{
	radiobuttonNA(getidlocator("Sight_Screening_general_nightdriving_Yes"), getidlocator("Sight_Screening_general_nightdriving_No"), getidlocator("Sight_Screening_general_nightdriving_NA"), general10, "trafficlightcolors: ");
	}
}
public static void general11_wearglasses(String general11)
{
	if(patientjob.equalsIgnoreCase("driver"))
	{
		radiobuttonNA(getidlocator("Sight_Screening_general_wearglasses_Yes"), getidlocator("Sight_Screening_general_wearglasses_No"), getidlocator("Sight_Screening_general_wearglasses_NA"), general11, "wearglasses: ");
	}else {
		String scrollclass="new UiSelector().className(\""+scrollclasswindow+"\")";
		String scrollele="new UiSelector().resourceId(\"Sightsavers.Sightsavers:id/btn_ScreeningSave\")";
		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable("+scrollclass+").scrollIntoView("+scrollele+")"));
		radiobuttonNA(getidlocator("Sight_Screening_general_wearglasses_Yes"), getidlocator("Sight_Screening_general_wearglasses_No"), getidlocator("Sight_Screening_general_wearglasses_NA"), general11, "wearglasses: ");
	}
}
public static void general12_useglasses(String general12)
{
	radiobuttonNA(getidlocator("Sight_Screening_general_useglasses_Yes"), getidlocator("Sight_Screening_general_useglasses_No"), getidlocator("Sight_Screening_general_useglasses_NA"), general12, "useglasses: ");
}
public static void general13_nearhospital(String general13, String general14)
{
	radiobuttonNA(getidlocator("Sight_Screening_general_nearhospital_Yes"), getidlocator("Sight_Screening_general_nearhospital_No"), getidlocator("Sight_Screening_general_nearhospital_NA"), general13, "nearhospital: ");
	if(general13.equalsIgnoreCase("yes"))
	{
		switch (general14) {
		case "Govt":
			getidlocator("Sight_Screening_general_typehospital_govt").click();
			break;
		case "Pvt":
			getidlocator("Sight_Screening_general_typehospital_private").click();
			break;
		case "NGO":
			getidlocator("Sight_Screening_general_typehospital_ngo").click();
			break;
		case "Others":
			getidlocator("Sight_Screening_general_typehospital_others").click();
			break;
		}
	}
}
public static void general14_roadaccident(String accident, String twelvemonths)
{
	if(patientjob.equalsIgnoreCase("driver"))
	{
	radiobuttonNA(getidlocator("Sight_Screening_general_involoveroadaccident_Yes"), getidlocator("Sight_Screening_general_involoveroadaccident_No"), getidlocator("Sight_Screening_general_involoveroadaccident_Decline"), accident, "Roadaccident: ");
	if(accident.equalsIgnoreCase("Yes"))
	{
		radiobuttonNA(getidlocator("Sight_Screening_general_roadaccident_twelvemonths_Yes"), getidlocator("Sight_Screening_general_roadaccident_twelvemonths_No"), getidlocator("Sight_Screening_general_roadaccident_twelvemonths_NA"), twelvemonths, "Twelve Months: ");
	}
	String scrollclass="new UiSelector().className(\""+scrollclasswindow+"\")";
	String scrollele="new UiSelector().resourceId(\"Sightsavers.Sightsavers:id/btn_ScreeningSave\")";
	driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable("+scrollclass+").scrollIntoView("+scrollele+")"));
	}
}
public static void general15_firstaidkit(String firstaidkit) throws InterruptedException
{
	//driver.findElement(By.id("Sightsavers.Sightsavers:id/txt_FirstAid")).click();
	WebDriverWait wait=new WebDriverWait(driver, 15);
	wait.until(ExpectedConditions.elementToBeClickable(getidlocator("Sight_Screening_general_Firstaidkit_truck_Yes")));
	WebDriverWait wait1=new WebDriverWait(driver, 15);
	wait1.until(ExpectedConditions.elementToBeClickable(getidlocator("Sight_Screening_general_Firstaidkit_truck_No")));
	WebDriverWait wait2=new WebDriverWait(driver, 15);
	wait2.until(ExpectedConditions.elementToBeClickable(getidlocator("Sight_Screening_general_Firstaidkit_truck_NA")));
	radiobuttonNA(getidlocator("Sight_Screening_general_Firstaidkit_truck_Yes"), getidlocator("Sight_Screening_general_Firstaidkit_truck_No"), getidlocator("Sight_Screening_general_Firstaidkit_truck_NA"), firstaidkit, "Firstaidkit: ");
}
public static void general16_happyprofession(String happy, String happyway)
{
	if(happy.equalsIgnoreCase("Happy"))
	{
		getidlocator("Sight_Screening_general_happyprofession_happy").click();
		switch (happyway) {
		case "Adequate Income":
			getidlocator("Sight_Screening_general_happyway_Adequateincome").click();
			break;
		case "Adequate Freedom":
			getidlocator("Sight_Screening_general_happyway_Adequatefreedom").click();
			break;
		case "Visiting Places":
			getidlocator("Sight_Screening_general_happyway_visitingplaces").click();
			break;
		case "Adequate Friends":
			getidlocator("Sight_Screening_general_happyway_Adequatefriends").click();
			break;
		case "Stable Employment":
			getidlocator("Sight_Screening_general_happyway_stableemployment").click();
			break;
		}
	}else if (happy.equalsIgnoreCase("Somewhat Happy")) {
		getidlocator("Sight_Screening_general_happyprofession_somewhathappy").click();
	}else if (happy.equalsIgnoreCase("Not Happy")) {
		getidlocator("Sight_Screening_general_happyprofession_nothappy").click();
	}
}
public static void screeninfo_save()
{
	getidlocator("Sight_Screening_save").click();
}

//case sheet for camp
public static void casesheet_varightdistanceunaided(String rdua)
{
	String full=vaxpath1+probid.getProperty("Sight_casesheet_VA_Right_unaided_distance")+vaxpath2;
	caseva_dropvalidate(full, rdua, "varightdistanceunaided: ");
}
public static void casesheet_varightnearunaided(String rnud)
{
	String full=vaxpath1+probid.getProperty("Sight_casesheet_VA_Right_unaided_near")+vaxpath2;
	caseva_dropvalidate(full, rnud, "varightnearunaided: ");	
}
public static void casesheet_valeftdistanceunaided(String ldua)
{
	String full=vaxpath1+probid.getProperty("Sight_casesheet_VA_Left_unaided_distance")+vaxpath2;
	caseva_dropvalidate(full, ldua, "valeftdistanceunaided: ");	
}
public static void casesheet_valeftnearunaided(String rnud)
{
	String full=vaxpath1+probid.getProperty("Sight_casesheet_VA_Left_unaided_near")+vaxpath2;
	caseva_dropvalidate(full, rnud, "valeftnearunaided: ");
}
public static void casesheet_varightdistanceaided(String rda)
{
	String full=vaxpath1+probid.getProperty("Sight_casesheet_VA_Right_aided_distance")+vaxpath2;
	caseva_dropvalidate(full, rda, "varightdistanceaided: ");
}
public static void casesheet_varightnearaided(String rnd)
{
	String full=vaxpath1+probid.getProperty("Sight_casesheet_VA_Right_aided_near")+vaxpath2;
	caseva_dropvalidate(full, rnd, "varightnearaided: ");
}
public static void casesheet_valeftdistanceaided(String lda)
{
	String full=vaxpath1+probid.getProperty("Sight_casesheet_VA_Left_aided_distance")+vaxpath2;
	caseva_dropvalidate(full, lda, "valeftdistanceaided: ");
}
public static void casesheet_valeftnearaided(String rnd)
{
	String full=vaxpath1+probid.getProperty("Sight_casesheet_VA_Left_aided_near")+vaxpath2;
	caseva_dropvalidate(full, rnd, "valeftnearaided: ");
}
public static void casesheet_varightpinhole(String rightpinhole)
{
	String full=vaxpath1+probid.getProperty("Sight_casesheet_VA_Right_pinhole")+vaxpath2;
	caseva_dropvalidate(full, rightpinhole, "varightpinhole: ");
}
public static void casesheet_valeftpinhole(String leftpinhole)
{
	String full=vaxpath1+probid.getProperty("Sight_casesheet_VA_Left_pinhole")+vaxpath2;
	caseva_dropvalidate(full, leftpinhole, "valeftpinhole: ");
}
public static void casesheet_varightcolour(String rightcolour)
{
	String full=vaxpath1+probid.getProperty("Sight_casesheet_VA_Right_Colour")+vaxpath2;
	caseva_dropvalidate(full, rightcolour, "varightcolour: ");
}
public static void casesheet_valeftcolour(String leftcolour)
{
	String full=vaxpath1+probid.getProperty("Sight_casesheet_VA_Left_Colour")+vaxpath2;
	caseva_dropvalidate(full, leftcolour, "valeftcolour: ");
}
public static void casesheet_treatrefraction(String refraction)
{
	getidlocator("Sight_casesheet_treatment_refraction").click();
	dropdown(refraction);
}
public static void casesheet_wantrefer(String wantrefer, String referfor, String anyother, String referto)
{
	radiobutton(getidlocator("Sight_casesheet_wanttorefer_Yes"), getidlocator("Sight_casesheet_wanttorefer_No"), wantrefer, "wantrefer: ");
	if(wantrefer.equalsIgnoreCase("yes"))
	{
		getidlocator("Sight_casesheet_Referfor").click();
		dropdown(referfor);
		if(referfor.equalsIgnoreCase("Any Other"))
		{
			getidlocator("Sight_casesheet_Anyother").sendKeys(anyother);
			hidekey();
		}
		getidlocator("Sight_casesheet_ReferTo").click();
		dropdown(referto);
	}
}
public static void casesheetinfo_save()
{
	getidlocator("Sight_casesheet_save").click();
}

//Glass prescription for Sight
public static void Glass_rightdistance_sph(String sph)
{
	driver.rotate(ScreenOrientation.LANDSCAPE);
	if(glassvali(sph))
	{
	getidlocator("Sight_Glassprescription_Rightdistance_SPH").sendKeys(sph);
	hidekey();
	}
}
public static void Glass_rightdistance_cyl(String cyl)
{
	if(glassvali(cyl))
	{
	getidlocator("Sight_Glassprescription_Rightdistance_CYL").click();
	getidlocator("Sight_Glassprescription_Rightdistance_CYL").sendKeys(cyl);
	hidekey();
	}
}
public static void Glass_rightdistance_axis(String axis)
{
	if(glassvali(axis))
	{
	getidlocator("Sight_Glassprescription_Rightdistance_AXIS").click();
	getidlocator("Sight_Glassprescription_Rightdistance_AXIS").sendKeys(axis);
	hidekey();
	}
}
public static void Glass_rightdistance_va(String va)
{
	if(glassvali(va)) 
	{
	driver.findElement(By.xpath("//android.widget.Spinner[@resource-id='Sightsavers.Sightsavers:id/spn_VARightDistanceVA']")).click();
	scrollbyelementlistland(listviewloca, va);
	}
}
public static void Glass_leftdistance_sph(String sph)
{
	if(glassvali(sph))
	{
	getidlocator("Sight_Glassprescription_Leftdistance_SPH").click();
	getidlocator("Sight_Glassprescription_Leftdistance_SPH").sendKeys(sph);
	hidekey();
	}
}
public static void Glass_leftdistance_cyl(String cyl)
{
	if(glassvali(cyl))
	{
	getidlocator("Sight_Glassprescription_Leftdistance_CYL").click();
	getidlocator("Sight_Glassprescription_Leftdistance_CYL").sendKeys(cyl);
	hidekey();
	}
}
public static void Glass_leftdistance_axis(String axis) throws InterruptedException
{
	if(glassvali(axis))
	{
	getidlocator("Sight_Glassprescription_Leftdistance_AXIS").click();
	getidlocator("Sight_Glassprescription_Leftdistance_AXIS").sendKeys(axis);
	hidekey();
	}
}
public static void Glass_leftdistance_va(String va) throws InterruptedException
{
	if(glassvali(va))
	{
	getidlocator("Sight_Glassprescription_Leftdistance_V/A").click();
	scrollbyelementlistland(listviewloca, va);
	}
}
public static void Glass_rightnear_sph(String sph)
{
	if(glassvali(sph))
	{
	getidlocator("Sight_Glassprescription_Rightnear_SPH").click();
	getidlocator("Sight_Glassprescription_Rightnear_SPH").sendKeys(sph);
	hidekey();
	}
}
public static void Glass_rightnear_cyl(String cyl)
{
	if(glassvali(cyl))
	{
	getidlocator("Sight_Glassprescription_Rightnear_CYL").click();
	getidlocator("Sight_Glassprescription_Rightnear_CYL").sendKeys(cyl);
	hidekey();
	}
}
public static void Glass_rightnear_axis(String axis)
{
	if(glassvali(axis))
	{
	getidlocator("Sight_Glassprescription_Rightnear_AXIS").click();
	getidlocator("Sight_Glassprescription_Rightnear_AXIS").sendKeys(axis);
	hidekey();
	}
}
public static void Glass_rightnear_va(String va)
{
	if(glassvali(va))
	{
	getidlocator("Sight_Glassprescription_Rightnear_V/A").click();
	scrollbyelementlistland(listviewloca, va);
	}
}
public static void Glass_leftnear_sph(String sph)
{
	if(glassvali(sph))
	{
	getidlocator("Sight_Glassprescription_Leftnear_SPH").click();
	getidlocator("Sight_Glassprescription_Leftnear_SPH").sendKeys(sph);
	hidekey();
	}
}
public static void Glass_leftnear_cyl(String cyl)
{
	if(glassvali(cyl))
	{
	getidlocator("Sight_Glassprescription_Leftnear_CYL").click();
	getidlocator("Sight_Glassprescription_Leftnear_CYL").sendKeys(cyl);
	hidekey();
	}
}
public static void Glass_leftnear_axis(String axis)
{
	if(glassvali(axis))
	{
	getidlocator("Sight_Glassprescription_Leftnear_AXIS").click();
	getidlocator("Sight_Glassprescription_Leftnear_AXIS").sendKeys(axis);
	hidekey();
	}
}
public static void Glass_leftnear_va(String va)
{
	if(glassvali(va))
	{
	getidlocator("Sight_Glassprescription_Leftnear_V/A").click();
	scrollbyelementlistland(listviewloca, va);
	}
}
public static void Glass_collectedatcamp(String campcollect, String collectpoint)
{
	radiobutton(getidlocator("Sight_Glassprescription_Glass_collectedpoint_Yes"), getidlocator("Sight_Glassprescription_Glass_collectedpoint_No"), campcollect, "collectedatcamp: ");
	getidlocator("Sight_Glassprescription_Glass_collectedpoint_text").click();
	if(campcollect.equalsIgnoreCase("yes"))
	{
		String full=vaxpath1+probid.getProperty("Sight_Glassprescription_Glass_Collected_point")+vaxpath2;
		caseva_dropvalidate(full, collectpoint, "campcollect: ");
	}else if (campcollect.equalsIgnoreCase("no")) {
		
	}
	scrollbyelement(scrollclasswindow, "Save");
}
public static void glassinfo_save()
{
	getidlocator("Sight_Glassprescription_Save").click();
}

//Multitab Casesheet
public static void multi_worklistcasesheet() throws IOException
{
	idprob();
	asser=new SoftAssert();
	WebDriverWait wait1=new WebDriverWait(driver, 35);
	wait1.until(ExpectedConditions.visibilityOfElementLocated(By.className("android.widget.ImageButton")));
	driver.findElement(By.className("android.widget.ImageButton")).click();
	driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='Casesheet']")).click();
}
public static void multi_menuworklist()
{
	WebDriverWait wait1=new WebDriverWait(driver, 35);
	wait1.until(ExpectedConditions.visibilityOfElementLocated(By.className("android.widget.ImageButton")));
	driver.findElement(By.className("android.widget.ImageButton")).click();
	driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='Worklist']")).click();
}

public static void multi_casesheet_UID(String UID)
{
	getidlocator("Sight_Multi_casesheet_UID").sendKeys(UID);
	hidekey();
}
public static void multi_casesheet_screencode(String screencode)
{
	getidlocator("Sight_Multi_casesheet_Screenresponsecode").click();
	dropdown(screencode);
}
public static void multi_casesheet_savescroll()
{
	scrollbyelement(scrollclasswindow, "Save");
}

//Multitab Glass prescription
public static void multi_worklistglass()
{
	WebDriverWait wait1=new WebDriverWait(driver, 35);
	wait1.until(ExpectedConditions.visibilityOfElementLocated(By.className("android.widget.ImageButton")));
	driver.findElement(By.className("android.widget.ImageButton")).click();
	driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='Glass Prescription']")).click();
}
public static void multi_glass_UID(String UID)
{
	driver.rotate(ScreenOrientation.LANDSCAPE);
	asser=new SoftAssert();
	getidlocator("Sight_Multi_Glassprescription_UID").sendKeys(UID);
	hidekey();
}












//common for locators
public static void idprob() throws IOException
{
	fileid=new FileInputStream(new File(System.getProperty("user.dir")+File.separator+"Locatorsproperties"+File.separator+"Idlocators.properties"));
	probid=new Properties();
	probid.load(fileid);
}
public static WebElement getidlocator(String ids)
{
	return driver.findElement(By.id(probid.getProperty(ids)));
}
public static WebElement getclasslocator(String classsin)
{
	return driver.findElement(By.className(classsin));
}
public static List<WebElement> getallclasslocator(String classlo)
{
	return driver.findElements(By.className(classlo));
}
public static WebElement getxpathlocators(String xpath)
{
	return driver.findElement(By.xpath(xpath));
}
public static void dropdown(String dropvalue)
{
	WebDriverWait wait=new WebDriverWait(driver, 20);
	wait.until(ExpectedConditions.visibilityOf(getclasslocator(dropdownloca)));
	List<WebElement> drop=getallclasslocator(dropdownloca);
	for(int i=0;i<drop.size();i++)
	{
		if(dropvalue.equalsIgnoreCase(drop.get(i).getText()))
		{
			drop.get(i).click();
			break;
		}
	}
}
public static void radiobutton(WebElement sayyes, WebElement sayno, String inputdata, String name)
{

	if(inputdata.equalsIgnoreCase("Yes"))
	{
		sayyes.click();
		asser.assertEquals(name+sayyes.getAttribute("checked").toString(), name+"true");
	}else if (inputdata.equalsIgnoreCase("No")) {
		sayno.click();
		asser.assertEquals(name+sayno.getAttribute("checked").toString(), name+"true");
	}
}
public static void radiobuttonNA(WebElement sayyes, WebElement sayno, WebElement sayna, String inputdata, String name)
{
	if(inputdata.equalsIgnoreCase("Yes"))
	{
		sayyes.click();
		asser.assertEquals(name+sayyes.getAttribute("checked").toString(), name+"true");
	}else if (inputdata.equalsIgnoreCase("No")) {
		sayno.click();
		asser.assertEquals(name+sayno.getAttribute("checked").toString(), name+"true");
	}else if (inputdata.equalsIgnoreCase("NA")) {
		sayna.click();
		asser.assertEquals(name+sayna.getAttribute("checked").toString(), name+"true");
	}
}

public static String BMInametext()
{
	String text = null;
	if(bmiresultvalue<16)
	{
		text="Severe Thinness";
	}else if (bmiresultvalue>=16 && bmiresultvalue<17) {
		text="Moderate Thinness";
	}else if (bmiresultvalue>=17 && bmiresultvalue<18.5) {
		text="Mild Thinness";
	}else if (bmiresultvalue>=18.5 && bmiresultvalue<25) {
		text="Normal";
	}else if (bmiresultvalue>=25 && bmiresultvalue<30) {
		text="Overweight";
	}else if (bmiresultvalue>=30 && bmiresultvalue<35) {
		text="Obese Class I";
	}else if (bmiresultvalue>=35 && bmiresultvalue<40) {
		text="Obese Class II";
	}else if (bmiresultvalue>=40) {
		text="Obese Class III";
	}
	return text;
}
public static void caseva_dropvalidate(String xpath, String input, String name)
{
	String act=driver.findElementByXPath(xpath).getText();
	System.out.print(act+" ");
	asser.assertEquals(name+act, name+input);
}
public static void scrollbyelement(String scrollwindow, String scrollelement)
{
	String scrollcontain="new UiSelector().className(\""+scrollwindow+"\")";
	String scrollele="new UiSelector().text(\""+scrollelement+"\")";
	driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable("+scrollcontain+").scrollIntoView("+scrollele+")"));
}
public static void scrollbyelementlist(String scrollwindow, String scrollelement)
{
	String scrollcontain="new UiSelector().className(\""+scrollwindow+"\")";
	String scrollele="new UiSelector().text(\""+scrollelement+"\")";
	driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable("+scrollcontain+").scrollIntoView("+scrollele+")")).click();
}
public static void scrollbyelementlistland(String scrollwindow, String scrollelement)
{
	driver.rotate(ScreenOrientation.LANDSCAPE);
	String scrollcontain="new UiSelector().className(\""+scrollwindow+"\")";
	String scrollele="new UiSelector().text(\""+scrollelement+"\")";
	driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable("+scrollcontain+").scrollIntoView("+scrollele+")")).click();
}

public static boolean glassvali(String glassvalue)
{
	if(glassvalue.equals("-"))
	{
		return false;
	}else
	{
		return true;
	} 
}
public static void hidekey()
{
	if(driver.isKeyboardShown())
	{
		driver.hideKeyboard();
	}
}
}