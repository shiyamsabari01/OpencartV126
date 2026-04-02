package driverManager;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utilities.Constants;
import utilities.ReadConfigProperties;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {
    public static WebDriver getDriver() {
        return driver;
    }

    private static WebDriver driver = null;
    public static DesiredCapabilities capabilities = new DesiredCapabilities();

    public static void loadBrowser() throws MalformedURLException {
        if (Constants.OS.equalsIgnoreCase("linux")) {
            capabilities.setPlatform(Platform.LINUX);
        } else {
            capabilities.setPlatform(Platform.WIN11);
        }

        switch (Constants.BROWSER.toLowerCase()) {
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximized");
                if (Constants.EXECUTION_ENV.equalsIgnoreCase("remote")) {
                    driver = new RemoteWebDriver(new URL(Constants.GRID_URL), firefoxOptions);
                } else {
                    driver = new FirefoxDriver(firefoxOptions);
                }
                break;

            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                if (Constants.EXECUTION_ENV.equalsIgnoreCase("remote")) {
                    driver = new RemoteWebDriver(new URL(Constants.GRID_URL), chromeOptions);
                } else {
                    driver = new ChromeDriver(chromeOptions);
                }
                break;

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                if (Constants.EXECUTION_ENV.equalsIgnoreCase("remote")) {
                    driver = new RemoteWebDriver(new URL(Constants.GRID_URL), edgeOptions);
                } else {
                    driver = new EdgeDriver(edgeOptions);
                }

                break;
            default:
                System.out.println("Please use valid browser!!!!!");
        }
    }
}
