package wikia;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddVideoPage extends BasePage{

	@FindBy(id = "wpWikiaVideoAddUrl")
	private WebElement addVideoUrlInput;
	@FindBy(className = "submits")
	private WebElement addButton;
	
	public AddVideoPage(WebDriver driver){
		this.driver = driver;
	}
	
	/**
	 * Type a url to the video url input field
	 * @param url
	 * @return
	 */
	public AddVideoPage typeVideoUrl(String url)
	{
		addVideoUrlInput.sendKeys(url);
		return this;
	}
	
	/**
	 * Click the Add button
	 * @return
	 */
	public SortVideoPage submitVideo(){
		addButton.submit();
		return PageFactory.initElements(driver, SortVideoPage.class);
	}
	
	/**
	 * Types the URL to the video url input field and click add
	 * @param url
	 * @return
	 */
	public SortVideoPage addVideoUrlAndSubmit(String url){
		typeVideoUrl(url);
		return submitVideo();
	}
}
