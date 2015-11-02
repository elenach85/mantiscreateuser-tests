package com.example.tests;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.example.fw.ApplicationManager;


public class TestBase {
	protected static ApplicationManager app;
	

		@BeforeTest
	public void setUp() throws Exception {
			String configFile = System.getProperty("configFile","application.properties");
			Properties properties = new Properties();
			properties.load(new FileReader(new File(configFile)));
			app=new ApplicationManager(properties);
			
	   
	  }

	@AfterTest
	public void tearDown() throws Exception {
		app.stop();
	    
	  }
	

}
	
