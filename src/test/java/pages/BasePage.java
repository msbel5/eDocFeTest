package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class BasePage {

    public static WebDriver driver;
    public WebDriverWait wait;
    public Actions action;
    public File downloadDirectory = new File(System.getProperty("user.dir") + File.separator + "downloadedFiles");


    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'is-page-loading off ')]")
    private WebElement loadingPageOff;


    public void setDriver(WebDriver driver) {
        BasePage.driver = driver;
        wait = new WebDriverWait(BasePage.driver,2);
        action = new Actions(BasePage.driver);
    }


    public void waitLoading() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'is-page-loading off ')]")));
    }


    public Boolean isFileDownloaded() throws InterruptedException {
        Thread.sleep(1000);
        int numberOfFiles = downloadDirectory.listFiles().length;
        if (numberOfFiles > 0) {
            for (File f : downloadDirectory.listFiles()) f.delete();
            return true;
        } else {
            return false;
        }
    }

    public int getSizeOfDownloadedFiles() throws InterruptedException {
        Thread.sleep(1000);
        int numberOfFiles = downloadDirectory.listFiles().length;
        if (numberOfFiles > 0) {
            int sizeOfFiles = 0;
            for (File f : downloadDirectory.listFiles()) {
                sizeOfFiles += f.length();
                f.delete();
            }
            return sizeOfFiles;
        } else {
            return 0;
        }
    }

    public String checkMessage() {
        String message = driver.findElement(By.xpath("//h4[contains(@class, 'c0136')]")).getText();
        return message;
    }

    public String getCurrentURLTrail() {
        String url = driver.getCurrentUrl();
        int charIndex = url.lastIndexOf('/');
        if (charIndex + 1 >= url.length()) charIndex -= 1;
        String urlTrail = url.substring(charIndex + 1);
        return urlTrail;
    }

    public static Boolean isAlertPresent(WebDriver driver) {
        try {
            driver.switchTo().alert();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void confirmAlert(WebDriver driver) {
        {
            driver.switchTo().alert().accept();
        }
    }

}
