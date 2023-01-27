package com.answerconnect.deployment.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.google.inject.matcher.Matchers;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

public class StatusBarPage extends BasePages {
	public StatusBarPage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}



	@iOSFindBy(accessibility = "status_toggle_view")
	public MobileElement statusToggle;
	
	//status_toggle_content_view
	@iOSFindBy(accessibility = "unavailable_status_title_label")
	public MobileElement iamUnvailable;

	@iOSFindBy(accessibility = "unavailable_status_subtitle_label")
	public MobileElement notTakingCalls;

	@iOSFindBy(accessibility = "available_status_title_label")
	public MobileElement available;

	@iOSFindBy(accessibility = "available_status_subtitle_label")
	public MobileElement iCanTakeCalls;

	@iOSFindBy(accessibility = "status_toggle_arrowbtn_view")
	public MobileElement statusToggleArrow;

	@iOSFindBy(accessibility = "not_available_cell_meeting")
	public MobileElement inAMeeting;

	@iOSFindBy(accessibility = "For an Hour")
	public MobileElement forAnHour;

	@iOSFindBy(accessibility = "Custom")
	public MobileElement setCustom;

	@iOSFindBy(accessibility = "not_available_cell_lunch")
	public MobileElement atLunch;

	@iOSFindBy(accessibility = "30 Minutes")
	public MobileElement minutes_30;

	@iOSFindBy(accessibility = "60 Minutes")
	public MobileElement minutes_60;

	@iOSFindBy(accessibility = "not_available_cell_vacation")
	public MobileElement onVacation;

	@iOSFindBy(accessibility = "1 Day")
	public MobileElement day_1;

	@iOSFindBy(accessibility = "1 Week")
	public MobileElement week_1;

	@iOSFindBy(accessibility = "not_available_cell_custom")
	public MobileElement custom;

	@iOSFindBy(accessibility = "custom_status_textfield")
	public MobileElement customTextField;

	@iOSFindBy(accessibility = "status_done_button")
	public MobileElement statusDone;

	@iOSFindBy(accessibility = "Return")
	public MobileElement return_key;
	
	@iOSFindBy(accessibility = "status_duration_back_button")
	public MobileElement statusDurationBackArrow;
	
	@iOSFindBy(accessibility = "available_cell")
	public MobileElement availableCell;
	
	
	

	public void status() throws InterruptedException {

		//Double tap flows
		System.out.println("In Status");
		doubleTap("status bar to change the status to I'm Unavailable", statusToggle);
		assertEquals(iamUnvailable, "I’m Unavailable", "Verified  the text I’m Unavailable successfully");
		assertEquals(notTakingCalls, "Not taking calls ", "Verified the text Not taking calls successfully");
		takeSS(getBase64());
		doubleTap("status bar to change the status to I'm Available", statusToggle);	
		assertEquals(available, "I'm Available", "Verified the text I'm Available succesfully");
		assertEquals(iCanTakeCalls, "I can take calls", "Verified the text I can take calls successfully");
		takeSS(getBase64());	

		//In a Meeting flows

		tapOn("status arrow", statusToggleArrow);
		tapOn("In a Meeting", inAMeeting);
		tapOn("Done button", statusDone);
		assertEquals(iamUnvailable, "In Meeting", "Verified  the text I’m Unavailable successfully");
		assertEquals(notTakingCalls, "Not taking calls ", "Verified the text Not taking calls successfully");
		takeSS(getBase64());					
		tapOn("status arrow", statusToggleArrow);
		tapOn("In a Meeting", inAMeeting);
		tapOn("For an Hour", forAnHour);
		tapOn("Done button", statusDone);
		assertEquals(iamUnvailable, "In Meeting", "Verified  the text I’m Unavailable successfully");
		validate(notTakingCalls, "Verified the status set for an hour successfully");
		takeSS(getBase64());
		tapOn("status arrow", statusToggleArrow);
		tapOn("In a Meeting", inAMeeting);
		tapOn("Custom and changed days, hours and minutes", setCustom);
		datePickerWheel();
		tapOn("Done button", statusDone);
		assertEquals(iamUnvailable, "In Meeting", "Verified  the text I’m Unavailable successfully");
		validate(notTakingCalls, "Verified the custom status successfully");
		takeSS(getBase64());

		//At Lunch flows

		tapOn("status arrow", statusToggleArrow);
		tapOn("At Lunch", atLunch);
		tapOn("30 Minutes", minutes_30);
		tapOn("Done button", statusDone);
		assertEquals(iamUnvailable, "At Lunch", "Verified  the text I’m Unavailable successfully");
		validate(notTakingCalls, "Verified the status set for 30 minutes successfully");
		takeSS(getBase64());
		tapOn("status arrow", statusToggleArrow);
		tapOn("At Lunch", atLunch);
		tapOn("60 Minutes", minutes_60);
		tapOn("Done button", statusDone);
		assertEquals(iamUnvailable, "At Lunch", "Verified  the text I’m Unavailable successfully");
		validate(notTakingCalls, "Verified the status set for 60 minutes successfully");
		takeSS(getBase64());
		tapOn("status arrow", statusToggleArrow);
		tapOn("At Lunch", atLunch);
		tapOn("Custom and changed days, hours and minutes", setCustom);
		datePickerWheel();
		tapOn("Done button", statusDone);
		assertEquals(iamUnvailable, "At Lunch", "Verified  the text I’m Unavailable successfully");
		validate(notTakingCalls, "Verified the custom status successfully");
		takeSS(getBase64());

		//On vacation flows

		tapOn("status arrow", statusToggleArrow);
		tapOn("On Vacation", onVacation);
		tapOn("1 Day", day_1);
		tapOn("Done button", statusDone);
		assertEquals(iamUnvailable, "On Vacation", "Verified  the text I’m Unavailable successfully");
		validate(notTakingCalls, "Verified the status set for 1 day successfully");
		takeSS(getBase64());
		tapOn("status arrow", statusToggleArrow);
		tapOn("On Vacation", onVacation);
		tapOn("1 Week", week_1);
		tapOn("Done button", statusDone);
		assertEquals(iamUnvailable, "On Vacation", "Verified  the text I’m Unavailable successfully");
		validate(notTakingCalls, "Verified the status set for 1 week successfully");
		takeSS(getBase64());
		tapOn("status arrow", statusToggleArrow);
		tapOn("On Vacation", onVacation);
		tapOn("Custom and changed days, hours and minutes", setCustom);
		datePickerWheel();
		tapOn("Done button", statusDone);
		assertEquals(iamUnvailable, "On Vacation", "Verified  the text I’m Unavailable successfully");
		validate(notTakingCalls, "Verified the custom status successfully");
		takeSS(getBase64());

		//Custom flows

		tapOn("status arrow", statusToggleArrow);
		tapOn("Custom", custom);
		sendKeys(customTextField, prop.getProperty("customText"), "Entered text in custom text field");
		tapOn("Return in Keyboard", return_key);
		tapOn("Done button", statusDone);
		assertEquals(iamUnvailable, "TestStatus", "Verified the custom text successfully");
		assertEquals(notTakingCalls, "Not taking calls ", "Verified the text Not taking calls successfully");
		takeSS(getBase64());
		tapOn("status arrow", statusToggleArrow);
		tapOn("Custom", custom);
		sendKeys(customTextField, prop.getProperty("customText"), "Entered text in custom text field");
		tapOn("Return in Keyboard", return_key);
		tapOn("For an Hour", forAnHour);
		tapOn("Done button", statusDone);
		assertEquals(iamUnvailable, "TestStatus", "Verified the custom text successfully");
		validate(notTakingCalls, "Verified the custom status set for an hour successfully");
		takeSS(getBase64());
		tapOn("status arrow", statusToggleArrow);
		tapOn("Custom", custom);
		sendKeys(customTextField, prop.getProperty("customText"), "Entered text in custom text field");
		tapOn("Return in Keyboard", return_key);
		tapOn("Custom and changed days, hours and minutes", setCustom);
		datePickerWheel();
		tapOn("Done button", statusDone);
		assertEquals(iamUnvailable, "TestStatus", "Verified the custom text successfully");
		validate(notTakingCalls, "Verified the custom status successfully");
		takeSS(getBase64());
		
	//	Toggle and back arrow checks
		
		doubleTap("status bar to change the status to I'm Available", statusToggle);
		tapOn("status arrow", statusToggleArrow);
		tapOn("In a Meeting", inAMeeting);
		tapOn("Back arrow", statusDurationBackArrow);
		tapOn("At Lunch", atLunch);
		tapOn("Back arrow", statusDurationBackArrow);
		tapOn("On Vacation", onVacation);
		tapOn("Back arrow", statusDurationBackArrow);
		tapOn("Custom", custom);
		tapOn("Back arrow", statusDurationBackArrow);
		tapOn("I'm Available", availableCell);
		assertEquals(available, "I'm Available", "Verified the text I'm Available succesfully");
		assertEquals(iCanTakeCalls, "I can take calls", "Verified the text I can take calls successfully");
	}
}