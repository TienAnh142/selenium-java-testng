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

public class TC03_Priority {

	@Test(enabled = false)
	public void EndUser_01_Register_Employee() {

	}

	@Test(enabled = false)
	public void EndUser_02_View_Employee() {

	}

	@Test(description = "Jira_142 - Edit created employee and verify edit successfully")
	public void EndUser_03_Edit_Employee() {
		Assert.assertTrue(false);
	}

	@Test
	public void EndUser_04_Move_Employee() {

	}
}
