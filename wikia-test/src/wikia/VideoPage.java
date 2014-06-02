package wikia;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class VideoPage extends BasePage {
    @FindBy(xpath= "/html/body/div[3]/section/div[2]/article/div/header/h1")
    private WebElement fileTitle;

    public VideoPage(WebDriver driver) {
		this.driver = driver;
	}
    
    public String getHeaderText() {
    	return fileTitle.getText();
    }
}
