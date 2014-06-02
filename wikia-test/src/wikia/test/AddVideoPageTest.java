package wikia.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import wikia.AddVideoPage;
import wikia.LoginPage;
import wikia.SortVideoPage;
import wikia.VideoPage;

public class AddVideoPageTest extends BaseTest{
	
	private LoginPage page;
	private AddVideoPage addVideoPage;
	private SortVideoPage sortVideoPage;
	private VideoPage videoPage;
	
	@Before
	public void setUp(){
		super.setUp();
        
		driver.get(HOMEPAGE_URL);
		// Create a new instance of the login page class
        // and initialize any WebElement fields in it.
        page = PageFactory.initElements(driver, LoginPage.class);
        //I put a try/catch block here instead of throwing an InterruptedException because I don't want
        //the setUp() signature to change to throw exceptions.
        try {
			page.enterCredentials(USERNAME, PASSWORD);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        Assert.assertTrue("Displaying incorrect username in Account Navigation label",
				page.getAccountNavigationText().contains(USERNAME));
	}
	
	@After
	public void tearDown(){
		super.tearDown();
	}
	
	@Test
	public void testAddVideoForChrome() throws Exception {
        runTestAddVideo();
    }
	
	@Test
	public void testAddVideoForFirefox() throws Exception {
        runTestAddVideo();
    }

	private void runTestAddVideo() throws Exception {
		//Navigate to HOMEPAGE
		driver.get(HOMEPAGE_URL);
		//Assert we navigated to login page
		assertRedirect(LOGIN_PAGE_URL);
		//Left click the “Contribute” button
		page.clickContributeButton();
		Assert.assertTrue("Contribute menu dropdown should be displayed", page.isContributeMenuDisplayed());
		addVideoPage = page.clickAddVideoButton();
		assertRedirect(ADD_VIDEO_PAGE_URL);
		//Type the youtube URL into the video url input
		sortVideoPage = addVideoPage.addVideoUrlAndSubmit(YOUTUBE_URL);
		//Assert successful upload
		String expected = "Video page File:"+VIDEO_NAME+" was successfully added.";
		Assert.assertEquals("Expected video was successully added", expected, sortVideoPage.getFlashText());
		//Click the file link and go to the next page
		videoPage = sortVideoPage.clickFileLink();
		//Assert we are at the file page
		Assert.assertEquals("Incorrect URL", EXPECTED_VIDEO_URL, driver.getCurrentUrl());
		//Assert the file name is correct
		Assert.assertEquals("Incorrect File name", VIDEO_NAME, videoPage.getHeaderText());
	}
}
