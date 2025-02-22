package pl.tomaszbuga.framework;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static pl.tomaszbuga.utils.ByLocatorFinder.getByFromWebElement;

public abstract class PageObject {
    private static final Duration TIMEOUT = Duration.ofSeconds(5);
    protected static final Logger LOGGER = LogManager.getLogger(PageObject.class);
    protected WebDriver driver;

    protected void waitUntilElementIsVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        By elementByLocator = getByFromWebElement(element);
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementByLocator));
    }

    protected void waitUntilElementIsClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        By elementByLocator = getByFromWebElement(element);
        wait.until(ExpectedConditions.elementToBeClickable(elementByLocator));
    }

    protected void enterTextToInput(String text, WebElement element) {
        waitUntilElementIsClickable(element);
        element.sendKeys(text);
    }
    protected String getTextFromInput(WebElement element) {
        waitUntilElementIsVisible(element);
        // .getAttribute("value"), bo wartośc wpisana w input
        // nie jest dostępna do pobrania przez .getText()
        return element.getAttribute("value");
    }

    protected String getTextFromDataPickerInput(WebElement element){
        // use instead of .getText or .getAttribute("value") because
        // datepicker input doesn't have this values
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        return (String) jse.executeScript("return arguments[0].value", element);
    }

    protected String checkAttributeType(WebElement element){
        waitUntilElementIsVisible(element);
        return element.getAttribute("type");
    }
}
