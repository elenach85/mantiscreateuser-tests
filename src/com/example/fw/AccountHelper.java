package com.example.fw;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import com.example.utils.SortedListOf;

public class AccountHelper extends WebDriverHelperBase{

	
	public AccountHelper(ApplicationManager manager) {
		super(manager);
	}
	public void signup(User user) throws Exception {
	   click(By.xpath("//span[@class='bracket-link']/a[@href='signup_page.php']"));
		type(By.name("username"),user.login);
		type(By.name("email"),user.email);
		click(By.cssSelector("input.button"));
		WebElement errorMessage =findElement(By.xpath("//table[@class='width50']//tr/td/p[@style='color:red']"));
		if (errorMessage !=null) {
		throw new RuntimeException(errorMessage.getText());
		}
		pause(50000);	
		manager.getMailHelperSLL().connect();
		String msg = manager.getMailHelperSLL().getEmailMessage();
		String confirmationLink=getConfirmationLink(msg);
		openAbsoluteUrl(confirmationLink);
		type(By.name("password" ),user.password);
		type(By.name("password_confirm"),user.password);
		click(By.cssSelector("input.button"));
	
		}
	public SortedListOf<User> getUsersList() {
		SortedListOf<User>users=new SortedListOf<User>();
	manager.getNavigationHelper().manageUsersPage();
	List<WebElement> rows=driver.findElements(By.xpath("//table[@cellspacing='1']//tr[@class='row-category']/following-sibling::*[contains(@class,'row')]"));
	for (WebElement row : rows) {
	List<WebElement> columns = row.findElements(By.tagName("td"));
	String login=columns.get(0).getText();
	String email=columns.get(2).getText();
	users.add(new User().setLogin(login).setEmail(email));
		}
	return users;
	}

	  public void deleteUser(String login){
		selectUser(login);	 
		initUserDeletion();	 
		confirmUserDeletion();	 
		 }
	  
		
		public void login(User user) {
		manager.getNavigationHelper().loginPage();
		type(By.name("username"),user.login);
		type(By.name("password"),user.password);
		click(By.xpath("//input[@class='button']"));
	}
		
		public String loggedUser() {
			String loginFromUI=manager.getDriver().findElement(By.xpath("(//td[@class='login-info-left']/span)[1]")).getText();
			return loginFromUI;
			}
		
		public String getConfirmationLink(String text) {
			Pattern regex = Pattern.compile("http\\S*");
			Matcher matcher = regex.matcher(text);
			if (matcher.find()) {
				return matcher.group();
			} else {
				return "";
			
			}
	
	}
		

public void confirmUserDeletion() {
	click(By.xpath("//input[@value='Delete Account']"));
}
public void initUserDeletion() {
	click(By.xpath("//input[@value='Delete User']"));
}
 public void selectUser(String login){
click(By.xpath("//table[@cellspacing='1']//tr[@class='row-category']/following-sibling::*[contains(@class,'row')]/td/a[contains(text(),'" + login + "')]"));	 
 }
public void logout() {
click(By.xpath("//td[@class='menu']/a[contains(@href,'logout')]"));
	
}
}