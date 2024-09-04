package steps;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Step {
    protected WebDriver driver;

    public void setUp() throws MalformedURLException {
        String browser = System.getenv("BROWSER");
        String hubHost = System.getenv("HUB_HOST");
        // Default to Chrome Local execution
        if (browser == null) {
            browser = "firefox-local";
        }
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            driver = new RemoteWebDriver(new URL("http://" + hubHost + ":4444/wd/hub"), options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            driver = new RemoteWebDriver(new URL("http://" + hubHost + ":4444/wd/hub"), options);
        } else if (browser.equalsIgnoreCase("chrome-local")) {
            //System.setProperty("webdriver.chrome.driver", projectPath);
            WebDriverManager.chromedriver().clearDriverCache().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox-local")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Browser not supported: " + browser);
        }
    }


    public void tearDown(ITestResult result) {
        // Cattura screenshot in caso di fallimento del test
        // if (!result.isSuccess()) {
        //     String methodName = result.getMethod().getMethodName();
        //     ScreenshotUtil.takeScreenshot(driver, "target/screenshots/" + methodName + ".png");
        // }
        if (driver != null) {
            driver.quit();
        }
    }
}
