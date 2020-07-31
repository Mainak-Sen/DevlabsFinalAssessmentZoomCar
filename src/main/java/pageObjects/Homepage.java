package pageObjects;

import zoomCar.Selenium.base.Locators;
import zoomCar.Selenium.base.SeleniumBase;

public class Homepage extends SeleniumBase{

private  String start_journey_xpath="//a[@class='search']";

public Start_Point_Page click_search() {
	click(findElementBy(Locators.valueOf("XPATH"),start_journey_xpath));
	return new Start_Point_Page();
}
}
