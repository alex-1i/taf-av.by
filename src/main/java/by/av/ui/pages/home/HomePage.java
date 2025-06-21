package by.av.ui.pages.home;

import by.av.ui.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private final WebDriver driver;

    public HomePage() {
        driver = Driver.getDriver();
    }

    public void openHomePage() {
        driver.get(HomePageLocator.HOME_PAGE_URL);
        try {
            closeCookie();
        } catch (Exception ignored) {

        }
    }

    private void closeCookie() {
        driver.findElement(By.xpath(HomePageLocator.BUTTON_SUBMIT_COOKIE)).click();
    }

    public void openLoginForm() {
        driver.findElement(By.xpath(HomePageLocator.BUTTON_LOGIN)).click();
    }
}
