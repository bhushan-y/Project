package com.TutorialNinjaTeastCase;



import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.TutorialNinja.qa.Base.Base;
import com.TutorialNinja.qa.Util.Utilities;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;

public class Register extends Base {
	public Register() {
		super();
	}
	WebDriver driver;

	@BeforeMethod
	public void openBrowser() {
	
		driver=initializBrowseAndOpenApllicationURL(prop.getProperty("browserName"));
		HomePage homePage=new HomePage(driver);
		homePage.clickOnMyAccount();
		homePage.selectRegisterOption();
		//driver.findElement(By.xpath("//span[text()='My Account']")).click();
		//driver.findElement(By.linkText("Register")).click();

	}

	@AfterMethod
	public void closeWindowSetUp() {

		driver.quit();
	}

	@Test(priority = 1)
	public void verifyRegisterPageWithMandatoryField() {
		RegisterPage registerPage=new RegisterPage(driver);
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		//driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		//driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(Utilities.genrateEmailTimeStamp());
		//driver.findElement(By.id("input-email")).sendKeys(Utilities.genrateEmailTimeStamp());
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		//driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		//driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		//driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		registerPage.selectPrivacyPolicy();
		//driver.findElement(By.name("agree")).click();
		registerPage.clickOnContinueButton();
		//driver.findElement(By.xpath("//input[@value='Continue']")).click();
		AccountSuccessPage accountSuccessPage=new AccountSuccessPage(driver);
		String actualHeading = accountSuccessPage.retrieveAccountSuccessPageHeading();
		//String actualHeading = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertEquals(actualHeading, dataProp.getProperty("accountSuccessfullyCreatedHeading", actualHeading), "Account is not created");
	

	}

	@Test(priority = 2)
	public void verifyRegisterPageWithMandatoryAllField() {
		RegisterPage registerPage=new RegisterPage(driver);
	
		//driver.findElement(By.xpath("//span[text()='My Account']")).click();
		//driver.findElement(By.linkText("Register")).click();
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		//driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		//driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(Utilities.genrateEmailTimeStamp());
		//driver.findElement(By.id("input-email")).sendKeys(Utilities.genrateEmailTimeStamp());
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		//driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		//driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		//driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		registerPage.selectYesNewsletterOption();
		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();
		//driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		//driver.findElement(By.name("agree")).click();
		//driver.findElement(By.xpath("//input[@value='Continue']")).click();
		AccountSuccessPage accountSuccessPage=new AccountSuccessPage(driver);
		String actualHeading = accountSuccessPage.retrieveAccountSuccessPageHeading();
		//String actualHeading = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertEquals(actualHeading, dataProp.getProperty("accountSuccessfullyCreatedHeading", actualHeading), "Account is not created");
		//Assert.assertEquals(actualHeading, dataProp.getProperty("accountSuccessfullyCreatedHeading"), "Account is not created");
		

	}

	@Test(priority = 3)
	public void verifyRegisterPageWitExistingEmailAddressWithMandatoryAllField() {
		RegisterPage registerPage=new RegisterPage(driver);
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		//driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		//driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(prop.getProperty("validEmail"));
		//findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		//driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		//driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		//driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		registerPage.selectYesNewsletterOption();
		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();
		//driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		//driver.findElement(By.name("agree")).click();
		//driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String actualWarning = registerPage.retrieveDuplicateEmailAddressWarning();
		//String actualWarning = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		Assert.assertEquals(actualWarning, dataProp.getProperty("duplicateEmailWarning"),"Account has been already created");
	

	}

}
