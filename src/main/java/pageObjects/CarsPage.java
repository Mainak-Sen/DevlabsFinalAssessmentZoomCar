package pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;

import zoomCar.Selenium.base.Locators;
import zoomCar.Selenium.base.SeleniumBase;

public class CarsPage extends SeleniumBase{

private String cars_availableCss=".book-car";
private String cars_priceCss=".car-amount div[class*='price']";
private String car_detailsCss=".details h3";

public void find_carCount(){
int car_count=findElementsBy(Locators.valueOf("CSS_SELECTOR"),cars_availableCss).size();
System.out.println("The number of available cars is: "+car_count);
}

public void get_max() throws InterruptedException {
int max_index = 0;
int max=Integer.MIN_VALUE;
String max_price="";
String max_car_name="";
List<WebElement> car_prices= findElementsBy(Locators.valueOf("CSS_SELECTOR"),cars_priceCss);
List<WebElement> car_details= findElementsBy(Locators.valueOf("CSS_SELECTOR"),car_detailsCss);
WebElement max_price_ele=null;
for(int i=0;i<car_prices.size();i++)
{
	String car_price=getElementText(car_prices.get(i)).replaceAll(",","").replaceAll("\\s+","");
	log.info("The extracted car price is:"+car_price);
	if(Integer.valueOf(car_price.substring(1))>max) {
		max=Integer.valueOf(car_price.substring(1));
		max_price_ele=car_prices.get(i)	;
	max_price=car_price;
	max_index=i;}
}
scroll_into_view(max_price_ele);
highlight_element(max_price_ele);
Thread.sleep(3000);
System.out.println("The max posible price of available cars is: "+max_price);
log.info("The max posible price of available cars is: "+max_price);
for(int j=0;j<car_details.size();j++)
{
	if(j==max_index) {max_car_name=getElementText(car_details.get(j));
	System.out.println("The car with the highest price is: "+max_car_name);
	log.info("The car with the highest price is: "+max_car_name);
	break;
	}
}
}
}
