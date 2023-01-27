package com.answerconnect.deployment.testcases;







import org.testng.annotations.Test;

import com.answerconnect.deployment.pages.Sign_In_Page;

public class Sign_In_Anct extends BaseTest {

	@Test(description = "Sign_In_Anct")
	public void signIn() throws Exception {
		
		childTest= test.createNode("Sign_In_Anct").assignAuthor("Arun").assignCategory("Smoke").assignDevice("iPhone 7 OS 14");
		System.out.println("extent report");
		Sign_In_Page signinpage = new Sign_In_Page();
		signinpage.signIn();

	}

}



