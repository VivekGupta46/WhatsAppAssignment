package com.WhatsApp.Pages;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.WhatsApp.Common.BasePage;

public class DashboardPage extends BasePage{
	
	public DashboardPage() {
		PageFactory.initElements(driver, this);	
	}

	@FindBy(xpath="//span[text()='Fucker Vivek']/ancestor::div[@class='_2WP9Q']")
	WebElement groupIdfr;
	
	@FindBy(xpath="//span[@class='P6z4j']")
	WebElement nbrOfMsgIdfr;
	
	@FindBy(xpath="//div[contains(@class,'FTBzM _')]//div[contains(@class,'copyable-text')]")
	List<WebElement> msgIdfr;
	
	public void clickOnGroup(String groupName) throws Exception{
		
		try{
			Actions action = new Actions(driver);
			String idft = "//span[text()='"+groupName+"']/ancestor::div[@class='_2WP9Q']";
			WebElement element = driver.findElement(By.xpath(idft));
			action.moveToElement(element).doubleClick().build().perform();
			
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		
	}
	
	public int getNbrOfNewMsg(){
		try{
			String msg = nbrOfMsgIdfr.getText();
			return Integer.parseInt(msg);
		}catch (Exception e) {
			return 0;
		}
	}
	
	public Map<String,String> getTextMsg(int nbrOfLatestMsg){
		
		Map<String,String> val = new HashMap<String, String>();
		for(int i=0;i<nbrOfLatestMsg;i++){
			val.put(msgIdfr.get(i).getAttribute("data-pre-plain-text"), msgIdfr.get(i).getText());
		}
		return val;
		
	}
	
	public void logValueInFile(Map<String,String> data) throws Exception{
		
		String path="/Reports/MsgLog.txt";	
		BufferedWriter writer=null;
	try{		
		File yourFile = new File(System.getProperty("user.dir") +path);
		yourFile.createNewFile(); // if file already exists will do nothing 
		writer = new BufferedWriter(new FileWriter(System.getProperty("user.dir") +path, true));		
	    for(String key: data.keySet()){
	    	writer.append(key);
	    	writer.append(" ");
	    	writer.append(data.get(key));
	    	writer.newLine();
		}
	}catch(Exception e){
		throw new Exception(e.getMessage());
	}
	finally{
		writer.close();
	}
		
	}
	
}