package com.example.fw;

import org.openqa.selenium.By;

public class NavigationHelper extends WebDriverHelperBase {

	public NavigationHelper(ApplicationManager manager) {
		super(manager);
	
	}

	public void loginPage() {
		if (!onLoginPage()) {
		driver.get(manager.baseUrl);
	 
	}
	}


	public boolean onLoginPage() {
		if (driver.findElements(By.xpath("//input[@class='button']")).size()>0) {
			return true;	
		} else {
			return false;
		}
	
	}
	public void manageUsersPage() {
		if (!onManagePage()) {
		openUrl("manage_user_page.php");
	}
	
	}

	public boolean onManagePage() {
		if (driver.getCurrentUrl().contains("/manage_user_page.php")) {	
		
		return true;
		}
		else{
			return false;
		}
		}
}


	

	/**
	 * 
	 */
		


