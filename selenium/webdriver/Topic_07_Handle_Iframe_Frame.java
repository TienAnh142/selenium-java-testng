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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Handle_Iframe_Frame {
	WebDriver driver;
	WebDriverWait explicitwait;
	Random rand= new Random();
	String projectPath= System.getProperty("user.dir");
	String osName=System.getProperty("os.name");
	JavascriptExecutor js=(JavascriptExecutor) driver;
	String emailAddress="testdemo" + getRandomnumber() + "@gmail.com";

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
		driver.manage().window().maximize();
		js=(JavascriptExecutor) driver;
		explicitwait= new WebDriverWait(driver, 10);
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	//@Test
	public void TC_01_Visible_Displayed_Visibility() {
		driver.get("https://www.facebook.com/");
		
		//1. Có trên UI(bắt buộc)
		//1. Có trng html(bắt buộc)
		//Wait cho email address textbox hien thi
		explicitwait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("email")));
		
		driver.findElement(By.id("email")).sendKeys("automation");
	}
	
	@Test
	public void TC_02_Invisible_Undisplayed_Unvisibility_I(){
		driver.get("https://www.facebook.com/");
		//K co tren UI va co tren Dom
		driver.findElement(By.xpath("//a[(@data-testid='open-registration-form-button')]")).click();
		//Wait cho re-enter email address khong hien thi trong 10s
		explicitwait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
	}
	//@Test
	public void TC_02_Invisible_Undisplayed_Unvisibility_II(){
		driver.get("https://www.facebook.com/");
		//k co tren UI va k co tren DOM
		//driver.findElement(By.xpath("//a[(@data-testid='open-registration-form-button')]")).click();
		//Wait cho re-enter email address khong hien thi trong 10s
		explicitwait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
	}
	
	//@Test
	public void TC_03_presence_I(){
		driver.get("https://www.facebook.com/");
		explicitwait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
	}	

	// @Test
	public void TC_04_presence_II() {
		driver.get("https://www.facebook.com/");
		//K co tren UI va co tren Dom
		driver.findElement(By.xpath("//a[(@data-testid='open-registration-form-button')]")).click();
		//Wait cho re-enter email address khong hien thi trong 10s
		explicitwait.until(ExpectedConditions.presenceOfElementLocated(By.name("reg_email_confirmation__")));
	}

	
	@Test
	public void TC_05_Staleness_I() {
		driver.get("https://www.facebook.com/");
		// k co tren UI va k co tren DOM

		driver.findElement(By.xpath("//a[(@data-testid='open-registration-form-button')]")).click();

		// Phase 1: Element co trong DOM
		WebElement reEnterEmailAddressTextbox = driver.findElement(By.name("reg_email_confirmation__"));

		// Thao tac voi 1 element khac de element nay k con trong DOM

		// close popup
		driver.findElement(By.cssSelector("img#u_1q_9_My")).click();

		// Wait cho re-enter email address khong hien thi trong 10s
		explicitwait.until(ExpectedConditions.stalenessOf(reEnterEmailAddressTextbox));
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
	
	public int getRandomnumber() {
		return rand.nextInt(100);
	}
}

