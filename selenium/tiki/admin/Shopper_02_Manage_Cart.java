package tiki.admin;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Shopper_02_Manage_Cart {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeTest(alwaysRun = true)
	public void initBrowser() {
		System.out.println("open browser");
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

	}

	@Test(groups = "cart")
	public void Shopper_01_Create_Visa() {

	}

	@Test(groups = "cart")
	public void Shopper_02_View_Visa() {

	}

	@Test(groups = "cart")
	public void Shopper_03_Update_Visa() {

	}

	@Test(groups = "cart")
	public void Shopper_04_Delete_Visa() {

	}

	@AfterTest(alwaysRun = true)
	public void cleanBrowser() {
		System.out.println("clean browser");
		driver.quit();
	}

}
