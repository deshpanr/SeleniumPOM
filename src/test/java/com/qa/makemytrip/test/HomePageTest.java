package com.qa.makemytrip.test;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.makemytrip.pages.HomePage;
import com.qa.makemytrip.testbase.TestBase;

public class HomePageTest extends TestBase {
	
	HomePage homePage;

	public HomePageTest() throws IOException {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws IOException {
		initionalization();
		homePage=new HomePage();	
	}
	
	@Test
	public void searchFlightTest() throws InterruptedException, IOException {
		homePage.searchFlights();
	}
	
	@AfterMethod
	public void shutDown() {
		driver.quit();
	}
}
