package zoomCar.Selenium.base;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumBase implements Browser, Element{


	protected static RemoteWebDriver driver;
	protected static WebDriverWait wait;
	protected static String PageTitle ;
	protected Actions builder;
	
	
	public void get_page_title() {
		PageTitle=driver.getTitle();
	}
	public void scroll_into_view(WebElement ele) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",ele);
	}
	public void scrollQuarter() {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)");
	}
	
	public void scrollHalf() {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");
	}
	public void scrollThreefourth() {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(500,750)");
	}
	
	public void highlight_element(WebElement ele) {
		((JavascriptExecutor)driver).executeScript("arguments[0].style.border='2px solid red'",ele);
	}
	public void scroll_until_bottom() {
		((JavascriptExecutor) driver)
	     .executeScript("window.scrollTo(750, document.body.scrollHeight)");
	}

	public void click(WebElement ele){
		try {
			wait.until(ExpectedConditions.visibilityOf(ele));
			if(ele.isDisplayed())
			{
			wait.until(ExpectedConditions.elementToBeClickable(ele));
	
				if(ele.isEnabled())
				{
					ele.click();
				}
				else
				{
					((JavascriptExecutor) driver).executeScript("arguments[0].click();",ele);
				}
			}
			System.out.println("The element: "+ele+" has been clicked  successfully ");
		} catch (TimeoutException e) {
		   System.err.println("Timeout Exception occured,retrying to click element: "+ele);
		   builder.moveToElement(ele).pause(2000).click().perform();
		   System.out.println("Successfully clicked after retrying");
		}
		catch (StaleElementReferenceException e) {
			System.err.println("The elment: "+ele+" to be clicked appears to be stale,refer screesnhot attached ");
			}
		catch (Exception e) {
			System.err.println("Exception occured while clicking element: "+ele+"\n"+e.getMessage());
			}
		 finally
		 {
			 File src=driver.getScreenshotAs(OutputType.FILE);
			   try {
				FileHandler.copy(src, new File("./Screenshots/clickSnap.png"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.err.println("I/O Exception occured whie trying to copy screenshot file: "+"\n"+e1.getMessage());
			}
		 }
	}

	public void append(WebElement ele, String data) {
		// TODO Auto-generated method stub
		try {
			if(data.equals(null))
			{
				throw new IllegalArgumentException("Keys to send cannot be null");
			}
			else
			{
				ele.sendKeys(data);
				System.err.println("The data: "+data+" is successfully entered in the webElement: "+ele);
			}
		}
        catch(IllegalArgumentException e)
		{
        	System.err.println("IllegalArgumentException occured with data: "+data+"\n"+e.getMessage());
		}
		catch(ElementNotInteractableException e)
		{
			System.err.println("ElementNotInteractableException occured with element: "+ele+"\n"+e.getMessage());
		}
		catch(Exception e)
		{
			System.err.println("Exception occured while entering data:"+data+"into element: "+ele+"\n"+e.getMessage());
		}
	}

	public void clear(WebElement ele) {
		// TODO Auto-generated method stub
		try
		{
			ele.clear();
			System.out.println("The element "+ele+" content cleared successfully");
		}
		catch(InvalidElementStateException e)
		{
			System.err.println("InvalidElementStateException occured while trying to clear "+ele+" content");
		}
		catch(Exception e)
		{
			System.err.println("Exception occured while clearing element "+ele+" "+"\n"+e.getMessage());
		}
		

	}

	public void clearAndType(WebElement ele, String data) {
		try {
			ele.clear();
			System.out.println("Element: "+ele+" "+"content cleared successfully");
			ele.sendKeys(data);
			if(data.equals(null))
			{
				throw new IllegalArgumentException("Keys to send cannot be null");
			}
			else
			{
				ele.sendKeys(data);
				System.err.println("The data: "+data+" is successfully entered in the webElement: "+ele);
			}
		}
        catch(IllegalArgumentException e)
		{
        	System.err.println("IllegalArgumentException occured with data: "+data+"\n"+e.getMessage());
		}
		catch(ElementNotInteractableException e)
		{
			System.err.println("ElementNotInteractableException occured with element: "+ele+"\n"+e.getMessage());
		}
		catch(Exception e)
		{
			System.err.println("Exception occured while clearing and entering data:"+data+"into element: "+ele+"\n"+e.getMessage());
		}
		finally
		{
			File src=driver.getScreenshotAs(OutputType.FILE);
			   try {
				FileHandler.copy(src, new File("./Screenshots/clearndType.png"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.err.println("I/O Exception occured whie trying to copy screenshot file: "+"\n"+e1.getMessage());
			}
		}
	}

	public String getElementText(WebElement ele) {
		// TODO Auto-generated method stub
		String text="";
		try {
			text=ele.getText();
			System.out.println("Element "+ele+" text: "+text+"extracted successfully" );
		}
		catch(Exception e)
		{
			System.err.println("Exception occured while extracting text from element: "+ele+"\n"+e.getMessage());
		}
		return text;
	}

	public String getBackgroundColor(WebElement ele) {
		// TODO Auto-generated method stub
		String CssValue=null;
		try{
		CssValue=ele.getCssValue("background-color");
		System.out.println("The background color of the element "+ele+" is"+CssValue);
		}
		catch(Exception e)
		{
			System.err.println("CSS value couldn't be extracted for element "+ele+"\n"+e.getMessage());
		}
		
		return CssValue;
	}

	public String getTypedText(WebElement ele) {
		// TODO Auto-generated method stub
		String propertyValue=null;
		try {
			propertyValue=ele.getAttribute("value");
			System.out.println("Value of the attribute extracted is: "+propertyValue);
		}
		catch(Exception e)
		{
			System.err.println("Typed text attribute couldnt be found for element: "+ele+"\n"+e.getMessage());
		}
		return propertyValue;
	}

	public void selectDropDownUsingText(WebElement ele, String value) {
		// TODO Auto-generated method stub
		try {
		Select s=new Select(ele);
		s.selectByVisibleText(value);
		System.out.println("Successfully selected the dropdown with visible text: "+value+"for dropdown element "+ele);
		}
		catch(Exception e)
		{
			System.err.println("Not able to select dropdown with visibe-text "+value+"\n"+e.getMessage());
		}

	}

	public void selectDropDownUsingIndex(WebElement ele, int index) {
		// TODO Auto-generated method stub
		try {
			Select s=new Select(ele);
			s.selectByIndex(index);
			System.out.println("Successfully selected the dropdown with index: "+index+"for dropdown element "+ele);
			}
			catch(Exception e)
			{
				System.err.println("Not able to select dropdown with index "+index+"\n"+e.getMessage());
			}

	}

	public void selectDropDownUsingValue(WebElement ele, String value) {
		// TODO Auto-generated method stub
		try {
			Select s=new Select(ele);
			s.selectByValue(value);
			System.out.println("Successfully selected the dropdown with value: "+value+"for dropdown element "+ele);
			}
			catch(Exception e)
			{
				System.err.println("Not able to select dropdown with value: "+value+"\n"+e.getMessage());
			}

	}

	public boolean verifyExactText(WebElement ele, String expectedText) {
		// TODO Auto-generated method stub
		try
		{String actual_text=ele.getText();
		if(actual_text.equals(expectedText))
		{
			System.out.println(expectedText+" matched with "+actual_text);
			return true;
		}
		else
		{
		    System.out.println("Expected: "+expectedText+"but found"+actual_text);
		}
		}
		catch(Exception e)
		{
			System.err.println("Verifying element with text: "+expectedText+"failed"+"\n"+e.getMessage());
		}
		return false;
	}

	public boolean verifyPartialText(WebElement ele, String expectedText) {
		// TODO Auto-generated method stub
		try
		{String actual_text=ele.getText();
		if(actual_text.contains(expectedText))
		{
			System.out.println(expectedText+" found in "+actual_text);
			return true;
		}
		else
		{
		    System.out.println("Expected: "+expectedText+"not found in"+actual_text);
		}
		}
		catch(Exception e)
		{
			System.err.println("Verifying element with partial text: "+expectedText+"failed"+"\n"+e.getMessage());
		}
		return false;
	}

	public boolean verifyExactAttribute(WebElement ele, String attribute, String value) {
		// TODO Auto-generated method stub
		try
		{String ActualValue=ele.getAttribute(attribute);
		if(ActualValue.equals(value))
		{
			System.out.println(attribute+" Expected value: "+value+" matched with "+" actual value "+ActualValue);
			return true;
		}
		else
		{
			System.out.println(attribute+" Expected value: "+value+" did not match with "+" actual value "+ActualValue);
		}
		}
		catch(Exception e)
		{
			System.err.println("Exception occured while verifying exact attibute text of attribute:"+attribute+"\n"+e.getMessage());
		}
		return false;
	}

	public boolean verifyPartialAttribute(WebElement ele, String attribute, String value) {
		// TODO Auto-generated method stub
		try
		{String ActualValue=ele.getAttribute(attribute);
		if(ActualValue.contains(value))
		{
			System.out.println(attribute+" Expected value: "+value+" found in "+" actual value "+ActualValue);
			return true;
		}
		else
		{
			System.out.println(attribute+" Expected value: "+value+" not found in  "+" actual value "+ActualValue);
		}
		}
		catch(Exception e)
		{
			System.err.println("Exception occured while verifying partial attibute text of attribute:"+attribute+"\n"+e.getMessage());
		}
		return false;

	}

	public boolean verifyDisplayed(WebElement ele) {
		// TODO Auto-generated method stub
		try
		{if(ele.isDisplayed())
		{
			System.out.println("Element "+ele+" is displayed in DOM");
			return true;
		}
		else
		{
			System.out.println("Element "+ele+" is not displayed in DOM");
		}
		}
		catch(Exception e)
		{
			System.err.println("Exception occured while checking visibility of element "+ele+"\n"+e.getMessage());
		}
		return false;
	}

	public boolean verifyDisappeared(WebElement ele) {
		// TODO Auto-generated method stub
		try
		{boolean is_disappeared=wait.until(ExpectedConditions.invisibilityOf(ele));
		System.out.println("waiting for element "+ele+" to disappear");
		return is_disappeared;
		}
		catch(TimeoutException e)
		{
			System.err.println("Element "+ele+" did not disappear"+"\n"+e.getMessage());
		}
		catch(Exception e)
		{
			System.err.println("Element "+ele+" did not disappear"+"\n"+e.getMessage());
		}
		return false;
	}

	public boolean verifyEnabled(WebElement ele) {
		// TODO Auto-generated method stub
		try
		{
			if(ele.isEnabled())
			{
				System.out.println("Element "+ele+" is enabled");
				return true;
			}
			else
			{
				System.out.println("Element "+ele+" is not enabled");
			}
		}
		catch(Exception e)
		{
			System.err.println("Exception occured while checking if element "+ele+" is enabled"+"\n"+e.getMessage());
		}
		return false;
	}

	public boolean verifySelected(WebElement ele) {
		// TODO Auto-generated method stub
		try
		{
			if(ele.isSelected())
			{
				System.out.println("Element "+ele+" is selected");
				return true;
			}
			else
			{
				System.out.println("Element "+ele+" is not selected");
			}
		}
		catch(Exception e)
		{
			System.err.println("Exception occured while checking if element "+ele+" is selected"+"\n"+e.getMessage());
		}
		return false;
	}

	public void startApp(String url) {
		try {
			ChromeOptions options = new ChromeOptions(); 
			options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
			System.setProperty("webdriver.chrome.driver","./Drivers/chromedriver.exe");
			SeleniumBase.driver = new ChromeDriver(options);
			wait=new WebDriverWait(driver,30);
			builder=new Actions(driver);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(url);
			System.out.println("The browser has been launched");
		}
		catch (Exception e) {
			System.err.println("Browser could not launch "+e.getMessage());
		}
	}

	public void startApp(String browser, String url) {
		// TODO Auto-generated method stub
	    try{
			if(browser.equalsIgnoreCase("chrome"))
		
		{
		System.setProperty("webdriver.chrome.driver","./Drivers/drivers/chromedriver.exe");
		SeleniumBase.driver= new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver","./Drivers/drivers/geckodriver.exe");
			SeleniumBase.driver= new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.ie.driver","./Drivers/drivers/IEDriverServer.exe");
			SeleniumBase.driver= new InternetExplorerDriver();
		}
		wait=new WebDriverWait(driver,30);
		//js=(JavascriptExecutor)driver;
		builder=new Actions(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(url);
		System.out.println("The browser has been launched");
	}
	catch(Exception e) {
		System.err.println("Browser could not launch "+e.getMessage());
	}
	
	}

	public WebElement findElementBy(Locators locator_type, String value) {
		try {
			switch (locator_type) {
			case ID:
				return driver.findElement(By.id(value));
			case CLASS_NAME:
				return	driver.findElement(By.className(value));
			case XPATH:
				return driver.findElement(By.xpath(value));
			case NAME:
				return	driver.findElement(By.name(value));
			case CSS_SELECTOR:
				return driver.findElement(By.cssSelector(value));
			case LINK_TEXT:
				return	driver.findElement(By.linkText(value));
			case PARTIAL_LINK_TEXT:
				return	driver.findElement(By.partialLinkText(value));
			case TAG_NAME:
				return driver.findElement(By.tagName(value));
			default:
				System.err.println("Locator type is wrong");
				break;
			}
		} 
		catch (NoSuchElementException e) {
			System.err.println("Element not found");
		}
		catch (Exception e) {
			System.err.println("Element with locator: "+locator_type+"not found with value: "+value+"\n"+e.getMessage());
		}
		return null;
		
	}

	public List<WebElement> findElementsBy(Locators locator_type, String value) {
		// TODO Auto-generated method stub
			try{
				
		    switch (locator_type) {
			
			case ID:
				return driver.findElements(By.id(value));
			case CLASS_NAME:
				return	driver.findElements(By.className(value));
			case XPATH:
				return driver.findElements(By.xpath(value));
			case NAME:
				return	driver.findElements(By.name(value));
			case CSS_SELECTOR:
				return driver.findElements(By.cssSelector(value));
			case LINK_TEXT:
				return	driver.findElements(By.linkText(value));
			case PARTIAL_LINK_TEXT:
				return	driver.findElements(By.partialLinkText(value));
			case TAG_NAME:
				return driver.findElements(By.tagName(value));
			default:
				System.err.println("Locator type is wrong");
				break;
			}
			}
			catch (Exception e) {
				System.err.println("Element with locator: "+locator_type+"not found with value: "+value+"\n"+e.getMessage());
			}
		return null;
	}

	public void switchToAlert() {
		// TODO Auto-generated method stub
		try{
			driver.switchTo().alert();
			System.out.println("Switched to Alert window");
		}
		catch(NoAlertPresentException e)
		{
	    	System.err.println("No such alert is found");
		}
		catch(Exception e)
		{
			System.err.println("Exception occured:"+e.getMessage());
		}
		

	}

	public void acceptAlert() {
		// TODO Auto-generated method stub
		try{
			driver.switchTo().alert().accept();
			System.out.println("Switched to Alert window and accepted the same");
		}
		catch(NoAlertPresentException e)
		{
	    	System.err.println("No such alert is found");
		}
		catch(Exception e)
		{
			System.err.println("Exception occured:"+e.getMessage());
		}

	}

	public void dismissAlert() {
		// TODO Auto-generated method stub
		try{
			driver.switchTo().alert().dismiss();
			System.out.println("Switched to Alert window and dismissed the same");
		}
		catch(NoAlertPresentException e)
		{
	    	System.err.println("No such alert is found");
		}
		catch(Exception e)
		{
			System.err.println("Exception occured:"+e.getMessage());
		}


	}

	public String getAlertText() {
		// TODO Auto-generated method stub
		String text="";
		try{
			text= driver.switchTo().alert().getText();
			System.out.println("The text present in the alert is: "+text);
		}
		catch(NoAlertPresentException e)
		{
	    	System.err.println("No such alert is found");
		}
		catch(Exception e)
		{
			System.err.println("Exception occured:"+e.getMessage());
		}
		return text;
	}

	public void typeAlert(String data) {
		// TODO Auto-generated method stub
		try{
			driver.switchTo().alert().sendKeys(data);;
			System.out.println("Successfully typed the data in the alert window");
		}
		catch(NoAlertPresentException e)
		{
	    	System.err.println("No such alert is found");
		}
		catch(Exception e)
		{
			System.err.println("Exception occured:"+e.getMessage());
		}

	}

	public void switchToWindow(int index) {
		// TODO Auto-generated method stub
		try{
		Set<String> window_handles=driver.getWindowHandles();
		List<String> window_handles_list=new ArrayList<String>();
		window_handles_list.addAll(window_handles);
		if(index>0)
		{
			wait.until(ExpectedConditions.numberOfWindowsToBe(index+1));
		}
		String title =driver.switchTo().window(window_handles_list.get(index)).getTitle();
		System.out.println("Successfully switched to the desired window index: "+index);
		System.out.println("The title of the current window is: "+title);	
		}
		catch(NoSuchWindowException e)
		{
			System.err.println("The window with index: "+index+" was not found");
		}
		catch(Exception e)
		{
			System.err.println("Exception occured: "+e.getMessage());
		}

	}

	public void switchToWindow_withExacttitle(String exact_title) {
		// TODO Auto-generated method stub
		try
		{Set<String> window_handles=driver.getWindowHandles();
		String parent_window=driver.getWindowHandle();
		for(String window: window_handles)
		{
			driver.switchTo().window(window);
			if(driver.switchTo().window(window).getTitle().equals(exact_title))
			{
				break;
			}
		}
		System.out.println("Successfully switched to window with title: "+exact_title);
		}
		catch(NoSuchWindowException e)
		{
			System.err.println("The window with title: "+exact_title+" was not found");
		}
		catch(Exception e)
		{
			System.err.println("Exception occured: "+e.getMessage());
		}

	}
	

	public void switchToWindow_withPartialtitle(String partial_title) { 
		// TODO Auto-generated method stub
		try
		{Set<String> window_handles=driver.getWindowHandles();
		String parent_window=driver.getWindowHandle();
		for(String window: window_handles)
		{
			if(driver.switchTo().window(window).getTitle().contains(partial_title))
			{
				break;
			}
			else
			{
				driver.switchTo().window(parent_window);
			}
		}
		System.out.println("Successfully switched to window with the partial title: "+partial_title);
		}
		catch(NoSuchWindowException e)
		{
			System.err.println("The window with partial title: "+partial_title+" was not found");
		}
		catch(Exception e)
		{
			System.err.println("Exception occured: "+e.getMessage());
		}

	}

	public void switchToFrame(int index) {
		 try{
			 Thread.sleep(5000);
			 //wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
			 driver.switchTo().frame(index);
			 System.out.println("Successfully switched to frame with index: "+index);
		    }
		 catch(NoSuchFrameException e )
		 {
			 System.err.println("No such frame found with frame index: "+index);
		 }
		 catch(Exception e)
		 {
			 System.err.println("Exception occured: "+e.getMessage());
		 }

	}

	public void switchToFrame(WebElement ele) {
		// TODO Auto-generated method stub
		try
		{
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(ele));
		   driver.switchTo().frame(ele);
		   System.out.println("Successfully switched to frame with WebElement: "+ele);
		}
		catch(NoSuchFrameException e)
		{
			System.err.println("No such frame found with the given WebElement: "+ele);
		}
		catch(StaleElementReferenceException e)
		{
			System.err.println("The element "+ele+" with which you are trying to locate frame appears to be stale");
		}
		catch(Exception e)
		{
			System.err.println("Exception occured: "+e.getMessage());
		}
		

	}

	public void switchToFrame(String idOrName) {
		// TODO Auto-generated method stub
		try
		{
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idOrName));
		   driver.switchTo().frame(idOrName);
		   System.out.println("Successfully switched to frame with id/name: "+idOrName);
		}
		catch(NoSuchFrameException e)
		{
			System.err.println("No such frame found with id/name : "+idOrName);
		}
		catch(Exception e)
		{
			System.err.println("Exception occured: "+e.getMessage());
		}

	}

	public void defaultContent() {
		// TODO Auto-generated method stub
		try
		{driver.switchTo().defaultContent();
		System.out.println("Successfully switched to the first or the default window");
		}
		catch(Exception e)
		{
			System.err.println("No such window found "+ e.getMessage());
		}
	}

	public boolean verifyUrl(String url) {
		// TODO Auto-generated method stub
		
	    if(driver.getCurrentUrl().equals(url))
		{   
			System.out.println("The given url: "+url+" matched successfully");
			return true;
		}
		else
		{
			System.err.println("Failed to match with the given url: "+url);
			return false;
		}
		
	}

	public boolean verifyTitle(String title) {
		// TODO Auto-generated method stub
		if(driver.getTitle().equals(title))
		{   
			System.out.println("The given title: "+title+" matched successfully");
			return true;
		}
		else
		{
			System.err.println("Failed to match with the given title: "+title);
			return false;
		}
	}

	public void close() {
		// TODO Auto-generated method stub
		try{
			driver.close();
			System.out.println("Successfully closed the active browser");
		}
		catch(Exception e)
		{
			System.err.println("Browser cannot be closed: "+ e.getMessage());
		}

	}

	public void quit() {
		// TODO Auto-generated method stub
		try{
			driver.quit();
			System.out.println("Successfully closed all the browsers");
		}
		catch(Exception e)
		{
			System.err.println("Browsers cannot be closed: "+ e.getMessage());
		}

	}



}
