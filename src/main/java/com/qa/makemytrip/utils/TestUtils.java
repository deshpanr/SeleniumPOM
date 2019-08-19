package com.qa.makemytrip.utils;
import java.io.IOException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.qa.makemytrip.testbase.TestBase;

public class TestUtils extends TestBase {
	
	public TestUtils() throws IOException {
		super();
	}

	public static final long PAGE_LOAD_TIMEOUT = 30;
	public static final long IMPLICIT_WAIT=10;
	
	public static void scrollDownPage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
		    long lastHeight=((Number)js.executeScript("return document.body.scrollHeight")).longValue();
		    while (true) {
		        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
		        Thread.sleep(2000);

		        long newHeight = ((Number)js.executeScript("return document.body.scrollHeight")).longValue();
		        if (newHeight == lastHeight) {
		            break;
		        }
		        lastHeight = newHeight;
		    }
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	}
	
	public static void scrollToSpecificWebElement(WebElement element) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		 ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-200)");
	}
}
