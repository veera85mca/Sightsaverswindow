package com.pellu.sight.patientinfo;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



import com.pellu.sight.initialize.Driverinitialize;
import com.pellu.sight.inputreader.Logindata;
import com.pellu.sight.patientinfopage.Patienttestpage;

public class Patienttestcase extends Driverinitialize{

public Map<String, String> mapvalue;
private String screen;
private String Multitab;


@Parameters({"patientregis"})
@Test(priority=1)
public void initialization(String patientreg) throws MalformedURLException
{
	Multitab=patientreg;
	initiatedrive(patientreg);
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
	registrationscreen(mapvalue);
	screen=mapvalue.get("Screen response code").toString();
	if(screen.equalsIgnoreCase("B - Screening negative but Symptomatic") || screen.equalsIgnoreCase("E - Only Color vision problem"))
	{
		screencasesheet(mapvalue);
	}
	if(screen.equalsIgnoreCase("C - Only Distance vision problem") || screen.equalsIgnoreCase("D - Only Near vision problem") || screen.equalsIgnoreCase("F - Both Distance and Near vision problem") || screen.equalsIgnoreCase("G - Both Distance and Color vision problem") || screen.equalsIgnoreCase("H - Both Near and Color vision problem"))
	{
		screencasesheet(mapvalue);
		if("no".equalsIgnoreCase(mapvalue.get("Do you want refer").toString()))
		{
		screenglassvalues(mapvalue);
		}
	}
	Patienttestpage.asser.assertAll();
}
@Test(priority=5, dataProvider="Multiregis", dataProviderClass=Logindata.class)
public void multipatientregistration(HashMap mapvalue) throws InterruptedException
{
	String patientUID=mapvalue.get("UID").toString();
	log=report.startTest(patientUID);
	registrationscreen(mapvalue);
	Patienttestpage.asser.assertAll();
}
@Test(priority=6, dataProvider="Multicasesheet", dataProviderClass=Logindata.class)
public void multipatientcasesheet(HashMap mapvalue) throws IOException
{
	String patientUID=mapvalue.get("UID").toString();
	log=report.startTest(patientUID);
	screen=mapvalue.get("Screen response code").toString();
	driver.rotate(ScreenOrientation.PORTRAIT);
	Patienttestpage.multi_worklistcasesheet();
	Patienttestpage.multi_casesheet_UID(patientUID);
	Patienttestpage.multi_casesheet_screencode(mapvalue.get("Screen response code").toString());
	screencasesheet(mapvalue);
	//Patienttestpage.asser.assertAll();
	if(screen.equalsIgnoreCase("C") || screen.equalsIgnoreCase("D") || screen.equalsIgnoreCase("F") || screen.equalsIgnoreCase("G") || screen.equalsIgnoreCase("H") && mapvalue.get("Do you want refer").toString().equalsIgnoreCase("No"))
	{
		if("no".equalsIgnoreCase(mapvalue.get("Do you want refer").toString()))
		{
		driver.rotate(ScreenOrientation.LANDSCAPE);
		Patienttestpage.multi_menuworklist();
		}
	}

}
@Test(priority=7, dataProvider="Multiglass", dataProviderClass=Logindata.class)
public void multipatientglass(HashMap mapvalue) throws InterruptedException
{
	String patientUID=mapvalue.get("UID").toString();
	log=report.startTest(patientUID);
	Patienttestpage.multi_worklistglass();
	Patienttestpage.multi_glass_UID(mapvalue.get("UID").toString());
	screenglassvalues(mapvalue);
	
	//Patienttestpage.asser.assertAll();
}


//Patient registration and Screening module
public void registrationscreen(HashMap mapvalue) throws InterruptedException
{
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
	Patienttestpage.patientjob(mapvalue.get("Occupation").toString(), mapvalue.get("Driving experience").toString());
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
	Patienttestpage.mediexam_bpSystolic(mapvalue.get("BP Systolic").toString());
	Patienttestpage.mediexam_bloodsugar(mapvalue.get("Blood Sugar").toString());
	Patienttestpage.BMIweight(mapvalue.get("BMI Weight").toString());
	Patienttestpage.BMIheight(mapvalue.get("BMI Height").toString());
	Patienttestpage.screenresponse(mapvalue.get("Screen response code").toString());
	Patienttestpage.Monthlyquestions_salarycalculate(mapvalue.get("Salary calculated").toString());
	Patienttestpage.Monthlyquestions_holdbackamount(mapvalue.get("Holding back amount").toString());
	Patienttestpage.Monthlyquestions_notemplyeemonth(mapvalue.get("Not Emplyeed").toString());
	Patienttestpage.Monthlyquestions_nonworkingmonth(mapvalue.get("Non working months").toString());
	Patienttestpage.Monthlyquestions_alteremplyee(mapvalue.get("Alter employment").toString());
	Patienttestpage.Monthlyquestions_alterskill(mapvalue.get("Alter Skills").toString());
	Patienttestpage.Monthlyquestions_earnsupport(mapvalue.get("Financial support").toString());	
	Patienttestpage.general1_medicalcheckup(mapvalue.get("Medical checkup").toString());
	Patienttestpage.general2_diabetes(mapvalue.get("Diabeties").toString());
	Patienttestpage.general3_hypertention(mapvalue.get("Hypertension").toString());
	Patienttestpage.general4_smoke(mapvalue.get("Smoke").toString());
	Patienttestpage.general5_alcohol(mapvalue.get("Alcohol").toString());
	Patienttestpage.general6_eyeexamin(mapvalue.get("Eye examin").toString());
	Patienttestpage.general7_distanceobject(mapvalue.get("Distance Object").toString());
	Patienttestpage.general8_distancedriving(mapvalue.get("Distance driving").toString());
	Patienttestpage.general9_trafficlightcolors(mapvalue.get("Traffic light Colors").toString());
	Patienttestpage.general10_nightdriving(mapvalue.get("Night driving").toString());
	Patienttestpage.general11_wearglasses(mapvalue.get("Wear Glass").toString());
	Patienttestpage.general12_useglasses(mapvalue.get("Use Glass").toString());
	Patienttestpage.general13_nearhospital(mapvalue.get("Near by hospital").toString(), mapvalue.get("Type of hospital").toString());
	Patienttestpage.general14_roadaccident(mapvalue.get("Road accident").toString(), mapvalue.get("Road accident 12 Months").toString());
	Patienttestpage.general15_firstaidkit(mapvalue.get("First aid kit").toString());
	Patienttestpage.general16_happyprofession(mapvalue.get("Happy profession").toString(), mapvalue.get("Happy way").toString());
	Patienttestpage.screeninfo_save();
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
	Patienttestpage.casesheet_wantrefer(mapvalue.get("Do you want refer").toString(), mapvalue.get("Refer for").toString(), mapvalue.get("If anyother then").toString(), mapvalue.get("Refer To").toString());
	if(Multitab.equalsIgnoreCase("Casesheet"))
	{
		Patienttestpage.multi_casesheet_savescroll();
		Patienttestpage.casesheetinfo_save();
	}else {
		Patienttestpage.casesheetinfo_save();
	}
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
	Patienttestpage.Glass_leftdistance_va(mapvalue.get("Glass Left Distance v/a").toString());
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
