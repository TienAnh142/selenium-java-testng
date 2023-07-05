package webdriver;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Handle_Alert {
	WebDriver driver;
	Random rand = new Random();
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor js = (JavascriptExecutor) driver;
	String emailAddress = "testdemo" + getRandomnumber() + "@gmail.com";
	WebDriverWait explicitWait;
	Alert alert;
	String authenFirefox = projectPath + "\\autoIT\\authen_firefox.exe";
	String authenChrome = projectPath + "\\autoIT\\authen_chrome.exe";
	String username = "admin";
	String password = "admin";

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		js = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 10);
	}

	// @Test
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		sleepInSecond(3);

		// Wait va switch qua luon
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());

		// Verify alert title
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		sleepInSecond(3);

		alert.accept();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),
				"You clicked an alert successfully");

	}

	// @Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		sleepInSecond(3);

		// Wait va switch qua luon
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());

		// Verify alert title
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		sleepInSecond(3);

		alert.dismiss();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");
	}

	// @Test
	public void TC_03_Prompt_Alert() {
		String input = "automationfc";
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		sleepInSecond(3);

		// Wait va switch qua luon
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());

		// Verify alert title
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		sleepInSecond(3);

		alert.sendKeys(input);
		alert.accept();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: " + input);
	}

	// @Test
	public void TC_04_Authentication_Alert_I() {
		String userNameAndPassword = "admin";
		String url = "http://" + userNameAndPassword + ":" + userNameAndPassword
				+ "@the-internet.herokuapp.com/basic_auth";
		driver.get(url);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#content p")).getText(),
				"Congratulations! You must have the proper credentials.");
	}

	// @Test
	public void TC_05_Authentication_Alert_II() {
		String url = "http://the-internet.herokuapp.com/basic_auth";
		String username = "admin";
		String password = "admin";
		driver.get(passUserAndPassToURL(url, username, password));
		Assert.assertEquals(driver.findElement(By.cssSelector("div#content p")).getText(),
				"Congratulations! You must have the proper credentials.");
	}

	@Test
	public void TC_06_Authentication_Alert_III_AutoIT() throws IOException {
		if (driver.toString().contains("firefox")) {
			Runtime.getRuntime().exec(new String[] { authenFirefox, username, password });
		} else if (driver.toString().contains("chrome")) {
			Runtime.getRuntime().exec(new String[] { authenChrome, username, password });
		}
		driver.get("http://the-internet.herokuapp.com/basic_auth");
		sleepInSecond(5);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#content p")).getText(),
				"Congratulations! You must have the proper credentials.");
	}

	public String passUserAndPassToURL(String url, String username, String password) {
		String[] arrayUrl = url.split("//");
		return arrayUrl[0] + "//" + username + ":" + password + "@" + arrayUrl[1];
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	public int getRandomnumber() {
		return rand.nextInt(100);
	}
}
