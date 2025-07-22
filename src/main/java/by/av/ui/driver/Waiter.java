package by.av.ui.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waiter {

    public static void waitOfElement(String locator) {
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.DEFAULT_WAIT_OF_SECONDS)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
    }

    public static void waitOfElement(String locator, String attribute, String attributeValue) {
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.DEFAULT_WAIT_OF_SECONDS)).until(ExpectedConditions.attributeToBe(By.xpath(locator), attribute, attributeValue));
    }

    public static void waitOfElementToBeClickable(String locator) {
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.DEFAULT_WAIT_OF_SECONDS)).until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
    }

    public static void waitOfNumberOfElements(String locator, int numberOfElements) {
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.DEFAULT_WAIT_OF_SECONDS)).until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(locator), numberOfElements));
    }

    public static void waitUntilInputIsNotEmpty(String locator, String attribute) {
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.DEFAULT_WAIT_OF_SECONDS)).until(ExpectedConditions.attributeToBeNotEmpty(Driver.getDriver().findElement(By.xpath(locator)), attribute));
    }

    public static void waitUntilVisibleAndClickable(String locator) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.DEFAULT_WAIT_OF_SECONDS));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
    }
}
