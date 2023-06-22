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

public class Topic_06_Popup_Part_III_Random_In_NotIn_DOM2 {
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
	public void TC_01_randomPopup_InDOM() {
		driver.get("https://www.javacodegeeks.com/");
		sleepInSecond(20);
		By lePopup = By.cssSelector("div.lepopup-popup-container>div:not([style*='display:none'])");

		// Vì nó luôn có trong DOM nên có thể dùng hàm isDisplayed() để kiểm tra
		if (driver.findElement(lePopup).isDisplayed()) {
			driver.findElement(By.cssSelector("input.lepopup-ta-left")).sendKeys(emailAddress);
			driver.findElement(By.cssSelector("a.lepopup-button-zoom-out  span")).click();
			sleepInSecond(5);
			// Verify
			Assert.assertEquals(driver.findElement(By.cssSelector("div.lepopup-element-html-content>h4")).getText(),
					"Thank you!");
			Assert.assertTrue(driver.findElement(By.cssSelector("div.lepopup-element-html-content>p")).getText()
					.contains("Your sign-up request was successful"));
			sleepInSecond(5);
		}

		driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile Testing Explained");
		js.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("button#search-submit")));
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.xpath("//a[@aria-label='Agile Testing Explained']/following-sibling::div/h2/a")).getText(), "Agile Testing Explained");
	}
	
	@Test
	public void TC_02_randomPopup_InDOM(){
		driver.get("https://vnk.edu.vn/");
		sleepInSecond(30);
	
		By popup=By.xpath("//div[@class='thrv_wrapper tve_image_caption']/parent::div");
		if(driver.findElement(popup).isDisplayed()) {
			//driver.findElement(By.cssSelector("svg.tcb-icon")).click();
			js.executeScript("arguments[0].click()", driver.findElement(By.cssSelector("div#tve-p-scroller div.tve_ea_thrive_leads_form_close")));
			sleepInSecond(3);
		}
		js.executeScript("arguments[0].click()", driver.findElement(By.cssSelector("button.btn-danger")));
		sleepInSecond(5);
		
		Assert.assertEquals(driver.getTitle(),"Lịch khai giảng các khóa học tại VNK EDU | VNK EDU");
		//Assert.assertEquals(driver.findElement(By.cssSelector("div.title-content h1")).getText(), "Lịch Khai Giảng tháng 4 – Duy nhất tháng 4 giảm 30-35% học phí ,Tặng 12GB tài liệu full MEPF Thiết kế ,thi công , vẽ shop");
	}
	
	//@Test
	public void TC_03_randomPopup_NotInDOM(){
		driver.get("https://dehieu.vn/");
		sleepInSecond(5);
		
		//hàm isDisplayed() không check trường hợp element k có trong DOM
		By popup= By.cssSelector("div.popup-content");
		
		if(driver.findElements(popup).size()>0 && driver.findElements(popup).get(0).isDisplayed()){
			driver.findElement(By.cssSelector("input#popup-name")).sendKeys("ahn");
			driver.findElement(By.cssSelector("input#popup-email")).sendKeys(emailAddress);
			driver.findElement(By.cssSelector("input#popup-phone")).sendKeys("09xxxxx");
			driver.findElement(By.cssSelector("button#close-popup")).click();
			sleepInSecond(3);
		}
		
		driver.findElement(By.xpath("//a[text()='Tất cả khóa học']")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.getTitle(), "CÔNG TY CỔ PHẦN TƯ VẤN VÀ HỖ TRỢ VIỆC LÀM VNK");
		driver.findElement(By.cssSelector("input#search-courses")).sendKeys("Khóa học Thiết kế và Thi công Hệ thống BMS");
		driver.findElement(By.cssSelector("button#search-course-button")).click();
		
		Assert.assertEquals(driver.findElements(By.cssSelector("div.course")).size(), 1);
		driver.findElement(By.cssSelector("div.course-info")).click();
		Assert.assertEquals(driver.getTitle(), "Khóa học Thiết kế và Thi công Hệ thống BMS");
		
	
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

