package by.av.ui.pages.home.loginform;

import by.av.ui.pages.home.HomePage;
import by.av.ui.pages.home.HomePageLocator;
import org.openqa.selenium.By;

public class LoginForm extends HomePage {

    public String getLoginFormOpenedAttributeAriaHiddenValue() {
        return driver.findElement(By.xpath(LoginFormLocator.LOGIN_FORM_OPENED)).getDomAttribute(HomePageLocator.ATTRIBUTE_ARIA_HIDDEN);
    }

    public String getLoginFormClosedAttributeAriaHiddenValue() {
        return driver.findElement(By.xpath(LoginFormLocator.LOGIN_FORM_CLOSED)).getDomAttribute(HomePageLocator.ATTRIBUTE_ARIA_HIDDEN);
    }

    public void clickButtonCross() {
        logger.info("Клик по кнопке \"Закрыть\"");
        driver.findElement(By.xpath(LoginFormLocator.BUTTON_CROSS)).click();
    }

    public String getTitleText() {
        return driver.findElement(By.xpath(LoginFormLocator.TITLE_LOGIN_FORM)).getText();
    }

    public String getButtonLoginByPhoneNumberText() {
        return driver.findElement(By.xpath(LoginFormLocator.BUTTON_LOGIN_BY_PHONE_NUMBER)).getText();
    }

    public void clickButtonLoginByPhoneNumber() {
        logger.info("Клик по кнопке \"по номеру телефона\"");
        driver.findElement(By.xpath(LoginFormLocator.BUTTON_LOGIN_BY_PHONE_NUMBER)).click();
    }

    public String getButtonLoginByPhoneNumberAttributeAriaSelectedValue() {
        return driver.findElement(By.xpath(LoginFormLocator.BUTTON_LOGIN_BY_PHONE_NUMBER)).getDomAttribute(HomePageLocator.ATTRIBUTE_ARIA_SELECTED);
    }

    public String getLabelPhoneNumberText() {
        return driver.findElement(By.xpath(LoginFormLocator.LABEL_PHONE_NUMBER)).getText();
    }

    public void inputPhoneNumber(String phoneNumber) {
        logger.info("Ввод номера телефона: {}", phoneNumber);
        driver.findElement(By.xpath(LoginFormLocator.INPUT_PHONE_NUMBER)).sendKeys(phoneNumber);
    }

    public String getLabelPasswordForPhoneNumberText() {
        return driver.findElement(By.xpath(LoginFormLocator.LABEL_PASSWORD_FOR_PHONE_NUMBER)).getText();
    }

    public void clickButtonInvert() {
        logger.info("Клик по кнопке \"скрытия пароля\"");
        driver.findElement(By.xpath(LoginFormLocator.BUTTON_INVERTER)).click();
    }

    public String getButtonInvertAttributeAriaPressedValue() {
        return driver.findElement(By.xpath(LoginFormLocator.BUTTON_INVERTER)).getDomAttribute(HomePageLocator.ATTRIBUTE_ARIA_PRESSED);
    }

    public void inputPasswordForPhoneNumber(String password) {
        logger.info("Ввод пароля для номера телефона: {}", password);
        driver.findElement(By.xpath(LoginFormLocator.INPUT_PASSWORD_FOR_PHONE_NUMBER)).sendKeys(password);
    }

    public void inputPhoneNumberAndPasswordForPhoneNumber(String phoneNumber,String password) {
        inputPhoneNumber(phoneNumber);
        inputPasswordForPhoneNumber(password);
    }

    public String getInputPasswordForPhoneNumberAttributeTypeValue() {
        return driver.findElement(By.xpath(LoginFormLocator.INPUT_PASSWORD_FOR_PHONE_NUMBER)).getDomAttribute(HomePageLocator.ATTRIBUTE_TYPE);
    }

    public String getButtonLoginByEmailOrLoginText() {
        return driver.findElement(By.xpath(LoginFormLocator.BUTTON_LOGIN_BY_EMAIL_OR_LOGIN)).getText();
    }

    public void clickButtonLoginByEmailOrLogin() {
        logger.info("Клик по кнопке \"по почте или логину\"");
        driver.findElement(By.xpath(LoginFormLocator.BUTTON_LOGIN_BY_EMAIL_OR_LOGIN)).click();
    }

    public String getButtonLoginByEmailOrLoginAttributeAriaSelectedValue() {
        return driver.findElement(By.xpath(LoginFormLocator.BUTTON_LOGIN_BY_EMAIL_OR_LOGIN)).getDomAttribute(HomePageLocator.ATTRIBUTE_ARIA_SELECTED);
    }

    public String getLabelEmailOrLoginText() {
        return driver.findElement(By.xpath(LoginFormLocator.LABEL_EMAIL_OR_LOGIN)).getText();
    }

    public void inputEmailOrLogin(String emailOrLogin) {
        logger.info("Ввод почты или логина: {}", emailOrLogin);
        driver.findElement(By.xpath(LoginFormLocator.INPUT_EMAIL_OR_LOGIN)).sendKeys(emailOrLogin);
    }

    public String getLabelPasswordForPEmailOrLoginText() {
        return driver.findElement(By.xpath(LoginFormLocator.LABEL_PASSWORD_FOR_EMAIL_OR_LOGIN)).getText();
    }

    public void inputPasswordForEmailOrLogin(String password) {
        logger.info("Ввод пароля для почты или логина: {}", password);
        driver.findElement(By.xpath(LoginFormLocator.INPUT_PASSWORD_FOR_EMAIL_OR_LOGIN)).sendKeys(password);
    }

    public void inputEmailOrLoginAndPasswordForEmailOrLogin(String emailOrLogin,String password) {
        inputEmailOrLogin(emailOrLogin);
        inputPasswordForEmailOrLogin(password);
    }

    public String getInputPasswordForEmailOrLoginAttributeATypeValue() {
        return driver.findElement(By.xpath(LoginFormLocator.INPUT_PASSWORD_FOR_EMAIL_OR_LOGIN)).getDomAttribute(HomePageLocator.ATTRIBUTE_TYPE);
    }

    public String getButtonRecoveryPasswordText() {
        return driver.findElement(By.xpath(LoginFormLocator.BUTTON_RECOVERY_PASSWORD)).getText();
    }

    public void clickButtonRecoveryPassword() {
        logger.info("Клик по кнопке \"Восстановление пароля\"");
        driver.findElement(By.xpath(LoginFormLocator.BUTTON_RECOVERY_PASSWORD)).click();
    }

    public String getButtonLoginText() {
        return driver.findElement(By.xpath(LoginFormLocator.BUTTON_LOGIN)).getText();
    }

    public void clickButtonLogin() {
        logger.info("Клик по кнопке \"Вход\"");
        driver.findElement(By.xpath(LoginFormLocator.BUTTON_LOGIN)).click();
    }

    public boolean isButtonLoginEnabled() {
        return driver.findElement(By.xpath(LoginFormLocator.BUTTON_LOGIN)).getDomAttribute(HomePageLocator.ATTRIBUTE_DISABLED) == null;
    }

    public String getButtonRegistrationText() {
        return driver.findElement(By.xpath(LoginFormLocator.BUTTON_REGISTRATION)).getText();
    }

    public void clickButtonRegistration() {
        logger.info("Клик по кнопке \"Регистрация\"");
        driver.findElement(By.xpath(LoginFormLocator.BUTTON_REGISTRATION)).click();
    }

    public String getErrorMessageText() {
        return driver.findElement(By.xpath(LoginFormLocator.ERROR_MESSAGE)).getText();
    }
}
