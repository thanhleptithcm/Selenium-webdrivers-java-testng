package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.text.Document;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.scanner.Constant;

public class Topic_05_Textbox_TextArea {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	
	String firstName, lastName, fullName, emailAddress, password, employeeId;
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		jsExecutor = (JavascriptExecutor) driver;
		
		firstName = "Le";
		lastName = "Thanh";
		fullName = firstName + " " + lastName;
		emailAddress = "thanhle" + getRandomNumber() + "@gmail.com";
		password = "123456";
	}

	@Test
	public void TC_01_Create_An_Account_Techpanda() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);
		
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "Thank you for registering with Main Website Store.");
	
		String result = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		
		Assert.assertTrue(result.contains(fullName));
		Assert.assertTrue(result.contains(emailAddress));
		
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		
		driver.findElement(By.xpath("//a[@title='Samsung Galaxy']")).click();
		
		driver.findElement(By.xpath("//a[text()='Add Your Review']")).click();
		
		driver.findElement(By.id("Quality 1_5")).click();
		driver.findElement(By.xpath("//textarea[@id='review_field']")).sendKeys("Good Application");

		driver.findElement(By.xpath("//input[@id='summary_field']")).sendKeys("Best Phone");

		driver.findElement(By.xpath("//input[@id='nickname_field']")).sendKeys("Matas");
		
		driver.findElement(By.xpath("//button[@title='Submit Review']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "Your review has been accepted for moderation.");
		
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		
		sleepInsecond(6);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/");
	}
	
	
	@Test
	public void TC_02_Create_An_Account_OrangeHRM() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
		
		driver.findElement(By.xpath("//div[@class= \"oxd-form-actions orangehrm-login-action\"]")).click();
		
		sleepInsecond(2);
		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		
		sleepInsecond(2);
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
		
		
		driver.findElement(By.name("firstName")).sendKeys(firstName);
		driver.findElement(By.name("lastName")).sendKeys(lastName);
		sleepInsecond(2);
		WebElement employeeIdElement = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"));
		employeeId = employeeIdElement.getAttribute("_value");
		
		sleepInsecond(2);
		WebElement createLoginDetailElement =  driver.findElement(By.xpath("//p[text()='Create Login Details']/following-sibling::div//span"));
		jsExecutor.executeScript("arguments[0].click();", createLoginDetailElement);
		
		sleepInsecond(2);
		
		driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys(firstName + lastName);
		driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys(password);
		driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys(password);
		
		driver.findElement(By.xpath("//button[text() = ' Save ']")).click();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

	public void sleepInsecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}