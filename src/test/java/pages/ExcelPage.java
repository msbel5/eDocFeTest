package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ExcelPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//button[contains(@class, 'footer-excel-tab-button')]/..")
    private List<WebElement> excelSheets;

    @FindBy(how = How.XPATH, using = "//figure[contains(@class, 'chevron-left')]")
    private WebElement previousPage;

    @FindBy(how = How.XPATH, using = "//figure[contains(@class, 'chevron-right')]")
    private WebElement nextPage;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'settings-excel visible')]")
    private WebElement excelSettingsExpandButton;

    @FindBy(how = How.XPATH, using = "//figure[contains(@class, 'settings-excel-icon')][1]")
    private WebElement excelSettingsCollapseButton;

    @FindBy(how = How.XPATH, using = "//figure[contains(@class, 'settings-excel-icon')][2]")
    private WebElement fullscreenButton;

    @FindBy(how = How.XPATH, using = "//figure[contains(@class, 'settings-excel-icon')][3]")
    private WebElement downloadButton;

    @FindBy(how = How.XPATH, using = "//figure[contains(@class, 'settings-excel-icon')][4]")
    private WebElement fitToWidthButton;

    @FindBy(how = How.XPATH, using = "//figure[contains(@class, 'settings-excel-icon')][5]")
    private WebElement fitToHeightButton;

    @FindBy(how = How.XPATH, using = "//figure[contains(@class, 'settings-excel-icon')][6]")
    private WebElement resetZoomButton;

    @FindBy(how = How.XPATH, using = "//span[contains(@class, 'settings-excel-slider')]/input")
    private WebElement zoomSliderInput;

    @FindBy(how = How.XPATH, using = "//span[contains(@class, 'MuiSlider-thumb')]")
    private WebElement zoomSliderThumb;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'loading-right-bottom')]")
    private List<WebElement> loadingIcon;


    public void waitSheetLoading() {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//div[contains(@class, 'loading-right-bottom')]"), 0));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[contains(@class, 'loading-right-bottom')]"), 0));

    }

    public int numberOfSheets() {
        wait.until(ExpectedConditions.elementToBeClickable(excelSheets.get(0)));
        return excelSheets.size();
    }

    public void goToSheetByNumber(int sheetNumber) {

        if (sheetNumber < 1) {
            sheetNumber = 1;
        } else if (sheetNumber > numberOfSheets()) {
            sheetNumber = numberOfSheets();
        }

        excelSheets.get(sheetNumber - 1).click();
        waitSheetLoading();
    }

    public void goToNextPage() {
        nextPage.click();
        waitSheetLoading();
    }

    public void goToPreviousPage() {
        previousPage.click();
        waitSheetLoading();
    }

    public void openSettings() {
        if (excelSettingsExpandButton.isDisplayed())
            excelSettingsExpandButton.click();
    }

    public void closeSettings() {
        excelSettingsCollapseButton.click();
    }

    public void clickFullscreenButton() {
        wait.until(ExpectedConditions.visibilityOf(fullscreenButton));
        fullscreenButton.click();
    }

    public void clickFullscreenButtonWAction() {
        wait.until(ExpectedConditions.visibilityOf(fullscreenButton));
        action.moveToElement(fullscreenButton).click(fullscreenButton).build().perform();
    }

    public void fitTOWidth() {
        fitToWidthButton.click();
    }

    public void fitToHeight() {
        fitToHeightButton.click();
    }

    public void resetZoom() {
        resetZoomButton.click();
    }

    public void setZoom(int Pixels) {
        action.clickAndHold(zoomSliderThumb)
                .moveByOffset((-(int) zoomSliderThumb.getSize().width / 2), 0)
                .moveByOffset(Pixels, 0).release().perform();
    }

    public void setZoomWithKey(int NumberOfPress, Keys arrowKey) {
        for (int i = 0; i < NumberOfPress; i++) {
            zoomSliderThumb.sendKeys(Keys.ARROW_RIGHT);
        }
    }

    public void downloadExcel() {
        downloadButton.click();
    }


}
