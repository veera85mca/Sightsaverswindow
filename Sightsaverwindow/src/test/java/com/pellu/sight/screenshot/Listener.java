package com.pellu.sight.screenshot;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGListener;
import org.testng.ITestResult;

public class Listener implements ITestListener{

public SimpleDateFormat sim;
public Date da;

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		Failedscreen.createsuccessreport(result.getMethod().getMethodName().toString());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		sim=new SimpleDateFormat("dd_mm_yyyy_hh_mm_ss");
		da=new Date();
		String currentdate=sim.format(da);
		String methodtime=result.getMethod().getMethodName()+currentdate;
		String error=result.getThrowable().toString();
		System.out.println(methodtime);
		try {
			String simage=Failedscreen.getmyscreen(methodtime);
			Failedscreen.createtestreports(simage, error);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
	}
	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		Failedscreen.createskippedreport(result.getMethod().getMethodName().toString());
	}
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
