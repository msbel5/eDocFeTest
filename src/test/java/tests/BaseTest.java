package tests;

import mocks.BaseMock;
import org.mockserver.configuration.ConfigurationProperties;
import org.mockserver.integration.ClientAndServer;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MINUTES;

public class BaseTest {

    public WebDriver driver;
    BasePage basePage;


    @BeforeMethod
    public void driver() {
        System.setProperty("webdriver.chrome.driver", "src" + File.separator + "test" + File.separator + "resources" + File.separator + "drivers" + File.separator + "chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("download.default_directory", System.getProperty("user.dir") + File.separator + "downloadedFiles");
        chromeOptions.setExperimentalOption("prefs", prefs);
        chromeOptions.addArguments("--ignore-certificate-errors");
        driver = new ChromeDriver(chromeOptions);
        basePage = PageFactory.initElements(driver, BasePage.class);
        basePage.setDriver(driver);
        if (!basePage.downloadDirectory.exists()) {
            basePage.downloadDirectory.mkdir();
        } else {
            for (File f : basePage.downloadDirectory.listFiles()) f.delete();
        }
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //driver.get("http://192.168.1.35:8080/home.jsp");
        //basePage.waitLoading();
    }

    //@AfterMethod(alwaysRun = true)
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    public void mockPage(){
        ConfigurationProperties.logLevel("DEBUG");
        new ClientAndServer(3001);
        new BaseMock().responseLiteralWithBodyOnly();
        driver.get("http://192.168.1.35:8080/home.jsp");
        basePage.waitLoading();
    }

}
