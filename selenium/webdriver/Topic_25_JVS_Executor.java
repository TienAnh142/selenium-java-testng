package webdriver;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_25_JVS_Executor {
	WebDriver driver;
	Random rand = new Random();
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor jsExecutor;
	String emailAddress = "testdemo" + getRandomnumber() + "@gmail.com";
	WebDriverWait explicitWait;
	String email = "anh@" + getRandomnumber() + "gmail.com";;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 10);
	}

	@Test
	public void TC_01_Javascript_Executor() {
		navigateToUrlByJS("http://live.techpanda.org/");
		sleepInSecond(5);

		Assert.assertEquals(getDomainName(), "live.techpanda.org");

		Assert.assertEquals(executeForBrowser("return document.URL"), "http://live.techpanda.org/");

		hightlightElement("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");
		sleepInSecond(2);

		hightlightElement(
				"//a[text()='Samsung Galaxy']//parent::h2//following-sibling::div//button[@class='button btn-cart']");
		clickToElementByJS(
				"//a[text()='Samsung Galaxy']//parent::h2//following-sibling::div//button[@class='button btn-cart']");
		sleepInSecond(2);

		Assert.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));

		hightlightElement("//a[text()='Customer Service']");
		clickToElementByJS("//a[text()='Customer Service']");
		sleepInSecond(2);

		Assert.assertEquals(getPageTitle(), "Customer Service");

		hightlightElement("//input[@id='newsletter']");
		scrollToElementOnDown("//input[@id='newsletter']");

		jsExecutor.executeScript("arguments[0].setAttribute('value','" + email + "')",
				driver.findElement(By.xpath("//input[@id='newsletter']")));

		hightlightElement("//span[text()='Subscribe']");
		clickToElementByJS("//span[text()='Subscribe']");
		sleepInSecond(2);

		Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));

		navigateToUrlByJS("http://demo.guru99.com/v4/");

		Assert.assertEquals(getDomainName(), "demo.guru99.com");
	}

	@Test
	public void TC_02_HTML5_Validation_Message() {
		driver.get("https://warranty.rode.com/login");
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//a[contains(text(),' Create an Account ')]")).click();
		sleepInSecond(2);
		clickToElementByJS("//button[text()=' Register ']");
		sleepInSecond(2);
		
		String nameTxtBox="//input[@id='name']";
		String emailTxtBox="//input[@id='email']";
		String passwordTxtBox="//input[@id='password']";
		String confirmPasswordTxtBox="//input[@id='password_confirmation']";
		
		Assert.assertEquals(getElementValidationMessage(nameTxtBox), "Please fill out this field.");
		
		sendkeyToElementByJS(nameTxtBox, "Tea");
		
		clickToElementByJS("//button[text()=' Register ']");
		sleepInSecond(2); 
		
		Assert.assertEquals(getElementValidationMessage(emailTxtBox), "Please fill out this field.");
		
		sendkeyToElementByJS(emailTxtBox, email);
		
		clickToElementByJS("//button[text()=' Register ']");
		sleepInSecond(2); 
		
		Assert.assertEquals(getElementValidationMessage(passwordTxtBox), "Please fill out this field.");
		
		sendkeyToElementByJS(passwordTxtBox, "Tea123");
		
		clickToElementByJS("//button[text()=' Register ']");
		sleepInSecond(2); 
		
		Assert.assertEquals(getElementValidationMessage(confirmPasswordTxtBox), "Please fill out this field.");
	
		sendkeyToElementByJS(confirmPasswordTxtBox, "Tea123");
	}

	public String getDomainName() {
		return (String) jsExecutor.executeScript("return document.domain");
	}

	public String getPageTitle() {
		return (String) jsExecutor.executeScript("return document.title");
	}

	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
		sleepInSecond(3);
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element,
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(2);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
		sleepInSecond(3);
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
		jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "');",
				getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public String getAttributeInDOM(String locator, String attributeName) {
		return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');",
				getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getElement(locator));
		return status;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
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
