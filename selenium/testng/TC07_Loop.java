package testng;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC07_Loop {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test(invocationCount = 5)
	public void Register() {
		driver.get("http://live.techpanda.org/index.php/customer/account/create/");

		driver.findElement(By.cssSelector("input#firstname")).sendKeys("Automation");
		driver.findElement(By.cssSelector("input#lastname")).sendKeys("fc");
		String emailAddress = "afc" + genarateNumber() + "@gmail.com";
		driver.findElement(By.cssSelector("input#email_address")).sendKeys(emailAddress);
		System.out.println("Email Address: " + emailAddress);
		driver.findElement(By.cssSelector("input#password")).sendKeys("123456");
		driver.findElement(By.cssSelector("input#confirmation")).sendKeys("123456");

		driver.findElement(By.xpath("//button[@title='Register']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='success-msg']//span[text()='Thank you for registering with Main Website Store.']")).isDisplayed());

		// Post-Condition: Logout ra để User tiếp theo nó chạy được
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
	}

	public int genarateNumber() {
		Random rand = new Random();
		return rand.nextInt(10000);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
