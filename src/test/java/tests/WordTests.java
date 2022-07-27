package tests;

import mocks.BaseMock;
import org.mockserver.configuration.ConfigurationProperties;
import org.mockserver.integration.ClientAndServer;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.WordPage;

import java.util.List;

import static java.util.concurrent.TimeUnit.MINUTES;


public class WordTests extends BaseTest {


    //@Test
    public void cyclePages() {
        WordPage wordPage = PageFactory.initElements(driver, WordPage.class);
        wordPage.setDriver(driver);

        wordPage.goToPageByNumber(5);
        wordPage.goToNextPage();
        wordPage.goToPreviousPage();
        wordPage.goToLastPage();
        wordPage.goToFirstPage();
    }

    //@Test
    public void checkSetting(){
        WordPage wordPage = PageFactory.initElements(driver, WordPage.class);
        wordPage.setDriver(driver);
        wordPage.openSettings();
        wordPage.closeSettings();
    }

    //@Test
    public void checkFullscreen() throws InterruptedException {
        WordPage wordPage = PageFactory.initElements(driver, WordPage.class);
        wordPage.setDriver(driver);
        wordPage.openSettings();
        wordPage.clickFullscreenButton();
        Thread.sleep(1000);
        wordPage.action.sendKeys(Keys.ESCAPE).perform();
    }

    //@Test
    public void checkFitToWidth(){
        WordPage wordPage = PageFactory.initElements(driver, WordPage.class);
        wordPage.setDriver(driver);
        wordPage.openSettings();
        wordPage.fitTOWidth();
    }

    //@Test
    public void checkFitToHeight(){
        WordPage wordPage = PageFactory.initElements(driver, WordPage.class);
        wordPage.setDriver(driver);
        wordPage.openSettings();
        wordPage.fitToHeight();
    }

    //@Test
    public void checkResetZoom() throws InterruptedException {
        WordPage wordPage = PageFactory.initElements(driver, WordPage.class);
        wordPage.setDriver(driver);
        wordPage.openSettings();
        wordPage.fitToHeight();
        Thread.sleep(500);
        wordPage.resetZoom();
        Thread.sleep(500);
        wordPage.fitTOWidth();
        Thread.sleep(500);
        wordPage.resetZoom();
    }

    //@Test
    public void checkZoom(){
        WordPage wordPage = PageFactory.initElements(driver, WordPage.class);
        wordPage.setDriver(driver);
        wordPage.openSettings();
        wordPage.setZoomWithKey(5, Keys.ARROW_LEFT);
        wordPage.setZoomWithKey(10, Keys.ARROW_RIGHT);
        wordPage.resetZoom();
    }

    //@Test
    public void checkDownload() throws InterruptedException {
        WordPage wordPage = PageFactory.initElements(driver, WordPage.class);
        wordPage.setDriver(driver);
        wordPage.openSettings();
        wordPage.downloadWord();
        String expectedUrl = "http://192.168.1.35:3001/ms-preview/api/v2/preview/EDM/download/file/1/PPTX-Sample-02?StoreURI=/home/edoc/nas/data1/edoc/src/test/";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl,actualUrl);
    }



    @Test
    public void checkMockServer () throws InterruptedException {
        ConfigurationProperties.logLevel("DEBUG");
        new ClientAndServer(1081);
        new BaseMock().responseLiteralWithBodyOnly();
        driver.get("localhost:1081");
        Thread.sleep(15);
    }

}
