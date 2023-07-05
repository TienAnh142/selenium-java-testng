package webdriver;

import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_26_Upload_File_Part_I {
	WebDriver driver;
	Random rand = new Random();
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String emailAddress = "testdemo" + getRandomnumber() + "@gmail.com";
	JavascriptExecutor jsExecutor;
	String rashfordImgName = "Rashford.jpg";
	String mountImgName = "Mount.jpg";
	String brunoImgName = "Bruno.jpg";

	String rahsfordImgPath = projectPath + "\\Upload File\\" + rashfordImgName;
	String mountImgPath = projectPath + "\\Upload File\\" + mountImgName;
	String brunoImgPath = projectPath + "\\Upload File\\" + brunoImgName;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
		}

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		jsExecutor = (JavascriptExecutor) driver;
	}

	@Test
	public void TC_01_One_File_Per_Time() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		// Load file lên
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(rahsfordImgPath);
		sleepInSecond(1);

		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(mountImgPath);
		sleepInSecond(1);

		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(brunoImgPath);
		sleepInSecond(1);

		// Verify file được load thành công
		Assert.assertTrue(getElement("//p[text()='" + rashfordImgName + "']").isDisplayed());
		Assert.assertTrue(getElement("//p[text()='" + mountImgName + "']").isDisplayed());
		Assert.assertTrue(getElement("//p[text()='" + brunoImgName + "']").isDisplayed());

		// Click upload file
		List<WebElement> listButtonUpload = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement buttonUpload : listButtonUpload) {
			buttonUpload.click();
			sleepInSecond(2);
		}

//		driver.findElement(By.xpath("//p[text()='" + rashfordImgName
//				+ "']//ancestor::td//following-sibling::td//button[@class='btn btn-primary start']")).click();
//		sleepInSecond(5);
//
//		driver.findElement(By.xpath("//p[text()='" + mountImgName
//				+ "']//ancestor::td//following-sibling::td//button[@class='btn btn-primary start']")).click();
//		sleepInSecond(5);
//
//		driver.findElement(By.xpath("//p[text()='" + brunoImgName
//				+ "']//ancestor::td//following-sibling::td//button[@class='btn btn-primary start']")).click();
//		sleepInSecond(5);

		// Verify file được upload thành công
		Assert.assertTrue(
				getElement("//a[@title='" + rashfordImgName + "' and text()='" + rashfordImgName + "']").isDisplayed());
		Assert.assertTrue(
				getElement("//a[@title='" + mountImgName + "' and text()='" + mountImgName + "']").isDisplayed());
		Assert.assertTrue(
				getElement("//a[@title='" + brunoImgName + "' and text()='" + brunoImgName + "']").isDisplayed());
	}

	@Test
	public void TC_02_Multiple_Files_Per_Time() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		// Load file lên
		driver.findElement(By.xpath("//input[@type='file']"))
				.sendKeys(rahsfordImgPath + "\n" + mountImgPath + "\n" + brunoImgPath);
		sleepInSecond(1);

//		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(mountImgPath);
//		sleepInSecond(1);
//
//		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(brunoImgPath);
//		sleepInSecond(1);

		// Verify file được load thành công
		Assert.assertTrue(getElement("//p[text()='" + rashfordImgName + "']").isDisplayed());
		Assert.assertTrue(getElement("//p[text()='" + mountImgName + "']").isDisplayed());
		Assert.assertTrue(getElement("//p[text()='" + brunoImgName + "']").isDisplayed());

		// Click upload file
		List<WebElement> listButtonUpload = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement buttonUpload : listButtonUpload) {
			buttonUpload.click();
			sleepInSecond(2);
		}

//		driver.findElement(By.xpath("//p[text()='" + rashfordImgName
//				+ "']//ancestor::td//following-sibling::td//button[@class='btn btn-primary start']")).click();
//		sleepInSecond(5);
//
//		driver.findElement(By.xpath("//p[text()='" + mountImgName
//				+ "']//ancestor::td//following-sibling::td//button[@class='btn btn-primary start']")).click();
//		sleepInSecond(5);
//
//		driver.findElement(By.xpath("//p[text()='" + brunoImgName
//				+ "']//ancestor::td//following-sibling::td//button[@class='btn btn-primary start']")).click();
//		sleepInSecond(5);

		// Verify file được upload thành công
		Assert.assertTrue(
				getElement("//a[@title='" + rashfordImgName + "' and text()='" + rashfordImgName + "']").isDisplayed());
		Assert.assertTrue(
				getElement("//a[@title='" + mountImgName + "' and text()='" + mountImgName + "']").isDisplayed());
		Assert.assertTrue(
				getElement("//a[@title='" + brunoImgName + "' and text()='" + brunoImgName + "']").isDisplayed());
	}

	// @Test
	public void TC_04_Click_And_Hold_Block_Element() {

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
