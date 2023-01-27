package com.answerconnect.deployment.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

public class AddNotePage extends BasePages {
	public AddNotePage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@iOSFindBy(xpath = "(//XCUIElementTypeCell[@name=\"inbound_message_cell\"])[1]")
	public WebElement message;
	
	@iOSFindBy(accessibility = "interactiondetails_addnote_button")
	public WebElement addNote;
	
	@iOSFindBy(accessibility = "Add a note for your team...")
	public WebElement AddNoteField;
	
	@iOSFindBy(accessibility = "JumpClip")
	public WebElement jumpClip;
	
	@iOSFindBy(accessibility = "Use Camera")
	public WebElement useCamera;
	
	@iOSFindBy(accessibility = "PhotoCapture")
	public WebElement photoCapture;
	
	@iOSFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Use Photo\"]")
	public WebElement usePhoto;
	
	@iOSFindBy(accessibility = "SendEnabled")
	public WebElement send;
	
	@iOSFindBy(accessibility = "moredetailscell_allactiviteslbl")
	public WebElement allActivities;
	
	@iOSFindBy(accessibility = "interactiondetail_header_activitybutton")
	public WebElement activityButton;

	
	@iOSFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"Automation note from iOS\"])[1]")
	public WebElement note;

	

	public void addNote() throws InterruptedException {
		System.out.println("message");
		tapOn("on a message", message);
		takeSS(getBase64());
		tapOn("add note", addNote);
		sendKeys(AddNoteField, "Automation note from iOS", "Add note field");
		tapOn("jumpclip", jumpClip);
		tapOn("Use camera", useCamera);
		tapOn("photoCapture",photoCapture);
		tapOn("use photo", usePhoto);
		tapOn("send arrow", send);
		wait(activityButton);
//		scrollDown(note,"Automation note from iOS", "scroll down");
		scrollDown(allActivities,"All Activities", "scroll down");
		assertEquals(note, "Automation note from iOS", "Verified the text");
		
	}
}