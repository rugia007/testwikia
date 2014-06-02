package wikia.test;

import org.apache.commons.lang3.SystemUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class BaseTest {
	protected final String HOMEPAGE_URL = "http://testhomework.wikia.com/";
	protected final String LOGIN_PAGE_URL = "http://testhomework.wikia.com/wiki/Test-homework_Wiki";
	protected final String USERNAME = "Psychoshocker13";
	protected final String PASSWORD = "123456";
	protected final String ADD_VIDEO_PAGE_URL = "http://testhomework.wikia.com/wiki/Special:WikiaVideoAdd";
	protected final String YOUTUBE_URL = "http://www.youtube.com/watch?v=h9tRIZyTXTI";
	protected final String VIDEO_NAME = "The Best Classical Music In The World";
	protected final String EXPECTED_VIDEO_URL = "http://testhomework.wikia.com/wiki/File:The_Best_Classical_Music_In_The_World";
	
	protected WebDriver driver;
	
	//Rule for allowing us to use the test method name; used to determine which driver to use
	@Rule
	public TestName name = new TestName();
	
	@Before
	public void setUp() {
		//Apparently Windows machines need a .exe version of chrome driver to work
		boolean needExe = false;
		if(SystemUtils.IS_OS_WINDOWS){
			needExe = true;
		}
		
		if(name.getMethodName().contains("Firefox")){
        	driver = new FirefoxDriver();
        }
		else if(name.getMethodName().contains("Chrome")){
			//Takes me to the /bin/ folder
			String path = getClass().getClassLoader().getResource("").getPath();
			//I want the lib folder where I put my chromedriver
			path = path+"../lib/";
			String chromeDriverPath = path+"chromedriver";
			if(needExe){
				chromeDriverPath = chromeDriverPath+".exe";
			}
			System.setProperty("webdriver.chrome.driver", path+"chromedriver");
			ChromeOptions options = new ChromeOptions();
        	driver = new ChromeDriver(options);
        }
		//Default to HTMLUnitDriver, even though we don't use it
		else {
			driver = new HtmlUnitDriver();
		}
	}

	@After
	public void tearDown(){
		driver.quit();
	}
	
	public void assertRedirect(String url){
		Assert.assertEquals("Should have redirected to " + url, url,
				driver.getCurrentUrl());
	}
}
