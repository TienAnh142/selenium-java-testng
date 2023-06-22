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

public class Topic_08_FindElement_FindElements {
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

//		ChromeOptions options= new ChromeOptions();
//		options.addArguments("----disable-notifications");
//		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		js=(JavascriptExecutor) driver;
	}

	@Test
	public void TC_01_findElement() {
		// Tìm thấy duy nhất 1 element/node
		// Thao tác trực tiếp lên node đó
		// Vì nó tìm thấy nên k cần chờ hết timeout là 15s
		
		
		
		// Tìm thấy nhiều hơn 1 element/node
		//Thao tác node đầu, không quan tâm node còn lại
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("ahn142@gmail.com");		
		
		
		// Không tìm thấy element/node nào
	}

	@Test
	public void TC_02_findElements() {
		driver.get("");
		
		// Tìm thấy duy nhất 1 element/node
		// Tìm thấy và lưu nó vào list= 1 element
		// Vì nó tìm thấy nên ko cần phải chờ hết timeout 15s
		List<WebElement> elements= driver.findElements(By.cssSelector("input#email"));
		System.out.println("List element number ="+ elements.size());
		
		
		// Tìm thấy nhiều hơn 1 element/node
		//Tìm thấy và lưu nó vào list= element tương ứng
		elements=driver.findElements(By.cssSelector("input[type='email']"));
		System.out.println("List element number = "+elements.size());
		// Không tìm thấy element/node nào
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
	
	public int getRandomnumber() {
		return rand.nextInt(100);
	}
}

