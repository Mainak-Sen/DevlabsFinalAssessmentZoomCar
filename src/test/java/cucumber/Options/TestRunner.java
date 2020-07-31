package cucumber.Options;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
@CucumberOptions(dryRun=false,
features= {"src/test/java/features/"},
glue= "stepDefinition",monochrome=true,
plugin= {"pretty","html:target/ZoomCarReport"})
public class TestRunner extends AbstractTestNGCucumberTests{
}
