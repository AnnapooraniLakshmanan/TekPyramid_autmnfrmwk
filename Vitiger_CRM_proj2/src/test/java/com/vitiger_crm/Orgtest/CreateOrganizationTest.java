package com.vitiger_crm.Orgtest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import generic_baseClass.BaseClass;
import generic_fileUtility.ExcelUtility;
import generic_webdriverUtility.JavaUtility;
import object_repository_utility.CreatingNewOrganizationPage;
import object_repository_utility.HomePage;

@Listeners(generic_listenerUtility.ListenerImp.class)
public class CreateOrganizationTest extends BaseClass
{
@Test(groups = "smoke")
public void createOrganizationTest() throws EncryptedDocumentException, IOException 
{
	
	HomePage hp = new HomePage(driver);
	hp.getOrglink().click();
	CreatingNewOrganizationPage oip = new CreatingNewOrganizationPage(driver);
	ExcelUtility eu = new ExcelUtility();
	JavaUtility ju = new JavaUtility();
	int data = ju.generateRandomNumber();
	String orgName = eu.readDataFromExcel("Sheet2", 0, 0)+data;
	oip.createOrg(orgName);
    String ex_header = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
    boolean flag=false;
	if(ex_header.contains(orgName))
    flag = true;
	Assert.assertTrue(flag);
	String ex_ognm = driver.findElement(By.xpath("//span[@id='dtlview_Organization Name']")).getText();
	Assert.assertEquals(orgName, ex_ognm, "Organisation got created sucessfully!!!");
	
}

	
}
