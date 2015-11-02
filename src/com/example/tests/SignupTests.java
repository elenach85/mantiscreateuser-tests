package com.example.tests;

import org.testng.annotations.BeforeClass;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import java.util.List;
import com.example.fw.AccountHelper;
import com.example.fw.User;
import com.example.utils.SortedListOf;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
public class SignupTests extends TestBase  {
	private AccountHelper accHelper;
	
	@BeforeClass
	public void InitShortCuts(){
	accHelper=app.getAccountHelper();
	}
	@Test(priority=1)
	public void newUserShouldSignup() throws Exception{
		User admin=new User().setLogin("administrator").setPassword("root");
		User user=new User().setLogin("elena_ch").setEmail("elena_ch@outlook.com").setPassword("123456");	
	accHelper.login(admin);
	//check that this user doesn't exist.If  the user exists, delete it
	SortedListOf<User>oldUserList=accHelper.getUsersList();
	if (oldUserList.contains(user)==true) {
		accHelper.deleteUser(user.login);
		SortedListOf<User>userListAfterDeletion=accHelper.getUsersList();
		assertThat(userListAfterDeletion,equalTo(oldUserList.without(user)));	
	}
SortedListOf<User>userListBeforeSignUp=accHelper.getUsersList();
accHelper.logout();
accHelper.signup(user);
Thread.sleep(3000);
accHelper.login(user);
Thread.sleep(3000);
assertThat(accHelper.loggedUser(),equalTo(user.login));
accHelper.logout();
//get the UserLists by administrator
accHelper.login(admin);
SortedListOf<User>userListAfterSignUp=accHelper.getUsersList();
assertThat(userListAfterSignUp,equalTo(userListBeforeSignUp.withAdded(user)));
accHelper.logout();
	}

 @Test(priority=2)
 public void exsistingUserShouldNotSignup() throws Exception{
User user=new User().setLogin("elena_ch").setEmail("elena_ch@outlook.com").setPassword("123456");
	 try {
		 accHelper.signup(user);
	} catch (Exception e) {
	assertThat(e.getMessage(),containsString("That username is already being used"));
	return;
	}
	fail("Exception was expected"); 
 }

@Test(priority=3)
public void UserWithInvalidEmailShouldNotSignup() throws Exception{
	User user2=new User().setLogin("elena").setEmail("elenaoutlook");
	 try {
		 app.getNavigationHelper().loginPage();
		 accHelper.signup(user2);
	} catch (Exception e) {
	assertThat(e.getMessage(),containsString("Invalid e-mail address"));
	return;
	}
	fail("Exception was expected"); 
}

 }
