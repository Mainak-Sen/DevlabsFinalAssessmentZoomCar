package pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;

import zoomCar.Selenium.base.Locators;
import zoomCar.Selenium.base.SeleniumBase;

public class PickUpDatePage extends SeleniumBase{

//private String currentDatecss="div.day.picked";
private String tomorrowDatexpath="//div[contains(@class,'day') and contains(@class,'picked')]/following-sibling::div[1]";
private String daysCss="div.day";
private String next_btn_xpath="//button[text()='Next']";
private String pickUpTimexpath="//div[text()='PICKUP TIME']";

public boolean is_PickupPointdiplayed() {
	return verifyDisplayed(findElementBy(Locators.valueOf("XPATH"),pickUpTimexpath));
}

public PickUpDatePage select_tomorrow() {
		/*
		 * WebElement current_date_ele=findElementBy(Locators.valueOf("CSS_SELECTOR"),
		 * currentDatecss); String
		 * currentDate=getElementText(current_date_ele).split("\\s+")[1];
		 * 
		 * System.out.println(currentDate); List<WebElement>
		 * days=findElementsBy(Locators.valueOf("CSS_SELECTOR"),daysCss); for(WebElement
		 * day:days) { String actualdate=getElementText(day).split("\\s+")[1];
		 * if(actualdate.equals(String.valueOf((Integer.valueOf(currentDate)+1)))) {
		 * click(day); break; } }
		 */
	WebElement tomorrow_date_ele=findElementBy(Locators.valueOf("XPATH"),tomorrowDatexpath);
	click(tomorrow_date_ele);
	return this;
}
public DropOffDatePage click_next() {
	click(findElementBy(Locators.valueOf("XPATH"),next_btn_xpath));
	return new DropOffDatePage();
}

}
