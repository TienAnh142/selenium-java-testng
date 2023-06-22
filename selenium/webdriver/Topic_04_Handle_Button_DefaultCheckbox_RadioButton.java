package webdriver;


import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Handle_Button_DefaultCheckbox_RadioButton {
	WebDriver driver;
	Random rand= new Random();
	String projectPath= System.getProperty("user.dir");
	String osName=System.getProperty("os.name");
	JavascriptExecutor js=(JavascriptExecutor) driver;
	
	
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		js=(JavascriptExecutor) driver;
	}
	
	
	//@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.cssSelector("ul#popup-login-tab_list li")).click();
		By loginButton=By.cssSelector("button.fhs-btn-login");
		
		//Verify if login button is disabled
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());
		
		String backgroundColorLoginButton=driver.findElement(loginButton).getCssValue("background-image");
		Assert.assertTrue(backgroundColorLoginButton.contains("rgb(224, 224, 224)"));
		System.out.println(backgroundColorLoginButton);
		
		//Verify if login button is enabled
		driver.findElement(By.cssSelector("input#login_username")).sendKeys("ahn@gmail.com");
		driver.findElement(By.cssSelector("input#login_password")).sendKeys("1423a1423a1423a");
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(loginButton).isEnabled());
		backgroundColorLoginButton=driver.findElement(loginButton).getCssValue("background-color");
		System.out.println(backgroundColorLoginButton);
		Color colorBackgroundLoginButton=Color.fromString(backgroundColorLoginButton);
		Assert.assertEquals(colorBackgroundLoginButton.asHex().toUpperCase(), "#C92127");
		Assert.assertEquals(colorBackgroundLoginButton.asRgb(), "rgb(201, 33, 39)");
		
	}
	
	//@Test
	public void TC_02_Default_Checkbox_Radiobutton(){
		
		//Default Checkbox
//		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
//		js.executeScript("arguments[0].click()", driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")));
//		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
//		sleepInSecond(3);
		//js.executeScript("arguments[0].click()", driver.findElement(By.cssSelector("input#eq5")));
		//Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
		
		//Default Radio button
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		checkToCheckbox(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		sleepInSecond(2);
		UncheckToCheckbox(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		
//		if (!driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input")).isSelected()) 
//			js.executeScript("arguments[0].click()", driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input")));
		
//		if (driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input")).isSelected()) 
//				js.executeScript("arguments[0].click()", driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input")));
	}	
	
	//@Test
	public void TC_03_Default_Checkbox_Multiple(){
			
			//Default Checkbox
//			driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
//			js.executeScript("arguments[0].click()", driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")));
//			Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
//			sleepInSecond(3);
			//js.executeScript("arguments[0].click()", driver.findElement(By.cssSelector("input#eq5")));
			//Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
			
			//Default Radio button
			driver.get("https://automationfc.github.io/multiple-fields/");
			List<WebElement> allCheckboxes= driver.findElements(By.cssSelector("input.form-checkbox"));
			for (WebElement checkbox : allCheckboxes) {
				if(checkbox.getAttribute("value").equals("Heart Attack"))
					checkbox.click();
			}	
			for (WebElement verifyCheckbox : allCheckboxes) {
				if(verifyCheckbox.getAttribute("value").equals("Heart Attack"))
				Assert.assertTrue(verifyCheckbox.isSelected());
				sleepInSecond(2);
			}
		}
	
	public void checkToCheckbox(By by) {
		if(!driver.findElement(by).isSelected()) {
		driver.findElement(by).click();
		}
	}
	
	public void UncheckToCheckbox(By by) {
		if(driver.findElement(by).isSelected()) {
		driver.findElement(by).click();
		}
	}
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond*1000);
		}
		catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}
