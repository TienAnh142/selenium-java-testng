package webdriver;


import static org.testng.Assert.assertTrue;

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

public class Topic_05_Handle_Custom_Checkbox_RadioButton {
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
	public void TC_01_Radio() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(5);
		/*Case 1*/
		// Thẻ input bị che k thao tác được
		//Thẻ input lại dùng để verify
		
		
		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")).click();
		//js.executeScript("arguments[0].click()", driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")));
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input]")).isSelected());
		
	}
	
	//@Test
	public void TC_02_Radio(){
		
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(5);
		/*Case 2*/
		// Thẻ khác input để click(span/div/label,..) > đang hiển thị
		//Thẻ này k dùng để verify
		
		
		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/parent::label")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/parent::label")).isSelected());
		//js.executeScript("arguments[0].click()", driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")));
	}
	
	//@Test
	public void TC_03_Default_Checkbox_Multiple(){	
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(5);
		/*Case 3*/
		// Dung Thẻ input để click
		//Dung Thẻ khac de verify
		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/parent::label")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")).isSelected());
		//js.executeScript("arguments[0].click()", driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")));
		}
	
	
		//@Test
		public void TC_04_() {
			driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
			sleepInSecond(5);
			/* Case 4 */
			// Dung Thẻ input bi an để click
			// Dung ham jvs de click
			js.executeScript("arguments[0].click()", driver.findElement(
					By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/parent::label")));
			Assert.assertTrue(
					driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input"))
							.isSelected());
			// js.executeScript("arguments[0].click()",
			// driver.findElement(By.xpath("//div[text()='Đăng ký cho người
			// thân']/preceding-sibling::div/input")));
		}
		
		@Test
		public void TC_05() {
			driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
			sleepInSecond(2);
			
			By radioButton=By.cssSelector("div[aria-label='Đà Nẵng']");
			By checkBox=By.cssSelector("div[aria-label='Quảng Nam']");
			js.executeScript("arguments[0].click(); arguments[1].click();", driver.findElement(radioButton), driver.findElement(checkBox));
			
			//Cach 1:
			Assert.assertTrue(driver.findElement(By.cssSelector("div[aria-label='Quảng Nam'][aria-checked='true']")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.cssSelector("div[aria-label='Quảng Nam'][aria-checked='true']")).isDisplayed());
			
			
			//Cach 2:
			Assert.assertEquals(driver.findElement(radioButton).getAttribute("aria-checked"),"true");
			Assert.assertEquals(driver.findElement(checkBox).getAttribute("aria-checked"), "true");
		}
	
	public void checkToCheckbox(By by) {
		js.executeScript("arguments[0].click()", driver.findElement(by));
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
