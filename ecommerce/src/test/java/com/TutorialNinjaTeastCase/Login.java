package com.TutorialNinjaTeastCase;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.TutorialNinja.qa.Base.Base;
import com.TutorialNinja.qa.Util.Utilities;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;

public class Login extends Base {
	public Login() {
		super();
	}
	WebDriver driver;
	@BeforeMethod
	public void setUp() {
		driver=initializBrowseAndOpenApllicationURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		homePage.selectLoginOption();
		
		
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority=1,dataProvider="validCredentialsSupplier")
	public void verifyLoginWithValidcredentials(String Email,String password) {
		LoginPage loginPage=new LoginPage(driver);
		loginPage.enterEmailAddress(Email);
		loginPage.enterPassword(password);
		loginPage.clickOnLoginButton();
		AccountPage accountPage=new AccountPage(driver);
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption(),"Edit Your Account Information option is not displayed");
		

	}

	@DataProvider(name="validCredentialsSupplier")
	public Object[][] supplyTestData() {
		
		Object[][] data = Utilities.getTestDataFromExcel("Sheet1");
		return data;
	}
	@Test(priority=2)
	public void verifyLoginWithInValidcredentials() {
		LoginPage loginPage=new LoginPage(driver);
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.enterEmailAddress( Utilities.genrateEmailTimeStamp());
		loginPage.clickOnLoginButton();
		//driver.findElement(By.id("input-email")).sendKeys( Utilities.genrateEmailTimeStamp());
		//driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty("invalidPassword"));
		//driver.findElement(By.xpath("//input[@value='Login']")).click();
		String ActualWarningMessege = loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
		String expectedWarningMessege=dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(ActualWarningMessege.contains(expectedWarningMessege), "expected Warning Messege");
		
		
	}
	
	@Test(priority=3)
	public void verifyLoginWithInValidEmailandValidPasswordCredentials() {
		LoginPage loginPage=new LoginPage(driver);
		loginPage.enterEmailAddress(Utilities.genrateEmailTimeStamp());
		loginPage.enterPassword(prop.getProperty("validPassword"));
		//driver.findElement(By.id("input-email")).sendKeys(Utilities.genrateEmailTimeStamp());
		//driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		loginPage.clickOnLoginButton();
		String ActualWarningMessege = loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
		String expectedWarningMessege=dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(ActualWarningMessege.contains(expectedWarningMessege), "expected Warning Messege");
		//String ActualWarningMessege = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		//String expectedWarningMessege=dataProp.getProperty("emailPasswordNoMatchWarning");
		//Assert.assertTrue(ActualWarningMessege.contains(expectedWarningMessege), "expected Warning Messege");
		
		
	}
	@Test(priority=4)
	public void verifyLoginWithValidEmailandInavlidPasswordCredentials() {
		LoginPage loginPage=new LoginPage(driver);
		loginPage.enterEmailAddress( prop.getProperty("validEmail"));
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();
		//driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		//driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty("invalidPassword"));
		//driver.findElement(By.xpath("//input[@value='Login']")).click();
		String ActualWarningMessege = loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
		String expectedWarningMessege=dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(ActualWarningMessege.contains(expectedWarningMessege), "expected Warning Messege");
		//String ActualWarningMessege = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		//String expectedWarningMessege=dataProp.getProperty("emailPasswordNoMatchWarning");
		//Assert.assertTrue(ActualWarningMessege.contains(expectedWarningMessege), "expected Warning Messege");
	
		
	}
	@Test(priority=5)
	public void verifyLoginWithoutEmailAndPasswordCredentials() {
		LoginPage loginPage=new LoginPage(driver);
		//driver.findElement(By.id("input-email")).sendKeys("");
		//driver.findElement(By.id("input-password")).sendKeys("");
		loginPage.clickOnLoginButton();
		String ActualWarningMessege = loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
		String expectedWarningMessege=dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(ActualWarningMessege.contains(expectedWarningMessege), "expected Warning Messege");
		//driver.findElement(By.xpath("//input[@value='Login']")).click();
		//String ActualWarningMessege = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		//String expectedWarningMessege=dataProp.getProperty("emailPasswordNoMatchWarning");
		//Assert.assertTrue(ActualWarningMessege.contains(expectedWarningMessege), "expected Warning Messege");
		
		
	}
	
	
	
	
	
	


}
