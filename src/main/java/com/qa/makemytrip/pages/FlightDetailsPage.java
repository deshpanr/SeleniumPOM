package com.qa.makemytrip.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.makemytrip.testbase.TestBase;
import com.qa.makemytrip.utils.TestUtils;

public class FlightDetailsPage extends TestBase {
	
	@FindBy(xpath="//label[@for='filter_stop0']//span[@class='labeltext'][contains(text(),'Non Stop')]")
	WebElement nonStopCheckBox;
	@FindBy(xpath="//label[@for='filter_stop1']//span[@class='labeltext'][contains(text(),'1 Stop')]")
	WebElement oneStopCheckBox;
	@FindBy(xpath="//div[contains(@class,'splitVw-footer-left')]/div[contains(@class,'clearfix')]/div[@class='timing-option pull-left']/div[@class='pull-right marL5 text-right']/p")
	public WebElement depFlightFareFooter;
	@FindBy(xpath="//div[contains(@class,'splitVw-footer-right')]/div[contains(@class,'clearfix')]/div[@class='timing-option pull-left']/div[@class='pull-right marL5 text-right']/p")
	public WebElement retFlightFareFooter;
	@FindBy(xpath="//span[contains(@class,'splitVw-total-fare')]")
	public WebElement totalFlightFare;
	
	public FlightDetailsPage() throws IOException {
		PageFactory.initElements(driver, this);
	}
	
	public Map<String, Integer> getCountOfDepAndReturnFlight(boolean nonStopChkBox,boolean oneStopChkBox) throws InterruptedException {
		
		Map<String,Integer> flightCount=new HashMap<String, Integer>();
		List<WebElement> depFlight=new ArrayList<WebElement>();
		List<WebElement> returnFlight=new ArrayList<WebElement>();;
		if((nonStopChkBox) && !(oneStopChkBox)) {
			nonStopCheckBox.click();
			depFlight=getDepFlights();
			returnFlight=getReturnFlights();
			flightCount.put("DepartureFlightCount", depFlight.size());
			flightCount.put("ReturnFlightCount", returnFlight.size());
	
		}
		else if((oneStopChkBox) && !(nonStopChkBox)) {
			oneStopCheckBox.click();
			depFlight=getDepFlights();
			returnFlight=getReturnFlights();
			flightCount.put("DepartureFlightCount", depFlight.size());
			flightCount.put("ReturnFlightCount", returnFlight.size());
		}
		
		else if(!(oneStopChkBox) && !(nonStopChkBox)) {
			depFlight=getDepFlights();
			returnFlight=getReturnFlights();
			flightCount.put("DepartureFlightCount", depFlight.size());
			flightCount.put("ReturnFlightCount", returnFlight.size());
		}
		return flightCount;
	}
	
	public Map<String, String> getDepAndRetFlightFares(int depFlightRowNo,int retFlightRowNo) throws InterruptedException {
		Map<String,String> depAndRetFlightFares=new HashMap<String, String>();
		List<WebElement> depFlightsFare=new ArrayList<WebElement>();
		List<WebElement> retFlightsFare=new ArrayList<WebElement>();
		String depFlightFare=null;
		String retFlightFare=null;
		
		//Getting Departure Flight Fare for selected Flight
		depFlightsFare=getDepFlightsFare();
		TestUtils.scrollToSpecificWebElement(depFlightsFare.get(depFlightRowNo));
		new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(depFlightsFare.get(depFlightRowNo))).click();
		depFlightFare=depFlightsFare.get(depFlightRowNo).getText();
		depAndRetFlightFares.put("SelectedDepartureFlightFare", depFlightFare);
		
		//Getting Return Flight Fare for selected Flight
		retFlightsFare=getRetFlightsFare();
		TestUtils.scrollToSpecificWebElement(retFlightsFare.get(retFlightRowNo));
		new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(retFlightsFare.get(retFlightRowNo))).click();
		retFlightFare=retFlightsFare.get(retFlightRowNo).getText();
		depAndRetFlightFares.put("SelectedReturnFlightFare", retFlightFare);
		
		//Getting Total Fare
		String replaced=depFlightFare.replaceAll("[-+.^:,]", "");
		String finalReplaced=replaced.replace("Rs", "").trim();
		long depFlightCost=Long.parseLong(finalReplaced);
		String replacedretFlightFare=retFlightFare.replaceAll("[-+.^:,]", "");
		String finalreplacedretFlightFare=replacedretFlightFare.replace("Rs", "").trim();
		long retFlightCost=Long.parseLong(finalreplacedretFlightFare);
		long totalFlightFare=depFlightCost+retFlightCost;
		depAndRetFlightFares.put("TotalFlightFare",Long.toString(totalFlightFare));
		return depAndRetFlightFares;
	}
	
	
	private List<WebElement> getDepFlights() throws InterruptedException {
		TestUtils.scrollDownPage();
		Thread.sleep(2000);
		List<WebElement> depFlights=driver.findElements(By.xpath("//div[@id='ow_domrt-jrny']/div[2]/div"));
			return depFlights;
		}
	
	private List<WebElement> getReturnFlights() throws InterruptedException {
		TestUtils.scrollDownPage();
		Thread.sleep(2000);
		List<WebElement> retFlights=driver.findElements(By.xpath("//div[@id='rt-domrt-jrny']/div[2]/div"));
	     return retFlights;
	}
	
	private List<WebElement> getDepFlightsFare() throws InterruptedException{
		TestUtils.scrollDownPage();
		Thread.sleep(2000);
		List<WebElement> depFlightsFare=driver.findElements(By.xpath("//div[@id='ow_domrt-jrny']/div[2]/div/label/div[2]/div[3]"));
		return depFlightsFare;	
	}
	
	private List<WebElement> getRetFlightsFare() throws InterruptedException{
		TestUtils.scrollDownPage();
		Thread.sleep(2000);
		List<WebElement> retFlightsFare=driver.findElements(By.xpath("//div[@id='rt-domrt-jrny']/div[2]/div/label/div[2]/div[3]"));
		return retFlightsFare;	
	}
	}
