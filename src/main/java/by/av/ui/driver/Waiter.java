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
}
