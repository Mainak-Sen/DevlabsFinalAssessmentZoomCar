package pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;

import zoomCar.Selenium.base.Locators;
import zoomCar.Selenium.base.SeleniumBase;

public class Start_Point_Page extends SeleniumBase{
	
private String pick_up_point_list_css="div.items";
private String next_btn_xpath="//button[text()='Next']";
private String starting_pointXpath="//div[text()='STARTING POINT']";

public boolean is_StartingPoint_diplayed() {
	return verifyDisplayed(findElementBy(Locators.valueOf("XPATH"),starting_pointXpath));
}

public Start_Point_Page select_pick_up_point(String place) {
	List<WebElement> pick_up_point_list=findElementsBy(Locators.valueOf("CSS_SELECTOR"),pick_up_point_list_css);
	for(WebElement each: pick_up_point_list)
	{
		if(getElementText(each).equalsIgnoreCase(place))
		{
			click(each);
			break;
		}
	}
	return this;
}

public PickUpDatePage click_next() {
	click(findElementBy(Locators.valueOf("XPATH"),next_btn_xpath));
	return new PickUpDatePage();
}

}
