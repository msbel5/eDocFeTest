package tests;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.ExcelPage;

import java.io.File;


public class ExcelTests extends BaseTest {

    //@Test
    public void cycleSheets() {
        ExcelPage excelPage = PageFactory.initElements(driver, ExcelPage.class);
        excelPage.setDriver(driver);

        for (int i = 2; i <= excelPage.numberOfSheets()  ; i++) {
            excelPage.goToSheetByNumber(i);
        }
    }


    //@Test
    public void cyclePages() {
        ExcelPage excelPage = PageFactory.initElements(driver, ExcelPage.class);
        excelPage.setDriver(driver);
        excelPage.goToNextPage();
        excelPage.goToPreviousPage();
    }

    //@Test
    public void checkSetting(){
        ExcelPage excelPage = PageFactory.initElements(driver, ExcelPage.class);
        excelPage.setDriver(driver);
        excelPage.openSettings();
        excelPage.closeSettings();
    }

    //@Test
    public void checkFullscreen() throws InterruptedException {
        ExcelPage excelPage = PageFactory.initElements(driver, ExcelPage.class);
        excelPage.setDriver(driver);
        excelPage.openSettings();
        excelPage.clickFullscreenButton();
        Thread.sleep(1000);
        excelPage.action.sendKeys(Keys.ESCAPE).perform();
    }

    //@Test
    public void checkFitToWidth(){
        ExcelPage excelPage = PageFactory.initElements(driver, ExcelPage.class);
        excelPage.setDriver(driver);
        excelPage.openSettings();
        excelPage.fitTOWidth();
    }

    //@Test
    public void checkFitToHeight(){
        ExcelPage excelPage = PageFactory.initElements(driver, ExcelPage.class);
        excelPage.setDriver(driver);
        excelPage.openSettings();
        excelPage.fitToHeight();
    }

    //@Test
    public void checkResetZoom() throws InterruptedException {
        ExcelPage excelPage = PageFactory.initElements(driver, ExcelPage.class);
        excelPage.setDriver(driver);
        excelPage.openSettings();
        excelPage.fitToHeight();
        Thread.sleep(500);
        excelPage.resetZoom();
        Thread.sleep(500);
        excelPage.fitTOWidth();
        Thread.sleep(500);
        excelPage.resetZoom();
    }

    //@Test
    public void checkZoom(){
        ExcelPage excelPage = PageFactory.initElements(driver, ExcelPage.class);
        excelPage.setDriver(driver);
        excelPage.openSettings();
        excelPage.setZoomWithKey(5, Keys.ARROW_LEFT);
        excelPage.setZoomWithKey(10, Keys.ARROW_RIGHT);
        excelPage.resetZoom();
    }

    //@Test
    public void checkDownload() throws InterruptedException {
        ExcelPage excelPage = PageFactory.initElements(driver, ExcelPage.class);
        excelPage.setDriver(driver);
        excelPage.openSettings();
        excelPage.downloadExcel();
        String expectedUrl = "http://192.168.1.35:3001/ms-preview/api/v2/preview/EDM/download/file/1/PPTX-Sample-02?StoreURI=/home/edoc/nas/data1/edoc/src/test/";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl,actualUrl);
    }


}
