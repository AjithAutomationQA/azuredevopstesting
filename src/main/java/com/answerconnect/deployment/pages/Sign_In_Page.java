package com.answerconnect.deployment.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

public class Sign_In_Page extends BasePages {

	public Sign_In_Page() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

	}

	@iOSFindBy(accessibility = "SkipTourPage")
	public WebElement skipTourPage;

	@iOSFindBy(xpath = "//XCUIElementTypeTextField[@value='Email']")
	public WebElement emailId;

	@iOSFindBy(xpath = "//XCUIElementTypeSecureTextField[@value='Password']")
	public WebElement password;

	@iOSFindBy(xpath = "//XCUIElementTypeButton[@name='Login']")
	public WebElement login;

	@iOSFindBy(xpath = "//XCUIElementTypeTextField[@value='XXX-XXXXXXX']")
	public WebElement phoneNumber;

	@iOSFindBy(xpath = "//XCUIElementTypeButton[@name='Next']")
	public WebElement next;

	@iOSFindBy(xpath = "//XCUIElementTypeOther[@name='suggestion']")
	public WebElement otp;


	public void signIn() throws InterruptedException {

		tapOn("Email Id field", emailId);
		sendKeys(emailId, prop.getProperty("emailId"), "email id field");
		tapOn("Password field", password);
		sendKeys(password, prop.getProperty("password"), "password field");
		tapOn("Login button", login);
		wait(skipTourPage);
		tapOn("Skip", skipTourPage);
		if(isXpathPresent("//XCUIElementTypeTextField[@value='XXX-XXXXXXX']")){
			tapOn("phone number field", phoneNumber);
			sendKeys(phoneNumber, prop.getProperty("phoneNumber"), "phonenumber field");
			tapOn("Next button", next);
			wait(otp);
			tapOn("OTP", otp);
			wait(header);
		assertEquals(header, "Inbox", "User Logged into the app successfully");
		takeSS(getBase64());	
		}
	}

}
