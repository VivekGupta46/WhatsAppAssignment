package com.WhatsApp.Tests;
import java.util.Map;

import org.testng.annotations.Test;

import com.WhatsApp.Common.BaseTest;
import com.WhatsApp.Pages.DashboardPage;
import com.WhatsApp.Pages.LoginPage;
public class MessageDashboardTest extends BaseTest{
	
	
	public MessageDashboardTest() {
		super();
	}
	
	@Test
	public void logMsgToTxtFile() throws Exception{
		LoginPage login = new LoginPage();
		DashboardPage dashboard = new DashboardPage();
		
		Boolean t = login.isChecked();
		System.out.println(t);	
		System.out.println("--- Scan your phone to Login ---");
		int nbrOfMsg = dashboard.getNbrOfNewMsg();
		dashboard.clickOnGroup("Fucker Vivek");
		Map<String, String> msg = dashboard.getTextMsg(nbrOfMsg);
		dashboard.logValueInFile(msg);
		dashboard.logout();
		
	}

}
