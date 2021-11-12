package cxpro_tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverService;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.service.DriverService;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.Optional;

public class Selen {

    private static DriverService service;
    public static WebDriver driver;
    public static WebDriverWait wait;

    public static void startService(String browser) {
        try {
            switch (browser) {
                case "chrome":
                    service = new ChromeDriverService.Builder()
                            .usingDriverExecutable(new File("drivers/chromedriver.exe"))
                            .usingAnyFreePort()
                            .build();
                    service.start();
                    break;
                case "firefox":
                    System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
                    break;
                default:
                    break;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void stopService() {
        if (service != null) service.stop();
    }

    public static void startBrowser(String browser) {
        switch (browser) {
            case "chrome":
                ChromeOptions ch = new ChromeOptions();
                ch.setCapability("applicationCacheEnabled", false);
                ch.addArguments("--disable-extensions");
                ch.addArguments("--disable-gpu");
                //options.addArguments("--headless");
                driver = new RemoteWebDriver(service.getUrl(), ch);
                break;
            case "firefox":
                FirefoxOptions ff = new FirefoxOptions();
                ff.setCapability("applicationCacheEnabled", false);
                ff.addArguments("--headless");
                driver = new FirefoxDriver(ff);
                break;
            default:
                break;
        }
    }

    public static void stopBrowser() {
        driver.quit();
    }
}
