package com.TutorialNinja.qa.Base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.TutorialNinja.qa.Util.Utilities;

public class Base {
	
	WebDriver driver;
	public Properties prop;
	public Properties dataProp;
	public  Base() {
		 prop=new Properties();
		 File propFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\TutorialNinja\\qa\\config\\config.properties");//user.dir  will get path of project
		 dataProp = new Properties();
			File dataPropFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\TutorialNinja\\qa\\teastdata\\testdata.properties");
			
			try {
				FileInputStream dataFis = new FileInputStream(dataPropFile);
				dataProp.load(dataFis);
			}catch(Throwable e) {
				e.printStackTrace();
			}
		 try {
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
	}

public WebDriver initializBrowseAndOpenApllicationURL(String browserName) {

	if(browserName.equalsIgnoreCase("chrome")) {
		
		driver = new ChromeDriver();
	}else if(browserName.equalsIgnoreCase("edge")) {
		
		driver = new EdgeDriver();
	}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		driver.get(prop.getProperty("url"));
		return driver;
	
}
}
	
