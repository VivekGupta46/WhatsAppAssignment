package com.WhatsApp.Common;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public abstract class BasePage {

	protected static Properties prop;
	public static WebDriver driver;

	
	public BasePage() {		
		prop = new Properties();
		FileInputStream ip;
		try {
			ip = new FileInputStream(System.getProperty("user.dir") + "/Resources/application.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void invoke(){
		String browerName = prop.getProperty("browser");
		if(browerName.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/Servers/chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("test-type");
			options.addArguments("start-maximized");
			options.addArguments("--js-flags=--expose-gc");
			options.addArguments("--enable-precise-memory-info");
			options.addArguments("--disable-popup-blocking");
			options.addArguments("--disable-default-apps");
			options.addArguments("test-type=browser");    
			options.addArguments("disable-infobars");
			options.setAcceptInsecureCerts(true);
			options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
			driver = new ChromeDriver(options);
		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.get(prop.getProperty("env.baseurl"));
	}

	public void logTextFile(String path, String logMessage) throws Exception{
		DataOutputStream dataOutStream = null;
		try{
			File yourFile = new File(System.getProperty("user.dir") +path);
			yourFile.createNewFile(); // if file already exists will do nothing 
			FileOutputStream oFile = new FileOutputStream(yourFile, false);
			dataOutStream = new DataOutputStream(new BufferedOutputStream(oFile));
			dataOutStream.writeUTF(logMessage);
		}catch(Exception e){
			throw new Exception(e.getMessage());
		}
		finally{
			dataOutStream.close();
		}
	}
	
	public void logout() throws Exception{
		try{
		WebElement menuItm = driver.findElement(By.xpath("(//div[@title='Menu'])[1]"));
		Actions action = new Actions(driver);
		action.moveToElement(menuItm).click().build().perform();
		
		WebElement logoutBtn = driver.findElement(By.xpath("//div[@title='Log out']"));
		action.moveToElement(logoutBtn).click().build().perform();
		}catch (Exception e) {
			throw new Exception("--- Issue in Logout----- Stack Trace: "+ e.getMessage());
		}
	}

}
