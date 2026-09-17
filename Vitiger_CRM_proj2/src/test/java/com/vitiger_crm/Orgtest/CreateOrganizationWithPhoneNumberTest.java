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
public class CreateOrganizationWithPhoneNumberTest extends BaseClass
{
	@Test(groups = "regression")
	public void createOrganizationWithPhoneNumbeTest() throws EncryptedDocumentException, IOException
	{
		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();
		CreatingNewOrganizationPage cop = new CreatingNewOrganizationPage(driver);
		ExcelUtility eu = new ExcelUtility();
		JavaUtility ju = new JavaUtility();
		int data = ju.generateRandomNumber();
		String orgName = eu.readDataFromExcel("Sheet2", 0, 0)+data;
		String phonenum = eu.readDataFromExcel("Sheet2", 0, 2);
		cop.createOrgWithPhone(orgName,phonenum);
		String ex_ognm = driver.findElement(By.xpath("//span[@id='dtlview_Organization Name']")).getText();
		Assert.assertEquals(orgName, ex_ognm, "Organisation got created sucessfully!!!");
		String ex_phone = driver.findElement(By.xpath("//span[@id='dtlview_Phone']")).getText();
		Assert.assertEquals(ex_phone,phonenum,"Org created with "+phonenum+" industries");
	}
}

