package com.inetbanking.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass {
	
	@Test(dataProvider="LoginData")
	public void LoginDDT(String user,String pwd) throws InterruptedException, IOException {
		
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(user);
		logger.info("User name Provided");
		lp.setPassword(pwd);
		logger.info("Password provided");
		lp.clickSubmit();
		Thread.sleep(3000);
		
		if(isAlertPresent()==true) {
			driver.switchTo().alert().accept(); // close alert
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.warn("Login Failed");
		}
		else {
		    Assert.assertTrue(true);
		    lp.clickLogout();
		    logger.info("Login Passed");
		    Thread.sleep(3000);
		    driver.switchTo().alert().accept(); // close logout alert
		    driver.switchTo().defaultContent();
		}
		
	}
	
	public boolean isAlertPresent() // User defined method created to check Alert is present or not. 
	{
		try
		{
			driver.switchTo().alert();
			return true;
		}
		catch(NoAlertPresentException e) {
			return false;
		}
	}
	
	@DataProvider(name="LoginData")
	String[][] getData() throws IOException{
		String path=System.getProperty("user.dir") +"/src/test/java/com/inetbanking/testData/LoginData.xlsx";
		int rownum=XLUtils.getRowCount(path, "sheet1");
		int colcount=XLUtils.getCellCount(path, "Sheet1", 1);
		
		String logindata[][]=new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				logindata[i-1][j]=XLUtils.getCellData(path, "Sheet1", i, j);// 1 0
			}
		}
		return logindata;
		
	}

}
