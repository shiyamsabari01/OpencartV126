package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testBase.BaseClass;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExtentReportManager implements ITestListener {
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extentReports;
    public ExtentTest extentTest;
    String repName;

    public void onStart(ITestContext context) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timeStamp + ".html";
        sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);
        sparkReporter.config().setDocumentTitle("OpenCart Ecommerce");
        sparkReporter.config().setReportName("OpenCart UI Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Application", "OpenCart");
        extentReports.setSystemInfo("Module", "OpenCartSite");
        extentReports.setSystemInfo("Username", System.getProperty("user.name"));
        extentReports.setSystemInfo("Environment", "QA");

        //Group name taken from xml file
        List<String> includeGroups = context.getCurrentXmlTest().getIncludedGroups();
        if (!includeGroups.isEmpty()) {
            extentReports.setSystemInfo("Groups", includeGroups.toString());
        }
    }

    public void onTestSuccess(ITestResult result) {
        extentTest = extentReports.createTest(result.getTestClass().getName());//Take which test class
        extentTest.assignCategory(result.getMethod().getGroups());//Take which test method
        extentTest.log(Status.PASS, result.getName() + " got successfully completed");
    }

    public void onTestFailure(ITestResult result) {
        extentTest = extentReports.createTest(result.getTestClass().getName());
        extentTest.assignCategory(result.getMethod().getGroups());
        extentTest.log(Status.FAIL, result.getName() + " got failed!!!");
        extentTest.log(Status.INFO, result.getThrowable().getMessage());

        try {
            String imgPath = new BaseClass().captureScreen(result.getName());
            extentTest.addScreenCaptureFromPath(imgPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result) {
        extentTest = extentReports.createTest(result.getTestClass().getName());
        extentTest.assignCategory(result.getMethod().getGroups());
        extentTest.log(Status.SKIP, result.getName() + " got skipped");
        extentTest.log(Status.INFO, result.getThrowable().getMessage());
    }

    public void onFinish(ITestContext context) {
        extentReports.flush();
        String pathFile = System.getProperty("user.dir") + "\\reports\\" + repName;
        File filePath = new File(pathFile);
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(filePath.toURI());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
