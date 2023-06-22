package webdriver;


import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Popup_Part_II_Fixed_NotIn_DOM {
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
	public void TC_01_fixedPopup_NotInDOM() {
		driver.get("https://tiki.vn/");
		By popupLogin=By.cssSelector("div.ReactModal__Content");
		
		//Verify khi mà popup chưa được bật/hiển thị
		Assert.assertEquals(driver.findElements(popupLogin).size(),0);
		
		driver.findElement(By.cssSelector("div[data-view-id*='header_account']")).click();
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(popupLogin).isDisplayed());
		
		driver.findElement(By.xpath("//p[contains(text(),'Đăng nhập bằng email')]")).click();
		sleepInSecond(1);
		
		driver.findElement(By.xpath("//button[contains(text(),'Đăng nhập')]")).click();
		sleepInSecond(2);
		
		//Verify error message
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='error-mess' and text()='Email không được để trống']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='error-mess' and text()='Mật khẩu không được để trống']")).isDisplayed());
		
		driver.findElement(By.cssSelector("button.btn-close")).click();
		
		Assert.assertEquals(driver.findElements(popupLogin).size(),0);
	}
	
	@Test
	public void TC_02_fixedPopup_NotInDOM(){
		driver.get("https://www.facebook.com/");
		
		By createAccountPopup=By.xpath("//div[text()='Sign Up']/parent::div/parent::div");
		
		//Verify if popup is existing
		Assert.assertEquals(driver.findElements(createAccountPopup).size(),0);
		
		driver.findElement(By.xpath("//a[@role='button' and text()='Create new account']")).click();
		sleepInSecond(1);
		
		//Verify popup opened already
		Assert.assertTrue(driver.findElement(createAccountPopup).isDisplayed());
		
		driver.findElement(By.xpath("//input[@aria-label='First name']")).sendKeys("Mr");
		driver.findElement(By.xpath("//input[@aria-label='Surname']")).sendKeys("A");
		driver.findElement(By.xpath("//input[@aria-label='Mobile number or email address']")).sendKeys("ahn@gmail.com");
		
		new Select(driver.findElement(By.name("birthday_day"))).selectByVisibleText("1");
		new Select(driver.findElement(By.name("birthday_month"))).selectByVisibleText("Apr");
		new Select(driver.findElement(By.name("birthday_year"))).selectByVisibleText("2000");
		
		List<WebElement> listRadio=driver.findElements(By.xpath("//span[@data-name='gender_wrapper']"));
		for (WebElement item : listRadio) {
			String gender= item.getText();
			if(gender.equals("Female"))
					item.click();
		}
		
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/parent::div/child::img")).click();
		sleepInSecond(1);
		Assert.assertEquals(driver.findElements(createAccountPopup).size(),0);
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
