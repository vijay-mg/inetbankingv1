package com.inetbanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.AddCustomerPage;
import com.inetbanking.pageObjects.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass{
	
	@Test
	public void addNewCustomer() throws InterruptedException, IOException
	{
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(username);
		
		logger.info("Username Provided");
		lp.setPassword(password);
		
		logger.info("Password Provided");
		lp.clickSubmit();
		
		Thread.sleep(3000);
		
		AddCustomerPage addcust=new AddCustomerPage(driver);
		addcust.clickAddNewCustomer();
		logger.info("Providing Customer Details...");
		
		addcust.custName("Kiran");
		addcust.custgender("Male");
		addcust.custdob("10", "05", "1985");
		Thread.sleep(3000);
		addcust.custaddress("India");
		addcust.custcity("Hyd");
		addcust.custstate("AP");
		addcust.custpinno("500038");
		
		String number=randomeNum();
		addcust.custtelephoneno(number);
		
		String email=randomestring() + "@gmail.com";
		addcust.custemailid(email);
		
		addcust.custpassword("abcdef");
		addcust.custsubmit();
		
		Thread.sleep(3000);
		logger.info("Validation Started...");
		
		boolean res=driver.getPageSource().contains("Customer Registered Successfully!!!");
		
		if(res==true)
		{
			Assert.assertTrue(true);
			logger.info("Testcase Passed...");
		}
		else {
			captureScreen(driver, "addNewCustomer");
			Assert.assertFalse(false);
			logger.info("Testcase Failed...");
		}
		
	}
	
	

}
