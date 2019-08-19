package com.qa.makemytrip.test;
import java.io.IOException;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.makemytrip.pages.FlightDetailsPage;
import com.qa.makemytrip.pages.HomePage;
import com.qa.makemytrip.testbase.TestBase;

//import junit.framework.Assert;

public class FlightDetailsPageTest extends TestBase {
	
	HomePage homePage;
	FlightDetailsPage flightDetailsPage;

	public FlightDetailsPageTest() throws IOException {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws IOException, InterruptedException {
		initionalization();
		homePage=new HomePage();
		flightDetailsPage=homePage.searchFlights();
	}
	
	@Test
	public void FlightCountWithoutSelectingAnyStopOptionsTest() throws InterruptedException{
		Map<String,Integer> flightCount=flightDetailsPage.getCountOfDepAndReturnFlight(false, false);
		for (Map.Entry<String, Integer> entry : flightCount.entrySet()) {
			if(entry.getKey().equalsIgnoreCase("DepartureFlightCount")) {
				int depFlightCount=entry.getValue();
				System.out.println("DepartureFlightCount: "+depFlightCount);
			}
			else if(entry.getKey().equalsIgnoreCase("ReturnFlightCount")) {
				int returnFlightCount=entry.getValue();
				System.out.println("ReturnFlightCount: "+returnFlightCount);
			}
		}
	}
	
	@Test
	public void FlightCountBySelectingNonStopOptionTest() throws InterruptedException {
		Map<String,Integer> flightCount=flightDetailsPage.getCountOfDepAndReturnFlight(true, false);
		for (Map.Entry<String, Integer> entry : flightCount.entrySet()) {
			if(entry.getKey().equalsIgnoreCase("DepartureFlightCount")) {
				int depFlightCount=entry.getValue();
				System.out.println("DepartureFlightCount: "+depFlightCount);
			}
			else if(entry.getKey().equalsIgnoreCase("ReturnFlightCount")) {
				int returnFlightCount=entry.getValue();
				System.out.println("ReturnFlightCount: "+returnFlightCount);
			}
		}
	}
	
	@Test
	public void FlightCountBySelectingOneStopOptionTest() throws InterruptedException {
		Map<String,Integer> flightCount=flightDetailsPage.getCountOfDepAndReturnFlight(false, true);
		for (Map.Entry<String, Integer> entry : flightCount.entrySet()) {
			if(entry.getKey().equalsIgnoreCase("DepartureFlightCount")) {
				int depFlightCount=entry.getValue();
				System.out.println("DepartureFlightCount: "+depFlightCount);
			}
			else if(entry.getKey().equalsIgnoreCase("ReturnFlightCount")) {
				int returnFlightCount=entry.getValue();
				System.out.println("ReturnFlightCount: "+returnFlightCount);
			}
		}
	}
	
	@Test
	public void DepAndReturnFlightFareTest() throws InterruptedException {
		Map<String,String> selDepAndRetFlightFares=flightDetailsPage.getDepAndRetFlightFares(3, 4);
		for (Map.Entry<String, String> entry : selDepAndRetFlightFares.entrySet()) {
			if(entry.getKey().equalsIgnoreCase("SelectedDepartureFlightFare")) {
			Assert.assertEquals(entry.getValue(), flightDetailsPage.depFlightFareFooter.getText());
			}
			else if(entry.getKey().equalsIgnoreCase("SelectedReturnFlightFare")) {
				Assert.assertEquals(entry.getValue(), flightDetailsPage.retFlightFareFooter.getText());
			}
			
			else if(entry.getKey().equalsIgnoreCase("TotalFlightFare")) {
				Assert.assertEquals(Long.parseLong(entry.getValue()), Long.parseLong(flightDetailsPage.totalFlightFare.getText().replace("₹", "").trim()));
			}
			
		}
		
	}
	
	@AfterMethod
	public void shutDown() {
		driver.quit();
	}
}
