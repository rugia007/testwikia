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
	
	public AddVideoPage typeVideoUrl(String url)
	{
		addVideoUrlInput.sendKeys(url);
		return this;
	}
	
	public SortVideoPage submitVideo(){
		addButton.submit();
		return PageFactory.initElements(driver, SortVideoPage.class);
	}
	
	public SortVideoPage addVideoUrlAndSubmit(String url){
		typeVideoUrl(url);
		return submitVideo();
	}
}
