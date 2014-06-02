package wikia;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	protected WebDriver driver;

	protected void hoverOverElement(WebElement element)
	{
		Actions action = new Actions(driver);
		//For some reason Firefox won't register a hover without waiting
		WebDriverWait wdw = new WebDriverWait(driver,100);
		wdw.until(ExpectedConditions.elementToBeClickable(element));
		action.moveToElement(element).perform();
	}
}
