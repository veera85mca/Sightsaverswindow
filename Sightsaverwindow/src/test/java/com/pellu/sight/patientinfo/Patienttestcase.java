package com.pellu.sight.patientinfo;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



import com.pellu.sight.initialize.Driverinitialize;
import com.pellu.sight.inputreader.Logindata;
import com.pellu.sight.patientinfopage.Patienttestpage;

public class Patienttestcase extends Driverinitialize{

public Map<String, String> mapvalue;
private String screen;

@Test(priority=1)
public void initialization() throws MalformedURLException
{
	initiatedrive();
	log=report.startTest("Driver Initialize Start");
}
@Test(priority=2, dataProvider="login", dataProviderClass=Logindata.class)
public void login(String user, String pass, String provider) throws IOException
{
	log=report.startTest("Login Test Start");
	Patienttestpage.login_user(user);
	Patienttestpage.login_pass(pass);
	Patienttestpage.login_provider(provider);
}
@Test(priority=3, dataProvider="campdetail", dataProviderClass=Logindata.class)
public void campdetailscreen(String model, String location, String date, String postpone, String postdate, String remarks)
{
	log=report.startTest("Camp details Test Start");
	Patienttestpage.campdetail_modeltype(model);
	Patienttestpage.campdetail_camplocation(location);
	Patienttestpage.campdetail_campdate(date);
	Patienttestpage.campdetail_Remarks(postpone, postdate, remarks);
}
@Test(priority=4, dataProvider="camp", dataProviderClass=Logindata.class)
public void patientregistration(HashMap mapvalue) throws InterruptedException
{
	String patientUID=mapvalue.get("UID").toString();
	log=report.startTest(patientUID);
	Patienttestpage.worklistregistration();
	Patienttestpage.PatientUID(mapvalue.get("UID").toString());
	Patienttestpage.patientname(mapvalue.get("Patient Name").toString());
	Patienttestpage.patientage(mapvalue.get("Age").toString());
	Patienttestpage.patientgender(mapvalue.get("Gender").toString());
	Patienttestpage.patientmobileno(mapvalue.get("Mobile no").toString());
	Patienttestpage.patientaddress(mapvalue.get("Address").toString());
	Patienttestpage.patientstate(mapvalue.get("State").toString());
	Patienttestpage.patientdistrict(mapvalue.get("District").toString());
	Patienttestpage.patienttaluk(mapvalue.get("Taluk").toString());
	Patienttestpage.patientaadhar(mapvalue.get("Aadhaar no").toString());
	Patienttestpage.patientlicenseno(mapvalue.get("License no").toString());
	Patienttestpage.patientrenewalmonth(mapvalue.get("Renewal month").toString());
	Patienttestpage.patientrenewalyear(mapvalue.get("Renewal year").toString());
	Patienttestpage.patientjob(mapvalue.get("Occupation").toString(), mapvalue.get("Driving expreience").toString());
	Patienttestpage.patienteducationquali(mapvalue.get("Education").toString(), mapvalue.get("Others specify").toString());
	Patienttestpage.patientmonthsemployee(mapvalue.get("Months employed").toString());
	Patienttestpage.patienttypevehicle(mapvalue.get("Type of vehicle").toString());
	Patienttestpage.patienttyperoute(mapvalue.get("Type of route").toString());
	Patienttestpage.patientcaste(mapvalue.get("Caste").toString());
	Patienttestpage.patientincome(mapvalue.get("Monthly Income").toString());
	Patienttestpage.patientvehicleinsur(mapvalue.get("Vehicle insurance").toString());
	Patienttestpage.patientlifeinsur(mapvalue.get("Life insurance").toString());
	Patienttestpage.patienthealthinsur(mapvalue.get("Health insurance").toString());
	Patienttestpage.patientknowcamp(mapvalue.get("Know about camp").toString());
	Patienttestpage.patientinfo_save();
	Patienttestpage.screenresponse(mapvalue.get("Screen response code").toString());
	Patienttestpage.general1_medicalcheckup(mapvalue.get("Medical checkup").toString());
	Patienttestpage.general2_diabetes(mapvalue.get("Diabeties").toString());
	Patienttestpage.general3_hypertention(mapvalue.get("Hypertension").toString());
	Thread.sleep(4000);
	Patienttestpage.general4_smoke(mapvalue.get("Smoke").toString());
	Patienttestpage.general5_alcohol(mapvalue.get("Alcohol").toString());
	Patienttestpage.general6_eyeexamin(mapvalue.get("Eye examin").toString());
	Patienttestpage.general7_distanceobject(mapvalue.get("Distance Object").toString());
	Patienttestpage.general8_wearglasses(mapvalue.get("Wear Glass").toString());
	Patienttestpage.general9_useglasses(mapvalue.get("Use Glass").toString());
	Patienttestpage.general10_nearhospital(mapvalue.get("Near by hospital").toString(), mapvalue.get("Type of hospital").toString());	
	Patienttestpage.screeninfo_save();
	Patienttestpage.mediexam_bpSystolic(mapvalue.get("BP Systolic").toString());
	Patienttestpage.mediexam_bpDiastolic(mapvalue.get("BP Diastolic").toString());
	Patienttestpage.mediexam_bloodsugar(mapvalue.get("Blood Sugar").toString());
	Patienttestpage.mediexam_syringing(mapvalue.get("Syringing").toString());
	Patienttestpage.BMIweight(mapvalue.get("BMI Weight").toString());
	Patienttestpage.BMIheight(mapvalue.get("BMI Height").toString());
	Patienttestpage.BMIresult();
	Patienttestpage.Intracularpressure_rightiop(mapvalue.get("IP Right IOP").toString());
	Patienttestpage.Intracularpressure_leftiop(mapvalue.get("IP Left IOP").toString());
	Patienttestpage.medicalinfo_save();
	screen=mapvalue.get("Screen response code").toString();
	if(screen.equalsIgnoreCase("B - Screening negative but Symptomatic") || screen.equalsIgnoreCase("E - Only Color vision problem"))
	{
		screencasesheet(mapvalue);
	}
	if(screen.equalsIgnoreCase("C - Only Distance vision problem") || screen.equalsIgnoreCase("D - Only Near vision problem") || screen.equalsIgnoreCase("F - Both Distance and Near vision problem") || screen.equalsIgnoreCase("G - Both Distance and Color vision problem") || screen.equalsIgnoreCase("H - Both Near and Color vision problem"))
	{
		screencasesheet(mapvalue);
		screenglassvalues(mapvalue);
	}
	Patienttestpage.asser.assertAll();
	
}
//Case sheet screen
public void screencasesheet(HashMap mapvalue)
{
	Patienttestpage.casesheet_varightdistanceunaided(mapvalue.get("Right eye un-aided Distance").toString());
	Patienttestpage.casesheet_varightnearunaided(mapvalue.get("Right eye Un-aided near").toString());
	Patienttestpage.casesheet_valeftdistanceunaided(mapvalue.get("Left eye un-aided distance").toString());
	Patienttestpage.casesheet_valeftnearunaided(mapvalue.get("Left eye Un-aided near").toString());
	Patienttestpage.casesheet_varightdistanceaided(mapvalue.get("Right eye aided Distance").toString());
	Patienttestpage.casesheet_varightnearaided(mapvalue.get("Right eye aided near").toString());
	Patienttestpage.casesheet_valeftdistanceaided(mapvalue.get("Left eye aided distance").toString());
	Patienttestpage.casesheet_valeftnearaided(mapvalue.get("Left eye aided near").toString());
	Patienttestpage.casesheet_varightpinhole(mapvalue.get("Right pinhole").toString());
	Patienttestpage.casesheet_valeftpinhole(mapvalue.get("Left pinhole").toString());
	Patienttestpage.casesheet_varightcolour(mapvalue.get("Right Colour").toString());
	Patienttestpage.casesheet_valeftcolour(mapvalue.get("Left Colour").toString());
	Patienttestpage.casesheet_treatrefraction(mapvalue.get("Treatment for refraction").toString());
	Patienttestpage.casesheet_wantrefer(mapvalue.get("Do you want refer").toString(), mapvalue.get("Refer for").toString(), mapvalue.get("Refer To").toString());
	Patienttestpage.casesheetinfo_save();
}
//Glass screen 
public void screenglassvalues(HashMap mapvalue) throws InterruptedException
{
	Patienttestpage.Glass_rightdistance_sph(mapvalue.get("Glass Right distance sph").toString());
	Patienttestpage.Glass_rightdistance_cyl(mapvalue.get("Glass Right Distance cyl").toString());
	Patienttestpage.Glass_rightdistance_axis(mapvalue.get("Glass Right Distance axis").toString());
	Patienttestpage.Glass_rightdistance_va(mapvalue.get("Glass Right Distance v/a").toString());
	Patienttestpage.Glass_leftdistance_sph(mapvalue.get("Glass Left distance sph").toString());
	Patienttestpage.Glass_leftdistance_cyl(mapvalue.get("Glass Left Distance cyl").toString());
	Patienttestpage.Glass_leftdistance_axis(mapvalue.get("Glass Left Distance axis").toString());
	//Patienttestpage.Glass_leftnear_va(mapvalue.get("Glass Left Distance v/a"));
	Patienttestpage.Glass_rightnear_sph(mapvalue.get("Glass Right near sph").toString());
	Patienttestpage.Glass_rightnear_cyl(mapvalue.get("Glass Right near cyl").toString());
	Patienttestpage.Glass_rightnear_axis(mapvalue.get("Glass Right near axis").toString());
	Patienttestpage.Glass_rightnear_va(mapvalue.get("Glass Right near v/a").toString());
	Patienttestpage.Glass_leftnear_sph(mapvalue.get("Glass Left near sph").toString());
	Patienttestpage.Glass_leftnear_cyl(mapvalue.get("Glass Left near cyl").toString());
	Patienttestpage.Glass_leftnear_axis(mapvalue.get("Glass Left near axis").toString());
	Patienttestpage.Glass_leftnear_va(mapvalue.get("Glass Left near v/a").toString());
	Patienttestpage.Glass_collectedatcamp(mapvalue.get("Glass collected at the point").toString(), mapvalue.get("Glass Collecting point").toString());
	Patienttestpage.glassinfo_save();
}
@AfterMethod
public void closereport()
{
	report.endTest(log);
	report.flush();
}
}
