package webdriver;


import org.testng.Assert;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Handle_Default_Dropdown {
	WebDriver driver;
	Random rand = new Random();
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstName, lastName, Email, companyName, Password, date, month, year, addFirstName, addLastName, emailAdress,
			country, city, address_1, Zip, phoneNumber;

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
		firstName = "Lil";
		lastName = "TeaA";
		Email = "Lil" + randomNum() + "@gmail.com";
		companyName = "BS";
		Password = "Password123!";
		date = "1";
		month = "April";
		year = "2000";
		addFirstName = "Truong";
		addLastName = "Chinh";
		country = "Viet Nam";
		city = "HCM";
		address_1 = "384";
		Zip = "70";
		phoneNumber = "63737376";
		emailAdress = "bshs@gmail.com";
	}

	@Test
	public void TC_01_Register_new_account() {
		driver.get("https://demo.nopcommerce.com/");

		driver.findElement(By.cssSelector("a.ico-register")).click();
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		new Select(driver.findElement(By.name("DateOfBirthDay"))).selectByVisibleText(date);
		new Select(driver.findElement(By.name("DateOfBirthMonth"))).selectByVisibleText(month);
		new Select(driver.findElement(By.name("DateOfBirthYear"))).selectByVisibleText(year);
		Assert.assertFalse(new Select(driver.findElement(By.name("DateOfBirthDay"))).isMultiple());
		driver.findElement(By.id("Email")).sendKeys(Email);
		driver.findElement(By.id("Company")).sendKeys(companyName);
		driver.findElement(By.id("Password")).sendKeys(Password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(Password);
		driver.findElement(By.name("register-button")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");

		driver.findElement(By.xpath("//a[text()='My account']")).click();
		driver.findElement(By.id("Email")).sendKeys(Email);
		driver.findElement(By.id("Password")).sendKeys(Password);
		driver.findElement(By.cssSelector("button.login-button")).click();

		Assert.assertEquals(
				new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption().getText(), date);
		Assert.assertEquals(
				new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText(), month);
		Assert.assertEquals(
				new Select(driver.findElement(By.name("DateOfBirthYear"))).getFirstSelectedOption().getText(), year);

	}

	public int randomNum() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void TC_02_Add_Address() {

		driver.findElement(By.xpath("//li[@class='customer-addresses inactive']/a")).click();
		driver.findElement(By.cssSelector("button.add-address-button")).click();
		driver.findElement(By.id("Address_FirstName")).sendKeys(addFirstName);
		driver.findElement(By.id("Address_LastName")).sendKeys(addLastName);
		driver.findElement(By.id("Address_Email")).sendKeys(emailAdress);
		new Select(driver.findElement(By.id("Address_CountryId"))).selectByVisibleText("Viet Nam");
		driver.findElement(By.id("Address_City")).sendKeys(city);
		driver.findElement(By.id("Address_Address1")).sendKeys(address_1);
		driver.findElement(By.id("Address_ZipPostalCode")).sendKeys(Zip);
		driver.findElement(By.id("Address_PhoneNumber")).sendKeys(phoneNumber);
		driver.findElement(By.cssSelector("button.save-address-button")).click();

		// Verify

	}

}
