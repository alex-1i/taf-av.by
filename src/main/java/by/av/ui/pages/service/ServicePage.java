package by.av.ui.pages.service;

import by.av.ui.pages.home.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static by.av.ui.driver.Waiter.*;

public class ServicePage extends HomePage {

    public void inputSearchInfo(String searchInfo) {
        logger.info("Ввод в строку поиска: {}", searchInfo);
        driver.findElement(By.xpath(ServicePageLocator.INPUT_SEARCH)).sendKeys(searchInfo);
    }

    public void inputSearchInfoAndSubmit(String searchInfo) {
        logger.info("Ввод в строку поиска и нажатие Enter: {}", searchInfo);
        driver.findElement(By.xpath(ServicePageLocator.INPUT_SEARCH)).sendKeys(searchInfo);

        waitUntilInputIsNotEmpty(ServicePageLocator.INPUT_SEARCH, ServicePageLocator.ATTRIBUTE);

        String buttonLocator = ServicePageLocator.getActiveService(searchInfo);
        waitUntilVisibleAndClickable(buttonLocator);

        WebElement button = driver.findElement(By.xpath(buttonLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);

        logger.info("Нажатие по кнопке компании: {}", searchInfo);
        try {
            button.click();
        } catch (ElementClickInterceptedException e) {
            logger.warn("Клик не удался, пробуем через JavaScript");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        }
    }

    public String getSearchResultText() {
        return driver.findElement(By.xpath(ServicePageLocator.SEARCH_RESULT)).getText();
    }

    public List<String> getAllCompaniesText() {
        waitOfNumberOfElements(ServicePageLocator.LIST_OF_COMPANIES, ServicePageLocator.DEFAULT_NUMBER_OF_COMPANIES);
        List<WebElement> companies = driver.findElements(By.xpath(ServicePageLocator.LIST_OF_COMPANIES));

        List<String> companyText = new ArrayList<>();

        for (WebElement company : companies) {
            String text = company.getText();
            logger.debug("Текст компании: {}", text);
            companyText.add(text);
        }

        return companyText;
    }

    public List<String> getAllServiceText() {
        List<WebElement> services = driver.findElements(By.xpath(ServicePageLocator.LIST_OF_SERVICES));
        List<String> serviceText = new ArrayList<>();

        for (WebElement service : services) {
            String text = service.getAttribute("textContent").trim();
            logger.debug("Текст услуги: {}", text);
            serviceText.add(text);
        }

        logger.info("Всего услуг найдено: {}", serviceText.size());
        return serviceText;
    }
}
