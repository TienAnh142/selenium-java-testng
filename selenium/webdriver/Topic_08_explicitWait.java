package webdriver;


import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.eclipse.jdt.internal.compiler.ast.ExplicitConstructorCall;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.server.browserlaunchers.Sleeper;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_explicitWait {
	WebDriver driver;
	Random rand= new Random();
	String projectPath= System.getProperty("user.dir");
	String osName=System.getProperty("os.name");
	WebDriverWait explicitWait;
	JavascriptExecutor js=(JavascriptExecutor) driver;
	//Image name
	String vietnam="Viet Nam.jpg";
	String thailan="Thai Lan.jpg";
	String indonesia="Indonesia.jpg";
	
	//Upload file folder
	String uploadFileFolderPath=projectPath + File.separator + "uploadFiles" + File.separator;
	
	//Image path
	String vietnamFilePath=uploadFileFolderPath + vietnam;
	String thailanFilePath=uploadFileFolderPath + thailan;
	//String indonesiaFilePath=uploadFileFolderPath + vietnam;
	
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		driver= new ChromeDriver();
		explicitWait= new WebDriverWait(driver, 15);
		driver.manage().window().maximize();
		js=(JavascriptExecutor) driver;
	}

	//@Test
	public void TC_01_visible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		explicitWait= new WebDriverWait(driver, 5);
		
		driver.findElement(By.cssSelector("div#start button")).click();
		//driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish h4")));
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(),"Hello World!");
	}
	
	//@Test
	public void TC_02_Invisible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		explicitWait= new WebDriverWait(driver, 5);
		
		driver.findElement(By.cssSelector("div#start button")).click();
		//driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(),"Hello World!");
	}

	//@Test
	public void TC_03_AJAX_Loading() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		explicitWait= new WebDriverWait(driver, 15);
		
		//Wait cho đến khi Date Picker hiển thị
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.RadCalendar")));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.RadAjaxPanel span.label")).getText(), "No Selected Dates to display.");
		//Wait cho đến khi ngày 19 được chọn
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='19']")));
		driver.findElement(By.xpath("//a[text()='19']")).click();
		
		//Wait cho den khi AJAX bien mat
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id$='RadCalendar1'] div.raDiv")));
		
		//Wait cho ngay vua duoc chon có thể click trở lại
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='rcSelected']/a[text()='19']")));
		
		//Verify ngày vừa được chọn
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(),"Wednesday, April 19, 2023");
	}
	
	@Test
	public void TC_04_upload_Files(){
		driver.get("https://gofile.io/uploadFiles");
		//driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		explicitWait= new WebDriverWait(driver, 20);
		
		//Wait cho upload file
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#filesUpload button.filesUploadButton")));
		
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(vietnamFilePath +"\n"+ thailanFilePath);
		
		//Wait cho các loading icon của từng file biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated((By.cssSelector("div.mainUploadFilesListDetails div.progress"))));
	
		//Wait upload thành công visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Your files have been successfully uploaded']")));
		
		//Verify text upload thanh cong
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Your files have been successfully uploaded']")).isDisplayed());
	
		//Wait doan hyperlink
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.mainUploadSuccessLink a")));
		
		driver.findElement(By.cssSelector("div.mainUploadSuccessLink a")).click();
		
		//Wait cho cac file upload xuat hien
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Thai Lan.jpg']/parent::a/parent::div/following-sibling::div//button[contains(@class,'filesContentOptionPlay')]")));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Viet Nam.jpg']/parent::a/parent::div/following-sibling::div//button[contains(@class,'filesContentOptionPlay')]")));
	
		//Verify
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Thai Lan.jpg']/parent::a/parent::div/following-sibling::div//button[contains(@class,'filesContentOptionPlay')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Viet Nam.jpg']/parent::a/parent::div/following-sibling::div//button[contains(@class,'filesContentOptionPlay')]")).isDisplayed());
		
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

