package com.example.fw;


import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.Properties;

public class ApplicationManager {
	private static WebDriver driver;
	public  String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	private Properties properties;
	private AccountHelper accountHelper;
	private MailHelperSLL mailHelperSLL;
	private NavigationHelper navigationHelper;
	public  ApplicationManager(Properties properties){
		this.properties = properties;
	}
	
		public void stop() {
		driver.quit();
	   		
	}

public NavigationHelper getNavigationHelper() {
	if (navigationHelper==null) {
	navigationHelper=new NavigationHelper(this);	
	}
	return navigationHelper;	
}
public AccountHelper getAccountHelper() {
	if (accountHelper==null) {
	accountHelper=new AccountHelper(this);	
	}
	return accountHelper;
}

public MailHelperSLL getMailHelperSLL() {
	if (mailHelperSLL==null) {
		mailHelperSLL=new MailHelperSLL(this);	
		}
		return mailHelperSLL;
}
public WebDriver getDriver() {
	String browser=properties.getProperty("browser");
	if (driver==null) {
		if ("firefox".equals(browser)) {
			driver = new FirefoxDriver();	
		} else 
		if ("ie".equals(browser)) {
			driver = new InternetExplorerDriver();	
		}
		else {
			throw new Error("Unsupported browser:"+ browser);
		}
		baseUrl=properties.getProperty("baseUrl");
	  //  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.get(baseUrl);
		}
	return driver;
}





}

