package webdriver;


import org.testng.Assert;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Handle_Custom_Dropdown {
	WebDriver driver;
	WebDriverWait explicitWait;
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
		explicitWait= new WebDriverWait(driver, 30);
		driver.manage().window().maximize();
	}

//	@Test
	public void TC_01_JQuerry() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
//		//Step 1:
//		driver.findElement(By.cssSelector("span#speed-button")).click();
//		//Step 2:
//		//Locator lấy thẻ đại diện cho các element
//		//Lấy đến thẻ chứa text
//		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#speed-menu div[role='option']")));
//		List<WebElement> speedDropdownItems= driver.findElements(By.cssSelector("ul#speed-menu div[role='option']"));
//
//		for (WebElement tempItem : speedDropdownItems) {
//			String itemText=tempItem.getText();
//			//System.out.println(itemText);
//			if (itemText.equals("Faster")) {
////				// Step 5:
//				tempItem.click();
//				System.out.println("The item is "+itemText);
//				break;
//			}
//		}
	selectItemInDropdown("span#speed-button",
			"ul#speed-menu div[role='option']", "Faster");
	Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span[class='ui-selectmenu-text']")).getText(), "Faster");
	
	sleepInSecond(3);
	selectItemInDropdown("span#speed-button",
			"ul#speed-menu div[role='option']", "Slower");
	Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span[class='ui-selectmenu-text']")).getText(), "Slower");
	
	sleepInSecond(3);
	selectItemInDropdown("span#speed-button",
			"ul#speed-menu div[role='option']", "Medium");
	Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span[class='ui-selectmenu-text']")).getText(), "Medium");
	
	sleepInSecond(3);
	selectItemInDropdown("span#speed-button",
			"ul#speed-menu div[role='option']", "Fast");
	Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span[class='ui-selectmenu-text']")).getText(), "Fast");
	
	sleepInSecond(3);
	selectItemInDropdown("span#speed-button",
			"ul#speed-menu div[role='option']", "Slow");
	Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span[class='ui-selectmenu-text']")).getText(), "Slow");
	}
	
	//@Test
	public void TC_02_reactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectItemInDropdown("div[role='listbox']", "div.menu span[class='text']", "Christian");
		Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Christian')]")).getText(), "Christian");
		
//		driver.findElement(By.xpath("//div[@role='listbox']")).click();
//		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.menu span[class='text']")));
//		List<WebElement> listEle=driver.findElements(By.cssSelector("div.menu span[class='text']"));
//		for (WebElement item : listEle) {
//			String itemtext=item.getText();
//			if(itemtext.equals("Christian"))
//			{
//				item.click();
//				break;
//			}
//		}
	}
	
	//@Test
	public void TC_03_vueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu li", "Third Option");
		Assert.assertEquals(driver.findElement(By.xpath("//li[contains(text(),'Third Option')]")).getText(), "Third Option");
		
//		driver.findElement(By.cssSelector("li.dropdown-toggle")).click();
//		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul.dropdown-menu li")));
//		List<WebElement> itemL=driver.findElements(By.cssSelector("ul.dropdown-menu li"));
//		for (WebElement temp : itemL) {
//			String s=temp.getText();
//			if(s.equals("Third Option")) {
//				temp.click();
//				break;
//			}
//		}
	}
	
	
	@Test
	public void TC_04_EditabelwithEnteringAndSelect() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		EnterAndSelectItemInDropdown("input.search",
				"Al", "div.transition span.text",
				"Algeria");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(), "Algeria");
	}
	
	
	//@Test
		public void TC_04_EditabelwithSelect() {
			driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
			selectItemInDropdown("div.dropdown",
					"div.transition span.text", "Algeria");
			sleepInSecond(3);
			Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(), "Algeria");
		}
	
	
	public void selectItemInDropdown(String locateClick, String expList, String selectedItem) {
		driver.findElement(By.cssSelector(locateClick)).click();
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(expList)));
		List<WebElement> list = driver.findElements(By.cssSelector(expList));
		for (WebElement temp : list) {
			String txt = temp.getText();
			if (txt.equals(selectedItem)) {
				temp.click();
				break;
			}
		}
	}
	
	public void EnterAndSelectItemInDropdown(String locateEnter,String keyWord, String expList, String selectedItem) {
		driver.findElement(By.cssSelector(locateEnter)).sendKeys(keyWord);
		sleepInSecond(1);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(expList)));
		List<WebElement> list = driver.findElements(By.cssSelector(expList));
		for (WebElement temp : list) {
			String txt = temp.getText();
			if (txt.equals(selectedItem)) {
				temp.click();
				break;
			}
		}
	}
	

	public int randomNum() {
		Random rand = new Random();
		return rand.nextInt(9999);
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

