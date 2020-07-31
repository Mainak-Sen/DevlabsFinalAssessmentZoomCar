package pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;

import zoomCar.Selenium.base.Locators;
import zoomCar.Selenium.base.SeleniumBase;

public class DropOffDatePage extends SeleniumBase{
	
private String daysCss="div.day"; 
private String donebtnCss=".proceed";
private String DropOffTimexpath="//div[text()='DROP OFF TIME']";

public boolean is_DropOffTime_diplayed() {
	return verifyDisplayed(findElementBy(Locators.valueOf("XPATH"),DropOffTimexpath));
}

public DropOffDatePage click_last_day() {
	List<WebElement> days=findElementsBy(Locators.valueOf("CSS_SELECTOR"),daysCss);
	for(int i=0;i<days.size();i++)
	{
		if(i==days.size()-1)
		{
			click(days.get(i));
		}
	}
	return this;
}


public CarsPage click_Done() throws InterruptedException {
	click(findElementBy(Locators.valueOf("CSS_SELECTOR"),donebtnCss));
	Thread.sleep(5000);
	return new CarsPage();
}

}
