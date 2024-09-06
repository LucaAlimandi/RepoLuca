package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features/restassured", glue = "steps/restassured", plugin = { "json:target/restassured.json",
                "pretty","io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"})
                
public class RestAssuredTestRunner extends AbstractTestNGCucumberTests {
}