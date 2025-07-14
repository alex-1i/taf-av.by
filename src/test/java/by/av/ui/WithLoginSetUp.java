package by.av.ui;

import by.av.ui.driver.Driver;
import by.av.ui.pages.home.HomePage;
import by.av.ui.pages.home.loginform.LoginForm;
import by.av.ui.pages.home.loginform.LoginFormLocator;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

abstract class WithLoginSetUp extends HomeTest{

    protected LoginForm loginForm;
    protected final Faker faker = new Faker();
    protected void openLoginForm() {
        new HomePage().openLoginForm();
        waitOfElement(LoginFormLocator.TITLE_LOGIN_FORM);
        loginForm = new LoginForm();
    }

    protected void waitOfElement(String locator) {
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.DEFAULT_WAIT_OF_SECONDS)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
    }

    protected void waitOfElement(String locator, String attribute, String attributeValue) {
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.DEFAULT_WAIT_OF_SECONDS)).until(ExpectedConditions.attributeToBe(By.xpath(locator), attribute, attributeValue));
    }

    protected void waitOfElementToBeClickable(String locator) {
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.DEFAULT_WAIT_OF_SECONDS)).until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
    }
}
