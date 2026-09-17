package generic_webdriverUtility;

import java.time.Duration;

import java.util.Set;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	
        WebDriver driver;
        WebDriverWait wait;
        Actions actions;
        JavascriptExecutor js;

		
        public WebDriverUtility(WebDriver driver)
        {
        	this.driver=driver;
        	this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        	this.actions = new Actions(driver);
        	this.js = (JavascriptExecutor) driver;
        }
		// =========================================================
		// 1. BROWSER / NAVIGATION METHODS
		// =========================================================

		public void launchUrl(String url) {
			driver.get(url);
		}

		public String getCurrentUrl() {
			return driver.getCurrentUrl();
		}

		public String getPageTitle() {
			return driver.getTitle();
		}

		public void maximizeWindow() {
			driver.manage().window().maximize();
		}

		public void minimizeWindow() {
			driver.manage().window().minimize();
		}

		public void refreshPage() {
			driver.navigate().refresh();
		}

		public void navigateBack() {
			driver.navigate().back();
		}

		public void navigateForward() {
			driver.navigate().forward();
		}

		public void closeBrowser() {
			driver.quit();
		}

		public void closeCurrentWindow() {
			driver.close();
		}

		// =========================================================
		// 2. IMPLICIT WAIT
		// =========================================================

		public void implicitWait(int seconds) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
		}

		// =========================================================
		// 3. EXPLICIT WAIT METHODS
		// =========================================================

		public WebElement waitForVisibility(By locator) {

			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}
		
		public WebElement waitForVisibility(WebElement ele) {

			return wait.until(ExpectedConditions.visibilityOf(ele));
		}

		public WebElement waitForPresence(By locator) {

			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		}

		public WebElement waitForClickable(By locator) {

			return wait.until(ExpectedConditions.elementToBeClickable(locator));
		}

		public boolean waitForElementInvisible(By locator) {

			return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		}

		public boolean waitForTitle(String title) {

			return wait.until(ExpectedConditions.titleIs(title));
		}

		public boolean waitForUrl(String url) {

			return wait.until(ExpectedConditions.urlToBe(url));
		}

		// =========================================================
		// 4. WEBELEMENT METHODS
		// =========================================================

		public void click(By locator) {

			waitForClickable(locator).click();
		}

		public void enterText(By locator, String text) {

			WebElement element = waitForVisibility(locator);

			element.clear();
			element.sendKeys(text);
		}

		public void clear(By locator) {

			waitForVisibility(locator).clear();
		}

		public String getText(By locator) {

			return waitForVisibility(locator).getText();
		}

		public String getAttribute(By locator, String attribute) {

			return waitForPresence(locator).getAttribute(attribute);
		}

		public boolean isDisplayed(By locator) {

			return waitForPresence(locator).isDisplayed();
		}

		public boolean isEnabled(By locator) {

			return waitForPresence(locator).isEnabled();
		}

		public boolean isSelected(By locator) {

			return waitForPresence(locator).isSelected();
		}

		public void sendKeys(By locator, CharSequence... keys) {

			waitForVisibility(locator).sendKeys(keys);
		}

		// =========================================================
		// 5. SELECT / DROPDOWN METHODS
		// =========================================================

		private Select getSelect(By locator) {

			return new Select(waitForVisibility(locator));
		}

		public void selectByVisibleText(By locator, String text) {

			getSelect(locator).selectByVisibleText(text);
		}

		public void selectByValue(By locator, String value) {

			getSelect(locator).selectByValue(value);
		}

		public void selectByIndex(By locator, int index) {

			getSelect(locator).selectByIndex(index);
		}

		public void deselectByVisibleText(By locator, String text) {

			getSelect(locator).deselectByVisibleText(text);
		}

		public void deselectAll(By locator) {

			getSelect(locator).deselectAll();
		}

		public boolean isMultiple(By locator) {

			return getSelect(locator).isMultiple();
		}

		// =========================================================
		// 6. ALERT METHODS
		// =========================================================

		public void acceptAlert() {

			wait.until(ExpectedConditions.alertIsPresent()).accept();
		}

		public void dismissAlert() {

			wait.until(ExpectedConditions.alertIsPresent()).dismiss();
		}

		public String getAlertText() {

			return wait.until(ExpectedConditions.alertIsPresent()).getText();
		}

		public void enterAlertText(String text) {

			Alert alert = wait.until(ExpectedConditions.alertIsPresent());

			alert.sendKeys(text);
		}

		// =========================================================
		// 7. FRAME METHODS
		// =========================================================

		public void switchToFrame(By locator) {

			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
		}

		public void switchToFrame(int index) {

			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
		}

		public void switchToFrame(String nameOrId) {

			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrId));
		}

		public void switchToFrame(WebElement frame) {

			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
		}

		public void switchToParentFrame() {

			driver.switchTo().parentFrame();
		}

		public void switchToDefaultContent() {

			driver.switchTo().defaultContent();
		}

		// =========================================================
		// 8. WINDOW HANDLING
		// =========================================================

		public String getParentWindow() {

			return driver.getWindowHandle();
		}

		public Set<String> getAllWindowHandles() {

			return driver.getWindowHandles();
		}

		public void switchToWindow(String windowHandle) {

			driver.switchTo().window(windowHandle);
		}

		public void switchToWindowByTitle(String expectedTitle) {

			Set<String> windowHandles = driver.getWindowHandles();

			for (String handle : windowHandles) {

				driver.switchTo().window(handle);

				if (driver.getTitle().contains(expectedTitle)) {
					return;
				}
			}

			throw new RuntimeException("Window with title '" + expectedTitle + "' not found");
		}

		public void switchToWindowByUrl(String expectedUrl) {

			Set<String> windowHandles = driver.getWindowHandles();

			for (String handle : windowHandles) {

				driver.switchTo().window(handle);

				if (driver.getCurrentUrl().contains(expectedUrl)) {
					return;
				}
			}

			throw new RuntimeException("Window with URL '" + expectedUrl + "' not found");
		}
		
		public void switchToWindowById(String parentId) {

			Set<String> windowHandles = driver.getWindowHandles();

			for (String handle : windowHandles) 
			{
                  if (!handle.equals(parentId))
                 driver.switchTo().window(handle);
                  
                  
			}
		}

			

		// =========================================================
		// 9. ACTIONS CLASS METHODS
		// =========================================================

		public void mouseHover(By locator) {

			actions.moveToElement(waitForVisibility(locator)).perform();
		}
		
		public void mouseHover(WebElement ele) {

			actions.moveToElement(waitForVisibility(ele)).perform();
		}

		public void doubleClick(By locator) {

			actions.doubleClick(waitForVisibility(locator)).perform();
		}

		public void rightClick(By locator) {

			actions.contextClick(waitForVisibility(locator)).perform();
		}

		public void clickAndHold(By locator) {

			actions.clickAndHold(waitForVisibility(locator)).perform();
		}

		public void dragAndDrop(By source, By target) {

			actions.dragAndDrop(waitForVisibility(source), waitForVisibility(target)).perform();
		}

		public void moveByOffset(int x, int y) {

			actions.moveByOffset(x, y).perform();
		}

		public void pressKey(Keys key) {

			actions.sendKeys(key).perform();
		}

		// =========================================================
		// 10. JAVASCRIPT METHODS
		// =========================================================

		public void javascriptClick(By locator) {

			WebElement element = waitForPresence(locator);

			js.executeScript("arguments[0].click();", element);
		}

		public void scrollToElement(By locator) {

			WebElement element = waitForPresence(locator);

			js.executeScript("arguments[0].scrollIntoView(true);", element);
		}

		public void scrollToBottom() {

			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		}

		public void scrollToTop() {

			js.executeScript("window.scrollTo(0, 0)");
		}

		public void scrollByPixels(int x, int y) {

			js.executeScript("window.scrollBy(arguments[0], arguments[1])", x, y);
		}

		public void javascriptEnterText(By locator, String text) {

			WebElement element = waitForPresence(locator);

			js.executeScript("arguments[0].value=arguments[1];", element, text);
		}

		// =========================================================
		// 11. ELEMENT STATE / ATTRIBUTE
		// =========================================================

		public String getDomAttribute(By locator, String attribute) {

			return waitForPresence(locator).getDomAttribute(attribute);
		}

		public String getDomProperty(By locator, String property) {

			return waitForPresence(locator).getDomProperty(property);
		}

		public String getCssValue(By locator, String property) {

			return waitForPresence(locator).getCssValue(property);
		}

		// =========================================================
		// 12. PAGE LOAD / DOCUMENT METHODS
		// =========================================================

		public void waitForPageLoad() {

			new WebDriverWait(driver, Duration.ofSeconds(30))
					.until(webDriver -> js.executeScript("return document.readyState").equals("complete"));
		}

		// =========================================================
		// 13. GENERIC WAIT
		// =========================================================

		public void waitForSeconds(int seconds) {

			try {
				Thread.sleep(seconds * 1000L);
			} catch (InterruptedException e) {

				Thread.currentThread().interrupt();

				throw new RuntimeException(e);
			}
		}
	}



