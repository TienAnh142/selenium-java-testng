package webdriver;

import java.time.Duration;
import java.util.Random;
import java.util.function.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_31_Wait_Part_IV {
	WebDriver driver;
	Random rand = new Random();
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor js = (JavascriptExecutor) driver;
	String emailAddress = "testdemo" + getRandomnumber() + "@gmail.com";
	FluentWait<WebDriver> fluentDriver;
	FluentWait<WebElement> fluentElement;
	long allTime=15;
	long pollingTime=133;

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

	}

	//@Test
	public void TC_01_Fluent() {

		driver.get("https://automationfc.github.io/dynamic-loading/");

		findElement("//button[text()='Start']").click();
//		Assert.assertTrue(findElement("//h4[text()='Hello World!']").isDisplayed());
//		fluentDriver=new FluentWait<WebDriver>(driver);
//		//Set thời gian
//		fluentDriver.withTimeout(Duration.ofSeconds(15))
//		//133s check 1 lần
//		.pollingEvery(Duration.ofSeconds(133))
//		.ignoring(NoSuchElementException.class);
//		
//		//Apply điều kiện
//		WebElement startButton= fluentDriver.until(new Function<WebDriver, WebElement>() {
//
//			@Override
//			public WebElement apply(WebDriver t) {
//				return driver.findElement(By.cssSelector("div#start>button"));
//			}
//		});

	}
	@Test
	public void TC_02_Fluent() {

		driver.get("https://automationfc.github.io/fluent-wait/");

		WebElement countdownTime=findElement("//div[@id='javascript_countdown_time']");
		
		fluentElement=new FluentWait<WebElement>(countdownTime);
		
		fluentElement.withTimeout(Duration.ofSeconds(allTime))
		.pollingEvery(Duration.ofMillis(pollingTime))
		.ignoring(NoSuchElementException.class);
		
		fluentElement.until(new Function<WebElement, Boolean>() {

			@Override
			public Boolean apply(WebElement element) {
				String text=element.getText();
				System.out.println(text);
				return text.endsWith("00");
			}
		});
	}

	public WebElement findElement(String xpathLocator) {
		fluentDriver = new FluentWait<WebDriver>(driver);
		// Set thời gian
		fluentDriver.withTimeout(Duration.ofSeconds(allTime))
				// 133s check 1 lần
				.pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class);

		// Apply điều kiện
		return fluentDriver.until(new Function<WebDriver, WebElement>() {

			@Override
			public WebElement apply(WebDriver t) {
				return driver.findElement(By.xpath(xpathLocator));
			}
		});
	}

	//@Test
	public void TC_02_Enough() {

	}

	//@Test
	public void TC_03_more_Time() {

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
