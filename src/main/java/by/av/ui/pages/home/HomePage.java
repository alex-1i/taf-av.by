package by.av.ui.pages.home;

import by.av.ui.driver.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    protected final WebDriver driver;
    protected final Logger logger = LogManager.getLogger(getClass());

    public HomePage() {
        driver = Driver.getDriver();
        logger.info("Инициализация HomePage");
    }

    public void openHomePage() {
        logger.info("Открытие главной страницы: {}", HomePageLocator.HOME_PAGE_URL);
        driver.get(HomePageLocator.HOME_PAGE_URL);
        try {
            closeCookie();
            logger.info("Cookie закрыты");
        } catch (Exception e) {
            logger.warn("Не удалось закрытьCookie: {}", e.getMessage());
        }
    }

    private void closeCookie() {
        logger.debug("Закрытие Cookie");
        driver.findElement(By.xpath(HomePageLocator.BUTTON_SUBMIT_COOKIE)).click();
    }

    public void openLoginForm() {
        logger.info("Открытие формы логина");
        driver.findElement(By.xpath(HomePageLocator.BUTTON_LOGIN)).click();
    }

    public void openServicePage() {
        logger.info("Открытие страницы услуг");
        driver.findElement(By.xpath(HomePageLocator.BUTTON_SERVICE)).click();
    }
}
