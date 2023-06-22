package webdriver;


import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Handle_Popup {
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

		ChromeOptions options= new ChromeOptions();
		options.addArguments("----disable-notifications");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		js=(JavascriptExecutor) driver;
	}
	
	
	//@Test
	public void TC_01_fixedPopup_InDOM() {
		driver.get("https://ngoaingu24h.vn/");
		sleepInSecond(3);
		By popupLogin=By.cssSelector("div#modal-login-v1 div.modal-content");
		Assert.assertFalse(driver.findElement(popupLogin).isDisplayed());
		
		driver.findElement(By.xpath("//button[contains(text(),'Đăng nhập')]")).click();
		driver.findElement(By.cssSelector("input#account-input")).sendKeys("automation");
		driver.findElement(By.cssSelector("input#password-input")).sendKeys("automation");
		driver.findElement(By.cssSelector("button.btn-login-v1")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("div.error-login-panel")).getText(), "Tài khoản không tồn tại!");
		driver.findElement(By.xpath("//h4[@class='modal-title']/preceding-sibling::button")).click();
		
	}
	
	@Test
	public void TC_02_fixedPopup_InDOM(){
		driver.get("https://skills.kynaenglish.vn/");
		sleepInSecond(3);
		
		By Popup=By.cssSelector("div#k-popup-account-login");
		
		Assert.assertFalse(driver.findElement(Popup).isDisplayed());
		
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(Popup).isDisplayed());
		
		
		driver.findElement(By.cssSelector("input#user-login")).sendKeys("automation@gmail.com");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
		
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();
		
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(),"Sai tên đăng nhập hoặc mật khẩu");
		
		driver.findElement(By.cssSelector("button.k-popup-account-close")).click();
		sleepInSecond(3);
		
		Assert.assertFalse(driver.findElement(Popup).isDisplayed());
		
		
		
	}
	
	//@Test
	public void TC_03_Default_Checkbox_Multiple(){
	}	
		
	
	
		//@Test
		public void TC_04_() {
			
		}
		
		//@Test
		public void TC_05() {
			
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
