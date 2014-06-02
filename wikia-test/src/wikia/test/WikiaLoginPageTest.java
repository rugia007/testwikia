package wikia.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import wikia.LoginPage;

public class WikiaLoginPageTest extends BaseTest {
	private LoginPage page;
		
	@Before
	public void setUp(){
		super.setUp();
        
		driver.get(HOMEPAGE_URL);
		// Create a new instance of the login page class
        // and initialize any WebElement fields in it.
        page = PageFactory.initElements(driver, LoginPage.class);
	}
	
	@After
	public void tearDown(){
		super.tearDown();
	}
	
	@Test
    public void testLoginPageForChrome() throws Exception {
        runTestLoginPage();
    }
	
	@Test
	public void testLoginPageForFirefox() throws Exception {
		runTestLoginPage();
	}
	
	//There is no difference in functionality between Chrome and Firefox.
	//Let's create one runTest function and have both tests call it
	private void runTestLoginPage() throws Exception{
		//Assert we navigated to login page
		Assert.assertEquals("Should have redirected to the to " + LOGIN_PAGE_URL, LOGIN_PAGE_URL,
				driver.getCurrentUrl());
		//Hover over login
		page.hoverOverLoginElement();
		Assert.assertTrue("Login dropdown was not visible", page.isUserLoginDropdownDisplayed());
		//Login as user
		page.enterCredentials(USERNAME, PASSWORD);
		//Assert we are logged in
		Assert.assertTrue("Displaying incorrect username in Account Navigation label",
				page.getAccountNavigationText().contains(USERNAME));
	}
}
