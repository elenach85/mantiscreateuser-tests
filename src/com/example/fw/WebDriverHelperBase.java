package com.example.fw;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public abstract class WebDriverHelperBase extends HelperBase {


protected WebDriver driver;
public boolean acceptNextAlert = true;
public WebDriverHelperBase(ApplicationManager manager){
	super(manager);
	this.driver=manager.getDriver();
	
}
public boolean isElementPresent(By by) {
    try {
     driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

public boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

public String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
public WebElement findElement(By locator) {
	try {
	return driver.findElement(locator);
	} catch (Exception e) {
	return null;
	}
	
}
public void type(By locator, String text) {
	if (text != null) {
		driver.findElement(locator).clear();
	    driver.findElement(locator).sendKeys(text);	
	}
	
	}
public void click(By locator) {
	driver.findElement(locator).click();
}
public void selectByText(By birthday, String text) {
	if (text != null) {
		new Select(driver.findElement(birthday)).selectByVisibleText(text);	
	}
	
}
protected void openUrl(String string) {
driver.get(manager.baseUrl + string);

}
	

protected void openAbsoluteUrl(String string) {
driver.get(string);
	
}
 
}
