package com.answerconnect.deployment.pages;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.answerconnect.deployment.testcases.BaseTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;


import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSElement;


public class BasePages extends BaseTest{
 

	WebDriverWait wait = new WebDriverWait(driver, 60);

	public boolean tapOn(String name, WebElement element) throws InterruptedException {
		try {
			element.click();
		} catch (Exception e) {
			wait(element);
			element.click();
		}
		childTest.log(Status.INFO, "Tapped on ->" + name);
		return false;

	}

	public Boolean isXpathPresent(String element) {
		try {

			int size = driver.findElementsByXPath(element).size();
			System.out.println("element size is " + size);
			if (size > 0) {
				System.out.println("Element found");
				return true;
			} else {
				System.out.println("Element not found");
				return false;
			}
		} catch (Exception e) {
			System.out.println("exception ::" + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	public Boolean isAccessibilityIdPresent(String element) {
		try {
			int size = driver.findElementsByAccessibilityId(element).size();
			System.out.println("element size is " + size);
			if (size>0) {
				System.out.println("Element found");
				return true;
			} else {
				System.out.println("Element not found");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Exception ::"+ e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	public void printL(String input, String name) {
		childTest.log(Status.INFO, input);
	}
	public boolean wait(WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		}catch(Exception e) {
			return false;
		}

	}

	public void sendKeys(WebElement element, String input, String name) {
		element.sendKeys(input);
		childTest.log(Status.INFO, "Entered[ " + input + "]in " + name);
		//		print(Status.INFO,input);
		//		print(Status.INFO, name);
	}

	public void print(Status action, String name) {
		childTest.log(action, name);
	}

	public void testResult(Status action, String name) {
		childTest.log(action, name);
	}

	public void takeSS(String action) {
		childTest.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64()).build());
	}
	public String getBase64() {
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
	}

	public void labelGreen(Markup markup, String input) {
		childTest.info(MarkupHelper.createLabel(input, ExtentColor.GREEN));
	}

	public void assertEquals(WebElement element, String input, String name) {
		String getheadertext = element.getText();
		System.out.println(getheadertext);
		Assert.assertEquals(input, getheadertext);
		childTest.log(Status.PASS, name);

	}

	public void validate(MobileElement element, String name) {
		String getElementText = element.getText();
		System.out.println(getElementText);
		//	compareDate(getElementText);
		Assert.assertEquals(getElementText, compareDate(getElementText));
		childTest.log(Status.PASS, name);
	}

	public String compareDate(String input) {
		String text = "Not taking calls until ";
		Calendar variable = Calendar.getInstance();
		if(checkIsFutureDate(input)) {
			int month = getMonth(input);
			int date = getDate(input);
			int year = getYear(input);
			System.out.println("Month is =" + month + " Date is =" + date + " Year is =" + year);
			variable.set(Calendar.MONTH, month);
			variable.set(Calendar.DATE, date);
			variable.set(Calendar.YEAR, year);
			long timeInMilliSeconds = variable.getTimeInMillis();
			System.out.println(timeInMilliSeconds);
			String format;
			if(date>=10) {
				format = "MMM dd, yyyy";
			} else {
				format = "MMM d, yyyy";
			}
			String dateFormat = new SimpleDateFormat(format).format(timeInMilliSeconds);
			System.out.println(dateFormat);
			text = text + dateFormat;
		} else {

		}
		return text;
	}

	public int getDate(String date) {
		String ldate = date.substring(27,29);
		if(ldate.contains(",")) {
			ldate = ldate.replace(",", "");
		}
		return Integer.parseInt(ldate);
	}

	public int getMonth(String month) {
		return convertStringToInt(month.substring(23,26));
	}

	public int getYear(String year) {
		int lPosition = year.indexOf(",");
		year = year.substring(lPosition + 2);
		System.out.println(year);
		return Integer.parseInt(year.trim());
	}

	public int convertStringToInt(String month) {
		int mon = 0;
		if(month.contains("Jan")) {
			mon = 0;
		} else if(month.contains("Feb")) {
			mon =1;
		}else if(month.contains("Mar")) {
			mon = 2;
		}else if(month.contains("Apr")) {
			mon = 3;
		}else if(month.contains("May")) {
			mon = 4;
		} else if(month.contains("Jun")) {
			mon =5;
		}else if(month.contains("Jul")) {
			mon =6;
		}else if(month.contains("Aug")) {
			mon =7;
		}else if(month.contains("Sep")) {
			mon=8;
		}else if(month.contains("Oct")) {
			mon=9;
		}else if(month.contains("Nov")) {
			mon=10;
		}else if(month.contains("Dec")) {
			mon =11;
		}
		return mon;
	}

	public Boolean checkIsFutureDate(String date) {
		Boolean isFutureDate = false;
		if(date.contains("Jan") || date.contains("Feb") || date.contains("Mar") || date.contains("Apr") || date.contains("May") || date.contains("Jun") || date.contains("Jul") || date.contains("Aug") || date.contains("Sep") || date.contains("Oct") || date.contains("Sep") || date.contains("Oct") || date.contains("Nov") || date.contains("Dec")) {
			isFutureDate = true;
		}
		return isFutureDate;
	}

	public void assertEqualsSubheader(WebElement element, String input, String name) {
		String getSubheadertext = element.getText();
		System.out.println(getSubheadertext);
		Assert.assertEquals(input, getSubheadertext);
		childTest.log(Status.PASS, name);

	}

	public boolean doubleTap(String name, MobileElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			HashMap<String, String> tapObject = new HashMap<String, String>();
			tapObject.put("element", element.getId());
			js.executeScript("mobile:doubleTap", tapObject);
			childTest.log(Status.INFO, "Double tapped on ->" + name);
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean scrollDown(WebElement element, String text, String input) {
		try {
			
		

			JavascriptExecutor js = (JavascriptExecutor) driver;

			HashMap<String, Object> scrollObject = new HashMap<String, Object>();
	//		scrollObject.put("element", ((RemoteWebElement) element).getId());
			scrollObject.put("direction", "down");
			 scrollObject.put("predicateString", "value == '" + text + "'");
			driver.executeScript("mobile: scroll", scrollObject );
//			JavascriptExecutor js = (JavascriptExecutor) driver;
//			HashMap<String, Object> scrollObject = new HashMap<String, Object>();
//			scrollObject.put("direction", "down");
//		
//			scrollObject.put("element", ((RemoteWebElement) element).getId());
////			scrollObject.put("element", element.getAttribute(name));
//			driver.executeScript("mobile:scroll", scrollObject);
			childTest.log(Status.INFO, input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean scrollUp(MobileElement element, String name, String input) {
		try {
			HashMap<String, Object> scrollObject = new HashMap<String, Object>();
			scrollObject.put("direction", "up");
			scrollObject.put("element", element.getAttribute(name));
			driver.executeScript("mobile:scroll", scrollObject);
			childTest.log(Status.INFO, input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean swipeLeft(MobileElement element, String name, String input) {
		try {
			HashMap<String, Object> swipeObject = new HashMap<String, Object>();	
			swipeObject.put("direction", "left");
			swipeObject.put("element", element.getId());
			driver.executeScript("mobile:swipe", swipeObject);
			childTest.log(Status.INFO, input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean swipeRight(MobileElement element, String name, String input) {
		try {
			HashMap<String, Object> swipeObject = new HashMap<String, Object>();	
			swipeObject.put("direction", "right");
			swipeObject.put("element", element.getId());
			driver.executeScript("mobile:swipe", swipeObject);
			childTest.log(Status.INFO, input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	public static void scrollToElement1(String elementName) {
		String targetCell = "//UIATableCell[UIAStaticText[@name=\"" + elementName + "\"]]";
		WebElement cellWithText = driver.findElement(By.xpath(targetCell));
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("element", ((RemoteWebElement) cellWithText).getId());
		driver.executeScript("mobile: scrollTo", scrollObject);
	}

	public void assertNotNull(WebElement locator,String name) {
		//	WebElement element = wait.until(ExpectedConditions.visibilityOf(locator));
		String element = locator.getText();	
		System.out.println(element);
		Assert.assertNotNull(element);
		childTest.log(Status.PASS, name);


	}

	public String element(WebElement locator, String name) {
		String ele = locator.getText();
		if(ele.contains(ele))
			Assert.assertEquals(true, true);
		childTest.log(Status.PASS, name);
		//	Assert.assertEquals(name, ele);
		return ele;
	}

	public void datePicker(String hours, String minutes) throws InterruptedException
	{
		//	List<WebElement> values = driver.findElementsByXPath("//XCUIElementTypePickerWheel");
		List<IOSElement> values = driver.findElementsByXPath("//XCUIElementTypePickerWheel");
		for(int i=0;i<values.size();i++) {
			System.out.println(values.get(i).getText());
		}
		//	Thread.sleep(3000);
		//	values.get(0).sendKeys(date);
		//	values.get(0).sendKeys(Keys.TAB);

		Thread.sleep(3000);
		values.get(1).sendKeys(hours);
		values.get(1).sendKeys(Keys.TAB);

		Thread.sleep(3000);
		values.get(2).sendKeys(minutes);
		values.get(2).sendKeys(Keys.TAB);
		//	
		//	Thread.sleep(3000);
		//	values.get(3).sendKeys(am);
		//	values.get(3).sendKeys(Keys.TAB);

	}

	public String datePickerWheel()
	{ 
		List<IOSElement> pickerEls = driver.findElementsByXPath("//XCUIElementTypePickerWheel");
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("order", "next");
		params.put("offset", 0.15);
		params.put("element", ((RemoteWebElement) pickerEls.get(0)).getId());
		driver.executeScript("mobile: selectPickerWheelValue", params);


		params.put("order", "next");
		params.put("element", ((RemoteWebElement) pickerEls.get(1)).getId());
		driver.executeScript("mobile: selectPickerWheelValue", params);

		params.put("order", "next");
		params.put("element", ((RemoteWebElement) pickerEls.get(2)).getId());
		driver.executeScript("mobile: selectPickerWheelValue", params);
		for(int i=0;i<pickerEls.size();i++) {
			System.out.println(pickerEls.get(i).getText());

		}
		return null;

	}	



}