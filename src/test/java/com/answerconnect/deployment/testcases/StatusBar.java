package com.answerconnect.deployment.testcases;

import org.testng.annotations.Test;

import com.answerconnect.deployment.pages.StatusBarPage;



public class StatusBar extends BaseTest {
	@Test(description = "StatusBarPage")
	public void statusBar() throws Exception {
		
		childTest= test.createNode("StatusBarPage").assignAuthor("Arun").assignCategory("Smoke").assignDevice("iPhone 7 OS 14");
		System.out.println("extent report");
		StatusBarPage statusbarpage = new StatusBarPage();
		statusbarpage.status();
		System.out.println("statusbar completed");
	}
}
 