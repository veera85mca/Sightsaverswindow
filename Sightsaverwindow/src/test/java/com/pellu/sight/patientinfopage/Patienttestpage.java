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
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.pellu.sight.initialize.Driverinitialize;
import com.relevantcodes.extentreports.LogStatus;


import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
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
private static String vaxpath1="//android.widget.Spinner[@resource-id='PellucidCampManagement.PellucidCampManagement:id/";
private static String vaxpath2="']//android.widget.TextView[@index='0']";


public static void login_user(String user) throws IOException
{	
	idprob();
	getidlocator("Sight_Login_username").clear();
	getidlocator("Sight_Login_username").sendKeys(user);
}
public static void login_pass(String pass)
{
	driver.hideKeyboard();
	getidlocator("Sight_Login_password").sendKeys(pass);
}
public static void login_provider(String provider)
{
	driver.hideKeyboard();
	getidlocator("Sight_Login_provider").clear();
	getidlocator("Sight_Login_provider").sendKeys(provider);
	driver.hideKeyboard();
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
	getidlocator("Sight_Menu_setting_UIDmanual_check").click();
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
	driver.hideKeyboard();
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
	driver.hideKeyboard();
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
	driver.hideKeyboard();
}
public static void patientaadhar(String aadhar)
{
	getidlocator("Sight_worklist_Patient_registration_aadhar").sendKeys(aadhar);
	driver.hideKeyboard();
}
public static void patientlicenseno(String licenseno)
{
	getidlocator("Sight_worklist_Patient_registration_license").sendKeys(licenseno);
	driver.hideKeyboard();
}
public static void patientrenewalmonth(String month) throws InterruptedException
{
	getidlocator("Sight_worklist_Patient_registration_Month").click();
	System.out.println(month);
	dropdown(month);
	//scrollbyelementlist(listviewloca, month);
}
public static void patientrenewalyear(String year)
{
	getidlocator("Sight_worklist_Patient_registration_Year").sendKeys(year);
	getidlocator("Sight_worklist_Patient_registration_job_text").click();
}
public static void patientjob(String job, String years)
{
	getidlocator("Sight_worklist_Patient_registration_job").click();
	dropdown(job);
	if(job.equalsIgnoreCase("Driver"))
	{
		getidlocator("Sight_worklist_Patient_registration_job_drivingyears").sendKeys(years);
	}
}
public static void patienteducationquali(String studies, String others)
{
	driver.hideKeyboard();
	getidlocator("Sight_worklist_Patient_registration_education").click();
	dropdown(studies);
	//scrollbyelementlist(listviewloca, studies);
	if(studies.equalsIgnoreCase("Others"))
	{
		getidlocator("Sight_worklist_Patient_registration_education_others").sendKeys(others);
	}
}
public static void patientmonthsemployee(String month)
{
	getidlocator("Sight_worklist_Patient_registration_monthsemployee").sendKeys(month);
	driver.hideKeyboard();
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
}
public static void patientvehicleinsur(String vehicleinsur)
{
	radiobutton(getidlocator("Sight_worklist_Patient_registration_vehicleinsurance_yes"), getidlocator("Sight_worklist_Patient_registration_vehicleinsurance_no"), vehicleinsur, "vehicleinsurence: ");
}
public static void patientlifeinsur(String lifeinsur)
{
	radiobutton(getidlocator("Sight_worklist_Patient_registration_lifeinsurance_yes"), getidlocator("Sight_worklist_Patient_registration_lifeinsurance_no"), lifeinsur, "lifeinsurence: ");
}
public static void patienthealthinsur(String healthinsur)
{
	scrollbyelement(scrollclasswindow, "Save and Proceed");
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





//Screening test details
public static void screenresponse(String responsecode)
{
screencode=responsecode;
switch (responsecode) {
case "A - Screening negative Asymptomatic":
	getidlocator("Camp_Screenresponsecode_A").click();
	break;
case "B - Screening negative but Symptomatic":
	getidlocator("Camp_Screenresponsecode_B").click();
	break;
case "C - Only Distance vision problem":
	getidlocator("Camp_Screenresponsecode_C").click();
	break;
case "D - Only Near vision problem":
	getidlocator("Camp_Screenresponsecode_D").click();
	break;
case "E - Only Color vision problem":
	getidlocator("Camp_Screenresponsecode_E").click();
	break;
case "F - Both Distance and Near vision problem":
	getidlocator("Camp_Screenresponsecode_F").click();
	break;
case "G - Both Distance and Color vision problem":
	getidlocator("Camp_Screenresponsecode_G").click();
	break;
case "H - Both Near and Color vision problem":
	getidlocator("Camp_Screenresponsecode_H").click();
	break;
}
}
public static void general1_medicalcheckup(String general1)
{
	radiobutton(getidlocator("Camp_Screening_general_medicalcheckup_Yes"), getidlocator("Camp_Screening_general_medicalcheckup_No"), general1, "medicalcheckup: ");
}
public static void general2_diabetes(String general2)
{
	radiobutton(getidlocator("Camp_Screening_general_diabetes_Yes"), getidlocator("Camp_Screening_general_diabetes_No"), general2, "diabetes: ");
}
public static void general3_hypertention(String general3)
{
	radiobutton(getidlocator("Camp_Screening_general_hypertension_Yes"), getidlocator("Camp_Screening_general_hypertension_No"), general3, "hypertention: ");
}
public static void general4_smoke(String general4)
{
	scrollbyelement(scrollclasswindow, "Save");
	getidlocator("Camp_Screening_general_smoke_Text").click();
	radiobutton(getidlocator("Camp_Screening_general_smoke_Yes"), getidlocator("Camp_Screening_general_smoke_No"), general4, "smoke: ");
}
public static void general5_alcohol(String general5)
{
	getidlocator("Camp_Screening_general_alcohol_Text").click();
	radiobutton(getidlocator("Camp_Screening_general_alcohol_Yes"), getidlocator("Camp_Screening_general_alcohol_No"), general5, "alcohol: ");
}
public static void general6_eyeexamin(String general6)
{

	radiobutton(getidlocator("Camp_Screening_general_eyeexamination_Yes"), getidlocator("Camp_Screening_general_eyeexamination_No"), general6, "eyeexamin: ");
}
public static void general7_distanceobject(String general7)
{

	radiobutton(getidlocator("Camp_Screening_general_distantobjects_Yes"), getidlocator("Camp_Screening_general_distantobjects_No"), general7, "distanceobject: ");
}
public static void general8_wearglasses(String general8)
{

	radiobutton(getidlocator("Camp_Screening_general_wearglasses_Yes"), getidlocator("Camp_Screening_general_wearglasses_No"), general8, "wearglasses: ");
}
public static void general9_useglasses(String general9)
{

	radiobutton(getidlocator("Camp_Screening_general_useglasses_Yes"), getidlocator("Camp_Screening_general_useglasses_No"), general9, "useglasses: ");
}
public static void general10_nearhospital(String general10, String general11)
{

	radiobutton(getidlocator("Camp_Screening_general_nearhospital_Yes"), getidlocator("Camp_Screening_general_nearhospital_No"), general10, "nearhospital: ");
	if(general10.equalsIgnoreCase("yes"))
	{
		switch (general11) {
		case "Govt":
			getidlocator("Camp_Screening_general_typehospital_govt").click();
			break;
		case "Pvt":
			getidlocator("Camp_Screening_general_typehospital_private").click();
			break;
		case "NGO":
			getidlocator("Camp_Screening_general_typehospital_ngo").click();
			break;
		case "Others":
			getidlocator("Camp_Screening_general_typehospital_others").click();
			break;
		}
	}
}
public static void screeninfo_save()
{
	getidlocator("Camp_Screening_save").click();
}
//medical examination details
public static void mediexam_bpSystolic(String bpsystolic) throws InterruptedException
{
	Thread.sleep(3000);
	WebDriverWait wait=new WebDriverWait(driver, 30);
	wait.until(ExpectedConditions.visibilityOf(getidlocator("Camp_medicalexamination_bloodpressure_Systolic")));
	getidlocator("Camp_medicalexamination_bloodpressure_Systolic").sendKeys(bpsystolic);
}
public static void mediexam_bpDiastolic(String bpdiastolic)
{
	getidlocator("Camp_medicalexamination_bloodpressure_Diastolic").sendKeys(bpdiastolic);
}
public static void mediexam_bloodsugar(String sugar)
{
	getidlocator("Camp_medicalexamination_bloodsugar").sendKeys(sugar);
}
public static void mediexam_syringing(String syring)
{
	radiobutton(getidlocator("Camp_medicalexamination_Syringing_yes"), getidlocator("Camp_medicalexamination_Syringing_no"), syring, "syringing: ");
}
public static void BMIweight(String weight)
{
	bmiweight=Integer.parseInt(weight);
	getidlocator("Camp_BMI_Weight").sendKeys(weight);
}
public static void BMIheight(String height)
{
	bmiheight=Double.parseDouble(height);
	getidlocator("Camp_BMI_Height").sendKeys(height);
}
public static void BMIresult()
{
	WebDriverWait wait=new WebDriverWait(driver, 15);
	wait.until(ExpectedConditions.visibilityOf(getidlocator("Camp_BMI_Result_value")));
	double d=Math.pow(bmiheight/100, 2);
	double n=bmiweight/d;
	double res=(double)Math.round(n*100)/100;
	bmiresultvalue=Double.parseDouble(getidlocator("Camp_BMI_Result_value").getText());
	System.out.println("result: "+res+" actual: "+bmiresultvalue);
	asser.assertEquals(bmiresultvalue, res);
	
	WebDriverWait wait1=new WebDriverWait(driver, 15);
	wait1.until(ExpectedConditions.visibilityOf(getidlocator("Camp_BMI_Result_text")));
	System.out.println("BMI get Text: "+getidlocator("Camp_BMI_Result_text").getText()+"BMI Value of text: "+BMInametext());
	asser.assertEquals(getidlocator("Camp_BMI_Result_text").getText(), BMInametext());
}
public static void Intracularpressure_rightiop(String riop)
{
	getidlocator("Camp_BMI_Weight_Text").click();
	getidlocator("Camp_Intracular_pressure_righteye_IOP").sendKeys(riop);
}
public static void Intracularpressure_leftiop(String liop) throws InterruptedException
{
	getidlocator("Camp_Intracular_pressure_lefteye_IOP").sendKeys(liop);
	getidlocator("Camp_BMI_Weight_Text").click();	
}
public static void medicalinfo_save()
{
	getidlocator("Camp_medicalexamination_save").click();
}
//case sheet for camp
public static void casesheet_varightdistanceunaided(String rdua)
{
	String full=vaxpath1+probid.getProperty("Camp_casesheet_VA_Right_unaided_distance")+vaxpath2;
	caseva_dropvalidate(full, rdua, "varightdistanceunaided: ");
}
public static void casesheet_varightnearunaided(String rnud)
{
	String full=vaxpath1+probid.getProperty("Camp_casesheet_VA_Right_unaided_near")+vaxpath2;
	caseva_dropvalidate(full, rnud, "varightnearunaided: ");	
}
public static void casesheet_valeftdistanceunaided(String ldua)
{
	String full=vaxpath1+probid.getProperty("Camp_casesheet_VA_Left_unaided_distance")+vaxpath2;
	caseva_dropvalidate(full, ldua, "valeftdistanceunaided: ");	
}
public static void casesheet_valeftnearunaided(String rnud)
{
	String full=vaxpath1+probid.getProperty("Camp_casesheet_VA_Left_unaided_near")+vaxpath2;
	caseva_dropvalidate(full, rnud, "valeftnearunaided: ");
}
public static void casesheet_varightdistanceaided(String rda)
{
	String full=vaxpath1+probid.getProperty("Camp_casesheet_VA_Right_aided_distance")+vaxpath2;
	caseva_dropvalidate(full, rda, "varightdistanceaided: ");
}
public static void casesheet_varightnearaided(String rnd)
{
	String full=vaxpath1+probid.getProperty("Camp_casesheet_VA_Right_aided_near")+vaxpath2;
	caseva_dropvalidate(full, rnd, "varightnearaided: ");
}
public static void casesheet_valeftdistanceaided(String lda)
{
	String full=vaxpath1+probid.getProperty("Camp_casesheet_VA_Left_aided_distance")+vaxpath2;
	caseva_dropvalidate(full, lda, "valeftdistanceaided: ");
}
public static void casesheet_valeftnearaided(String rnd)
{
	String full=vaxpath1+probid.getProperty("Camp_casesheet_VA_Left_aided_near")+vaxpath2;
	caseva_dropvalidate(full, rnd, "valeftnearaided: ");
}
public static void casesheet_varightpinhole(String rightpinhole)
{
	String full=vaxpath1+probid.getProperty("Camp_casesheet_VA_Right_pinhole")+vaxpath2;
	caseva_dropvalidate(full, rightpinhole, "varightpinhole: ");
}
public static void casesheet_valeftpinhole(String leftpinhole)
{
	String full=vaxpath1+probid.getProperty("Camp_casesheet_VA_Left_pinhole")+vaxpath2;
	caseva_dropvalidate(full, leftpinhole, "valeftpinhole: ");
}
public static void casesheet_varightcolour(String rightcolour)
{
	String full=vaxpath1+probid.getProperty("Camp_casesheet_VA_Right_Colour")+vaxpath2;
	caseva_dropvalidate(full, rightcolour, "varightcolour: ");
}
public static void casesheet_valeftcolour(String leftcolour)
{
	String full=vaxpath1+probid.getProperty("Camp_casesheet_VA_Left_Colour")+vaxpath2;
	caseva_dropvalidate(full, leftcolour, "valeftcolour: ");
}
public static void casesheet_treatrefraction(String refraction)
{
	getidlocator("Camp_casesheet_treatment_refraction").click();
	dropdown(refraction);
}
public static void casesheet_wantrefer(String wantrefer, String referfor, String referto)
{
	radiobutton(getidlocator("Camp_casesheet_wanttorefer_Yes"), getidlocator("Camp_casesheet_wanttorefer_No"), wantrefer, "wantrefer: ");
	if(wantrefer.equalsIgnoreCase("yes"))
	{
		getidlocator("Camp_casesheet_Referfor").click();
		dropdown(referfor);
		getidlocator("Camp_casesheet_ReferTo").click();
		dropdown(referto);
	}
}
public static void casesheetinfo_save()
{
	getidlocator("Camp_casesheet_save").click();
}

//Glass prescription for camp
public static void Glass_rightdistance_sph(String sph)
{
	driver.rotate(ScreenOrientation.LANDSCAPE);
	if(glassvali(sph))
	{
	getidlocator("Camp_Glassprescription_Rightdistance_SPH").sendKeys(sph);
	}
}
public static void Glass_rightdistance_cyl(String cyl)
{
	if(glassvali(cyl))
	{
	getidlocator("Camp_Glassprescription_Rightdistance_CYL").sendKeys(cyl);
	}
}
public static void Glass_rightdistance_axis(String axis)
{
	if(glassvali(axis))
	{
	getidlocator("Camp_Glassprescription_Rightdistance_AXIS").sendKeys(axis);
	}
}
public static void Glass_rightdistance_va(String va)
{
	if(glassvali(va)) 
	{
	getidlocator("Camp_Glassprescription_Rightdistance_V/A").click();
	scrollbyelementlist(listviewloca, va);
	}
}
public static void Glass_leftdistance_sph(String sph)
{
	if(glassvali(sph))
	{
	getidlocator("Camp_Glassprescription_Leftdistance_SPH").sendKeys(sph);
	}
}
public static void Glass_leftdistance_cyl(String cyl)
{
	if(glassvali(cyl))
	{
	getidlocator("Camp_Glassprescription_Leftdistance_CYL").sendKeys(cyl);
	}
}
public static void Glass_leftdistance_axis(String axis) throws InterruptedException
{
	if(glassvali(axis))
	{
	getidlocator("Camp_Glassprescription_Leftdistance_AXIS").sendKeys(axis);
	}
	/*Thread.sleep(4000);
	driver.rotate(ScreenOrientation.LANDSCAPE);
	scrollbyelement(scrollclasswindow, "Prescription of Glass");*/
}
public static void Glass_leftdistance_va(String va) throws InterruptedException
{
	Thread.sleep(4000);
	driver.findElement(By.id("txt_GlassLeftVA")).click();
	JavascriptExecutor js=(JavascriptExecutor)driver;
	js.executeScript("arguments[0].click();", getidlocator("Camp_Glassprescription_value_Leftdistance"));
	//getidlocator("Camp_Glassprescription_Leftdistance_V/A").click();
	scrollbyelementlist(listviewloca, va);
}
public static void Glass_rightnear_sph(String sph)
{
	if(glassvali(sph))
	{
	getidlocator("Camp_Glassprescription_Rightnear_SPH").sendKeys(sph);
	}
}
public static void Glass_rightnear_cyl(String cyl)
{
	if(glassvali(cyl))
	{
	getidlocator("Camp_Glassprescription_Rightnear_CYL").sendKeys(cyl);
	}
}
public static void Glass_rightnear_axis(String axis)
{
	if(glassvali(axis))
	{
	getidlocator("Camp_Glassprescription_Rightnear_AXIS").sendKeys(axis);
	}
}
public static void Glass_rightnear_va(String va)
{
	if(glassvali(va))
	{
	getidlocator("Camp_Glassprescription_Rightnear_V/A").click();
	scrollbyelementlist(listviewloca, va);
	}
}
public static void Glass_leftnear_sph(String sph)
{
	if(glassvali(sph))
	{
	getidlocator("Camp_Glassprescription_Leftnear_SPH").sendKeys(sph);
	}
}
public static void Glass_leftnear_cyl(String cyl)
{
	if(glassvali(cyl))
	{
	getidlocator("Camp_Glassprescription_Leftnear_CYL").sendKeys(cyl);
	}
}
public static void Glass_leftnear_axis(String axis)
{
	if(glassvali(axis))
	{
	getidlocator("Camp_Glassprescription_Leftnear_AXIS").sendKeys(axis);
	}
}
public static void Glass_leftnear_va(String va)
{
	if(glassvali(va))
	{
	getidlocator("Camp_Glassprescription_Leftnear_V/A").click();
	scrollbyelementlist(listviewloca, va);
	}
}
public static void Glass_collectedatcamp(String campcollect, String collectpoint)
{
	radiobutton(getidlocator("Camp_Glassprescription_Glass_collectedpoint_Yes"), getidlocator("Camp_Glassprescription_Glass_collectedpoint_No"), campcollect, "collectedatcamp: ");
	getidlocator("Camp_Glassprescription_Glass_collectedpoint_text").click();
	if(campcollect.equalsIgnoreCase("yes"))
	{
		String full=vaxpath1+probid.getProperty("Camp_Glassprescription_Glass_Collected_point")+vaxpath2;
		caseva_dropvalidate(full, collectpoint, "campcollect: ");
	}else if (campcollect.equalsIgnoreCase("no")) {
		
	}
	scrollbyelement(scrollclasswindow, "Save");
}
public static void glassinfo_save()
{
	getidlocator("Camp_Glassprescription_Save").click();
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
	//return driver.findElementById(probid.getProperty(id));
	//return driver.findElement(By.id(probid.getProperty(ids)));
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
}