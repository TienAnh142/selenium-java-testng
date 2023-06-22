package webdriver;


import org.testng.Assert;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_HandleTB_TA {
	WebDriver driver;
	Random rand = new Random();
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String employeeID = String.valueOf(rand.nextInt(9999));
	String passportNumber = "40517-402-96-7202";
	String comment = "This is generated data\nOf certain people";
	JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

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
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}

	public String getAttributeByJS(String locator) {
		String str = (String) jsExecutor.executeScript("arguments[0].getAttribute('value');", getElement(locator));
		return str;
	}

	@Test
	public void TC_01_Create_new_employee() throws InterruptedException {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		driver.findElement(By.xpath("//span[text()='PIM']/parent::a")).click();
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
		driver.findElement(By.name("firstName")).sendKeys("Automation");
		driver.findElement(By.name("lastName")).sendKeys("FC");
		String values = String.valueOf(driver.findElement(By.xpath("//label[contains(text(),'Employee Id')]/parent::div/following-sibling::div/input")).getAttribute("value"));
		driver.findElement(By.xpath("//label[contains(text(),'Employee Id')]/parent::div/following-sibling::div/input")).sendKeys(employeeID);
		//driver.findElement(By.xpath("//p[text()='Create Login Details']/following-sibling::div/label")).click();
		driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys("afc" + employeeID);
		driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys("@Password123");
		driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys("@Password123");
		driver.findElement(By.xpath("//button[text()=' Save ']")).click();
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), "Automation");
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), "FC");
		Assert.assertEquals(driver.findElement(By.xpath("//label[contains(text(),'Employee Id')]/parent::div/following-sibling::div/input")).getAttribute("value"), values + employeeID);

		driver.findElement(By.xpath("//a[contains(text(),'Immigration')]")).click();
		driver.findElement(By.xpath("//h6[contains(string(),'Assigned Immigration Records')]/parent::div/button")).click();
		driver.findElement(By.xpath("//label[contains(text(),'Number')]/parent::div/following-sibling::div/input")).sendKeys(passportNumber);
		driver.findElement(By.xpath("//textarea[@placeholder='Type Comments here']")).sendKeys(comment);
		driver.findElement(By.xpath("//button[text()=' Save ']")).click();

		driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
		//String str = (String) jsExecutor.executeScript(" return arguemnts[0].getAttribute('value')", driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")));
		//Assert.assertEquals(str, passportNumber);
		
		sleepInSecond(5);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"),passportNumber);
		//Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"),passportNumber);
		
		driver.findElement(By.cssSelector("span.oxd-userdropdown-tab")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys("afc" + employeeID);
		driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys("@Password123");
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		
		driver.findElement(By.xpath("//span[contains(string(),'My Info')]")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//input[@placeholder='First Name']")).getAttribute("placeholder"),"First Name");
		
		System.out.println(driver.findElement(By.name("lastName")).getAttribute("_value"));
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("_value"), "FC");
		Assert.assertEquals(driver.findElement(By.xpath("//label[contains(text(),'Employee Id')]/parent::div/following-sibling::div/input")).getAttribute("value"), values + employeeID);

		driver.findElement(By.xpath("//a[contains(text(),'Immigration')]")).click();
		driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"),passportNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"),comment);

	}

//	@Test
	public void TC_02_Verify_Employee() {
//	driver.findElement(By.name("username")).sendKeys("Admin");
//	driver.findElement(By.name("password")).sendKeys("admin123");
//	driver.findElement(By.cssSelector("//button[@type='submit']")).click();
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
