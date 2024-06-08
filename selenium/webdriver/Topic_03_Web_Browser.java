package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Web_Browser {
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
	public void TC_01_Verify_URL() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		String currentUrl = driver.getCurrentUrl();
		
		Assert.assertEquals(currentUrl, "http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		currentUrl = driver.getCurrentUrl();
		
		Assert.assertEquals(currentUrl, "http://live.techpanda.org/index.php/customer/account/create/");
	}
	
	@Test
	public void TC_02_Verify_Title() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		String currentTitle = driver.getTitle();
		Assert.assertEquals(currentTitle, "Customer Login");
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		currentTitle = driver.getTitle();
		
		Assert.assertEquals(currentTitle, "Create New Customer Account");
	}

	@Test
	public void TC_03_Navigate() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		String currentUrl = driver.getCurrentUrl();
		Assert.assertEquals(currentUrl, "http://live.techpanda.org/index.php/customer/account/create/");
		
		driver.navigate().back();
		
		currentUrl = driver.getCurrentUrl();
		Assert.assertEquals(currentUrl, "http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.navigate().forward();
		
		String currentTitle = driver.getTitle();
		Assert.assertEquals(currentTitle, "Create New Customer Account");
	}

	@Test
	public void TC_04_Get_Page_Source() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		String currentUrl = driver.getPageSource();
		Assert.assertTrue(currentUrl.contains("Login or Create an Account"));
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		currentUrl = driver.getPageSource();
		Assert.assertTrue(currentUrl.contains("Create an Account"));
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