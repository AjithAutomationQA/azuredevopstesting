package com.answerconnect.deployment.testcases;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


import com.answerconnect.app.base.AutomationTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;



public class BaseTest extends AutomationTest {


	public BaseTest() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

	}

	@iOSFindBy(accessibility = "Add Label")
	public WebElement addLabel;

	@iOSFindBy(xpath = "//XCUIElementTypeButton[@name='tabbar_item_inbox']")
	public WebElement inbox;

	@iOSFindBy(accessibility = "tab_navbar_title")
	public WebElement header;
	//Header for nav title bar
	

}
