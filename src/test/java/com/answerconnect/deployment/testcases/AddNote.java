package com.answerconnect.deployment.testcases;

import org.testng.annotations.Test;

import com.answerconnect.deployment.pages.AddNotePage;


public class AddNote extends BaseTest{

	@Test(description = "AddNote")
	public void addNote() throws InterruptedException
	{
		childTest= test.createNode("AddNote").assignAuthor("Arun").assignCategory("Smoke").assignDevice("iPhone 7 OS 14");
		System.out.println("extent report");
		AddNotePage addnotepage = new AddNotePage();
		addnotepage.addNote();
	}

}
