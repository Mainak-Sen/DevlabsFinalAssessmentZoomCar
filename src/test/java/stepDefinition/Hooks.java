package stepDefinition;

import org.openqa.selenium.OutputType;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import zoomCar.Selenium.base.SeleniumBase;

public class Hooks extends SeleniumBase{

@Before
public void BeforeScenario() {
	startApp("https://www.zoomcar.com/chennai/");
}

@After
public void AfterScenario() {
	quit();
}

@AfterStep
public void TakeScreesnhot(Scenario sc) {
	byte[] screenshot =driver.getScreenshotAs(OutputType.BYTES);
	sc.embed(screenshot,"image/png");
}

}
