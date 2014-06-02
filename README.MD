To run tests:
1) Install ant.  http://ant.apache.org/bindownload.cgi 
2) Install Java.  http://www.oracle.com/technetwork/java/javase/downloads/index.html
3) Go to the wikia-test folder with build.xml
4) Run the tests by "ant runtest"

#Design implementation choices
For maintability:
BaseTest is a test class which is the central location to change things shared between tests like URL strings or driver initiation.
WikiaLoginPageTest extends BaseTest
AddVideoPageTest extends BaseTest
Thus they share the same setUp and tearDown, and can extend the setUps/tearDowns further if necessary.

Likewise, BasePage is a central location for common functionality like hovering.
LoginPage extends BasePage
AddVideoPage extends BasePage
VideoPage extends BasePage

I had to run both in Chrome and Firefox.  Since there is no major functionality differences, I created a private runTestLoginPage() where both tests can just run that method.  Thus I have one central location to change my test and both tests will be reflected.
Likewise in AddVideoPageTest I have runTestAddVideo().


#Painpoints
XPATHS.  There were multiple times when the webdriver could not find certain relative xpaths, so I had to use absolute xpaths.
Examples:
Add a Video button:
	@FindBy(xpath = "/html/body/div[3]/section/div[2]/header/div[2]/nav/ul/li[2]/a[@data-id='wikiavideoadd']")
The File header on the file page after loading a video:
    @FindBy(xpath= "/html/body/div[3]/section/div[2]/article/div/header/h1")
If I were at the company, I would really urge that we add some Id or class names to these fields.

Chrome Driver sometimes submits Log In button before the username/password text fields sendKeys were executed, resulting in failed logins.
I have tried using WebDriver implicit and explicit wait conditions, but none of them seem to work consistently.
Googling the situation got me nowhere:  the most similar hits were bug reports on selenium's webpage which resulted in "Marking unreproducible."
I finally reluctantly chose to use Thread.sleep(1000).  This is very bad coding practice but it is the only way I can get it to work consistently.  This resulted in two code changes:
1) LoginPage.enterCredentials now throws InterruptedException.  No real harm done here
2) I use enterCredentials in AddVideoPageTest.setUp().  Since I didn't want to throw InterruptedException in a setUp method, I opted to use a try/catch block instead to keep the code cleaner.
Again, the cleanest code would NOT to use Thread.sleep in the first place, and look for a better solution.  I am not entirely sure how to do this at this point without furhter investigating WebDriver's explicit and implicit wait funtionality further.