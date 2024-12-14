package com.TutorialNinjaTeastCase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.TutorialNinja.qa.Base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class Search extends Base {
	public Search() {
		super();
	}
	
	WebDriver driver;
	@BeforeMethod
	public void setUp() {
		driver=initializBrowseAndOpenApllicationURL(prop.getProperty("browserName"));		
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifySerchWithValidProduct() {
		HomePage homePage=new HomePage(driver);
		homePage.enterProductIntoSearchBoxField(dataProp.getProperty("validProduct"));
		//driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("validProduct"));
		homePage.clickOnSearchButton();
		//driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		SearchPage searchPage=new SearchPage(driver);
		Assert.assertTrue(searchPage.displayStatusOfHPValidProduct(),"Product is not available");
		
	}
	@Test(priority=2)
	public void verifySerchWithInValidProduct() {
		HomePage homePage=new HomePage(driver);
		homePage.enterProductIntoSearchBoxField(dataProp.getProperty("invalidProduct"));
		//driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("invalidProduct"));
		homePage.clickOnSearchButton();
		//driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		SearchPage searchPage=new SearchPage(driver);
		String actualSerachMessege = searchPage.retrieveNoProductMessageText();
		//String actualSerachMessege = driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
		Assert.assertEquals(actualSerachMessege,dataProp.getProperty("NoProductTextInSearchResults"),"Product is not available");
		
	}
	@Test(priority=3)
	public void verifySerchWithoutAnyProduct() {
		HomePage homePage=new HomePage(driver);
		homePage.clickOnSearchButton();
		SearchPage searchPage=new SearchPage(driver);
		String actualSerachMessege = searchPage.retrieveNoProductMessageText();
		Assert.assertEquals(actualSerachMessege,dataProp.getProperty("NoProductTextInSearchResults"),"Product is not available");
		//driver.findElement(By.name("search")).sendKeys("");
		//driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		//String actualSerachMessege = driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
		//Assert.assertEquals(actualSerachMessege,dataProp.getProperty("NoProductTextInSearchResults"),"Product is not available");
		
	}

}
