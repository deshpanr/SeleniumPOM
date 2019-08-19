package com.qa.makemytrip.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.makemytrip.testbase.TestBase;
import com.qa.makemytrip.utils.TestUtils;

public class HomePage extends TestBase {

	@FindBy(xpath = "//span[text()='Flights']")
	WebElement flightsLink;
	@FindBy(xpath = "//span[contains(text(),'From')]")
	WebElement fromSection;
	@FindBy(xpath = "//span[contains(text(),'To')]")
	WebElement toSection;
	@FindBy(xpath = "//input[contains(@placeholder,'From')]")
	WebElement fromSectionInput;
	@FindBy(xpath = "//input[contains(@placeholder,'To')]")
	WebElement toSectionInput;
	@FindBy(xpath = "//a[contains(text(),'Search')]")
	WebElement searchButton;
	@FindBy(xpath = "//li[@class='selected']")
	WebElement OptionSelected;
	@FindBy(xpath = "//span[@class='tabsCircle appendRight5']/parent::li[contains(text(),'Round Trip')]")
	WebElement RoundTrip;
	@FindBy(xpath = "//div[@class='DayPicker-Caption']")
	WebElement months;
	@FindBy(xpath = "//span[contains(text(),'DEPARTURE')]")
	WebElement depature;
	@FindBy(xpath = "//span[contains(text(),'RETURN')]")
	WebElement returnSection;
	@FindBy(xpath = "//span[@class='DayPicker-NavButton DayPicker-NavButton--next']")
	WebElement nextButton;

	public HomePage() throws IOException {
		PageFactory.initElements(driver, this);
	}

	public FlightDetailsPage searchFlights() throws InterruptedException, IOException {
		String expMonth = "August2019";
		String expDepDay = "19";
		String expReurnDay= String.valueOf(Integer.valueOf(expDepDay)+7);
		if (!(OptionSelected.getText().equalsIgnoreCase("Round Trip"))) {
			RoundTrip.click();
		}
		fromSection.click();
		fromSectionInput.sendKeys(prop.getProperty("FromCity"));
		driver.findElement(By.xpath(
				"//li[@id='react-autowhatever-1-section-0-item-0']//p[@class='font14 appendBottom5 blackText'][contains(text(),'"
						+ prop.getProperty("FromCity") + "')]"))
				.click();
		toSectionInput.sendKeys(prop.getProperty("DestCity"));
		driver.findElement(By.xpath("//p[contains(text(),'" + prop.getProperty("DestCity") + "')]")).click();
		depature.click();
		selectDate(expMonth, expDepDay);
		returnSection.click();
		selectDate(expMonth, expReurnDay);
		searchButton.click();
	    return new FlightDetailsPage();
	}

	private void selectDate(String expMonth, String expDepDay) {
		String actMonth = driver.findElement(By.xpath("//div[@class='DayPicker-Caption']")).getText();
		while (true) {
			if (actMonth.equalsIgnoreCase(expMonth)) {
				break;
			}

			else {
				nextButton.click();
			}
		}

		List<WebElement> allDates = driver
				.findElements(By.xpath("//div[@class='DayPicker-Months']/div[1]/div[3]/div/div/div"));
		for (WebElement dates : allDates) {
			if (dates.getText().startsWith(expDepDay)) {
				dates.click();
				break;
			}

		}
	}
}
