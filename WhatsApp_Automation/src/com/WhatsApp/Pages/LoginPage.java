package com.WhatsApp.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.WhatsApp.Common.BasePage;

public class LoginPage extends BasePage{

	public LoginPage() {
		PageFactory.initElements(driver, this);		
	}
	
	@FindBy(name="rememberMe")
	WebElement rememberMeChkBx;
	
	public boolean isChecked(){
		return rememberMeChkBx.isSelected();
	}

}
