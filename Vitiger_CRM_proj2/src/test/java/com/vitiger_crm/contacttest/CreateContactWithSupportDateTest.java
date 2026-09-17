package com.vitiger_crm.contacttest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import generic_baseClass.BaseClass;
import generic_fileUtility.ExcelUtility;
import generic_webdriverUtility.JavaUtility;
import generic_webdriverUtility.UtilityClassObj;
import object_repository_utility.CreateNewContactPage;

@Listeners(generic_listenerUtility.ListenerImp.class)
public class CreateContactWithSupportDateTest extends BaseClass
{
	@Test(groups = "regression")
	public void createContactWithSupportDateTest() throws EncryptedDocumentException, IOException
	{
		CreateNewContactPage ccp = new CreateNewContactPage(driver);
		ExcelUtility eu = new ExcelUtility();
		JavaUtility ju = new JavaUtility();
		int data = ju.generateRandomNumber();
		String lastName = eu.readDataFromExcel("Sheet2", 1, 0)+data;
		String curDate = ju.dateInReqFormat("yyyy-MM-dd");
		String endDate = ju.particularDateFromNow(30);
		ccp.createContactWithDate(lastName,curDate,endDate);
		String actStartDate = driver.findElement(By.xpath("//span[@id='dtlview_Support Start Date']")).getText();
		String actEndDate = driver.findElement(By.xpath("//span[@id='dtlview_Support End Date']")).getText();
		Assert.assertEquals(actStartDate, curDate);
		Assert.assertEquals(actEndDate, endDate);
		UtilityClassObj.getTest().log(Status.INFO,"Current date "+curDate);
		UtilityClassObj.getTest().log(Status.INFO,"End date "+endDate);
		UtilityClassObj.getTest().log(Status.INFO,"Contact got created sucessfully!!!");
		
	}
	
}
