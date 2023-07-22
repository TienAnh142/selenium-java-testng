package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TC08_Depend {

	@Test
	public void Shopper_01_Create_Product() {
		Assert.assertTrue(false);
	}

	@Test(dependsOnMethods = "Shopper_01_Create_Product")
	public void Shopper_02_View_Product() {

	}

	@Test(dependsOnMethods = "Shopper_02_View_Product")
	public void Shopper_03_Update_Product() {

	}

	@Test(dependsOnMethods = "Shopper_03_Update_Product")
	public void Shopper_04_Delete_Product() {

	}
}
