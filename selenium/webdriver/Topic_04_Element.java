package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Element {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_01_Displayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='mail']"));

		if (emailTextbox.isDisplayed()) {
			System.out.println("Email textbox is displayed");
			emailTextbox.sendKeys("Automation Testing");
		} else {
			System.out.println("Email textbox is not displayed");
		}

		WebElement ageUnder18Radio = driver.findElement(By.id("under_18"));
		if (ageUnder18Radio.isDisplayed()) {
			System.out.println("Age under 18 radio button is displayed");
			ageUnder18Radio.click();
		} else {
			System.out.println("Age under 18 radio button is not displayed");
		}

		WebElement educationTextArea = driver.findElement(By.name("user_edu"));
		if (educationTextArea.isDisplayed()) {
			System.out.println("Education textarea is displayed");
			educationTextArea.sendKeys("Automation Testing");
		} else {
			System.out.println("Education textarea is not displayed");
		}

		WebElement nameH5 = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
		if (nameH5.isDisplayed()) {
			System.out.println("Name User 5 is displayed");
		} else {
			System.out.println("Name User 5 is not displayed");
		}
	}

	@Test
	public void TC_02_Enabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		WebElement passwordTextbox = driver.findElement(By.id("password"));
		if (passwordTextbox.isEnabled()) {
			System.out.println("Password textbox is enabled");
		} else {
			System.out.println("Password textbox is disabled");
		}

		WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='mail']"));
		if (emailTextbox.isEnabled()) {
			System.out.println("Email textbox is enabled");
		} else {
			System.out.println("Email textbox is disabled");
		}
	}
	
	@Test
	public void TC_03_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		WebElement ageUnder18Radio = driver.findElement(By.id("under_18"));
		ageUnder18Radio.click();

		if (ageUnder18Radio.isSelected()) {
			System.out.println("Age under 18 radio button is selected");
		} else {
			System.out.println("Age under 18 radio button is de-selected");
		}

		WebElement javaCheckbox = driver.findElement(By.id("java"));
		javaCheckbox.click();

		if (javaCheckbox.isSelected()) {
			System.out.println("Java checkbox is selected");
		} else {
			System.out.println("Java checkbox is de-selected");
		}

		javaCheckbox.click();
		if (javaCheckbox.isSelected()) {
			System.out.println("Java checkbox is selected");
		} else {
			System.out.println("Java checkbox is de-selected");
		}
	}

	@Test
	public void TC_04_MailChimp() {
		driver.get("https://login.mailchimp.com/signup/");

		driver.findElement(By.id("email")).sendKeys("thanhle@gmail.com");
		driver.findElement(By.xpath("//input[@id='new_username']")).sendKeys("");

		WebElement passwordTextbox = driver.findElement(By.id("new_password"));
		WebElement signUpButton = driver.findElement(By.id("create-account-enabled"));

		// 1 - Lowercase
		passwordTextbox.sendKeys("auto");
		sleepInSecond(3);
		
		// Expected displayed (assertTrue)
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).getCssValue("color")).asHex().toUpperCase(), "#008547");
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check completed']")).isDisplayed());
		Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//li[@class='username-check completed']")).getCssValue("color")).asHex().toUpperCase(), "#008547");
		
		// 2 - Uppercase
		passwordTextbox.clear();
		passwordTextbox.sendKeys("AUTO");
		sleepInSecond(3);

		// Expected displayed (assertTrue)
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).getCssValue("color")).asHex().toUpperCase(), "#008547");
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check completed']")).isDisplayed());
		Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//li[@class='username-check completed']")).getCssValue("color")).asHex().toUpperCase(), "#008547");

		// 3 - Number
		passwordTextbox.clear();
		passwordTextbox.sendKeys("1245");
		sleepInSecond(3);

		// Expected displayed (assertTrue)
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//li[@class='number-char completed']")).getCssValue("color")).asHex().toUpperCase(), "#008547");
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check completed']")).isDisplayed());
		Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//li[@class='username-check completed']")).getCssValue("color")).asHex().toUpperCase(), "#008547");

		// 4 - Special
		passwordTextbox.clear();
		passwordTextbox.sendKeys("!@#$");
		sleepInSecond(3);

		// Expected displayed (assertTrue)
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//li[@class='special-char completed']")).getCssValue("color")).asHex().toUpperCase(), "#008547");
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check completed']")).isDisplayed());
		Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//li[@class='username-check completed']")).getCssValue("color")).asHex().toUpperCase(), "#008547");


		// 5 - Eight chars
		passwordTextbox.clear();
		passwordTextbox.sendKeys("12345678");
		sleepInSecond(3);

		// Expected displayed (assertTrue)
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
		Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//li[@class='8-char completed']")).getCssValue("color")).asHex().toUpperCase(), "#008547");
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check completed']")).isDisplayed());
		Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//li[@class='username-check completed']")).getCssValue("color")).asHex().toUpperCase(), "#008547");


		// 6 - Combine
		passwordTextbox.clear();
		passwordTextbox.sendKeys("Auto123@@@");
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//input[@class='rounded-corners-4  av-password success-check']")).isDisplayed());

		System.out.println(driver.getCurrentUrl());
	}

	@AfterClass
	public void afterClass() {
//		driver.quit();
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);

	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}