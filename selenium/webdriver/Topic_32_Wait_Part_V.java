package webdriver;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_32_Wait_Part_V {
	WebDriver driver;
	Random rand = new Random();
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor js = (JavascriptExecutor) driver;
	String emailAddress = "testdemo" + getRandomnumber() + "@gmail.com";
	WebDriverWait explicitWait;
	Actions actions;

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
		explicitWait = new WebDriverWait(driver, 30);
		actions = new Actions(driver);
	}

	// @Test
	public void TC_01_Orange_HRM_API() {
		driver.get("https://api.orangehrm.com/");
		// Wait cho icon loading biến mất
		// Vì khi nó biến mât thì cái trang nó load hết dữ liệu về thành công
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loader>div.spinner")));

		Assert.assertEquals(driver.findElement(By.cssSelector("div#project h1")).getText(),
				"OrangeHRM REST API Documentation");

	}

	// @Test
	public void TC_02_Admin_NopCommerce() {
		driver.get("https://admin-demo.nopcommerce.com");
		driver.findElement(By.cssSelector("input#Email")).clear();
		driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");
		driver.findElement(By.cssSelector("input#Password")).clear();
		driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");

		// Click chuyển trang từ login vào dashboard
		driver.findElement(By.cssSelector("button.login-button")).click();
		Assert.assertTrue(waitForAjaxBusyInvisible());

		// Chuyển từ dashboard về login
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		Assert.assertTrue(waitForAjaxBusyInvisible());
		Assert.assertEquals(driver.getTitle(), "Your store. Login");

	}

	// @Test
	public void TC_03_Admin_NopCommerce() {
		driver.get("https://admin-demo.nopcommerce.com");
		driver.findElement(By.cssSelector("input#Email")).clear();
		driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");
		driver.findElement(By.cssSelector("input#Password")).clear();
		driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");

		// Click chuyển trang từ login vào dashboard
		driver.findElement(By.cssSelector("button.login-button")).click();
		Assert.assertTrue(isPageLoadedSuccess());

		// Chuyển từ dashboard về login
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		Assert.assertTrue(isPageLoadedSuccess());
		Assert.assertEquals(driver.getTitle(), "Your store. Login");

	}

	 @Test
	public void TC_04_Blog_Test_Project() {
		driver.get("https://blog.testproject.io/");

		// Hover vào chuột vào 1 element bất kì tại page này đẻ page ready
		actions.moveToElement(driver.findElement(By.cssSelector("section#search-2 input.search-field"))).perform();

		Assert.assertTrue(isPageLoadedSuccess());

		driver.findElement(By.cssSelector("section#search-2 input.search-field")).sendKeys("Selenium");
		driver.findElement(By.cssSelector("section#search-2 span.glass")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.main-heading")));
		Assert.assertTrue(isPageLoadedSuccess());
		
		List<WebElement> listArticle=driver.findElements(By.cssSelector("h3.post-title"));
		
		for (WebElement webElement : listArticle) {
			Assert.assertTrue(webElement.getText().contains("Selenium"));
		}

	}

	public boolean isPageLoadedSuccess() {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public boolean waitForAjaxBusyInvisible() {
		return explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#ajaxBusy")));
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
