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

public class Topic_08_Element_Condition_Status {
	WebDriver driver;
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		js=(JavascriptExecutor) driver;
	}

	//@Test
	public void TC_01_iframe() {
		driver.get("https://skills.kynaenglish.vn/");
		
		
		//Verify Iframe FB hiển thị
		//FB iframe vẫn là element của trang Kyna
		Assert.assertTrue(driver.findElement(By.xpath("//iframe[contains(@src,'kyna.vn')]")).isDisplayed());
		
		//Switch den FB
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'kyna.vn')]")));
		
		//Switch ve lai main Page
		driver.switchTo().defaultContent();
		
		//Switch sang chat
		driver.switchTo().frame("cs_chat_iframe");
		
		//click vao button de mo chat
		driver.findElement(By.cssSelector("div.button_bar")).click();
		
		
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("Ahn");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0909xxxx");
		new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
		driver.findElement(By.cssSelector("textarea[name='message']")).sendKeys("This is message of\nmine");
		
		
		//switch ve main page
		driver.switchTo().defaultContent();
		driver.findElement(By.id("live-search-bar")).sendKeys("Excel");
		driver.findElement(By.cssSelector("button.search-button")).click();
		sleepInSecond(5);
		
		List<WebElement> list=driver.findElements(By.cssSelector("div.content h4"));
		for (WebElement temp : list) {
			Assert.assertTrue(temp.getText().contains("Excel"));
				
				
		}
	}
	
	@Test
	public void TC_02_frame(){
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		driver.switchTo().frame("login_page");
		driver.findElement(By.name("fldLoginUserId")).sendKeys("ahn142");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(5);
		//driver.switchTo().defaultContent();
		Assert.assertTrue(driver.findElement(By.xpath("//input[@type='password']")).isDisplayed());
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("john2023");
		
	}
	
	//@Test
	public void TC_03_randomPopup_NotInDOM(){

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

