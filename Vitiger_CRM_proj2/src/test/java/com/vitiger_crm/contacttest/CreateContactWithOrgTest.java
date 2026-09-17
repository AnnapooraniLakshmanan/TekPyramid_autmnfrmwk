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
import object_repository_utility.CreatingNewOrganizationPage;
import object_repository_utility.HomePage;

@Listeners(generic_listenerUtility.ListenerImp.class)
public class CreateContactWithOrgTest extends BaseClass {

	@Test(groups = "smoke")
	public void createContactWithOrgTest() throws EncryptedDocumentException, IOException, InterruptedException {
		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();
		CreatingNewOrganizationPage oip = new CreatingNewOrganizationPage(driver);
		ExcelUtility eu = new ExcelUtility();
		JavaUtility ju = new JavaUtility();
		int data = ju.generateRandomNumber();
		String orgName = eu.readDataFromExcel("Sheet2", 0, 0) + data;
		oip.createOrg(orgName);
		String ex_ognm = driver.findElement(By.xpath("//span[@id='dtlview_Organization Name']")).getText();
		Assert.assertEquals(orgName, ex_ognm, "Organisation got created sucessfully!!!");

		String lastName = eu.readDataFromExcel("Sheet2", 1, 0) + data;

		CreateNewContactPage ccp = new CreateNewContactPage(driver);
		ccp.createContactWithOrg(lastName, orgName);

		String ex_header = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		boolean flag = false;
		if (ex_header.contains(lastName))
			flag = true;
		Assert.assertTrue(flag);
		UtilityClassObj.getTest().log(Status.INFO, "Contact got created sucessfully!!!");

		String ex_orgnm = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		String act = " " + orgName;
		Assert.assertEquals(act, ex_orgnm);
		UtilityClassObj.getTest().log(Status.INFO, "Contact verified sucessfully!!!");
	}
}
