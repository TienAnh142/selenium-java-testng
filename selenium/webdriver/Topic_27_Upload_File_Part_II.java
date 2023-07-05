package webdriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.tools.Tool;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.openqa.selenium.server.browserlaunchers.Sleeper;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_27_Upload_File_Part_II {
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

	String autoITChromeOneTime = projectPath + "\\autoITScript\\chromeUploadOneTime.exe";
	String autoITFirefoxOneTime = projectPath + "\\autoITScript\\firefoxUploadOneTime.exe";

	String autoITChromeMultipleTime = projectPath + "\\autoITScript\\chromeUploadMultiple.exe";
	String autoITFirefoxMultipleTime = projectPath + "\\autoITScript\\firefoxUploadMultiple.exe";

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
	}

	// @Test
	public void TC_01_One_File_Per_Time() throws IOException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		// Click để open file dialog
		driver.findElement(By.cssSelector("span.btn-success")).click();
//		if(driver.toString().contains("firefox")) {
//			Runtime.getRuntime().exec(new String[] { autoITFirefoxOneTime, rahsfordImgPath});
//		}else if(driver.toString().contains("chrome")){
		Runtime.getRuntime().exec(new String[] { autoITChromeOneTime, rahsfordImgPath });
		// }
		sleepInSecond(5);

		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + rashfordImgName + "']")).isDisplayed());

		List<WebElement> listButtonUpload = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement buttonUpload : listButtonUpload) {
			buttonUpload.click();
			sleepInSecond(2);
		}

		Assert.assertTrue(
				getElement("//a[@title='" + rashfordImgName + "' and text()='" + rashfordImgName + "']").isDisplayed());
	}

	// @Test
	public void TC_02_Multiple_Files_Per_Time() throws IOException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		driver.findElement(By.cssSelector("span.btn-success")).click();
		// Load file lên
		Runtime.getRuntime()
				.exec(new String[] { autoITChromeMultipleTime, rahsfordImgPath, brunoImgPath, mountImgPath });
		sleepInSecond(5);

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

		// Verify file được upload thành công
		Assert.assertTrue(
				getElement("//a[@title='" + rashfordImgName + "' and text()='" + rashfordImgName + "']").isDisplayed());
		Assert.assertTrue(
				getElement("//a[@title='" + mountImgName + "' and text()='" + mountImgName + "']").isDisplayed());
		Assert.assertTrue(
				getElement("//a[@title='" + brunoImgName + "' and text()='" + brunoImgName + "']").isDisplayed());
	}

	@Test
	public void TC_03_Java_Robot() throws AWTException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		driver.findElement(By.cssSelector("span.btn-success")).click();
		
		// copy file path
		StringSelection select=new StringSelection(rahsfordImgPath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
		
		//Load file
		Robot robot=new Robot();
		sleepInSecond(1);
		
		//Nhan xuong ctrl-v
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		
		//Nha ctrl - v
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		
		//Nhan enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		sleepInSecond(5);

		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + rashfordImgName + "']")).isDisplayed());

		List<WebElement> listButtonUpload = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement buttonUpload : listButtonUpload) {
			buttonUpload.click();
			sleepInSecond(2);
		}

		Assert.assertTrue(
				getElement("//a[@title='" + rashfordImgName + "' and text()='" + rashfordImgName + "']").isDisplayed());
		
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
