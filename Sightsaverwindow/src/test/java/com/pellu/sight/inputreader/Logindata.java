package com.pellu.sight.inputreader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Logindata {

public Object[][] data=null;

@DataProvider(name="login")
public Object[][] readlogin() throws IOException
{
	FileInputStream file=new FileInputStream(new File(System.getProperty("user.dir")+File.separator+"Patientinfo"+File.separator+"Patientregis.xlsx"));
	XSSFWorkbook work=new XSSFWorkbook(file);
	XSSFSheet sheet=work.getSheet("Login");
	int r=sheet.getLastRowNum()+1;
	int c=sheet.getRow(1).getLastCellNum();
	data=new Object[r-1][c];
	for(int i=1;i<r;i++)
	{
		for(int j=0;j<c;j++)
		{
			int cel=sheet.getRow(i).getCell(j).getCellType();
			if(cel==Cell.CELL_TYPE_STRING)
			{
				data[i-1][j]=sheet.getRow(i).getCell(j).getStringCellValue();
				System.out.println(data[i-1][j]);
			}else if (cel==Cell.CELL_TYPE_NUMERIC) {
				DataFormatter form=new DataFormatter();
				Cell ce=sheet.getRow(i).getCell(j);
				data[i-1][j]=form.formatCellValue(ce).toString();
			}
		}
	}
	return data;
}
@DataProvider(name="campdetail")
public Object[][] readcampdetails() throws IOException
{
	FileInputStream file=new FileInputStream(new File(System.getProperty("user.dir")+File.separator+"Patientinfo"+File.separator+"Patientregis.xlsx"));
	XSSFWorkbook work=new XSSFWorkbook(file);
	XSSFSheet sheet=work.getSheet("Campdetails");
	int r=sheet.getLastRowNum()+1;
	int c=sheet.getRow(1).getLastCellNum();
	data=new Object[r-1][c];
	for(int i=1;i<r;i++)
	{
		for(int j=0;j<c;j++)
		{
			int cel=sheet.getRow(i).getCell(j).getCellType();
			if(cel==Cell.CELL_TYPE_STRING)
			{
				data[i-1][j]=sheet.getRow(i).getCell(j).getStringCellValue();
			}else if (cel==Cell.CELL_TYPE_NUMERIC) {
				DataFormatter form=new DataFormatter();
				Cell ce=sheet.getRow(i).getCell(j);
				data[i-1][j]=form.formatCellValue(ce).toString();
			}
		}
	}
	return data;
}
@DataProvider(name="camp")
public Object[][] patientreg() throws IOException
{
	String value=null;
	FileInputStream file=new FileInputStream(new File(System.getProperty("user.dir")+File.separator+"Patientinfo"+File.separator+"Patientregis.xlsx"));
	XSSFWorkbook work=new XSSFWorkbook(file);
	XSSFSheet sheet=work.getSheet("Patientreg");
	int r=sheet.getLastRowNum();
	int c=sheet.getRow(0).getLastCellNum();
	data=new Object[r][1];
	for(int i=0;i<r;i++)
	{
		HashMap<Object, Object> map=new HashMap<Object, Object>();
		for(int j=0;j<c;j++)
		{
			int ce1=sheet.getRow(i+1).getCell(j).getCellType();
			if(ce1==Cell.CELL_TYPE_STRING)
			{
				value=sheet.getRow(i+1).getCell(j).getStringCellValue();
			}else if (ce1==Cell.CELL_TYPE_NUMERIC) {
				DataFormatter form=new DataFormatter();
				Cell cas=sheet.getRow(i+1).getCell(j);
				value=form.formatCellValue(cas).toString();	
			}
			map.put(sheet.getRow(0).getCell(j).toString(), value);
		}
		data[i][0]=map;
	}
	return data;
}
}
