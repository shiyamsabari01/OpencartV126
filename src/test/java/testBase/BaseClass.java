package testBase;

import driverManager.DriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utilities.Constants;
import utilities.ReadConfigProperties;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class BaseClass {
    public Logger logger;

    @BeforeClass(groups = {"sanity", "regression", "master"})
    public void setup() {
        try {
            logger = LogManager.getLogger(this.getClass());
            ReadConfigProperties readConfigProperties = new ReadConfigProperties();
            readConfigProperties.loadProperties();
            DriverManager.loadBrowser();
            logger.info("Initializing browser");
            DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            DriverManager.getDriver().navigate().to(Constants.APP_URL);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @AfterClass(groups = {"sanity", "regression", "master"})
    public void tearDown() {
        DriverManager.getDriver().quit();
    }

    public String randomString() {
        String name = RandomStringUtils.secure().nextAlphabetic(6);
        return name;
    }

    public String randomAlphanumeric() {
        String alphanumeric = RandomStringUtils.secure().nextAlphanumeric(6);
        return alphanumeric;
    }

    public String captureScreen(String tname) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String filePath = System.getProperty("user.dir") + "\\screenShots\\" + tname + "_" + timeStamp + ".png";
        File sourceFile = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(filePath);
        try {
            Files.copy(sourceFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }
}
