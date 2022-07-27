package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class WordPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//figure[contains(@class, 'footer-pdf-icon-hide')]")
    private WebElement hideSettingsButton;

    @FindBy(how = How.XPATH, using = "//figure[contains(@class, 'footer-pdf-icon')][1]")
    private WebElement firstPage;

    @FindBy(how = How.XPATH, using = "//figure[contains(@class, 'footer-pdf-icon')][2]")
    private WebElement previousPage;

    @FindBy(how = How.XPATH, using = "//figure[contains(@class, 'footer-pdf-icon')][3]")
    private WebElement nextPage;

    @FindBy(how = How.XPATH, using = "//figure[contains(@class, 'footer-pdf-icon')][4]")
    private WebElement lastPage;

    @FindBy(how = How.XPATH, using = "//input[contains(@class, 'footer-pdf-nav-bar-input')]")
    private WebElement goToPageInput;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'footer-pdf-nav-bar')]/p")
    private WebElement pageNumbers;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'settings-pdf visible')]")
    private WebElement pdfSettingsExpandButton;

    @FindBy(how = How.XPATH, using = "//figure[contains(@class, 'settings-pdf-icon')][1]")
    private WebElement pdfSettingsCollapseButton;

    @FindBy(how = How.XPATH, using = "//figure[contains(@class, 'settings-pdf-icon')][2]")
    private WebElement fullscreenButton;

    @FindBy(how = How.XPATH, using = "//figure[contains(@class, 'settings-pdf-icon')][3]")
    private WebElement downloadButton;

    @FindBy(how = How.XPATH, using = "//figure[contains(@class, 'settings-pdf-icon')][4]")
    private WebElement fitToWidthButton;

    @FindBy(how = How.XPATH, using = "//figure[contains(@class, 'settings-pdf-icon')][5]")
    private WebElement fitToHeightButton;

    @FindBy(how = How.XPATH, using = "//figure[contains(@class, 'settings-pdf-icon')][6]")
    private WebElement resetZoomButton;

    @FindBy(how = How.XPATH, using = "//span[contains(@class, 'settings-pdf-slider')]/input")
    private WebElement zoomSliderInput;

    @FindBy(how = How.XPATH, using = "//span[contains(@class, 'MuiSlider-thumb')]")
    private WebElement zoomSliderThumb;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'loading-right-bottom')]")
    private List<WebElement> loadingIcon;


    public void waitPageLoading() {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//div[contains(@class, 'loading-right-bottom')]"), 0));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[contains(@class, 'loading-right-bottom')]"), 0));
    }

    public int currentPage() {
        wait.until(ExpectedConditions.visibilityOf(pageNumbers));
        String numbers = pageNumbers.getText();
        int separator = numbers.indexOf('/');
        String currentPage = numbers.substring(0,separator);
        return Integer.parseInt(currentPage);
    }

    public int lastPage() {
        wait.until(ExpectedConditions.visibilityOf(pageNumbers));
        String numbers = pageNumbers.getText();
        int separator = numbers.indexOf('/');
        int space = numbers.indexOf(' ');
        String lastPage = numbers.substring(separator+1, space);
        return Integer.parseInt(lastPage);
    }

    public void goToPageByNumber(int pageNumber) {

        if (pageNumber < 1) {
            pageNumber = 1;
        } else if (pageNumber > lastPage()) {
            pageNumber = lastPage();
        }
        goToPageInput.sendKeys(Integer.toString(pageNumber));
        goToPageInput.sendKeys(Keys.ENTER);
        waitPageLoading();

    }

    public void goToNextPage() {
        nextPage.click();
    }

    public void goToPreviousPage() {
        previousPage.click();
    }

    public void goToFirstPage() {
        firstPage.click();
    }

    public void goToLastPage() {
        lastPage.click();
        waitPageLoading();
    }

    public void openSettings() {
        if (pdfSettingsExpandButton.isDisplayed())
            pdfSettingsExpandButton.click();
    }

    public void closeSettings() {
        pdfSettingsCollapseButton.click();
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

    public void downloadWord() {
        downloadButton.click();
    }


}
