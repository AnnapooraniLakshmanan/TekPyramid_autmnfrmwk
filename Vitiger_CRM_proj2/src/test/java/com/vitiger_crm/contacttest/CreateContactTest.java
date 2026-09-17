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
public class CreateContactTest extends BaseClass 
{
@Test(groups = "smoke",retryAnalyzer = generic_listenerUtility.RetryImp.class)
public void createContactTest() throws EncryptedDocumentException, IOException
{
         
	
	CreateNewContactPage ccp = new CreateNewContactPage(driver);
	ExcelUtility eu = new ExcelUtility();
	JavaUtility ju = new JavaUtility();
	int data = ju.generateRandomNumber();
	String lastName = eu.readDataFromExcel("Sheet2", 1, 0)+data;
	ccp.createContact(lastName);
	String ex_header = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
    boolean flag=false;
	if(ex_header.contains(lastName))
    flag = true;
	Assert.assertTrue(flag);
	UtilityClassObj.getTest().log(Status.INFO,"Contact got created sucessfully!!!");
	String ex_lsnm = driver.findElement(By.xpath("//span[@id='dtlview_Last Name']")).getText();
	Assert.assertEquals(lastName, ex_lsnm);
	UtilityClassObj.getTest().log(Status.INFO,"Contact verified sucessfully!!!");
}
}
