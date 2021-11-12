package cxpro_tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class Selen {

    private static ChromeDriverService service;
    public static WebDriver driver;
    public static WebDriverWait wait;

    public static void startService() {
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("drivers/chromedriver.exe"))
                .usingAnyFreePort()
                .build();
        try {
            service.start();
        }
        catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void stopService() {
        service.stop();
    }

    public static void startBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.setCapability("applicationCacheEnabled", false);
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-gpu");
        //options.addArguments("--headless");
        driver = new RemoteWebDriver(service.getUrl(), options);
    }

    public static void stopBrowser() {
        driver.quit();
    }
}
