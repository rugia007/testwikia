package wikia;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SortVideoPage extends BasePage {

	@FindBy(className = "msg")
    private WebElement msgFrame;
    @FindBy(xpath = "/html/body/div[3]/div[3]/div/a")
    private WebElement fileLink;
	
	public SortVideoPage(WebDriver driver) {
		this.driver = driver;
	}

	public String getFlashText(){
		return msgFrame.getText();
	}
	
	public VideoPage clickFileLink(){
		fileLink.click();
		return PageFactory.initElements(driver, VideoPage.class);
	}
}
