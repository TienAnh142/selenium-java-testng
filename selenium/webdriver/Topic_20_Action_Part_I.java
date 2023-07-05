package webdriver;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Action_Part_I {
	WebDriver driver;
	Random rand = new Random();
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor js = (JavascriptExecutor) driver;
	String emailAddress = "testdemo" + getRandomnumber() + "@gmail.com";
	WebDriverWait explicitWait;
	Actions action;
	Alert alert;

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
		action = new Actions(driver);
	}

	// @Test
	public void TC_01_Hover_To_Element_Tooltip() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		action.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
		Assert.assertTrue(driver.findElement(By.cssSelector("div.ui-tooltip-content")).isDisplayed());
	}

	// @Test
	public void TC_02_Hover_To_Element() {
		driver.get("http://www.myntra.com/");
		action.moveToElement(driver.findElement(By.xpath("//a[contains(@class,'desktop-main') and text()='Kids']")))
				.perform();
		sleepInSecond(2);
		driver.findElement(By.xpath("//a[contains(@class,'desktop-categoryName') and text()='Home & Bath']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("h1.title-title")).getText(), "Kids Home Bath");

	}

	// @Test
	public void TC_04_Click_And_Hold_Block_Element() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> listElement = driver.findElements(By.cssSelector("li.ui-state-default"));
		// Click va giu chuot
		action.clickAndHold(listElement.get(0))
				// Keo toi target
				.moveToElement(listElement.get(3))
				// Nha chuot ra
				.release().perform();
		sleepInSecond(2);

		// Verify
		List<WebElement> listSelectedNumbers = driver.findElements(By.cssSelector("ol#selectable li.ui-selected"));
		Assert.assertEquals(listSelectedNumbers.size(), 4);
	}

	// @Test
	public void TC_05_Click_And_Hold_Random_Element() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> listElement = driver.findElements(By.cssSelector("li.ui-state-default"));
		// Nhan ctrl xuong
		action.keyDown(Keys.CONTROL).perform();
		// Click tung element
		action.click(listElement.get(0)).perform();
		action.click(listElement.get(1)).perform();
		action.click(listElement.get(3)).perform();
		action.click(listElement.get(6)).perform();

		// Nha nut ctrl ra
		action.keyUp(Keys.CONTROL);
		sleepInSecond(2);

		// Verify
		List<WebElement> listSelectedNumbers = driver.findElements(By.cssSelector("ol#selectable li.ui-selected"));
		Assert.assertEquals(listSelectedNumbers.size(), 4);
	}

	//@Test
	public void TC_06_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		js.executeScript("arguments[0].scrollIntoView(false)", driver.findElement(By.cssSelector("p#demo")));
		Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
	}

	//@Test
	public void TC_07_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
		action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());
		driver.findElement(By.cssSelector("li.context-menu-icon-quit")).click();
		alert=explicitWait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
		Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
	}

	@Test
	public void TC_08_Drag_Drop_Element_HTML4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		WebElement sourceElement=driver.findElement(By.cssSelector("div#draggable"));
		WebElement targetElement=driver.findElement(By.cssSelector("div#droptarget"));
		action.dragAndDrop(sourceElement, targetElement).perform();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#droptarget")).getText(), "You did great!");
		
		//Verify colorBackground
		String targetBackground=driver.findElement(By.cssSelector("div#droptarget")).getCssValue("background-color");
		
		String targetBackgroundAsHex=Color.fromString(targetBackground).asHex();
		
		Assert.assertEquals(targetBackgroundAsHex, "#03a9f4");
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
