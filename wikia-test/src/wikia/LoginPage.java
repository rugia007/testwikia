package wikia;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage{
	
	@FindBy(className = "ajaxLogin")
	private WebElement ajaxLoginElement;
	@FindBy(id = "UserLoginDropdown")
	private WebElement UserLoginDropdown;
	@FindBy(name = "username")
	private WebElement usernameInput;
	@FindBy(name = "password")
	private WebElement passwordInput;
	@FindBy(className = "login-button")
	private WebElement loginButton;
	@FindBy(id = "AccountNavigation")
	private WebElement accountNavigation;
	@FindBy(className = "wikia-menu-button")
	private WebElement contributeButton;
	@FindBy(className = "chevron")
	private WebElement contributeMenu;
	@FindBy(xpath = "/html/body/div[3]/section/div[2]/header/div[2]/nav/ul/li[2]/a[@data-id='wikiavideoadd']")
	private WebElement addVideoButton;
	
	public LoginPage(WebDriver driver){
		this.driver = driver;
	}
	
	/**
	 * Types in the username to the username input text field
	 * @param username the username to type
	 * @return LoginPage object
	 */
	public LoginPage typeUsername(String username){
		usernameInput.sendKeys(username);
		return this;
	}
	
	/**
	 * Types in the password to the password input text field
	 * @param password
	 * @return LoginPage object
	 */
	public LoginPage typePassword(String password){
		passwordInput.sendKeys(password);
		return this;
	}
	
	/**
	 * Hovers over the login element to trigger the display of the login dropdown
	 */
	public void hoverOverLoginElement(){
		hoverOverElement(ajaxLoginElement);
		//Sometimes hover is too fast and screws up the later type user/password
		WebDriverWait wdw = new WebDriverWait(driver,100);
		wdw.until(ExpectedConditions.visibilityOf(usernameInput));
	}
	
	/**
	 * Clicks the contribute button to trigger the display of the contribute dropdown
	 */
	public void clickContributeButton(){
		hoverOverElement(contributeButton);
		contributeButton.click();
	}
	
	/**
	 * Clicks the "Add a Video" button
	 * @return AddVideoPage
	 */
	public AddVideoPage clickAddVideoButton(){
		WebDriverWait wdw = new WebDriverWait(driver,100);
		wdw.until(ExpectedConditions.visibilityOf(addVideoButton));
		hoverOverElement(addVideoButton);
		addVideoButton.click();
		return PageFactory.initElements(driver, AddVideoPage.class);
	}
	
	/**
	 * Types in the username and password, and clicks the Submit button
	 * @param username
	 * @param password
	 * @return
	 * @throws InterruptedException 
	 */
	public LoginPage enterCredentials(String username, String password) throws InterruptedException{
		hoverOverLoginElement();
		//Sometimes Chrome gets an invalid login because it is too fast
		typeUsername(username);
		//Really don't want to do this, but this is the only way I can get Chrome to consistently pass
		Thread.sleep(1000);
		typePassword(password);
		Thread.sleep(1000);
		return submitLogin();
	}
	
	/**
	 * Clicks the Submit login button
	 * @return
	 */
	public LoginPage submitLogin(){
		loginButton.submit();
		return PageFactory.initElements(driver, LoginPage.class);
	}
	
	/**
	 * Returns true if the login dropdown is visible
	 * @return true if the dropdown is visible, false otherwise
	 */
	public boolean isUserLoginDropdownDisplayed(){
		return ajaxLoginElement.isDisplayed();
	}
	
	/**
	 * Returns true if the Contribute menu dropdown is visible
	 * @return true if the Contribute menu dropdown is visible
	 */
	public boolean isContributeMenuDisplayed(){
		return contributeMenu.isDisplayed();
	}
	
	/**
	 * Gets the account navigation text. Useful for retrieving the user's account panel
	 * @return String text of account navigation panel
	 */
	public String getAccountNavigationText(){
		return accountNavigation.getText();
	}
}
